package org.ancestris.trancestris.resources;

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResourceFile.java
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ResourceFile {

    private static final Logger logger = Logger.getLogger(ResourceFile.class.getName());
    private static final String PREFIX = "Bundle";
    private static final String SUFFIX = ".properties";
    private String fromBundleName = "";
    private String toBundleName = "";
    private String directoryPath = "";
    private ResourceStructure defaultLanguage = null;
    private ResourceStructure fromLanguage = null;
    private ResourceStructure toLanguage = null;
    private ArrayList<String> content = null;
    private int not_translated;
    private List<PropertyChangeListener> listeners = Collections.synchronizedList(new LinkedList());
    private TreeMap<String, ResourceStructure> resourceFiles = new TreeMap();
    private boolean modified = false;
    private boolean translationCreated = false;

    ResourceFile(String directoryPath) {
        this.directoryPath = directoryPath;
        not_translated = 0;
    }

    public void put(ZipEntry zipEntry, InputStream inputStream, String bundleName) throws IOException {
        ResourceParser resourceParser = new ResourceParser(inputStream);
        resourceParser.initParser();
        ResourceStructure resourceStructure = resourceParser.parseFile();
        resourceStructure.setZipEntry(zipEntry);
        resourceFiles.put(bundleName, resourceStructure);
    }

    public Set<String> getFiles() {
        return resourceFiles.keySet();
    }

    public void writeTo(ZipOutputStream zipOutputStream) throws IOException {
        for (String bundleName : getFiles()) {
            if (bundleName.equals(toBundleName)) {
                // the file already exits
                if (translationCreated == false) {
                    // and has not been modified
                    if (modified == false) {
                        logger.log(Level.INFO, "Save file {0} ...", this.directoryPath + "/" + bundleName);

                        ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                        zipEntry.setTime(toLanguage.getZipEntry().getTime());

                        zipOutputStream.putNextEntry(zipEntry);
                        for (String key : content) {
                            String lineString = toLanguage.getResourceLineString(key);
                            if (lineString != null) {
                                zipOutputStream.write(lineString.getBytes());
                            }
                        }
                        logger.log(Level.INFO, "Done");
                    } // and has been modified
                    else {
                        logger.log(Level.INFO, "Save file {0} ...", this.directoryPath + "/" + bundleName);
                        ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                        Calendar c = Calendar.getInstance();
                        zipEntry.setTime(c.getTimeInMillis());
                        zipOutputStream.putNextEntry(zipEntry);
                        for (String key : content) {
                            String lineString = toLanguage.getResourceLineString(key);
                            if (lineString != null) {
                                zipOutputStream.write(lineString.getBytes());
                            }
                        }
                        logger.log(Level.INFO, "Done");
                    }
                    // the file has been created
                } else if (modified == true) {
                    logger.log(Level.INFO, "Create file {0} ...", this.directoryPath + "/" + bundleName);
                    ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                    Calendar c = Calendar.getInstance();
                    zipEntry.setTime(c.getTimeInMillis());
                    zipOutputStream.putNextEntry(zipEntry);
                    for (String key : content) {
                        String lineString = toLanguage.getResourceLineString(key);
                        if (lineString != null) {
                            zipOutputStream.write(lineString.getBytes());
                        }
                    }
                    logger.log(Level.INFO, "Done");
                }
            } else {
                logger.log(Level.INFO, "Saving file {0} ...", this.directoryPath + "/" + bundleName);

                ResourceStructure current = resourceFiles.get(bundleName);
                ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                zipEntry.setTime(current.getZipEntry().getTime());

                zipOutputStream.putNextEntry(zipEntry);
                for (String key : content) {
                    String lineString = current.getResourceLineString(key);
                    if (lineString != null) {
                        zipOutputStream.write(lineString.getBytes());
                    }
                }
                logger.log(Level.INFO, "Done");
            }
        }
    }

    public void saveTranslation(ZipOutputStream zipOutputStream) throws IOException {
        for (String bundleName : getFiles()) {
            if (bundleName.equals(toBundleName)) {
                if (translationCreated == false) {
                    if (modified == false) {
                        logger.log(Level.INFO, "Save file {0} ...", this.directoryPath + "/" + bundleName);

                        ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                        zipEntry.setTime(toLanguage.getZipEntry().getTime());

                        zipOutputStream.putNextEntry(zipEntry);
                        for (String key : content) {
                            String lineString = toLanguage.getResourceLineString(key);
                            if (lineString != null) {
                                zipOutputStream.write(lineString.getBytes());
                            }
                        }
                        logger.log(Level.INFO, "Done");
                    } else {
                        logger.log(Level.INFO, "Save file {0} ...", this.directoryPath + "/" + bundleName);
                        ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                        Calendar c = Calendar.getInstance();
                        zipEntry.setTime(c.getTimeInMillis());
                        zipOutputStream.putNextEntry(zipEntry);
                        for (String key : content) {
                            String lineString = toLanguage.getResourceLineString(key);
                            if (lineString != null) {
                                zipOutputStream.write(lineString.getBytes());
                            }
                        }
                        logger.log(Level.INFO, "Done");

                    }
                } else if (modified == true) {
                    logger.log(Level.INFO, "Create file {0} ...", this.directoryPath + "/" + bundleName);
                    ZipEntry zipEntry = new ZipEntry(this.directoryPath + "/" + bundleName);
                    Calendar c = Calendar.getInstance();
                    zipEntry.setTime(c.getTimeInMillis());
                    zipOutputStream.putNextEntry(zipEntry);
                    for (String key : content) {
                        String lineString = toLanguage.getResourceLineString(key);
                        if (lineString != null) {
                            zipOutputStream.write(lineString.getBytes());
                        }
                    }
                    logger.log(Level.INFO, "Done");
                }
            }
        }
    }

    void setTranslation(Locale fromLocale, Locale toLocale) {

        if (fromLocale.getLanguage().equals("en")) {
            fromBundleName = PREFIX + SUFFIX;
        } else {
            fromBundleName = PREFIX + "_" + fromLocale.getLanguage() + SUFFIX;
        }

        // load Default Language
        defaultLanguage = resourceFiles.get(PREFIX + SUFFIX);

        if (fromBundleName.equals(PREFIX + SUFFIX) == true) {
            fromLanguage = defaultLanguage;
        } else {
            fromLanguage = resourceFiles.get(fromBundleName);
        }

        if (defaultLanguage != null) {

            content = new ArrayList(defaultLanguage.keySet());

            // Load translayed language
            if (toLocale.getLanguage().equals("en")) {
                toBundleName = PREFIX + SUFFIX;
            } else {
                toBundleName = PREFIX + "_" + toLocale.getLanguage() + SUFFIX;
            }

            toLanguage = resourceFiles.get(toBundleName);
            if (toLanguage == null) {
                logger.log(Level.INFO, "Create Language file {0}", this.directoryPath + "/" + toBundleName);
                toLanguage = new ResourceStructure();
                resourceFiles.put(toBundleName, toLanguage);
                translationCreated = true;
            }

            Iterator<ResourceItem.ResourceLine> it = defaultLanguage.iterator();
            not_translated = getLineCount();
            while (it.hasNext()) {
                ResourceItem.ResourceLine line = it.next();
                if (toLanguage.getLine(line.getKey()) != null) {
                    not_translated = Math.max(0, not_translated - 1);
                }
            }
        } else {
            logger.log(Level.SEVERE, "No default language for directory {0}", this.directoryPath);
        }
    }

    public boolean isTranslated() {
        return not_translated == 0;
    }

    public int getLineCount() {
        return defaultLanguage.size();
    }

    public String getLine(int i) {
        ResourceItem.ResourceLine line = null;

        if (fromLanguage != null) {
            line = fromLanguage.getLine(content.get(i));
        }

        if (line == null) {
            line = defaultLanguage.getLine(content.get(i));
        }

        String comment = line.getComment();
        String value = line.getValue();

        return (comment == null ? "" : comment) + (value == null ? "" : value);
    }

    public String getLineTranslation(int i) {
        ResourceItem.ResourceLine line = toLanguage.getLine(content.get(i));

        if (line == null) {

            if (fromLanguage != null) {
                line = fromLanguage.getLine(content.get(i));
            }

            if (line == null) {
                line = defaultLanguage.getLine(content.get(i));
            }
        }
        return line.getValue();
    }

    public void setLineTranslation(int i, String s) {
        ResourceItem.ResourceLine old = toLanguage.getLine(content.get(i));
        ResourceItem.PropertyComment comment = defaultLanguage.getLine(content.get(i)).getPropertyComment();
        ResourceItem.PropertyKey key = defaultLanguage.getLine(content.get(i)).getPropertyKey();
        ResourceItem.PropertyValue value = new ResourceItem.PropertyValue(s);
        if (old == null) {
            not_translated--;
            toLanguage.put(key, value, comment);
        } else {
            toLanguage.put(key, value, comment);
        }
        modified = true;
        fire(content.get(i), old, s);
    }

    public int getLineState(int i) {
        if (toLanguage.getLine(content.get(i)) != null) {
            String from = null;

            if (fromLanguage != null) {
                from = fromLanguage.getLine(content.get(i)).getValue();
            } else {
                from = defaultLanguage.getLine(content.get(i)).getValue();
            }

            String to = toLanguage.getLine(content.get(i)) != null ? toLanguage.getLine(content.get(i)).getValue() : "";
            if (from.equalsIgnoreCase(to)) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        listeners.remove(pcl);
    }

    private void fire(String propertyName, Object old, Object nue) {
        //Passing 0 below on purpose, so you only synchronize for one atomic call
        @SuppressWarnings(value = "unchecked")
        PropertyChangeListener[] pcls = (PropertyChangeListener[]) listeners.toArray(new PropertyChangeListener[0]);
        for (int i = 0; i < pcls.length; i++) {
            pcls[i].propertyChange(new PropertyChangeEvent(this, propertyName, old, nue));
        }
    }
}