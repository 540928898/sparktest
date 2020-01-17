package com.sunxj.javatest.MultyThread.Chapter07ThreadHook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class PreventDuplicate {
    private final static String LOCK_PATH = "/Users/4paradigm/Desktop/";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received KILL signal");
            getLockFile().toFile().delete();
        }));
        checkRunning();
        for (; ; ) {
            Thread.sleep(1000);
            System.out.println("Program is running");
        }

    }
    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program is already running");
        }
        //设置权限
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));

    }
    private static Path getLockFile(){
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
