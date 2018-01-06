package com.sschudakov.logging;

import java.io.File;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class LogsRemover {
    private static final String LOGS_DIRECTORY_PATH = "D:\\Workspace.java\\Vocabulary\\logs";

    public static void removeLogs() {
        removeLogs(LOGS_DIRECTORY_PATH);
    }

    private static void removeLogs(String path) {
        File logsDirectory = new File(path);
        removeFile(logsDirectory);
    }

    private static void removeFile(File file) {
        if (file.isFile()) {
            file.delete();
        }
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                removeFile(file1);
            }
        }
    }
}
