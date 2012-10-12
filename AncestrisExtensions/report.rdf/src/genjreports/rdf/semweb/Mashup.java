// @formatter:off
/*
 * Copyright 2012, J. Pol
 *
 * This file is part of free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation.
 *
 * This package is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details. A copy of the GNU General Public License is
 * available at <http://www.gnu.org/licenses/>.
 */
// @formatter:on
package genjreports.rdf.semweb;

import static com.hp.hpl.jena.rdf.model.ResourceFactory.*;
import static genjreports.rdf.semweb.Predicate.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;

/**
 * Manager of data from the semantic web related to place name literals.
 * 
 * @author Jo Pol
 */
public class Mashup
{
    private static final int DOWNLOAD_INTERVAL = 5000;

    private static final Logger logger = Logger.getLogger(Mashup.class.getName());

    private final URI idPrefix;
    private final File file;
    private final String language;
    private Model model;
    private Date lastGeoNamesRequest;
    private Date lastDbpediaRequest;
    private boolean isNewFile;

    /**
     * Creates an object to download and link data from the semantic web for place name literals.
     * 
     * @param idPrefix
     *        used to construct new URIs.
     * @param file
     *        a file with one of the extension rdf/n3/ttl. External changes to the file between and
     *        during method invocations of this instance will be overwritten. Downloads are not refreshed
     *        for an existing file.
     */
    public Mashup(final URI idPrefix, final File file)
    {
        this.file = file;
        this.idPrefix = idPrefix;
        final String extension = file.getName().trim().replaceAll(".*\\.", "").toLowerCase();
        language = Extension.valueOf(extension).language();
    }

    /**
     * Links KEY-VALUE pairs and downloads related resources from the web. Only new links and downloads
     * are added to the {@link File} passed to the constructor. As far as not yet stored, the following
     * statements are created:
     * 
     * <pre>
     * &lt;ID&gt; rdf:label KEY.
     * &lt;ID&gt; rdfs:isDefinedBy &lt;http://sws.geonames.org/VALUE&gt;.
     * </pre>
     * 
     * The &lt;ID&gt; is generated by extending the {@link URI} passed to the constructor. Also as far as
     * not yet stored, the GeoNames resource is fetched. DBPedia resources are fetched as far as related
     * to the GeoNames resource in one or two steps with seeAlso and sameAs predicates.
     * 
     * @param placeNameIdMap
     *        KEY: place name literal. For gedcom files typically<br>
     *        the unique values of INDI:*:PLAC and/or FAM:*:PLAC<br>
     *        for example ", Washington, , , , DC, USA"<br>
     *        VALUE: GeoNameId's (the numeric component of the URIs)<br>
     *        uri example http://sws.geonames.org/4140963/about.rdf
     * @throws FileNotFoundException
     *         in case the location does not exist for the {@link File} passed to the constructor, or the
     *         file is removed between checking its existence and reading it.
     */
    public void add(final Map<String, String> placeNameIdMap) throws FileNotFoundException
    {
        logger.log(Level.FINE,Arrays.deepToString(placeNameIdMap.keySet().toArray()));
        if (model == null)
        {
            model = ModelFactory.createDefaultModel();
            if (isNewFile = !file.exists())
                model.setNsPrefixes(Prefix.NAME_ID_MAP);
            else
                model.read(new FileInputStream(file), (String) null, language);
        }
        for (final String place : placeNameIdMap.keySet())
        {
            final Resource subject = createResource(idPrefix + placeNameIdMap.get(place));
            addPlace(place, placeNameIdMap.get(place), subject);
        }
        logger.log(Level.INFO,"loaded " + model.size() + " statements");
        model.write(new FileOutputStream(file), language);
    }

    private void addPlace(final String place, final String geoNameId, final Resource subject)
    {
        final String uri = "http://sws.geonames.org/" + geoNameId + "/";
        if (isNewFile || !model.contains(subject, hasLabel.property(), place))
        {
            // being the key of a map object the place is unique
            // the check for a new file prevents a needless expensive check
            model.add(subject, hasLabel.property(), place);
        }
        if (!model.contains(subject, isDefinedBy.property(), createResource(uri)))
        {
            model.add(subject, isDefinedBy.property(), createResource(uri));
        }
        download(uri);
    }

    private void download(final String geoNamesUri)
    {
        if (!model.contains(createResource(geoNamesUri), isA.property()))
        {
            // if the type is present, it has been added successfully before
            logger.log(Level.INFO, geoNamesUri);
            lastGeoNamesRequest = Nice.sleep(DOWNLOAD_INTERVAL, lastGeoNamesRequest);
            model.read(geoNamesUri + "about.rdf");
        }
        for (final String uri : runQuery(geoNamesUri, seeAlso, "dbpedia.org"))
        {
            downloadDbPedia(uri);
            for (final String uri2 : runQuery(uri, seeAlso, "dbpedia.org"))
                downloadDbPedia(uri2);
            for (final String uri2 : runQuery(uri, sameAs, "dbpedia.org"))
                downloadDbPedia(uri2);
        }
    }

    private void downloadDbPedia(final String uri)
    {
        if (model.contains(createResource(uri), isA.property()))
        {
            // if the type is present, it has been added successfully before
            return;
        }
        try
        {
            final String url = URLDecoder.decode(uri, "UTF-8").replace("/resource/", "/data/") + ".rdf";
            logger.log(Level.INFO, url);
            lastDbpediaRequest = Nice.sleep(DOWNLOAD_INTERVAL, lastDbpediaRequest);
            model.read(url);
        }
        catch (final UnsupportedEncodingException e)
        {
            // Is there any OS that does not support UTF-8?
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private List<String> runQuery(final String uri, final Predicate predicate, final String filterRegEx)
    {
        final ArrayList<String> result = new ArrayList<String>();
        final String format = "select distinct ?n {<%s> <%s> ?n. FILTER regex(str(?n),'%s')}";
        final String q = String.format(format, uri, predicate.property(), filterRegEx);
        logger.log(Level.FINE, "query: " + q);
        final QueryExecution queryExecution = QueryExecutionFactory.create(q, Syntax.syntaxARQ, model, new QuerySolutionMap());
        try
        {
            final ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext())
            {
                result.add(resultSet.next().get("n").asResource().getURI());
            }
        }
        finally
        {
            queryExecution.close();
        }
        return result;
    }

    public static void main(final String[] args) throws URISyntaxException, IOException
    {
        final String message = "expected arguments: input file (per line GeoNameID, tab, place name), URI, output file";
        if (args.length < 3)
        {
            logger.log(Level.SEVERE, message);
            return;
        }
        final File inputFile = new File(args[0]);
        final URI uri = new URI(args[1]);
        final File outputFile = new File(args[2]);
        final Map<String, String> places = new HashMap<String, String>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        String line;
        logger.log(Level.INFO, "started");
        try
        {
            while ((line = reader.readLine()) != null)
            {
                final String[] fields = line.split("\t");
                if (fields.length > 1)
                {
                    final String geoNameId = fields[0].replaceAll("[^0-9]*", "");
                    final String placeLiteral = line.substring(fields[0].length() + 1).trim();
                    if (geoNameId.length() > 0 && placeLiteral.length() > 0)
                        places.put(line.substring(fields[0].length() + 1), geoNameId);
                }
            }
            if (places.isEmpty())
                logger.log(Level.WARNING, message);
            else
                new Mashup(uri, outputFile).add(places);
        }
        finally
        {
            reader.close();
        }
        logger.log(Level.INFO, "finished");
    }
}
