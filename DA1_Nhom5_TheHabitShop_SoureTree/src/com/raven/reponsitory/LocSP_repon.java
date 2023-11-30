///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.raven.reponsitory;
//
//import com.raven.classinterface.SanPhamCTKM_Inf;
//import com.raven.classmodel.SanPhamCTKM_Dang;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.ArrayList;
//
///**
// *
// * @author ACER
// */
//public class LocSP_repon implements SanPhamCTKM_Inf {
//
//    Connection con = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;
//    String sql = null;
//
////    public ArrayList<SanPhamCTKM> getList (int ma){
////        ArrayList<SanPhamCTKM> SPList = new ArrayList();
////        sql = " \"\"\"\n" +
////"                  SELECT                           \n" +
////"                            SanPhamChiTiet.[ma]\n" +
////"                      	  ,SanPham.ten[tenSP]\n" +
////"                              ,[gia]  \n" +
////"                            ,mausac.ten[tenMauSac]\n" +
////"                            ,ChatLieu.ten[tenChatLieu]\n" +
////"                            ,KichCo.ten[tenKichCo]\n" +
////"                            ,NhanHieu.ten[tenNhanHieu]\n" +
////"                            ,DeGiay.ten[tenDeGiay]\n" +
////"                        FROM [DA1_TheHabitsShop].[dbo].[SanPhamChiTiet] \n" +
////"                       join SanPham on SanPham.id = SanPhamChiTiet.id_sanpham\n" +
////"                        join MauSac on MauSac.id = SanPhamChiTiet.id_mausac\n" +
////"                        JOIN \n" +
////"                          ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu\n" +
////"                      JOIN \n" +
////"                          KichCo ON KichCo.id = SanPhamChiTiet.id_kichco\n" +
////"                      JOIN \n" +
////"                          NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu\n" +
////"                      JOIN \n" +
////"                          Degiay ON Degiay.id = SanPhamChiTiet.id_degiay\n" +
////"                     \"\"\"";
////        try{
////            con = DBconnect1.getConnection();
////            ps = con.prepareStatement(sql);
////            ps.setInt(1,ma);
////            ps.execute();
////            rs = ps.executeQuery();
////            while(rs.next()){
////                String masp = rs.getString("ma");
////                String ten = rs.getString("tenSP");
////                BigDecimal gia = rs.getBigDecimal("gia");
////                String tenMS = rs.getString("tenMauSac");
////                String tenChatlieu = rs.getString("tenChatLieu");
////                String tenKC = rs.getString("tenKichCo");
////                String tenNhanHieu = rs.getString("tenNhanHieu");
////                String tenDeGiay = rs.getString("tenDeGiay");
////                
////                
////                
////            }
////        }catch(Exception e){
////            e.printStackTrace();
////            return null;
////        }
////    }
//    
//    
//    @Override
//    public ArrayList<SanPhamCTKM_Dang> getAll() {
//        sql = """
//                  SELECT                           
//                            SanPhamChiTiet.[ma]
//                      	  ,SanPham.ten[tenSP]
//                              ,[gia]  
//                            ,mausac.ten[tenMauSac]
//                            ,ChatLieu.ten[tenChatLieu]
//                            ,KichCo.ten[tenKichCo]
//                            ,NhanHieu.ten[tenNhanHieu]
//                            ,DeGiay.ten[tenDeGiay]
//                        FROM [DA1_TheHabitsShop].[dbo].[SanPhamChiTiet] 
//                       join SanPham on SanPham.id = SanPhamChiTiet.id_sanpham
//                        join MauSac on MauSac.id = SanPhamChiTiet.id_mausac
//                        JOIN 
//                          ChatLieu ON ChatLieu.id = SanPhamChiTiet.id_chatlieu
//                      JOIN 
//                          KichCo ON KichCo.id = SanPhamChiTiet.id_kichco
//                      JOIN 
//                          NhanHieu ON NhanHieu.id = SanPhamChiTiet.id_nhanhieu
//                      JOIN 
//                          Degiay ON Degiay.id = SanPhamChiTiet.id_degiay
//                     """;
//        ArrayList<SanPhamCTKM_Dang> list = new ArrayList<>();
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPhamCTKM_Dang sp = new SanPhamCTKM_Dang(rs.getString("ma"),
//                         rs.getString("tenSP"),
//                        rs.getBigDecimal("gia"),
//                        rs.getString("tenMauSac"),
//                        rs.getString("tenChatLieu"),
//                        rs.getString("tenKichCo"),
//                        rs.getString("tenNhanHieu"),
//                        rs.getString("tenDeGiay"));
//                list.add(sp);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public int AddSP(SanPhamCTKM_Dang sp) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
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
//}
