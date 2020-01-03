package com.sunxj.javatest.designModel.TemplateMethod;

public class TemplateSun extends Template {
    @Override
    protected void wrapPrint(String message) {
        System.out.println("I am Template sum "+message+", Overwide Success");
    }

    public static void main(String[] args) {
        new TemplateSun().wrapPrint("I am Tempsun");
    }
}
