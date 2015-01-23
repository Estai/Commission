package com.epam.entity;

/**
 * Created by User on 13.12.14.
 */
public class Subject extends BaseEntity {
    private String name;

    public Subject() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
