package com.epam.entity;


import java.util.ArrayList;
import java.util.List;

public class Faculty extends BaseEntity {
    private String name;
    private String decan;
    private List<Group> groups;

    public Faculty() {
        groups=new ArrayList<>();
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

    public void add(List<Group> group) {
        groups.addAll(group);
    }

    public void remove(List<Group> group) {
        groups.removeAll(group);
    }

    public String getDecan() {
        return decan;
    }

    public void setDecan(String decan) {
        this.decan = decan;
    }
}
