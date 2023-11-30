/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

/**
 *
 * @author ADMIN
 */
public class XLogin {
    
    public static NhanVien user = null;

    /*
        Xóa thông tin người sử dụng sau khi đăng xuất
     */
    public static void clear() {
        XLogin.user = null;
    }

    public static boolean isLogin() {
        return XLogin.user != null;
    }

    public static boolean isManager() {
        return XLogin.isLogin() && XLogin.user.getId_chucVu().getTenCV().equals("Quản Lý");
    }
}
