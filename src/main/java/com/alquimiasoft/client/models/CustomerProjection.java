/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alquimiasoft.client.models;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author linuxlite
 */
public interface CustomerProjection {

    long getId();

    @Value("#{target.identification_type}")
    IdentificationType getIdentificationType();

    @Value("#{target.identification_number}")
    String getIdentificationNumber();

    String getNames();

    String getEmail();

    String getPhone();

    @Value("#{target.main_province}")
    String getMainProvince();

    @Value("#{target.main_city}")

    String getMainCity();

    @Value("#{target.main_address}")

    String getMainAddress();

}
