///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.raven.reponsitory;
//
//
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.sql.*;
//import com.raven.classinterface.SanPhamCTKM_Inf;
//import com.raven.classmodel.SanPhamCTKM_Dang;
///**
// *
// * @author ThanhDat
// */
//public class SanPhamChiTiet_service implements SanPhamCTKM_Inf{
//
//    @Override
//    public ArrayList<SanPhamCTKM_Dang> getAll() {
//      String sql = """
//                  SELECT  SanPhamChiTiet.[id]                           
//                            ,SanPhamChiTiet.[ma]
//                      	  ,SanPham.ten[tenSP]
//                            ,[soluong]
//                            ,[trangthai]
//                              ,[gia]  
//                            ,[ghichu]
//                            ,mausac.ten[tenMauSac]
//                            ,ChatLieu.ten[tenChatLieu]
//                            ,KichCo.ten[tenKichCo]
//                            ,NhanHieu.ten[tenNhanHieu]
//                            ,[id_sanpham]
//                            ,DeGiay.ten[tenDeGiay]
//                            ,DayGiay.ten[tenDayGiay]
//                            ,[id_anh]
//                            ,KieuDang.ten[tenKieuDang]
//                            ,[id_nhanvien]
//                            ,[id_khachhang]
//                            ,SanPhamChiTiet.[id_nguoitao]
//                            ,SanPhamChiTiet.[ngaytao]
//                        FROM [DA1_TheHabitsShop].[dbo].[SanPhamChiTiet] 
//                        join SanPham on SanPham.id = SanPhamChiTiet.id_sanpham
//                        join MauSac on MauSac.id = SanPhamChiTiet.id_mausac
//                        JOIN 
//                          ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
//                      JOIN 
//                          KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
//                      JOIN 
//                          NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
//                      JOIN 
//                          Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
//                      JOIN 
//                          Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
//                      JOIN 
//                          KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang;
//                     
//                  
//                     """;
//        try {
//            Connection con = DBConnect.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.execute();
//            ArrayList<SanPhamCTKM_Dang> list = new ArrayList<>();
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                SanPhamCTKM_Dang sp = new SanPhamCTKM_Dang( rs.getString("ma"),
//                        rs.getString("tenSP"), rs.getBigDecimal("gia"),
//                        rs.getString("tenMauSac"), rs.getString("tenChatLieu"));
//                list.add(sp);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int AddSP(SanPhamCTKM_Dang sp) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//  public ArrayList<SanPhamCTKM_Dang> getALl2(String ten) {
//      String sql = """
//                  SELECT  SanPhamChiTiet.[id]                           
//                            ,SanPhamChiTiet.[ma]
//                      	  ,SanPham.ten[tenSP]
//                            ,[soluong]
//                            ,[trangthai]
//                              ,[gia]  
//                            ,[ghichu]
//                            ,mausac.ten[tenMauSac]
//                            ,ChatLieu.ten[tenChatLieu]
//                            ,KichCo.ten[tenKichCo]
//                            ,NhanHieu.ten[tenNhanHieu]
//                            ,[id_sanpham]
//                            ,DeGiay.ten[tenDeGiay]
//                            ,DayGiay.ten[tenDayGiay]
//                            ,[id_anh]
//                            ,KieuDang.ten[tenKieuDang]
//                            ,[id_nhanvien]
//                            ,[id_khachhang]
//                            ,SanPhamChiTiet.[id_nguoitao]
//                            ,SanPhamChiTiet.[ngaytao]
//                        FROM [DA1_TheHabitsShop].[dbo].[SanPhamChiTiet] 
//                        join SanPham on SanPham.id = SanPhamChiTiet.id_sanpham
//                        join MauSac on MauSac.id = SanPhamChiTiet.id_mausac
//                        JOIN 
//                          ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
//                      JOIN 
//                          KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
//                      JOIN 
//                          NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
//                      JOIN 
//                          Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
//                      JOIN 
//                          Daygiay ON Daygiay.id = SanPhamChiTiet.id_daygiay
//                      JOIN 
//                          KieuDang ON KieuDang.id = SanPhamChiTiet.id_kieudang
//                 
//                   where SanPham.ten like ?
//
//                     """;
//        try {
//            ArrayList<SanPhamCTKM_Dang> list = new ArrayList<>();
//            Connection con = DBConnect.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setObject(1, ten);
//            ps.execute();
//            
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                SanPhamCTKM_Dang sp = new SanPhamCTKM_Dang( rs.getString("ma"),
//                        rs.getString("tenSP"), rs.getBigDecimal("gia"),
//                        rs.getString("tenMauSac"), rs.getString("tenChatLieu"));
//                list.add(sp);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    @Override
//    public int updateSP(SanPhamCTKM_Dang sp, String ma) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int xoaSP(String ma) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public int size() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public SanPhamCTKM_Dang get(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public void remove(SanPhamCTKM_Dang xx) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//}
