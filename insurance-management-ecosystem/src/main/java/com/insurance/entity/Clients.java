package com.insurance.entity;

import jakarta.persistence.*;

@Entity
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    
    private String name;
    private String contactInformation;
    private String address;
    private String clientType; // Individual or Organization

    // Constructors
    

    

    public String getName() {
        return name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public String getClientType() {
        return clientType;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}

