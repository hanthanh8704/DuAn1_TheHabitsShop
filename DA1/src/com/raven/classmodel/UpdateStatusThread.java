/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import com.raven.service.VoucherService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Ninh Than Thanh
 */
public class UpdateStatusThread extends Thread {
    private VoucherService service = new VoucherService();
//    private Thread t;
//    private Date ngayHienTai;
//    UpdateStatusThread(Date name) {
//        ngayHienTai = name;
//    }
    com.raven.form.Voucher vc = new com.raven.form.Voucher();
    @Override
    public void run() {
//        Timer timer = new Timer(100, e -> {
            while(true){
                // Cập nhật dòng trạng thái tự động
                updateTrangThai();
            
        }
//        });
        // Bắt đầu Timer
//        timer.start();
    }
    public void updateTrangThai() {
        vc.index = vc.tblVoicherAll.getSelectedRow();
        com.raven.classmodel.Voucher nv = this.readForm1();
        for (vc.index = 0; vc.index < vc.tblVoicherAll.getRowCount(); vc.index++) {
            String ma = vc.tblVoicherAll.getValueAt((vc.index), 1).toString();

            if (service.update1(nv, ma) > 0) {
//                JOptionPane.showMessageDialog(this, "Sửa thành công");
                vc.fillTable(service.getAll());
            }
        }
        vc.fillTable1(service.getAll1(1));
        vc.fillTable2(service.getAll1(2));
        vc.fillTable3(service.getAll1(3));

    }
    private com.raven.classmodel.Voucher readForm1() {
        int trangthai = 2;
        return new com.raven.classmodel.Voucher(trangthai);
    }

    private com.raven.classmodel.Voucher readForm2() {
        int trangthai = 3;
        return new com.raven.classmodel.Voucher(trangthai);
    }
}