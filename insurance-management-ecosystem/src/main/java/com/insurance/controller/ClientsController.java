package com.insurance.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.Clients;
import com.insurance.service.ClientsService;


@RestController
@RequestMapping("/clients")
public class ClientsController {
	
    private final ClientsService clientService;
    
    public ClientsController(ClientsService clientService) {
    	this.clientService=clientService;
    }
	
	//Endpoint 1
	@PostMapping
    public ResponseEntity<Object> createClient(@RequestBody Clients clientdata) {
		Map<String,Object> response=new LinkedHashMap<>();
    	// response.put("success", true);
    	// response.put("message", "placeholder for logic of adding clinets ");
        // return ResponseEntity.ok(response);

        //validate client data
        if (clientdata.getName() == null || clientdata.getContactInformation() == null ||clientdata.getAddress() == null || clientdata.getClientType()== null){
            response.put("success",false);
            response.put("message", "client info is incomplete.");
            return ResponseEntity.badRequest().body(response);
        }
        //validate contact information
        if (clientdata.getContactInformation().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            response.put("success",false);
            response.put("message", "contact info is not vaild.");
            return ResponseEntity.badRequest().body(response);
        }
        // validate client type
        if (clientdata.getClientType().equalsIgnoreCase("Individual") && clientdata.getClientType().equalsIgnoreCase("Organization")){
            response.put("success", false);
            response.put("message", "contact type info is not valid");
            return ResponseEntity.badRequest().body(response);
        }
        //check if there user already exists
        if (clientService.isContactInformationInUse(clientdata.getContactInformation())){
            response.put("success", false);
            response.put("message", "user info is already in use");
            return ResponseEntity.badRequest().body(response);
        }
        //validate pass create client
        Clients createdClient = clientService.createClient(clientdata);

        if (createdClient != null) {
            response.put("success", true);
            response.put("message", "Client created successfully.");
            response.put("client", createdClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("success", false);
            response.put("message", "Client creation failed.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

	
	// Endpoint 2
    @GetMapping
    public ResponseEntity<Object> getAllClients() {
       
    	Map<String,Object> response=new LinkedHashMap<>();
    	response.put("success", true);
    	response.put("message", "placeholder for logic of retrieving all clients ");
        return ResponseEntity.ok(response);
	
	
}
    
    //Endpoint 3
    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable Long clientId) {
       
    	Map<String,Object> response=new LinkedHashMap<>();
    	response.put("success", true);
    	response.put("message", "placeholder for logic of retrieving client details using clientId ");
        return ResponseEntity.ok(response);
    }
    
    //Endpoint 4
    @PostMapping("/update/{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable Long clientId) {
    	Map<String,Object> response=new LinkedHashMap<>();
    	response.put("success", true);
    	response.put("message", "placeholder for logic of updating client details using clientId ");
        return ResponseEntity.ok(response);
    }

   

}

