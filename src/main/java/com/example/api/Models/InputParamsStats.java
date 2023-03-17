package com.example.api.Models;

import java.util.Collection;
import java.util.List;

public class InputParamsStats {
    public Integer MaxValue;

    public Integer MinValue;

    public Integer ConvertedMaxValue;

    public Integer ConvertedMinValue;

    public Double MiddleValue;

    public Collection<InitialNumber> InitialNumbers;
    public List<ConvertedNumber> ConvertedNumbers;

    public void SetInputStats(Collection<InitialNumber> numbers)
    {
        MaxValue = numbers.stream().filter(num -> num.value != null).map(InitialNumber::getValue).max(Integer::compare).get();

        MinValue = numbers.stream().filter(num -> num.value != null).map(InitialNumber::getValue).min(Integer::compare).get();

        MiddleValue = numbers.stream().filter(num -> num.value != null).map(InitialNumber::getValue).mapToInt(n -> n).average().getAsDouble();

        InitialNumbers = numbers;
    }

    public void SetResultStats(List<ConvertedNumber> numbers)
    {
        ConvertedMaxValue = numbers.stream().filter(num -> num.value != null).map(ConvertedNumber::getValue).max(Integer::compare).get();

        ConvertedMinValue = numbers.stream().filter(num -> num.value != null).map(ConvertedNumber::getValue).min(Integer::compare).get();

        ConvertedNumbers = numbers;
    }
}
