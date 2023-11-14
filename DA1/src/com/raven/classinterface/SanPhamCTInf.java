/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.classinterface;

import com.raven.classmodel.SanPhamChiTiet;
import java.util.ArrayList;

/**
 *
 * @author ThanhDat
 */
public interface SanPhamCTInf {
    
    ArrayList<SanPhamChiTiet>getAll();
    
     int AddSP(SanPhamChiTiet sp);

    int updateSP(SanPhamChiTiet sp, String ma);
    
    int xoaSP(String ma);
}
