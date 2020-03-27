package com.nitesh.map;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class testMap {
    public void testfn() {
        HashMap<String, Integer> m = new HashMap<>();
        m.put("age1", 10);
        m.put("age2", 11);

        Set<Map.Entry<String, Integer>> s = m.entrySet();
        for(Map.Entry<String, Integer> entry : s) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println("Lets remove elements from set to see if it gets removed from map");
        //s.clear();
        System.out.println(s.size());
        System.out.println(m.size());

        // System.out.println(m.getOrDefault("age1", 30));

        System.out.println("Lets print all keys in map");
        for(String str : m.keySet()) {
            System.out.println(str);
        }

        System.out.println("Lets print key-value pairs in map");
        Iterator<Map.Entry<String, Integer>> iter = m.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println("key = " + entry.getKey() + " Value = " + entry.getValue());

        }

    }
}
