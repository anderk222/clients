/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alquimiasoft.client.repositories;

import com.alquimiasoft.client.models.CustomerAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author linuxlite
 */
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    List<CustomerAddress> findAllByCustomerId(long id);

    Optional<CustomerAddress> findByCustomerIdAndMain(long id, boolean main);

    Optional<CustomerAddress> findByIdAndCustomerId(long id, long user);

}
