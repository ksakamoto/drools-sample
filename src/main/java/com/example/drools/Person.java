package com.example.drools;

public class Person {
    private String name;
    private int age;
    private String category;
    private boolean eligible;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.eligible = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", category='" + category + '\'' +
                ", eligible=" + eligible +
                '}';
    }
}