/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client.controllers;

import com.alquimiasoft.client.models.CustomerAddress;
import com.alquimiasoft.client.services.CustomerAddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linuxlite
 */
@CrossOrigin({"*"})
@RequestMapping("/api/customer/address")
@RestController
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customerAddressService;

    @GetMapping("/{userId}/user")
    public List<CustomerAddress> findAllByUser(@PathVariable long userId) {

        return customerAddressService.findAllByCustomer(userId);

    }

    @GetMapping("/main/{userId}/user")
    public CustomerAddress findMain(@PathVariable long userId) {

        return customerAddressService.findMainByCustomer(userId);

    }

    @PostMapping("/{userId}/user")
    public CustomerAddress save(@RequestBody CustomerAddress address,
             @PathVariable long userId
    ) {

        return customerAddressService.save(address, userId);

    }

    @PutMapping("/{id}/user/{userId}")
    public CustomerAddress update(@RequestBody CustomerAddress address,
             @PathVariable long id, @PathVariable long userId
    ) {

        return customerAddressService.update(address, id, userId);

    }
    
    @DeleteMapping("/{id}")
    public CustomerAddress delete(@PathVariable long id){
        
        return customerAddressService.delete(id);
        
    }

}
