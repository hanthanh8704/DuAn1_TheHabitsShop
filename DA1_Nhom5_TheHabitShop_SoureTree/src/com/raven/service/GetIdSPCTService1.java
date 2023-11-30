/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;


import com.raven.reponsitory.NhanVien_DAO;


/**
 *
 * @author ThanhDat
 */
public class GetIdSPCTService1 {

    public NhanVien_DAO spct = new NhanVien_DAO();

    public String getIDChucVu(String chucVu) {
        return spct.getidChucVu(chucVu);
    }

   
}
