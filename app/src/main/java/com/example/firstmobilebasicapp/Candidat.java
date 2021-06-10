package com.example.firstmobilebasicapp;

public class Candidat {
    long idCandidat;
    String name;
    String phoneNumber;
    String country;
    String sex;
    long birthDate;

    public Candidat() {
    }

    public Candidat(long idCandidat, String name, String phoneNumber, String country, String sex, long birthDate) {
        this.idCandidat = idCandidat;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Candidat(String name, String phoneNumber, String country, String sex, long birthDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }
}
