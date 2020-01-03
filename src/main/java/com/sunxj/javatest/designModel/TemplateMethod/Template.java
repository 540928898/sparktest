package com.sunxj.javatest.designModel.TemplateMethod;

public class Template {
    /**
     * 核心思想就是父类写算法结构代码，子类实现逻辑细节
     * @param message: To say something
     */
    private final void print(String message) {
        System.out.println("####");
        wrapPrint(message);
        System.out.println("####");
    }
    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        Template t1 = new Template(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("This is WrrapPrint: "+message+", I am overwride success");
            }
        };
        t1.print("I am gupeng");
    }
}

