package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

import org.apache.spark.unsafe.types.ByteArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    private final static Path DEFAULT_CLASS_DIR = Paths.get("/tmp", "classloader1");
    private final Path classDir;


    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classBytes = this.readClassBytes(name);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load class" + name);
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException{
        String classPath = name.replace(".", "/");
        String[] names = name.split("\\.") ;
        System.out.println(classDir);
        Path classFullPath = classDir.resolve(Paths.get(classDir+"/"+names[names.length-1] + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("the class " + name + " not found");
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + "occur error" + e);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
