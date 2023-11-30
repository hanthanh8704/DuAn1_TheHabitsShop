/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.reponsitory.HoaDonTraHangCTRepon;

/**
 *
 * @author ThanhDat
 */
public class GetIdSPCTService {

    public HoaDonTraHangCTRepon hdthctService = new HoaDonTraHangCTRepon();

    public String getIdSanPhamChiTiet(String ma) {
        return hdthctService.getidSPCT(ma);
    }
}
