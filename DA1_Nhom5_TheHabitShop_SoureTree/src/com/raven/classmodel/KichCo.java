/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

/**
 *
 * @author ThanhDat
 */
public class KichCo {
    String id;
    private String maKichCo; 
    private String tenKichCo;

    public KichCo() {
    }

    public KichCo(String id, String maKichCo, String tenKichCo) {
        this.id = id;
        this.maKichCo = maKichCo;
        this.tenKichCo = tenKichCo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public KichCo(String maKichCo, String tenKichCo) {
        this.maKichCo = maKichCo;
        this.tenKichCo = tenKichCo;
    }

    public String getMaKichCo() {
        return maKichCo;
    }

    public void setMaKichCo(String maKichCo) {
        this.maKichCo = maKichCo;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }

    @Override
    public String toString() {
        return "KichCo{" + "maKichCo=" + maKichCo + ", tenKichCo=" + tenKichCo + '}';
    }
    
}
