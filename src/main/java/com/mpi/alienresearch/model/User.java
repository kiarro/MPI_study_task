package com.mpi.alienresearch.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String jobAgreementNumber = null;
    private String username = null;
    private String password = null;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    private Long userGroup = null;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String firstName = null;
    private String lastName = null;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    private String email = null;
    private String phoneNumber = null;
    private String aboutYourself = null;

    public User() {
    }

    public User(String jobAgreementNumber, String username, String password, UserRole role, String firstName,
            String lastName, LocalDate birthDate) {
        this.jobAgreementNumber = jobAgreementNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s', role='%s']",
                id, firstName, lastName, role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobAgreementNumber() {
        return jobAgreementNumber;
    }

    public void setJobAgreementNumber(String jobAgreementNumber) {
        this.jobAgreementNumber = jobAgreementNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGroup() {
        return userGroup;
    }

    public void setGroup(Long group) {
        this.userGroup = group;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public void updateWith(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setBirthDate(user.getBirthDate());
        setEmail(user.getEmail());
        setGroup(user.getGroup());
        setJobAgreementNumber(user.getJobAgreementNumber());
        setPhone(user.getPhone());
        setRole(user.getRole());
    }

}