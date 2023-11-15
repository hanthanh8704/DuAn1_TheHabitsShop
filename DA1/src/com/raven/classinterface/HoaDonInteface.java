/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.classinterface;

import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public interface HoaDonInteface {

    List<HoaDon> getAll();

    List<String> getListMa();

    String getTenKHById(UUID id);

    String getMucGiamById(UUID id);

    String getTenNVKHById(UUID id);

    List<HoaDonChiTiet> getListById(UUID id);

    List<HoaDon> getListByHTTT(UUID id);

    List<HoaDon> getListByMa(String ma);

    List<HoaDon> getListByID(UUID id);

}
