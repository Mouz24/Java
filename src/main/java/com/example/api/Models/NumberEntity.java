package com.example.api.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "number")
public class NumberEntity {
    @Id
    @Column (name = "ID")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Column(name = "initial")
    private Integer initialValue;

    @Column(name = "converted")
    private Integer convertedValue;

    public NumberEntity() {}

    public NumberEntity(Integer initialValue, Integer convertedValue)
    {
        this.convertedValue = convertedValue;
        this.initialValue = initialValue;
    }

    public NumberEntity(Integer initialValue)
    {
        this.initialValue = initialValue;
    }

    public long getId() {
        return id;
    }

    public Integer getInitialValue()
    {
        return initialValue;
    }

    public Integer getConvertedValue()
    {
        return convertedValue;
    }

    public void setInitialValue(Integer value)
    {
        initialValue = value;
    }

    public void setConvertedValue(Integer value)
    {
        convertedValue = value;
    }

    @Override
    public String toString() {
        return "Number [id=" + id + ", initialValue=" + initialValue + ", convertedValue=" + convertedValue + "]";
    }
}



