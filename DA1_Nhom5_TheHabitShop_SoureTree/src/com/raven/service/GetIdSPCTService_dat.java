/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.classmodel.SanPhamChiTietRespose;
import com.raven.reponsitory.SanPhamChiTietRepository;

/**
 *
 * @author ThanhDat
 */
public class GetIdSPCTService_dat {

    public SanPhamChiTietRepository spct = new SanPhamChiTietRepository();

    public String getIDMauSac(String mauSac) {
        return spct.getidMauSac(mauSac);
    }

    public String getIDChatLieu(String chatlieu) {
        return spct.getidChatLieuc(chatlieu);
    }

    public String getIDKichCo(String kichco) {
        return spct.getiKichCo(kichco);
    }

    public String getIDNhanHieu(String nhanhieu) {
        return spct.getidNhanHieu(nhanhieu);
    }

    public String getIDDeGiay(String degiay) {
        return spct.getidDeGiay(degiay);
    }

    public String getIDDayGiay(String daygiay) {
        return spct.getidDayGiay(daygiay);
    }

    public String getIDKieuDang(String kieudang) {
        return spct.getidKieuDang(kieudang);
    }

    public String getIDSanPham(String sanpham) {
        return spct.getidSanPham(sanpham);
    }
}
