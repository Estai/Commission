package com.epam.entity;

/**
 * Created by User on 13.12.14.
 */
public class Subject extends BaseEntity {
    private String name;
    private boolean main;

    public Subject() {

    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
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
