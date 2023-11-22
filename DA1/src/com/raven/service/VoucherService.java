/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.classinterface.TheHabitShop;
import com.raven.classmodel.Voucher;
import com.raven.reponsitory.DBConnect;
import com.toedter.calendar.JDateChooser;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
/**
 *
 * @author Ninh Than Thanh
 */
public class VoucherService implements TheHabitShop<Voucher, String>{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String sql = null;
    @Override
    public List<Voucher> getAll() {
        sql = "Select ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher order by ma desc";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> getAll1(int trangthai) {
        sql = "select ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai from Voucher where trangthai = ? order by ma desc";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> loadData1(long trang,int trangthai){
        sql = "select top 5 ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*5-5)+" ma FROM Voucher) and trangthai =? order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher sv = new Voucher();
                  sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                  list.add(sv);
                }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> findcbb(String loaiGiam) {
        sql = "select ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai from Voucher where loaigiamgia like ?";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, loaiGiam);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> loadData(long trang){
        sql = "select top 5 ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai \n" +
"FROM Voucher WHERE ma NOT IN (SELECT TOP "+(trang*5-5)+" ma FROM Voucher) order by ma desc";// WHERE HOTEN NOT IN (SELECT TOP "+(trang*5-5)+" FROM STUDENTS)
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher();
                  sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getIdVoucher(String id) {
        String idVoucher = " ";
        sql = "select id from Voucher where ma=? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
               idVoucher = rs.getString(1);
            }
            return idVoucher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> find(JDateChooser d1, JDateChooser d2) {
        sql = "select ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai from Voucher where ten between  ? and ? ";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            if(d1.getDate()!=null&& d2.getDate()!=null){
            ps.setDate(1, new java.sql.Date(d1.getDate().getTime()));
            ps.setDate(2, new java.sql.Date(d2.getDate().getTime()));
            }
            rs = ps.executeQuery();
            while(rs.next()){
                com.raven.classmodel.Voucher sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Voucher entity) {
        sql="INSERT INTO Voucher(ma,ten,giatrimax,loaigiamgia, giatrimin, soluong, ngaybatdau, ngayketthuc,trangthai,ngaytao,id_nhanvien,id_hoadon) VALUES\n" +
"(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMa());
            ps.setObject(2, entity.getTen());
            ps.setObject(3, entity.getGiatrimax());
            ps.setObject(4, entity.getLoaiGiamGia());
            ps.setObject(5, entity.getGiatrimin());
            ps.setObject(6, entity.getSoLuong());
            ps.setObject(7, entity.getNgayBatDau());
            ps.setObject(8, entity.getNgayKetThuc());
            ps.setObject(9, entity.getTrangThai());
            ps.setObject(10, entity.getNgayTao());
            ps.setObject(11, UUID.fromString(entity.getId_nv()));
            ps.setObject(12, UUID.fromString(entity.getId_hd()));
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Voucher entity, String id) {
        sql="UPDATE Voucher SET ten=?,giatrimax=?,loaigiamgia=?,giatrimin=?,soluong=?,ngaybatdau=?,ngayketthuc=?,trangthai =?,ngaytao=? WHERE ma =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTen());
            ps.setObject(2, entity.getGiatrimax());
            ps.setObject(3, entity.getLoaiGiamGia());
            ps.setObject(4, entity.getGiatrimin());
            ps.setObject(5, entity.getSoLuong());
            ps.setObject(6, entity.getNgayBatDau());
            ps.setObject(7, entity.getNgayKetThuc());
            ps.setObject(8, entity.getTrangThai());
            ps.setObject(9, entity.getNgayTao());
            ps.setObject(10, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int update1(Voucher entity, String ma) {
        sql="UPDATE Voucher SET trangthai =? where ma =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getTrangThai());
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int update2(Voucher entity, String ma) {
        sql="UPDATE Voucher SET ngaybatdau =ngaytao, ngayketthuc = ngaytao, trangthai=3 where ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
     sql="DELETE FROM Voucher WHERE id = ?\n"
             + "delete from Voucher_KhachHang where id_voucher=?";
        try {
            con = DBConnect.getConnection();
            ps= con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Voucher getID(String id) {
     com.raven.classmodel.Voucher sv = null;
        sql="select ma,ten,giatrimax,loaigiamgia,giatrimin, soluong, ngaybatdau, ngayketthuc, trangthai from Voucher where ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs =ps.executeQuery();
            while(rs.next()){
                  sv = new com.raven.classmodel.Voucher(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getString(4),rs.getBigDecimal(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
                }
            return sv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Voucher> getSql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}
