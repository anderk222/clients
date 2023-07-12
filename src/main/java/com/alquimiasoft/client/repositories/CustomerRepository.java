/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alquimiasoft.client.repositories;

import com.alquimiasoft.client.models.Customer;
import com.alquimiasoft.client.models.CustomerProjection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author linuxlite
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAll();

    @Query(value="SELECT c.*, ca.province as main_province,ca.city as main_city, ca.address as main_address"
            + " FROM customers c LEFT JOIN customer_addresses ca ON c.id=ca.user_id WHERE c.names ~* :name "
            + "OR c.identification_number ~* :num OR ca.main=true", nativeQuery = true)
            
    Page<CustomerProjection> findAll(@Param("name") String name, @Param("num") String num, Pageable pageable);


}
