/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.classinterface.TaoMaTuSinh_inf;
import com.raven.classmodel.GioHang;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.XLogin;
import com.raven.reponsitory.BanHangReponsitory;
import com.raven.service.getidNhanVienService;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ADMIN
 */
public class BanHangForm extends javax.swing.JPanel {

    private BanHangReponsitory reponBanHang = new BanHangReponsitory();
    private List<HoaDon> listHD = new ArrayList<>();
    private int index = -1;
    public NhanVien nv = new NhanVien();
    public getidNhanVienService getid = new getidNhanVienService();
    private Map<UUID, HoaDonChiTiet> mapGioHang = new HashMap<>();
    private static List<GioHang> listGH = new ArrayList<>();
    private DefaultComboBoxModel<String> dcbHTTT = new DefaultComboBoxModel<>();
    // Call list
    List<SanPhamChiTiet> listMCTSP = new ArrayList<>();

    // tinhtrang = 0 =  "Chờ Thanh Toán"; 
    // tinhtrang = 1 = "Đã Thanh Toán";
    /**
     * Creates new form HoaDonForm
     */
    public BanHangForm() {
        initComponents();
        loadCBB();
        // Create a KeyStroke for the Enter key
        KeyStroke enterKey = KeyStroke.getKeyStroke("ENTER");
        // Create an Action to be performed when Enter key is pressed
        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action, for example, get the selected row
                int selectedRow = tblGioHang.getSelectedRow();
                if (selectedRow != -1) {
                    suasoluongsp();
                    tinhTongTien();
                }
            }
        };

        // Add the Enter key binding to the JTable
        tblGioHang.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enterKey, "Enter");
        tblGioHang.getActionMap().put("Enter", enterAction);
        loadTableSanPham(reponBanHang.getAllSP());
        loadTableHoaDon(reponBanHang.getAllHD());
    }

    public static JPanel createPanel() {
        BanHangForm panel = new BanHangForm();
        // Cấu hình JPanel
        return panel;
    }

    void loadTableHoaDonCT(List<HoaDonChiTiet> listHDCT) {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (HoaDonChiTiet h : listHDCT) {
            model.addRow(new Object[]{
                stt++,
                h.getMaspct(),
                h.getTensp(),
                h.getSoLuong(),
                NumberFormat.getInstance().format(h.getGia_SPCT()),
                h.getTongTienCuaHDCT()
            });
        }
    }

    void loadTableHoaDon(List<HoaDon> listHD) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        int stt = 1;
        String tinhtrang = "";
        for (HoaDon h : listHD) {
            if (h.getTinhTrang() == 0) {
                tinhtrang = "Chờ Thanh Toán";
            } else {
                tinhtrang = "Đã Thanh Toán";
            }
            model.addRow(new Object[]{
                stt++,
                h.getMa(),
                h.getTennhanvien(),
                date2String(h.getNgayTao()),
                tinhtrang
            });
        }
    }

    void loadTableSanPham(List<SanPhamChiTiet> listSPCT) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (SanPhamChiTiet s : listSPCT) {
            model.addRow(new Object[]{
                stt++,
                s.getMa(),
                s.getTenSanPham().getTenSP(),
                s.getTenMauSac().getTenMauSac(),
                s.getTenChatlieu().getTen(),
                s.getTenKichCo().getTenKichCo(),
                s.getTenNhanHieu().getTen(),
                s.getSoLuong(),
                s.getGiaBan1()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblQuetMa = new javax.swing.JLabel();
        cbb_TrangThai = new javax.swing.JComboBox<>();
        txt_TimKiem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnXoaSPGH = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        btnSuaSPTRONGGH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTienMat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbbHinhThuc = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTienCK = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SP", "Màu Sắc", "Chất Liệu", "Size", "Nhãn Hiệu", "SL SP tồn tại", "Giá Bán"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setMinWidth(50);
            tblSanPham.getColumnModel().getColumn(0).setMaxWidth(50);
            tblSanPham.getColumnModel().getColumn(8).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SP", "Số Lượng", "Giá Bán", "Thành Tiền", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Quét Mã Sản Phẩm"));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuetMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblQuetMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbb_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Tìm Kiếm");

        btnXoaSPGH.setText("Xóa Sản Phẩm");
        btnXoaSPGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPGHMouseClicked(evt);
            }
        });
        btnXoaSPGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPGHActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setText("Hủy Hóa Đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnSuaSPTRONGGH.setText("Sửa Số Lượng SP");
        btnSuaSPTRONGGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSPTRONGGHMouseClicked(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel6.setText("SDT");

        jLabel12.setText("Tên KH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT)
                    .addComponent(txtTenKH))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tên Nhân Viên", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMinWidth(50);
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đặt Hàng", jPanel6);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnThanhToan.setBackground(new java.awt.Color(51, 204, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");

        jLabel1.setText("Mã HD");

        jLabel2.setText("Ngày Tạo");

        jLabel3.setText("Tên NV");

        jLabel4.setText("Tổng Tiền");

        jLabel5.setText("Tiền Mặt ");

        txtMaHD.setEditable(false);

        txtNgayTao.setEditable(false);

        txtTen.setEditable(false);

        txtTongTien.setEditable(false);

        jLabel7.setText("Giảm Giá KM");

        txtGiamGia.setEditable(false);

        jLabel8.setText("Hình Thức");

        cbbHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Mới");

        jLabel9.setText("Tiền CK");

        jLabel11.setText("Tiền Thừa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtTen)
                            .addComponent(txtNgayTao)
                            .addComponent(txtMaHD)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbHinhThuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienMat)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(53, 53, 53)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienCK)
                            .addComponent(txtTienThua)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienCK, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnThanhToan))
                .addGap(4, 4, 4))
        );

        jTabbedPane1.addTab("Đơn Hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addComponent(cbb_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnXoaSPGH))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuaSPTRONGGH, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaSPTRONGGH)
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cbb_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaSPGH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addhoaDOnCT() {
        HoaDonChiTiet hdct = readFormHDCT();
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (tblHoaDon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn trước khi thêm sản phẩm");
        } else {
            int index = tblSanPham.getSelectedRow();
            HoaDonChiTiet hdct = readFormHDCT();
            if (reponBanHang.themSPGioHang(hdct) != 0) {
                System.out.println("Them thanh cong");
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(txtMaHD.getText()));
                loadTableSanPham(reponBanHang.getAllSP());
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    TaoMaTuSinh_inf genMaHDCT = new TaoMaTuSinh_inf() {
        @Override
        public String maTuSinh() {
            int maTuSinh = new Random().nextInt(1000000);
            return "HDCT" + maTuSinh;
        }
    };
    int soluong = 0;

    boolean checkValidateSoLuong() {
        try {
            soluong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng"));
            // Kiểm tra số lượng phải lớn hơn 0
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                return false;
            }

            // Kiểm tra số lượng nhập vào có lớn hơn số lượng trong JTable hay không
            int soLuongTrongBang = Integer.parseInt(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 7).toString());
            if (soluong > soLuongTrongBang) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng có sẵn!");
                return false;
            }
            // Nếu số lượng hợp lệ và đủ, trả về true
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương!");
            return false;
        }
    }

    HoaDonChiTiet readFormHDCT() {
        if (checkValidateSoLuong()) {
            int index = tblSanPham.getSelectedRow();
            String idHd = reponBanHang.selectIdHDByMaHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
            String idSPCT = reponBanHang.selectIDSPCTByMaSPCT(tblSanPham.getValueAt(index, 1).toString());
            String id_nguoitao = XLogin.user.getId().toString();

            System.out.println(idHd);
            System.out.println(idSPCT);
            System.out.println(id_nguoitao);

            return new HoaDonChiTiet(genMaHDCT.maTuSinh(),
                    soluong,
                    idHd,
                    idSPCT,
                    id_nguoitao,
                    0
            );
        } else {
            return null;
        }
    }


    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        txtMaHD.setText(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
        // String mahd = reponBanHang.selectIdHDByMaHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
        loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(txtMaHD.getText()));
        tinhTongTien();
        index = tblHoaDon.getSelectedRow();
        showDataHoaDon(this.index);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        // TODO add your handling code here:
        if (tblHoaDon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn dòng");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn không", "abc", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                if (tblGioHang.getRowCount() <= 0) {
                    String mahd = reponBanHang.selectIdHDByMaHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
                    reponBanHang.huyHoaDonKhongCoHang(mahd);
                    JOptionPane.showMessageDialog(this, "Hủy Thành Công");
                    loadTableHoaDon(reponBanHang.getAllHD());
                    System.out.println(mahd);
                } else {
                    for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                        String mahd = reponBanHang.selectIdHDByMaHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
                        String maspct = reponBanHang.selectIDSPCTByMaSPCT(tblGioHang.getValueAt(i, 1).toString());
                        String mahdct = reponBanHang.selecIDHoaDonCTByMaHoaDonAndMaSanPhamCT(mahd, maspct);
                        reponBanHang.huyHoaDon(mahd, mahdct, maspct);
                    }
                }
                loadTableHoaDon(reponBanHang.getAllHD());
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(""));
                loadTableSanPham(reponBanHang.getAllSP());
            }
        }
//        showDataHoaDon();
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnXoaSPGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPGHMouseClicked
        // TODO add your handling code here:
        //int index = tblGioHang.getSelectedRow();
        String mahd = tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString();
        System.out.println("mahd" + mahd);
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            if (null != tblGioHang.getValueAt(i, 6)) {
                String check = tblGioHang.getValueAt(i, 6).toString();

                if (check.endsWith("true")) {
                    String maspct = tblGioHang.getValueAt(i, 1).toString();
                    String mahdct = reponBanHang.selecIDHoaDonCTByMaHoaDonAndMaSanPhamCT(reponBanHang.selectIdHDByMaHD(mahd), reponBanHang.selectIDSPCTByMaSPCT(maspct));
                    reponBanHang.xoaHoaDonChiTiet(reponBanHang.selecIDHoaDonCTByMaHoaDonAndMaSanPhamCT(reponBanHang.selectIdHDByMaHD(mahd), reponBanHang.selectIDSPCTByMaSPCT(maspct)), reponBanHang.selectIDSPCTByMaSPCT(maspct));
                    System.out.println("id_spct : " + reponBanHang.selectIDSPCTByMaSPCT(maspct));
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Xoa thanh cong");
        loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
        loadTableSanPham(reponBanHang.getAllSP());
    }//GEN-LAST:event_btnXoaSPGHMouseClicked

    private void btnXoaSPGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPGHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPGHActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangMouseClicked

//        @mahdct UNIQUEIDENTIFIER,
//    @maspct UNIQUEIDENTIFIER,
//    @mahd UNIQUEIDENTIFIER,
//    @soluongthaydoi INT,
//    @soluongtronggiohang INT

    private void btnSuaSPTRONGGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSPTRONGGHMouseClicked
        suasoluongsp();
    }//GEN-LAST:event_btnSuaSPTRONGGHMouseClicked

    private void suasoluongsp() throws NumberFormatException, HeadlessException {
        // TODO add your handling code here:
        int i = tblGioHang.getSelectedRow();
        String mahd = tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString();   // lấy gt từ bảng
        //       for (int i = 0; i < tblGioHang.getRowCount(); i++) {
        String maspct = tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 1).toString(); // lấy gt từ bảng
        System.out.println(maspct + "hihi");
        String mahdct = reponBanHang.selecIDHoaDonCTByMaHoaDonAndMaSanPhamCT(
                reponBanHang.selectIdHDByMaHD(mahd),
                reponBanHang.selectIDSPCTByMaSPCT(maspct)
        );
        String id_hd = reponBanHang.selectIdHDByMaHD(mahd);
        String id_spct = reponBanHang.selectIDSPCTByMaSPCT(maspct);
        int soluongthaydoi = 0;
        try {
            soluongthaydoi = Integer.parseInt(tblGioHang.getValueAt(i, 3).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng thay đổi không hợp lệ");
            loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
            return;
        }
        // lấy gt từ bảng
        try {
            if (tblGioHang.getValueAt(i, 3).toString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống số lượng thay đổi");
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
                return;
            } else if (soluongthaydoi <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng thay đổi không hợp lệ");
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
                return;
            } else if (soluongthaydoi > reponBanHang.selectSoLuongBySanPhamChiTiet(id_spct, mahdct)) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ");
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
                return;
            } else {
                reponBanHang.suaHoaDonChiTiet(mahdct, id_spct, id_hd, soluongthaydoi);
                JOptionPane.showMessageDialog(this, "Sửa thành công ");
                loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
                loadTableSanPham(reponBanHang.getAllSP());
            }
        } catch (Exception e) {
            loadTableHoaDonCT(reponBanHang.selectHDCTByMaHD(mahd));
            return;
        }
        //       int soluongtronggio = reponBanHang.selectSoLuongByHoaDonChiTietID(mahdct);
        //        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnSuaSPTRONGGH;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSPGH;
    private javax.swing.JComboBox<String> cbbHinhThuc;
    private javax.swing.JComboBox<String> cbb_TrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblQuetMa;
    private javax.swing.JTable tblGioHang;
    public javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienCK;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables

    void loadCBB() {
        dcbHTTT = (DefaultComboBoxModel<String>) cbbHinhThuc.getModel();
        dcbHTTT.removeAllElements();
        dcbHTTT.addElement("Tiền mặt");
        dcbHTTT.addElement("Chuyển khoản");
        dcbHTTT.addElement("Kết hợp");
    }

    ;
    
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

        return sdf.format(date);
    }

    // Hàm tự viết để chuyển đổi ngày tháng
    private Date parseDate(String ngayThang) {
        // Đối tượng hỗ trợ đọc kiểu dữ liệu ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //String date = DateFormat.
        try {
            return sdf.parse(ngayThang);
        } catch (Exception e) {
            //Nếu lỗi trả về thời điểm hiện tại
            return new Date();
        }
    }

    void showDataHoaDon(int index) {
        HoaDon hd = reponBanHang.getAllHD().get(index);
        txtMaHD.setText(hd.getMa());
        txtNgayTao.setText(date2String(hd.getNgayTao()));
        txtTen.setText(hd.getTennhanvien());
    }

//    BigDecimal tinhTongTien() {
//    double tongtien1 = 0;
//
//    // Định dạng để hiển thị số tiền theo VND
//    DecimalFormat vndFormat = new DecimalFormat("#,###,### VND");
//
//    for (int i = 0; i < tblGioHang.getRowCount(); i++) {
//        double dongia = Double.parseDouble(tblGioHang.getValueAt(i, 4).toString());
//        double soluong2 = Double.parseDouble(tblGioHang.getValueAt(i, 3).toString());
//        double tongtien2 = dongia * soluong2;
//        tongtien1 += tongtien2;
//    }
//
//    // Hiển thị tổng tiền trong JTextField txtTongTien
//    String formattedTongTien = vndFormat.format(tongtien1);
//    
//    // Loại bỏ dấu chấm và phẩy
//    formattedTongTien = formattedTongTien.replaceAll("[.,]", "");
//
//    txtTongTien.setText(formattedTongTien);
//
//    // Sử dụng BigDecimal để tránh mất mát độ chính xác
//    return BigDecimal.valueOf(tongtien1);
//}
    BigDecimal tinhTongTien() {
        double tongtien1 = 0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            double tongTien = Double.parseDouble(tblGioHang.getValueAt(i, 5).toString());
            //   int soluong2 = Integer.parseInt((tblGioHang.getValueAt(i, 3).toString()));
            //   double tongtien2 = dongia ;
            tongtien1 += tongTien;
        }
        txtTongTien.setText(tongtien1 + "");
        return BigDecimal.valueOf(tongtien1);
    }

}
