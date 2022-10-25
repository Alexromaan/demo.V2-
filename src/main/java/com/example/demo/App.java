package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class App {

    private List<Car> cars;


    public App(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getAllCars() {
        return this.cars;
    }

    public List<Person> getAllPersons() {
        return this.getPeople(this.cars);
    }

    public List<String> getShirtBrands() {
        return this.getShirtBrands(this.getAllShirts(this.getPeople(this.cars)));
    }

    public List<String> getShirtsByAColoredCar(String color) {

        List<Car> carList = this.cars.stream()
                .filter(c -> c.getColor().equals(color))
                .collect(Collectors.toList());

        return getShirtBrands(getAllShirts(this.getPeople(carList)));
    }

    public List<Person> getPersonsWithBrands(String brand) {
        return this.getAllPersons().stream()
                .filter(person -> person.getShirts().stream()
                        .map(Shirt::getBrand)
                        .collect(Collectors.toList())
                        .contains(brand))
                .collect(Collectors.toList());
    }

    public List<String> getPeopleNameWithABrand(String brand) {
        return this.getPersonsWithBrands(brand).stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    public List<String> getMatriculaFromCarsWithPeople() {
        return this.cars.stream()
                .filter(car -> car.getPeople() != null && !car.getPeople().isEmpty())
                .map(Car::getMatricula)
                .collect(Collectors.toList());
    }

    public List<String> getColorByMostPeopleCar() {
        return this.mostPeopleCar().stream()
            .map(Car::getColor)
            .collect(Collectors.toList());}

    public int getNumberOfCars() {
        return (int) this.cars.stream()
                .filter(c -> c.people != null && !c.people.isEmpty())
                .map(Objects::toString).count();
    }

    public int getNumberOfShirts() {
        return this.getAllShirts(getAllPersons()).size();
    }

    public int getPersonsByRedCarCount(String color) {
        return this.getPeople(cars.stream()
                .filter(c -> Objects.equals(c.getColor(), color))
                .collect(Collectors.toList()))
                .size();
    }

    private List<Person> getPeople(List<Car> coches) {
        return coches.stream()
                .map(Car::getPeople)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> getShirtBrands(List<Shirt> allCamisas) {
        return allCamisas.stream()
                .map(Shirt::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Shirt> getAllShirts(List<Person> allPersons) {
        return allPersons.stream()
                .map(Person::getShirts)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Car> mostPeopleCar() {
        AtomicLong actual = new AtomicLong();
        List<Car> car = new ArrayList<>();
        this.cars.stream()
                .filter(c -> c.people != null && !c.people.isEmpty())
                .forEach(c->{
                    if(c.getPeople().size()> actual.get()) {
                        car.clear();
                        car.add(c);
                        actual.set(c.getPeople().size());
                    }
                });
        return car;
    }
}

