/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
/**
 *
 * @author Ninh Than Thanh com.raven.form.Voucher
 */
public class SwingStatusExample extends JFrame{
    private JLabel statusLabel;

    public SwingStatusExample() {
        // Khởi tạo JFrame và JLabel
        super("vi du ve trang thai xoay");
        statusLabel = new JLabel("trang thai ban dau");
        add(statusLabel);

        // Tạo một Timer với khoảng thời gian cập nhật là 1000ms (1 giây)
        Timer timer = new Timer(1000, e -> {
            // Cập nhật dòng trạng thái tự động
            updateStatus();
        });

        // Bắt đầu Timer
        timer.start();
    }

    private void updateStatus() {
        // Thực hiện các thay đổi dòng trạng thái tự động ở đây
        // Ví dụ:
        statusLabel.setText("New Status: " + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingStatusExample example = new SwingStatusExample();
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setSize(300, 200);
            example.setVisible(true);
        });
    }

}
