package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {

    // adding attributes/fields for student class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    private String emailAdress;
    private String major;
    private String phoneNumber;

    @ManyToOne
    private School school;

    //creating empty&full constructors


    public Student() {
    }

    public Student(String name, String lastname, String emailAdress, String major, String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.emailAdress = emailAdress;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    // creating getters&setters


    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getMajor() {
        return major;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    // overriding equals & hashCode methods to compare objects


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;

        return Objects.equals(emailAdress, student.emailAdress);
    }

    @Override
    public int hashCode() {
        return emailAdress != null ? emailAdress.hashCode() : 0;
    }

    // overriding toString method


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailAdress='" + emailAdress + '\'' +
                ", major='" + major + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


}
