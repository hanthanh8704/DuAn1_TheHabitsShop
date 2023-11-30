/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.reponsitory.BanHangReponsitory;
import com.raven.reponsitory.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ThanhDat
 */
public class getidNhanVienService {
    public BanHangReponsitory bh = new BanHangReponsitory();
    
      
     public String getIDNhanVien(String nhanVien) {
        return bh.getidNhanVien(nhanVien);
    }
}
