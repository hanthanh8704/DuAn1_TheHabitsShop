/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.UUID;

/**
 *
 * @author MSI 15
 */
public class ChucVu {

    private UUID id;
    private String tenCV;

    public ChucVu() {
    }

    public ChucVu(UUID id, String tenCV) {
        this.id = id;
        this.tenCV = tenCV;
    }

    public ChucVu(String tenCV) {
        this.tenCV = tenCV;
    }
    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

}
