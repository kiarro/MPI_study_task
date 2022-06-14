package com.mpi.alienresearch.model;

import java.util.Date;

/**
 * Подопытный
 */
public class Subject {
    String id;
    String name;
    
    String hairColor;
    String eyesColor;
    String skinColor;
    String specials;

    Float weight;
    Float height;
    Date birthDate;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHairColor() {
        return hairColor;
    }
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    public String getEyesColor() {
        return eyesColor;
    }
    public void setEyesColor(String eyesColor) {
        this.eyesColor = eyesColor;
    }
    public String getSkinColor() {
        return skinColor;
    }
    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }
    public String getSpecials() {
        return specials;
    }
    public void setSpecials(String specials) {
        this.specials = specials;
    }
    public Float getWeight() {
        return weight;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }
    public Float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
