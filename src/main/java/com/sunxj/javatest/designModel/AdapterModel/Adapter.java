package com.sunxj.javatest.designModel.AdapterModel;

public class Adapter extends Adaptee implements Target {
    @Override
    public void Request() {
        this.SpecificRequest();
    }
}
