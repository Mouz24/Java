package com.example.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.Nullable;
import org.springframework.context.annotation.Configuration;

public class InitialNumber {
    public Integer value;

    public InitialNumber(){}

    public InitialNumber(Integer value)
    {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
