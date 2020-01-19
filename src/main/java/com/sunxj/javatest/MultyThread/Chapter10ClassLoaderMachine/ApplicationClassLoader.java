package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

import org.springframework.context.ApplicationContext;

public class ApplicationClassLoader {
    /**
     * 负责加载classpath下的类库资源
      */
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
