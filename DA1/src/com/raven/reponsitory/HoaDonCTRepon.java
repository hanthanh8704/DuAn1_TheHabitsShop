/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;
import com.raven.classmodel.HoaDonChiTiet;
import java.sql.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class HoaDonCTRepon {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<HoaDonChiTiet> getAll(){
        String sql = "SELECT SanPhamChiTiet.STT, SanPhamChiTiet.ma,\n" +
                    "SanPhamChiTiet.id_mausac, MauSac.id AS 'ID_MauSac', MauSac.ten AS 'Ten_MauSac', \n" +
                    "SanPhamChiTiet.id_chatlieu, ChatLieu.id AS 'ID_ChatLieu', ChatLieu.ten AS 'Ten_ChatLieu',\n" +
                    "SanPhamChiTiet.id_kichco, KichCo.id AS 'ID_KichCo', KichCo.ten AS 'Ten_KichCo', \n" +
                    "SanPhamChiTiet.id_nhanhieu, NhanHieu.id AS 'ID_NhanHieu', NhanHieu.ten AS 'Ten_NhanHieu',\n" +
                    "SanPhamChiTiet.id_sanpham,SanPham.id AS 'ID_SanPham', SanPham.ten AS 'Ten_SanPham',\n" +
                    "SanPhamChiTiet.id_degiay,DeGiay.id AS 'ID_DeGiay', DeGiay.ten AS 'Ten_DeGiay',\n" +
                    "SanPhamChiTiet.id_daygiay,DayGiay.id AS 'ID_DayGiay', DayGiay.ten AS 'Ten_DayGiay',\n" +
                    "SanPhamChiTiet.id_anh,HinhAnh.id AS 'ID_HinhAnh', HinhAnh.ten AS 'Ten_HinhAnh',\n" +
                    "SanPhamChiTiet.id_kieudang,KieuDang.id AS 'ID_KieuDang', KieuDang.ten AS 'Ten_KieuDang'\n" +
                    "FROM SanPhamChiTiet\n" +
                    "JOIN MauSac ON MauSac.id = SanPhamChiTiet.id_mausac\n" +
                    "JOIN ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu\n" +
                    "JOIN KichCo ON KichCo.id = SanPhamChiTiet.id_kichco\n" +
                    "JOIN NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu\n" +
                    "JOIN SanPham ON SanPham.id = SanPhamChiTiet.id_sanpham\n" +
                    "JOIN DeGiay ON DeGiay.id = SanPhamChiTiet.id_degiay\n" +
                    "JOIN DayGiay ON DayGiay.id = SanPhamChiTiet.id_daygiay\n" +
                    "JOIN HinhAnh ON HinhAnh.id = SanPhamChiTiet.id_anh\n" +
                    "JOIN KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang;";
        try {
            
        } catch (Exception e) {
        }
        return null;
    }
}
