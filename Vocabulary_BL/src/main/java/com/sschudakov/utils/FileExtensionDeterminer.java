package com.sschudakov.utils;

/**
 * Created by Semen Chudakov on 15.12.2017.
 */
public class FileExtensionDeterminer {
    private static final String HTML_EXTENSION = ".html";
    private static final String TXT_EXTENSION = ".txt";
    private static final String SERIALIZED_FILE_EXTENSION = ".ser";
    private static final String XSL_EXTENSION = ".xsl";
    private static final String XML_EXTENSION = ".xml";
    private static final String JAVA_EXTENSION = ".java";
    private static final String GROOVY_EXTENSION = ".groovy";
    private static final String DOCX_EXTENSION = ".docx";

    public static boolean isHTNLFile(String path) {
        return path.endsWith(HTML_EXTENSION);
    }

    public static boolean isTXTFile(String path) {
        return path.endsWith(TXT_EXTENSION);
    }

    public static boolean isTableFile(String path) {
        return path.endsWith(SERIALIZED_FILE_EXTENSION);
    }

    public static boolean isXMLFile(String path) {
        return path.endsWith(XML_EXTENSION);
    }

    public static boolean isXSLFile(String path) {
        return path.endsWith(XSL_EXTENSION);
    }

    public static boolean isJavaFile(String path) {
        return path.endsWith(JAVA_EXTENSION);
    }

    public static boolean isGroovyFile(String path) {
        return path.endsWith(GROOVY_EXTENSION);
    }

    public static boolean isDOCXFile(String path) {
        return path.endsWith(DOCX_EXTENSION);
    }

}
