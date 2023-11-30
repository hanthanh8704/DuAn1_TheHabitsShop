/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.classinterface;

import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.SanPhamChiTietRespose;
import java.util.ArrayList;

/**
 *
 * @author ThanhDat
 */
public interface SanPhamCTInf {
    
    ArrayList<SanPhamChiTiet>getAll();
    
//     int AddSP(SanPhamChiTiet spct);

    int updateSP(SanPhamChiTietRespose spct, String ma);
    
    int xoaSP(String ma);
}
