/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author linuxlite
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends RuntimeException {
    
    private String require;
    private String resource;
    private String fieldName;

    public BadRequestException(String _require,String _resource,String _fieldName){

        super(String.format("El %s con el campo %s %s ", _resource, _fieldName,_require));

        this.resource = _resource;
        this.require = _require;
        this.fieldName = _fieldName;
       
    }
    
}