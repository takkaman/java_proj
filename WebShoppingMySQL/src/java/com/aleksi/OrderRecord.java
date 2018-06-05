/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksi;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * Registration Record stores a single set of information 
 * submitted by the user through the SimpleRegistration application
 * @author FlexTio
 */ 
public class OrderRecord implements Serializable
{
    // Add your serial version UID here later
//   private final long serialVersionUID = -1L;
    private Integer orderId;
    private Integer customerId;
    private Integer orderPrice;
    private Integer orderPoints;
    private Timestamp timestamp;
    
    // Create your default constructor here later
    public OrderRecord(){}
    // Implement your getters and setters here later
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer OrderId) {
        this.orderId = orderId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderPoints() {
        return orderPoints;
    }

    public void setOrderPoints(Integer orderPoints) {
        this.orderPoints = orderPoints;
    }
    
    public Timestamp getTime() {
        return timestamp;
    }

    public void setPoints(Timestamp timestamp) {
        this.timestamp = timestamp;
    }  
}