/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client.services;

import com.alquimiasoft.client.exceptions.ResourceNotFoundException;
import com.alquimiasoft.client.models.CustomerAddress;
import com.alquimiasoft.client.repositories.CustomerAddressRepository;
import com.alquimiasoft.client.repositories.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linuxlite
 */

@Service
public class CustomerAddressService {
    
    @Autowired
    private CustomerAddressRepository customerAddressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    
    
    public List<CustomerAddress> findAllByCustomer(long user){
        
        return this.customerAddressRepository.findAllByCustomerId(user);
        
    }
    
    public CustomerAddress findMainByCustomer(long id) throws ResourceNotFoundException{
        
        return customerAddressRepository.findByCustomerIdAndMain(id, true)
                .orElseThrow(()-> new ResourceNotFoundException(id, "id", "customer address"));
        
    }
    
    public CustomerAddress save(CustomerAddress customerAddress, long user){
        
            CustomerAddress newCustomerAddres = customerRepository.findById(user)
                    .map((customer)->{
                    
                    customerAddress.setCustomer(customer);
                    
                    return  customerAddress;
                    })
            .orElseThrow(()-> new ResourceNotFoundException(user, "id", "user"));
            
            
            return customerAddressRepository.save(newCustomerAddres);
            
        
    }
    
    public CustomerAddress update(CustomerAddress address, long id,long user) throws ResourceNotFoundException{
        
        CustomerAddress updateAddress = customerAddressRepository.findByIdAndCustomerId(id, user)
            .orElseThrow(()->new ResourceNotFoundException(id +" "+ user, "address id customer id", "user and address"));
        
        updateAddress.setProvince(address.getProvince());
        updateAddress.setCity(address.getCity());
        updateAddress.setAddress(address.getAddress());
            
        return customerAddressRepository.save(updateAddress);
        
    }
    
    public CustomerAddress delete(long id) throws ResourceNotFoundException{
        
        CustomerAddress address = customerAddressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id, "id", "address customer"));
        
        customerAddressRepository.deleteById(id);
        
        return address;
        
    }
    
}
