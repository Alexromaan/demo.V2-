package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.emptyList;
import static org.assertj.core.util.Lists.newArrayList;


public class AppShould {

    private final Shirt shirt1 = new Shirt("nike");
    private final Shirt shirt2 = new Shirt("adidas");
    private final Shirt shirt3 = new Shirt("puma");

    private final List<Shirt> shirtsPerson1 = Arrays.asList(shirt1, shirt2);
    private final List<Shirt> shirtsPerson2 = Arrays.asList(shirt2, shirt3);
    private final List<Shirt> shirtsPerson3 = Arrays.asList(shirt1, shirt3);

    private final Person person1 = new Person("Manuel", shirtsPerson1);
    private final Person person2 = new Person("Pedro", shirtsPerson2);
    private final Person person3 = new Person("Javier", shirtsPerson3);

    private final List<Person> peopleInFirstCar = Arrays.asList(person1, person2);
    private final List<Person> peopleInSecondCar = Collections.singletonList(person3);

    private final Car car1 = new Car("berlina", "1234abc", "rojo", peopleInFirstCar);
    private final Car car2 = new Car("turismo", "9876zxy", "beige", peopleInSecondCar);
    private final Car car3 = new Car("turismo", "3456cgv", "rojo", emptyList());
    private final Car car4 = new Car("turismo", "3456cgv", "rojo");
    private final List<Car> cars = Arrays.asList(car1, car2, car3, car4);

    private App app;

    @BeforeEach
    void setUp() {
        app = new App(cars);
    }

    // Listar todos los coches -> List<String>
    @Test
    void list_all_cars_should() {
        assertThat(app.getAllCars()).isEqualTo(cars);
    }

    // Listar todas las personas -> List<STring>
    @Test
    void list_all_people_should() {
        assertThat(app.getAllPersons()).isEqualTo(aPersons());
    }

    // Lista todas las marcas de camiseta (un listado unico) -> List<String>
    @Test
    void list_all_shirts_brands_should() {
        assertThat(app.getShirtBrands()).isEqualTo(shirtsBrands());
    }

    // Listar todas las marcas de camiseta de los coches rojos (un listado unico) -> List<STring>
    @Test
    void list_camisas_but_red_cars() {
        assertThat(app.getShirtsByAColoredCar("rojo")).isEqualTo(shirtsBrands());
    }

    // Listar las personas que tienen camiseta de la marca X -> List<PErsona>
    @Test
    void list_personas_but_marca_X() {
        assertThat(app.getPersonsWithBrands("nike")).isEqualTo(aPersonWithBrand());
    }


    // Listar los nombres de las personas que tiene camiseta de la marca X -> List<String>
    @Test
    void list_personas_nombre_with_marca_X() {
        assertThat(app.getPeopleNameWithABrand("nike")).isEqualTo(aPersonNameWithBrand());
    }


    // Listar la matricula de todos los coches que tienen personas -> List<String>
    @Test
    void list_matricula_all_cars_with_persons() {
        assertThat(app.getMatriculaFromCarsWithPeople()).isEqualTo(aMatriculaFromCarWithPeople());
    }

    // Listar los colores del coche que mas personas tienen -> List<String>
    @Test
    void list_color_by_most_people_car() {
        assertThat(app.getColorByMostPeopleCar()).isEqualTo(new ArrayList<String>(Collections.singleton(car1.color)));
    }

    // Total de coches con pasajeros -> Integer
    @Test
    void cars_with_people_count() {
        assertThat(app.getNumberOfCars()).isEqualTo(2);
    }

    // Total de camisetas de todos los pasajeros -> Integer
    @Test
    void shirts_count() {
        assertThat(app.getNumberOfShirts()).isEqualTo(6);
    }

    // Total de camisetas de los pasajeros que tengan un coche de color rojo -> Integer
    @Test
    void persons_with_red_car_count() {
        assertThat(app.getPersonsByRedCarCount("rojo")).isEqualTo(2);
    }


    private ArrayList<Person> aPersons() {
        return newArrayList(person1, person2, person3);
    }

    private ArrayList<Shirt> aCamisas() {
        return newArrayList(shirt1, shirt2, shirt3);
    }

    private List<String> shirtsBrands() {
        return aCamisas().stream()
                .map(Shirt::getBrand)
                .collect(Collectors.toList());
    }

    private List<Person> aPersonWithBrand() {
        return newArrayList(person1, person3);
    }

    private List<String> aPersonNameWithBrand() {
        return aPersonWithBrand().stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    @NotNull
    private ArrayList<String> aMatriculaFromCarWithPeople() {
        return newArrayList("1234abc", "9876zxy");
    }
}
