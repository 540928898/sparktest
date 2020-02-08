package com.sunxj.javatest.CollectionUtils;

import java.util.Map;

public  interface Options<T> {
    public void insert(Map.Entry<T,T>t1);
    public void delete(String id);
    public void update(String id,String value);
    public void searchMap(String id);
    public void searchAll();
}
