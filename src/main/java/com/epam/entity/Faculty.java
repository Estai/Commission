package com.epam.entity;


import java.util.List;

public class Faculty extends BaseEntity {
    private String name;
    private List<Group> groups;

    public Faculty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void add(Group group) {
        groups.add(group);
    }

    public void remove(Group group) {
        groups.remove(group);
    }
}
