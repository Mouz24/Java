package com.example.api.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@Component(value="cache")
@Scope("singleton")
public class Cache {
    public Integer Get(Integer value)
    {
        return map.get(value);
    }

    public void Put(Integer key, Integer value)
    {
        if (!map.containsValue(value))
        {
            map.put(key, value);
        }
    }

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
}
