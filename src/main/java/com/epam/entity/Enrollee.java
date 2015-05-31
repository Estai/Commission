package com.epam.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Enrollee extends User {
    private String firstName;
    private Integer idUser;
    private Long IIN;
    private String lastName;
    private String middleName;
    private Map<Subject, Score> score;
    private String certificateNumber;
    private String nationality;
    private String mobileNumber;
    private String address;
    private String email;
    private String educationalInstitution;
    private Boolean goldMedal;
    private Boolean excellentPupil;
    private Date birthday;
    private Language language;
    private Gender gender;
    private String formStudy;




    public Enrollee(){
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

    public Long getIIN() {
        return IIN;
    }

    public void setIIN(Long IIN) {
        this.IIN = IIN;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public Boolean getGoldMedal() {
        return goldMedal;
    }

    public void setGoldMedal(Boolean goldMedal) {
        this.goldMedal = goldMedal;
    }

    public Boolean getExcellentPupil() {
        return excellentPupil;
    }

    public void setExcellentPupil(Boolean excellentPupil) {
        this.excellentPupil = excellentPupil;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {

        this.birthday = birthday;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public String getFormStudy() {
        return formStudy;
    }

    public void setFormStudy(String formStudy) {
        this.formStudy = formStudy;
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
