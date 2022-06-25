package com.mpi.alienresearch.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpi.alienresearch.model.enums.UserRole;

/**
 * Пользователь
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String jobAgreementNumber = null;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    private String phoneNumber = null;
    private String email = null;
    private String aboutYourself = null;
    
    @ManyToOne
    private UserGroup userGroup;

    @OneToOne
    private Credentials credentials;

    public User(Long id, String firstName, String lastName, LocalDate birthDate, String jobAgreementNumber,
            UserRole role, String phoneNumber, String email, String aboutYourself, UserGroup userGroup, 
            Credentials credentials) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.jobAgreementNumber = jobAgreementNumber;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aboutYourself = aboutYourself;
        this.userGroup = userGroup;
        this.credentials = credentials;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobAgreementNumber() {
        return jobAgreementNumber;
    }

    public void setJobAgreementNumber(String jobAgreementNumber) {
        this.jobAgreementNumber = jobAgreementNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    
}