package com.epam.entity;

public class Group extends BaseEntity {
    private String  name;
    private Integer numberStudent;
    private Subject profileSubject;
    private Integer idFaculty;
    private Boolean isExam;

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(Integer numberStudent) {
        this.numberStudent = numberStudent;
    }

    public Integer getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public Subject getProfileSubject() {
        return profileSubject;
    }

    public void setProfileSubject(Subject profileSubject) {
        this.profileSubject = profileSubject;
    }

    public Boolean getIsExam() {
        return isExam;
    }



    public void setIsExam(Boolean isExam) {
        this.isExam = isExam;
    }
}
