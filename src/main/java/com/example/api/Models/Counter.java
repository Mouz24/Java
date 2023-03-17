package com.example.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="counter")
@Scope("singleton")
public class Counter {
    public int GetCounter()
    {
        return count;
    }

    public void Increment()
    {
        count++;
    }

    @JsonProperty("Calls")
    private static Integer count = 0;

}
