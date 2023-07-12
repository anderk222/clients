/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alquimiasoft.client;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author linuxlite
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {

    private int page;
    private int count;
    private long total;

    private List<T> data = new ArrayList<>();

    public Pagination(int page, int count) {

        this.page = page;
        this.count = count;

    }

    ;
        public Pagination(int page, int count, long total) {

        this.page = page;
        this.count = count;
        this.total = total;

    }

}
