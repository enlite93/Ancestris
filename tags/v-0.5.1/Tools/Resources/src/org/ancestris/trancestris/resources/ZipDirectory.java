package org.ancestris.trancestris.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {

    private static final Logger logger = Logger.getLogger(ZipDirectory.class.getName());
    private String directoryName;
    private ResourceFile resourceFile = null;
    private LinkedHashMap<String, ZipDirectory> dirs;

    public ZipDirectory(String name) {
        logger.log(Level.INFO, "New directory {0}", name);

        dirs = new LinkedHashMap<String, ZipDirectory>();
        this.directoryName = name;
    }

    public void writeTo(ZipOutputStream zipoutputstream, String path) throws IOException {
        if (resourceFile != null) {
            resourceFile.writeTo(zipoutputstream);
        }

        for (ZipDirectory zipDirectory : dirs.values()) {
            if (path.isEmpty() == true) {
                zipDirectory.writeTo(zipoutputstream, directoryName);
            } else {
                zipDirectory.writeTo(zipoutputstream, path + "/" + directoryName);
            }
        }
    }

    public void saveTranslation (ZipOutputStream zipoutputstream, String path) throws IOException {
        if (resourceFile != null) {
            resourceFile.saveTranslation(zipoutputstream);
        }

        for (ZipDirectory zipDirectory : dirs.values()) {
            if (path.isEmpty() == true) {
                zipDirectory.saveTranslation(zipoutputstream, directoryName);
            } else {
                zipDirectory.saveTranslation(zipoutputstream, path + "/" + directoryName);
            }
        }
    }

    public void put(ZipEntry zipEntry, InputStream inputstream) throws IOException {
        String filePath = zipEntry.getName();
        StringTokenizer tokenizefilePath = new StringTokenizer(filePath, "/");
        put(tokenizefilePath, zipEntry, inputstream, filePath);
    }

    private void put(StringTokenizer tokenizefilePath, ZipEntry zipEntry, InputStream inputstream, String filePath) throws IOException {
        String token = tokenizefilePath.nextToken();
        if (!tokenizefilePath.hasMoreTokens()) {
            logger.log(Level.INFO, "Add File {0}", filePath);
            if (this.resourceFile == null) {
                this.resourceFile = new ResourceFile(filePath.substring(0, filePath.lastIndexOf("/")));
            }
            this.resourceFile.put(zipEntry, inputstream, token);
        } else {
            if (dirs.containsKey(token) == true) {
                dirs.get(token).put(tokenizefilePath, zipEntry, inputstream, filePath);
            } else {
                logger.log(Level.INFO, "Add dir {0}", token);

                ZipDirectory zipDirectory = new ZipDirectory(token);
                dirs.put(token, zipDirectory);
                zipDirectory.put(tokenizefilePath, zipEntry, inputstream, filePath);
            }
        }
    }

    public String getName() {
        return directoryName;
    }

    public ArrayList<ZipDirectory> getDirs() {
        return new ArrayList<ZipDirectory>(dirs.values());
    }

    public ResourceFile getResourceFile() {
        return resourceFile;
    }

    @Override
    public String toString() {
        return directoryName;
    }

    public void setTranslation(Locale fromLocale, Locale toLocale) {
        if (resourceFile != null) {
            resourceFile.setTranslation(fromLocale, toLocale);
        }
        for (ZipDirectory zipDirectory : dirs.values()) {
            zipDirectory.setTranslation(fromLocale, toLocale);
        }
    }

    public boolean isTranslated() {
        if (resourceFile != null) {
            return resourceFile.isTranslated();
        } else {
            for (ZipDirectory zipDirectory : dirs.values()) {
                if (zipDirectory.isTranslated() == false) {
                    return false;
                }
            }
            return true;
        }
    }
}