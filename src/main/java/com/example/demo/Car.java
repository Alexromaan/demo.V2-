package com.example.demo;
import java.util.List;

public class Car {
    String type;
    private String matricula;
    String color;
    List<Person> people;

    public Car(){

    }
    public Car(String type, String matricula, String color, List<Person> people) {
        this.type = type;
        this.matricula = matricula;
        this.color = color;
        this.people = people;
    }

    public Car(String type, String matricula, String color) {
        this.type = type;
        this.matricula = matricula;
        this.color = color;
    }

    public List<Person> getPeople() {
        return this.people;
    }
    public String getColor() { return this.color; }

    public  String getMatricula() {
        return this.matricula;
    }
}
