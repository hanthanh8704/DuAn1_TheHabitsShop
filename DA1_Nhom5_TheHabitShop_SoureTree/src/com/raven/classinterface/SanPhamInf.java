/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.classinterface;

import com.raven.classmodel.SanPham;
import java.util.ArrayList;

/**
 *
 * @author ThanhDat
 */
public interface SanPhamInf {

    ArrayList<com.raven.classmodel.SanPham> getAll();

    int AddSP(SanPham sp);

    int updateSP(SanPham sp, String ma);

}
