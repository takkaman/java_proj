/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksi;

import java.io.Serializable;

/**
 * Registration Record stores a single set of information 
 * submitted by the user through the SimpleRegistration application
 * @author FlexTio
 */ 
public class CustomerRecord implements Serializable
{
    // Add your serial version UID here later
//   private final long serialVersionUID = -1L;
   
    private String name;
    private String addr1;
    private String addr2;
    private String email;
    private String phone;
    private String postcode;
    private String psw;
    
    // Create your default constructor here later
    
    
    public CustomerRecord(){}
    // Implement your getters and setters here later

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
    
    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }  
}