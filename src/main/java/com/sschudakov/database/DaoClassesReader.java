package com.sschudakov.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.LinkedList;

public class DaoClassesReader {
    private static final String CLASS_EXTENSION = ".class";
    private static final String PACKAGE_NAME = "com.sschudakov.entity";

    public static Collection<Class<?>> readClasses() {
        return readClasses(formProgramDirectoryPath());
    }

    private static Collection<Class<?>> readClasses(String directory) {
        Collection<Class<?>> result = new LinkedList<>();
        Path directoryWithFilesPath = Paths.get(directory);

        try {
            Files.walkFileTree(directoryWithFilesPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (isClassFile(file.toFile())) {
                        try {
                            result.add(Class.forName(formFullyQualifiedClassName(file)));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String formProgramDirectoryPath() {
        return Thread.currentThread().getContextClassLoader()
                .getResource(PACKAGE_NAME.replace('.', '/'))
                .getFile().substring(1);//in order to replace first '/' symbol

    }

    private static boolean isClassFile(File file) {
        String path = file.getPath();
        return path.endsWith(CLASS_EXTENSION);
    }

    private static String formFullyQualifiedClassName(Path path) {
        StringBuffer result = new StringBuffer("");
        Path daosPackagePath = Paths.get(PACKAGE_NAME.replace('.', '/'));

        int i = 0;
        while (!daosPackagePath.getName(0).equals(path.getName(i))) {
            i++;
        }

        while (i != path.getNameCount()) {
            result.append(path.getName(i) + ".");
            i++;
        }

        return result.deleteCharAt(result.length() - 1).substring(0, result.length() - CLASS_EXTENSION.length());
    }

    private static String formClassName(File file) {
        return file.getName().substring(0, file.getName().length() - CLASS_EXTENSION.length());
    }
}
