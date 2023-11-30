/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.raven.classinterface.GenMaTuDong;
import com.raven.classmodel.ChatLieu;
import com.raven.classmodel.DayGiay;
import com.raven.classmodel.DeGiay;
import com.raven.classmodel.KichCo;
import com.raven.classmodel.KieuDang;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanHieu;
import com.raven.classmodel.SanPham;
import com.raven.reponsitory.ChatLieuRepository;
import com.raven.reponsitory.DayGiayRepository;
import com.raven.reponsitory.DeGiayRepository;
import com.raven.reponsitory.KichCoRepository;
import com.raven.reponsitory.KieuDangRespository;
import com.raven.reponsitory.MauSacRepository;
import com.raven.reponsitory.NhanHieuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThanhDat
 */
public class ThemNhanThuocTinh extends javax.swing.JDialog {

    private MauSacRepository ms = new MauSacRepository();
    private List<MauSac> listMS = new ArrayList<>();
    private ChatLieuRepository cl = new ChatLieuRepository();
    private List<ChatLieu> listCL = new ArrayList<>();
    private DeGiayRepository dg = new DeGiayRepository();
    private List<DeGiay> listDG = new ArrayList<>();
    private KichCoRepository kc = new KichCoRepository();
    private List<KichCo> listKC = new ArrayList<>();
    private DayGiayRepository day = new DayGiayRepository();
    private List<DayGiay> listDay = new ArrayList<>();
    private KieuDangRespository kg = new KieuDangRespository();
    private List<KieuDang> listKG = new ArrayList<>();
    private NhanHieuRepository nh = new NhanHieuRepository();
    private List<NhanHieu> listNH = new ArrayList<>();
    private int index = -1;
    public static String tenThuocTinh;
    private List<SanPham> ListSP = new ArrayList<>();
    SanPhamView newsP = new SanPhamView();

    /**
     * Creates new form ThemNhanThuocTinh
     */
    public ThemNhanThuocTinh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Thêm Nhanh Thuộc Tính");
        setLocationRelativeTo(null);
//        switch (ThemNhanThuocTinh.tenThuocTinh) {
//            case ("Mausac"):
//                rbMauSac.setSelected(true);
//                //   loadTableAll(ms.getAll());
//
//                break;
//            case ("Kichco"):
//                rbKichC.setSelected(true);
//                //     loadTableKC(kc.getAll());
//                break;
//            case ("Nhanhieu"):
//                rbNhanHieu.setSelected(true);
//                //        loadTableNH(nh.getAll());
//                break;
//
//            case ("Chatlieu"):
//                rbChatLieu.setSelected(true);
//                //   loadTableCL(cl.getAll());
//                break;
//
//            case ("Degiay"):
//                rbDeGiay.setSelected(true);
//                //  loadTableDG(dg.getAll());
//                break;
//
//            case ("Daygiay"):
//                rbDayGiay.setSelected(true);
//                //    loadTableD(day.getAll());
//                break;
//
//            case ("Kieudang"):
//                rbKieuDang.setSelected(true);
//                // loadTableKG(kg.getAll());
//                break;
//
//            default:
//                throw new AssertionError();
//        }
//        listMS = ms.getAll();
//        listCL = cl.getAll();
//        listDG = dg.getAll();
//        listKC = kc.getAll();
//        listDay = day.getAll();
//        listNH = nh.getAll();
//        listKG = kg.getAll();
////        listSPCTRE.add(spctre);
//        loadTableKC(listKC);
//        loadTableDG(listDG);
//
//        loadTableAll(listMS);
//        loadTableD(listDay);
//        loadTableNH(listNH);
//        loadTableKG(listKG);
    }

//    ///////////////-------------------------MauSac-------------------------//////////
//    void loadTableAll(List<MauSac> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (MauSac m : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                m.getMaMauSac(), m.getTenMauSac()
//            });
//        }
//    }
//    void showDataMauSac(int index) {
//        if (rbMauSac.isSelected()) {
//            MauSac mauSac = ms.getAll().get(index);
//            //
//            txtTenThuocTinh.setText(mauSac.getTenMauSac());
//        } else if (rbChatLieu.isSelected()) {
//            ChatLieu c = cl.getAll().get(index);
//            //     txtMaThuocTinh.setText(c.getMa());
//            txtTenThuocTinh.setText(c.getTen());
//        } else if (rbDeGiay.isSelected()) {
//            DeGiay d = dg.getAll().get(index);
//            //   txtMaThuocTinh.setText(d.getMa());
//            txtTenThuocTinh.setText(d.getTen());
//        } else if (rbKichC.isSelected()) {
//            KichCo c = kc.getAll().get(index);
//            //    txtMaThuocTinh.setText(c.getMaKichCo());
//            txtTenThuocTinh.setText(c.getTenKichCo());
//        } else if (rbDayGiay.isSelected()) {
//            DayGiay c = day.getAll().get(index);
//            //     txtMaThuocTinh.setText(c.getMa());
//            txtTenThuocTinh.setText(c.getTen());
//        } else if (rbKieuDang.isSelected()) {
//            KieuDang c = kg.getAll().get(index);
//            //   txtMaThuocTinh.setText(c.getMa());
//            txtTenThuocTinh.setText(c.getTen());
//        } else if (rbNhanHieu.isSelected()) {
//            NhanHieu c = nh.getAll().get(index);
//            //   txtMaThuocTinh.setText(c.getMa());
//            txtTenThuocTinh.setText(c.getTen());
//        }
//
//    }
    boolean validateThuoctiinh() {
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        if (txtTenThuocTinh.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Không được điền lớn hơn 50 ký tự");
            return false;
        }
//        if (rbKichC.isSelected()) {
//            try {
//                // Kiểm tra nếu là thuộc tính "Kích Cỡ" thì phải là số
//                int kichCo = Integer.parseInt(txtTenThuocTinh.getText());
//                if (kichCo < 0) {
//                    JOptionPane.showMessageDialog(this, "Kích cỡ phải lớn hơn 0!");
//                    return false;
//                }
//
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Giá trị của thuộc tính 'Kích Cỡ' phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//        }
        return true;
    }

    MauSac readFormMS() {
//        String maTuDong = "MS";
//        int soMaTuDong = tblTTSP.getRowCount() + 1;
//        maTuDong = maTuDong + soMaTuDong;
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "MS" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new MauSac(maSp, txtTenThuocTinh.getText());
    }
    ///////////////-------------------------ChatLieu-------------------------//////////

//    void loadTableCL(List<ChatLieu> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (ChatLieu m : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                m.getMa(), m.getTen()
//            });
//        }
//    }
    ChatLieu readFormCL() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "CL" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new ChatLieu(maSp, txtTenThuocTinh.getText());
    }

//    ///////////////-------------------------DeGiay-------------------------//////////
//    void loadTableDG(List<DeGiay> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (DeGiay d : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                d.getMa(), d.getTen()
//            });
//        }
//    }
    DeGiay readFormDG() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "DG" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new DeGiay(maSp, txtTenThuocTinh.getText());
    }

//    ///////////////-------------------------KichCo-------------------------//////////
//    void loadTableKC(List<KichCo> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (KichCo d : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                d.getMaKichCo(), d.getTenKichCo()
//            });
//        }
//    }
    KichCo readFormKC() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "KC" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new KichCo(maSp, txtTenThuocTinh.getText());
    }

//    ///////////////-------------------------DayGiay-------------------------//////////
//    void loadTableD(List<DayGiay> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (DayGiay d : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                d.getMa(), d.getTen()
//            });
//        }
//    }
    DayGiay readFormDayGiay() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "DG_" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new DayGiay(maSp, txtTenThuocTinh.getText());
    }

//    ///////////////-------------------------KieuDang-------------------------//////////
//    void loadTableKG(List<KieuDang> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (KieuDang d : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                d.getMa(), d.getTen()
//            });
//        }
//    }
    KieuDang readFormKG() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "KG" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new KieuDang(maSp, txtTenThuocTinh.getText());
    }
//
//    ///////////////-------------------------NhanHieu-------------------------//////////
//    void loadTableNH(List<NhanHieu> ms) {
//        DefaultTableModel tbModel = (DefaultTableModel) tblTTSP.getModel();
//        int STT = 1;
//        tbModel.setRowCount(0);
//        for (NhanHieu d : ms) {
//            tbModel.addRow(new Object[]{
//                STT++,
//                d.getMa(), d.getTen()
//            });
//        }
//    }

    NhanHieu readFormNH() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);

                return "NH" + gen;
            }
        };
        String maSp = genTD.maTuDong();
        return new NhanHieu(maSp, txtTenThuocTinh.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jButton24 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setText("Tên Thuộc Tính");

        txtTenThuocTinh.setBorder(null);

        jSeparator5.setForeground(new java.awt.Color(102, 102, 255));

        jButton24.setBackground(new java.awt.Color(153, 204, 255));
        jButton24.setText("Thêm");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 127, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton24)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if (validateThuoctiinh()) {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?", "thêm", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                switch (ThemNhanThuocTinh.tenThuocTinh) {
                    case "Mausac":
                        ms.AddMS(readFormMS());
                        listMS = ms.getAll();
                        newsP.setComBoBoxTenMauSac();
                        newsP.loadTableAll(listMS);
                        break;
                    case "Chatlieu":
                        cl.AddMS(readFormCL());
                        listCL = cl.getAll();
                        break;
                    case "Daygiay":
                        day.AddDay(readFormDayGiay());
                        listDay = day.getAll();
                        break;
                    case "Kichco":
                        kc.AddKC(readFormKC());
                        listKC = kc.getAll();
                        break;
                    case "Nhanhieu":
                        nh.AddNH(readFormNH());
                        listNH = nh.getAll();
                        newsP.setComBoBoxTenMauSac();
                        newsP.loadTableNH(listNH);
                        break;
                    case "Degiay":
                        dg.AddDG(readFormDG());
                        listDG = dg.getAll();
                        break;
                    case "Kieudang":
                        kg.AddKG(readFormKG());
                        listKG = kg.getAll();
                        break;
                }

                JOptionPane.showMessageDialog(this, "Thêm thành công");

                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemNhanThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemNhanThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemNhanThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemNhanThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemNhanThuocTinh dialog = new ThemNhanThuocTinh(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton24;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}
