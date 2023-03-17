package com.example.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class ConvertedNumber
{
    public ConvertedNumber(Integer value)
    {
        this.value = value;
    }

    @JsonProperty("NumberInBinarySystem")
    public Integer value;

    public Integer getValue() {
        return value;
    }
}
