/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client.services;

import com.alquimiasoft.client.Pagination;
import com.alquimiasoft.client.exceptions.BadRequestException;
import com.alquimiasoft.client.exceptions.ResourceNotFoundException;
import com.alquimiasoft.client.models.Customer;
import com.alquimiasoft.client.models.CustomerAddress;
import com.alquimiasoft.client.models.CustomerDTO;
import com.alquimiasoft.client.models.CustomerProjection;
import com.alquimiasoft.client.repositories.CustomerAddressRepository;
import com.alquimiasoft.client.repositories.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author linuxlite
 */

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressRepository customerAddressRepository;
    
    public Pagination<CustomerProjection> search(String numId, String name, int page, int limit){
        
        
        Pageable pageable = PageRequest.of(page, limit);
        
        Page<CustomerProjection> data = customerRepository.findAll(name, numId, pageable);
        
        
        Pagination<CustomerProjection> res = new Pagination(page,limit, data.getTotalElements());
        
        res.setData(data.getContent());
        
        return res;
        
    }
    
    
    public CustomerDTO save(CustomerDTO customer) throws BadRequestException{
        
        if(customer.getId() > 0) 
            throw  new BadRequestException("must not be greater than 0", "customer" , "0");
        
        
        Customer newCustomer = new Customer(
                customer.getNames(), customer.getEmail(), customer.getPhone()
        );
        
        
        newCustomer.setIdentificationNumber(customer.getIdentificationNumber());
        newCustomer.setIdentificationType(customer.getIdentificationType());
        
        Customer _newCustomer = customerRepository.save(newCustomer);

        
        CustomerAddress mainAddress =  new CustomerAddress(
                customer.getMainProvince(), customer.getMainCity(), customer.getMainAddress()
        );
        
        mainAddress.setMain(true);
        mainAddress.setCustomer(_newCustomer);
        
        customerAddressRepository.save(mainAddress);
        
        customer.setId(_newCustomer.getId());
        
        return  customer;
        
    }
    
    
    public CustomerDTO update(CustomerDTO customer,long id) throws ResourceNotFoundException{
        
        Customer updateCustomer = customerRepository.findById(id).map((_customer)->{
        
        _customer.setNames(customer.getNames());
        _customer.setEmail(customer.getEmail());
        _customer.setPhone(customer.getPhone());
        _customer.setIdentificationNumber(customer.getIdentificationNumber());
        _customer.setIdentificationType(customer.getIdentificationType());
        
        return customerRepository.save(_customer);
            
        })
            .orElseThrow(()->new ResourceNotFoundException(id, "customer id", "customer"));
        
    
        Optional<CustomerAddress> address = customerAddressRepository.findByCustomerIdAndMain(id, true);
        
        
        CustomerAddress updatedAdress = new CustomerAddress(
            customer.getMainProvince(), customer.getMainCity(), customer.getMainAddress()
        );
        
        updatedAdress.setMain(true);
        
        if(address.isPresent()){
            updatedAdress.setCustomer(updateCustomer);
            updatedAdress.setId(address.get().getId());
            customerAddressRepository.save(updatedAdress);
        }else{
            updatedAdress.setCustomer(updateCustomer);
            customerAddressRepository.save(updatedAdress);
        }
        
        customer.setId(updateCustomer.getId());
        
        return customer;
        
        
    }
    
    
    public Customer delete(long id) throws ResourceNotFoundException{
        
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id, "id", "customer"));
        
        customerRepository.deleteById(id);
        
        return customer;
        
    }
    
}
