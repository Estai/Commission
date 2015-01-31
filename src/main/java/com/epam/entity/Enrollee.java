package com.epam.entity;

import java.util.Map;

public class Enrollee extends User {
    private String firstName;
    private Integer idUser;
    private String lastName;
    private String middleName;
    private Map<Subject, Score> score;
    private Double certificate;
    private String certificateNumber;

    public Enrollee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Subject, Score> getScore() {
        return score;
    }

    public void setScore(Map<Subject, Score> score) {
        this.score = score;
    }

    public Double getCertificate() {
        return certificate;
    }

    public void setCertificate(Double certificate) {
        this.certificate = certificate;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Enrollee enrollee = (Enrollee) obj;
        if (firstName != null && enrollee.firstName != null) {
            if (!firstName.equals(enrollee.firstName)) return false;
        }
        if (lastName != null && enrollee.lastName != null) {
            if (!lastName.equals(enrollee.lastName)) return false;
        }
        if (middleName != null && enrollee.middleName != null) {
            if (!middleName.equals(enrollee.middleName)) return false;
        }
        if (certificateNumber != null && enrollee.certificateNumber != null) {
            if (!certificateNumber.equals(enrollee.certificateNumber)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int defaul = 0;
        int h = 1;
        h = 31 * h + firstName != null ? firstName.hashCode() : defaul;
        h = 31 * h + lastName != null ? lastName.hashCode() : defaul;
        h = 31 * h + middleName != null ? middleName.hashCode() : defaul;
        h = 31 * h + certificateNumber != null ? certificateNumber.hashCode() : defaul;
        return h;
    }
}
