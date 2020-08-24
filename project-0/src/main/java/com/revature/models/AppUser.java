package com.revature.models;


import java.util.Objects;

public class AppUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String passWord;
    private String email;

//constructors

    public AppUser(){
        super();
    }

    public AppUser(Integer id, String firstName, String lastName, String passWord,String email) {
        this(firstName, lastName, passWord,email);
        this.id = id;

    }

    public AppUser(String firstName, String lastName, String passWord,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passWord = passWord;
        this.email = email;
    }
// Getters and Setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
//Hashcode and equals override


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(passWord, appUser.passWord) &&
                Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, passWord,email);
    }
// toString override


    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

