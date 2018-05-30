/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp;

import java.io.Serializable;

/**
 * Registration Record stores a single set of information 
 * submitted by the user through the SimpleRegistration application
 * @author FlexTio
 */ 
public class RegistrationRecord implements Serializable
{
    // Add your serial version UID here later
   private final long serialVersionUID = -1L;
   
    private String name;
    private String adminno;
    private String email;
    private String gender;
    private String specialisation;
    private int height;
    private double weight;
    private double bmi;
    
    // Create your default constructor here later
    
    
    public RegistrationRecord(){}
    // Implement your getters and setters here later

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminno() {
        return adminno;
    }

    public void setAdminno(String adminno) {
        this.adminno = adminno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
    
    
}