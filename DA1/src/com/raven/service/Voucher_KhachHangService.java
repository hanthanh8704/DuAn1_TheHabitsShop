/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import com.raven.classinterface.TheHabitShop;
import com.raven.classmodel.Voucher;
import com.raven.classmodel.Voucher_KhachHang;
import com.raven.reponsitory.DBConnect;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.*;

/**
 *
 * @author Ninh Than Thanh
 */
public class Voucher_KhachHangService implements TheHabitShop<Voucher_KhachHang, String>{
Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String sql = null;
    @Override
    public List<Voucher_KhachHang> getAll() {
    sql = "Select vc.id,vc.ma,vc.ten,mucgiamgia, soluong, ngaybatdau, ngayketthuc, vc.trangthai,vc.ngaytao \n" +
"from Voucher vc join Voucher_KhachHang VH on vc.id =id_voucher\n" +
"join KhachHang kh on kh.id = vh.id_khachhang\n" +
"join NhanVien nv on nv.id = vc.id_nhanvien\n" +
"join HoaDon hd on hd.id = vc.id_hoadon";
        List<Voucher> list  = new ArrayList<>();
        try {
            con= DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher_KhachHang sv = new Voucher_KhachHang(UUID.fromString(rs.getString(1)),rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getDate(9));
                list.add(sv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }

    @Override
    public int insert(Voucher_KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Voucher_KhachHang entity, String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Voucher_KhachHang getID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Voucher_KhachHang> getSql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
