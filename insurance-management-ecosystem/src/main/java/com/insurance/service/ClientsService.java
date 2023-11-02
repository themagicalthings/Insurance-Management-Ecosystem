package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.entity.Clients;
import com.insurance.repository.ClientsRepository;



@Service
public class ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    public Clients createClient(Clients clientdata) {
        // validate clientdata
        if (clientdata.getName() == null || clientdata.getContactInformation() == null || clientdata.getAddress() == null || clientdata.getClientType() == null) {
            // Handle incomplete client data
            return null; // You can define a proper error handling mechanism
        }

        //  valid email
        if (!isValidEmail(clientdata.getContactInformation())) {
            // Handle invalid contact information
            return null; // You can define a proper error handling mechanism
        }

        // clientType 
        if (!clientdata.getClientType().equalsIgnoreCase("Individual") && !clientdata.getClientType().equalsIgnoreCase("Organization")) {
            // Handle invalid client type
            return null; // You can define a proper error handling mechanism
        }

        // check if exist
        if (isContactInformationInUse(clientdata.getContactInformation())) {
            // Handle duplicate contact information
            return null; // You can define a proper error handling mechanism
        }

        // validat pass, create the client
        Clients createdClient = clientsRepository.save(clientdata);

        return createdClient;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public boolean isContactInformationInUse(String contactInformation) {
        return false; // Implement according to your database check
    }
}


