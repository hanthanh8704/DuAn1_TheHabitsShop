/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.reponsitory;


import com.raven.classmodel.SanPhamChiTiet;
import java.util.ArrayList;
import java.sql.*;
import com.raven.classinterface.SanPhamCTInf;
/**
 *
 * @author ThanhDat
 */
public class SanPhamChiTietRepository implements SanPhamCTInf{

    @Override
    public ArrayList<SanPhamChiTiet> getAll() {
      String sql = """
                  SELECT  SanPhamChiTiet.[id]
                            ,[stt]
                            ,SanPhamChiTiet.[ma]
                      	  ,SanPham.ten[tenSP]
                            ,[soluong]
                            ,[trangthai]
                            ,[ghichu]
                            ,mausac.ten[tenMauSac]
                            ,ChatLieu.ten[tenChatLieu]
                            ,KichCo.ten[tenKichCo]
                            ,NhanHieu.ten[tenNhanHieu]
                            ,[id_sanpham]
                            ,DeGiay.ten[tenDeGiay]
                            ,DayGiay.ten[tenDayGiay]
                            ,[id_anh]
                            ,KieuDang.ten[tenKieuDang]
                            ,[id_nhanvien]
                            ,[id_khachhang]
                            ,SanPhamChiTiet.[id_nguoitao]
                            ,SanPhamChiTiet.[ngaytao]
                        FROM [DA1_TheHabitsShop].[dbo].[SanPhamChiTiet] 
                        join SanPham on SanPham.id = SanPhamChiTiet.id_sanpham
                        join MauSac on MauSac.id = SanPhamChiTiet.id_mausac
                        JOIN 
                          ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
                      JOIN 
                          KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
                      JOIN 
                          NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
                      JOIN 
                          Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
                      JOIN 
                          Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
                      JOIN 
                          KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang;
                     
                  
                     """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ArrayList<SanPhamChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet sp = new SanPhamChiTiet(
                        rs.getString("ma"),rs.getString("tenSP"), rs.getInt("soluong"), 
                        rs.getString("tenMauSac"),
                        rs.getString("tenChatLieu"), 
                        rs.getString("tenKichCo"), 
                        rs.getString("tenNhanHieu"), 
                        rs.getString("tenDeGiay"),
                        rs.getString("tenDayGiay"),
                        rs.getString("tenKieuDang"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int AddSP(SanPhamChiTiet sp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int updateSP(SanPhamChiTiet sp, String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoaSP(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
