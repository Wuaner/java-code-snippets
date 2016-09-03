package com.wuaner.classload;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import sun.net.spi.nameservice.dns.DNSNameService;


public class ClassLoaderTest {

    public static final String PROP_JAVA_CLASS_PATH = "java.class.path";
    public static final String PROP_BOOT_CLASS_PATH = "sun.boot.class.path";
    public static final String PROP_EXT_DIR = "java.ext.dirs";

    public static final String SPLITTER = "=============================================================================================";

    public static void main(String[] args) {

        printPath(PROP_JAVA_CLASS_PATH); // Could be wrong if run this in IntelliJ
        printPath(PROP_BOOT_CLASS_PATH);
        printPath(PROP_EXT_DIR);

        System.out.println("\n\n\n");

        printClassLoaderFor(String.class);
        printClassLoaderFor(DNSNameService.class);
        printClassLoaderFor(ClassLoaderTest.class);

        System.out.println("\n\n\n");

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        System.out.println("URLs of ClassLoader.getSystemClassLoader(count:" + urls.length + "):");
        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }

    private static void printPath(String propPath) {
        String property = System.getProperty(propPath);
        System.out.println(" Path of " + propPath + " (count:" + property.split(File.pathSeparator).length + "):");
        for (String path : property.split(File.pathSeparator)) {
            System.out.println(path);
        }

        System.out.println(SPLITTER);
    }

    private static void printClassLoaderFor(Class<?> clazz) {
        System.out.println("ClassLoader for " + clazz.getName() + ":\n" + clazz.getClassLoader());
        System.out.println(SPLITTER);
    }

}
