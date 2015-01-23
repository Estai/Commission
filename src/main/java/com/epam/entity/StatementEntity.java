package com.epam.entity;



import java.util.HashMap;
import java.util.Map;

public class StatementEntity extends BaseEntity {
    private int idEnrollee;
    private String groupName;
    private PriorityStatement priority;

    public StatementEntity() {
    }

    public int getIdEnrollee() {
        return idEnrollee;
    }

    public void setIdEnrollee(int idEnrollee) {
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
