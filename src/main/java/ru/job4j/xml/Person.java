package ru.job4j.xml;

public class Person {
    private boolean isActive;
    private int age;
    private String name;
    private Address address;
    private String[] hobbies;

    public Person(boolean isActive, int age, String name, Address address, String[] hobbies) {
        this.isActive = isActive;
        this.age = age;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
    }
}

class Address {
    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}