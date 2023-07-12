/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client.controllers;

import com.alquimiasoft.client.Pagination;
import com.alquimiasoft.client.models.Customer;
import com.alquimiasoft.client.models.CustomerDTO;
import com.alquimiasoft.client.models.CustomerProjection;
import com.alquimiasoft.client.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linuxlite
 */

@RestController
@RequestMapping("/api/customer")
@CrossOrigin({"*"})
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Pagination<CustomerProjection> search(
            @RequestParam(name = "num_id",defaultValue = "" ,required = false) String numId,
            @RequestParam(name="name", defaultValue = "", required = false) String name,
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="limit", defaultValue = "10", required = false) int limit
            
            ) {

        return customerService.search(numId, name, page, limit);

    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customer) {

        return new ResponseEntity(customerService.save(customer),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public CustomerDTO update(@RequestBody CustomerDTO customer, @PathVariable long id) {

        return customerService.update(customer, id);

    }
    
    @DeleteMapping("/{id}")
    public Customer  delete(@PathVariable long id){
        
        return customerService.delete(id);
        
    }
    
}