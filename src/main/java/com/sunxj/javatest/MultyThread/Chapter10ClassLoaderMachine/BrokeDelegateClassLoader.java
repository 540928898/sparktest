package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BrokeDelegateClassLoader extends MyClassLoader {
    /**
     * 重写loadClass，LoadClass中调用findClass
     */
    private final static Path DEFAULT_CLASS_DIR = Paths.get("/tmp", "classloader1");
    private final Path classDir;
    public BrokeDelegateClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public BrokeDelegateClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }
    public BrokeDelegateClassLoader(String classDir, String parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> kclass = findLoadedClass(name);
            if (kclass == null) {
                if (name.startsWith("java.") || name.startsWith("javax")) {
                    try {
                        kclass = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        kclass = this.findClass(name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (kclass == null) {
                        if (getParent() != null) {
                            kclass = getParent().loadClass(name);
                        } else {
                            kclass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            if (null == kclass) {
                throw new ClassNotFoundException("The class :" + name + " not fount");
            }
            if (resolve) {
                resolveClass(kclass);
            }
            return kclass;
        }
    }
}
