/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

/**
 *
 * @author ThanhDat
 */
public class MauSac {
    
    private String id;
    private String maMauSac;
    private String tenMauSac;

    public MauSac() {
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MauSac(String id, String maMauSac, String tenMauSac) {
        this.id = id;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }
    
    public MauSac(String maMauSac, String tenMauSac) {
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    @Override
    public String toString() {
        return "MauSac{" + "maMauSac=" + maMauSac + ", tenMauSac=" + tenMauSac + '}';
    }
    
}
