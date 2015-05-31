package com.epam.entity;



import java.util.HashMap;
import java.util.Map;

public class Application extends BaseEntity {
    private Integer idEnrollee;
    private String groupName;
    private PriorityStatement priority;

    public Application() {
    }

    public Integer getIdEnrollee() {
        return idEnrollee;
    }

    public void setIdEnrollee(Integer idEnrollee) {
        this.idEnrollee = idEnrollee;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PriorityStatement getPriority() {
        return priority;
    }

    public void setPriority(PriorityStatement priority) {
        this.priority = priority;
    }
}
