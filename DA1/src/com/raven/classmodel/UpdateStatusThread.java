/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Ninh Than Thanh
 */
public class UpdateStatusThread extends Thread {
    private Thread t;
//    private Date ngayHienTai;
//    UpdateStatusThread(Date name) {
//        ngayHienTai = name;
//    }
    com.raven.form.Voucher vc = new com.raven.form.Voucher();
    @Override
    public void run() {
        Timer timer = new Timer(100, e -> {
            try {
                // Cập nhật dòng trạng thái tự động
                vc.updateTrangThai();
                vc.updateTrangThai1();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateStatusThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // Bắt đầu Timer
        timer.start();
    }
}