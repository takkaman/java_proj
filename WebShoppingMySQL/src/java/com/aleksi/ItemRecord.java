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
public class ItemRecord implements Serializable
{
    // Add your serial version UID here later
//   private final long serialVersionUID = -1L;
    private Integer itemId;
    private String itemDescription;
    private String brand;
    private Integer price;
    private Integer points;
    
    // Create your default constructor here later
    
    
    public ItemRecord(){}
    // Implement your getters and setters here later

    public Integer getId() {
        return itemId;
    }

    public void setId(Integer itemId) {
        this.itemId = itemId;
    }
    
    public String getDesc() {
        return itemDescription;
    }

    public void setDesc(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }  
}