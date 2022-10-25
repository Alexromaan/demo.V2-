package com.example.demo;

import java.util.List;

public class Person {
    private String name;
    private List<Shirt> shirts;

    public Person(String name, List<Shirt> shirts) {
        this.name = name;
        this.shirts = shirts;
    }

    public List<Shirt> getShirts() {
        return this.shirts;
    }

    public String getName() {
        return this.name;
    }
}
