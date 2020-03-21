package com.sunxj.javatest.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {
    /**
     * Map的遍历的主要方法是使用entrySet 或者使用 foreach 或者使用keySet
     * put
     *
     * remove
     *
     * replace
     */
    class HashMapTest implements Options<String> {
        Map<String,String > map = new HashMap<String,String>();

        @Override
        public void insert(Map.Entry<String,String> t1) {
            map.put(t1.getKey(),t1.getValue());
        }

        @Override
        public void delete(String id) {
            map.remove("E");
        }

        @Override
        public void update(String id,String value) {
            map.replace("F", "value");
        }

        @Override
        public void searchMap(String id) {
            if(map.containsKey(id)) {
                System.out.println(map.get(id));
            }
            else{
                System.out.println();
            }
        }

        @Override
        public void searchAll() {
            Set<String> lps = map.keySet();
            for(String num:lps){
                System.out.println(num);

            }
            Set<Map.Entry<String,String>>entrys = map.entrySet();
            for(Map.Entry<String,String> item:entrys){//通过这种方法也可以达到遍历的效果
                System.out.println( item.getKey() + "-->>"+ item.getValue());
        }
            map.forEach((k,v)-> System.out.println("key : " + k + "; value : " + v));

        }

}
}
