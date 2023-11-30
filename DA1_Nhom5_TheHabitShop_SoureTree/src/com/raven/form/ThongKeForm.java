package com.raven.form;

import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.HoaDonTraHang;
import com.raven.classmodel.HoaDonTraHangCT;
import com.raven.reponsitory.HoaDonChiTietRepon;
import com.raven.reponsitory.HoaDonRepon;
import com.raven.reponsitory.HoaDonTraHangCTRepon;
import com.raven.reponsitory.HoaDonTraHangRepon;
import com.raven.reponsitory.KhachHang_DAO;
import com.raven.reponsitory.ThongKeReponsitory;
import java.awt.BorderLayout;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKeForm extends javax.swing.JPanel {

    private DefaultCategoryDataset dataSet;
    private JFreeChart chart;
    private CategoryPlot categoryPlot;
    private ChartPanel chartPanel;
    private DecimalFormat df = new DecimalFormat("#,###");
    private ThongKeReponsitory reponThongKe = new ThongKeReponsitory();
    private HoaDonChiTietRepon reponHDCT = new HoaDonChiTietRepon();
    private HoaDonRepon reponHD = new HoaDonRepon();
    private KhachHang_DAO reponKH = new KhachHang_DAO();
    private HoaDonTraHangRepon reponHDTH = new HoaDonTraHangRepon();
    private HoaDonTraHangCTRepon reponHDTHCT = new HoaDonTraHangCTRepon();
    private DefaultTableModel modelDoanhThu = new DefaultTableModel();
    private DefaultTableModel modelHDCT = new DefaultTableModel();
    private DefaultTableModel modelHDTH = new DefaultTableModel();
    private DefaultTableModel modelHDTHCT = new DefaultTableModel();
    List<HoaDon> listHD = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    List<HoaDonTraHang> listHDTH = new ArrayList<>();
    List<HoaDonTraHangCT> listHDTHCT = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public ThongKeForm() {
        initComponents();
        showBarChart();
        modelDoanhThu = (DefaultTableModel) tblDoanhThu.getModel();
        this.showDoanhThuCaNam();
        this.showDoanhThuToDay();
        this.showDoanhThu7NgayGanNhat();
        this.showDoanhThuThangNay();
        loadTableDoanhThu(reponHD.getAll());
    }

    void showBarChart() {
        dataSet = new DefaultCategoryDataset();
        dataSet.addValue(720000.0, "Tháng 5", "Năm 2022");
        dataSet.addValue(750000.0, "Tháng 6", "Năm 2022");
        dataSet.addValue(780000.0, "Tháng 7", "Năm 2022");
        dataSet.addValue(800000.0, "Tháng 8", "Năm 2022");
        dataSet.addValue(820000.0, "Tháng 9", "Năm 2022");
        dataSet.addValue(850000.0, "Tháng 10", "Năm 2022");
        dataSet.addValue(880000.0, "Tháng 11", "Năm 2022");
        dataSet.addValue(900000.0, "Tháng 12", "Năm 2022");

        dataSet.addValue(920000.0, "Tháng 1", "Năm 2023");
        dataSet.addValue(950000.0, "Tháng 2", "Năm 2023");
        dataSet.addValue(980000.0, "Tháng 3", "Năm 2023");
        dataSet.addValue(1000000.0, "Tháng 4", "Năm 2023");
        dataSet.addValue(1030000.0, "Tháng 5", "Năm 2023");
        dataSet.addValue(1060000.0, "Tháng 6", "Năm 2023");
        dataSet.addValue(200000.0, "Tháng 7", "Năm 2023");
        dataSet.addValue(180000.0, "Tháng 8", "Năm 2023");
        dataSet.addValue(220000.0, "Tháng 9", "Năm 2023");
        dataSet.addValue(265000.0, "Tháng 10", "Năm 2023");
        dataSet.addValue(300000.0, "Tháng 11", "Năm 2023");
        chart = ChartFactory.createBarChart("Biểu đồ doanh thu TheHabitShop năm 2022 đến hiện tại",
                "Biểu đồ thống kê", "VN", dataSet, PlotOrientation.VERTICAL, true, true, false);

        categoryPlot = chart.getCategoryPlot();

        chartPanel = new ChartPanel(chart);
        pnBieuDo.add(chartPanel, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateNgayNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnLoc = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbbDoanhThuTheoNam = new com.toedter.calendar.JYearChooser();
        jPanel3 = new javax.swing.JPanel();
        cbbDoanhThuTheoThang = new com.toedter.calendar.JMonthChooser();
        tblTongSoHoaDonHuy = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        pnBieuDo = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnXuatDanhSach = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblDoanhThuHomNay = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblDoanhThuCaNamNay = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblDoanhThuThangNay = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTongDoanhThu7NgayGanNhat = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống Kê", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 204, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Theo Khoảng Thời Gian"));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Từ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Đến");

        btnLoc.setBackground(new java.awt.Color(51, 153, 255));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(dateNgayNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(dateNgayNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Theo Năm"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(cbbDoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbDoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Theo Tháng"));

        cbbDoanhThuTheoThang.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(cbbDoanhThuTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbDoanhThuTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnBieuDo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Biểu Đồ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pnBieuDo.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblTongSoHoaDonHuy.addTab("Biểu Đồ", jPanel5);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo Hóa Đơn", "Tổng Tiền Của Hóa Đơn"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThu);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tblTongSoHoaDonHuy.addTab("Doanh Thu Theo Thời Gian", jPanel11);

        btnXuatDanhSach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatDanhSach.setText("Xuất Danh Sách");
        btnXuatDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblTongSoHoaDonHuy)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatDanhSach)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(tblTongSoHoaDonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Doanh Thu Hôm Nay");

        lblDoanhThuHomNay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoanhThuHomNay.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDoanhThuHomNay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Doanh Thu Cả Năm");

        lblDoanhThuCaNamNay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoanhThuCaNamNay.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDoanhThuCaNamNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDoanhThuCaNamNay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(102, 255, 102));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel7.setText("Doanh Thu Theo Tháng");

        lblDoanhThuThangNay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoanhThuThangNay.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
            .addComponent(lblDoanhThuThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDoanhThuThangNay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 102));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel9.setText("Doanh Thu 7 Ngày Gần Nhất");

        lblTongDoanhThu7NgayGanNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongDoanhThu7NgayGanNhat.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lblTongDoanhThu7NgayGanNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTongDoanhThu7NgayGanNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (dateNgayNgayBatDau.getDateFormatString().toString().trim().isEmpty() || dateNgayKetThuc.getDateFormatString().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày và giờ");
            return;
        } else {
            List<HoaDon> listHD = reponHD.findDate(dateNgayNgayBatDau, dateNgayKetThuc);
            DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
            model.setRowCount(0);
            int stt = 1;
            for (HoaDon hoaDon : listHD) {
                Object[] row = new Object[]{
                    stt++,
                    hoaDon.getMa(),
                    hoaDon.getNgaytaohoadon(),
                    hoaDon.getTongTienHoaDon(),};
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có đồng xuất danh sách?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                ArrayList<HoaDon> listHD = reponHD.getAll();
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Thống Kê");
                XSSFRow row = null;
                Cell cell = null;

                // Xuất phần hóa đơn
                row = sheet.createRow(0);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("MaHD");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày Tạo Hóa Đơn");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Doanh Thu");
                int stt =1 ;
                for (int i = 0; i < listHD.size(); i++) {
                    HoaDon hoaDon = listHD.get(i);
                    row = sheet.createRow(i + 1);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(stt++);

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(hoaDon.getMa());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(date2String(hoaDon.getNgaytaohoadon()));
                }
                FileOutputStream fileOut = new FileOutputStream("D:\\Excel\\HoaDon.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // Xử lý khi người dùng không đồng ý thêm
            JOptionPane.showMessageDialog(this, "Đã hủy xuất danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXuatDanhSachActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnXuatDanhSach;
    private com.toedter.calendar.JYearChooser cbbDoanhThuTheoNam;
    private com.toedter.calendar.JMonthChooser cbbDoanhThuTheoThang;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgayNgayBatDau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDoanhThuCaNamNay;
    private javax.swing.JLabel lblDoanhThuHomNay;
    private javax.swing.JLabel lblDoanhThuThangNay;
    private javax.swing.JLabel lblTongDoanhThu7NgayGanNhat;
    private javax.swing.JPanel pnBieuDo;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTabbedPane tblTongSoHoaDonHuy;
    // End of variables declaration//GEN-END:variables
    // Hàm chuyển đổi String về Date
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

    //GetToday
    private LocalDate getToDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        String ngay = "";
        if (month < 9 && date < 10) {
            ngay = (year + "-" + "0" + (month + 1) + "-" + "0" + date);
        } else if (month < 9 && date >= 10) {
            ngay = (year + "-" + "0" + (month + 1) + "-" + date);
        } else if (month >= 9 && date < 10) {
            ngay = (year + "-" + (month + 1) + "-" + "0" + date);
        } else if (month >= 9 && date >= 10) {
            ngay = (year + "-" + (month + 1) + "-" + date);
        }
        LocalDate ngay1 = LocalDate.parse(ngay);
        return ngay1;
    }

    //Doanh Thu Cả Năm
    private void showDoanhThuCaNam() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        BigDecimal doanhThuCaNam = reponThongKe.getDoanhThuCaNam(year);
        lblDoanhThuCaNamNay.setText(NumberFormat.getInstance().format(doanhThuCaNam));
    }

    //Doanh Thu Hôm Nay
    private void showDoanhThuToDay() {
        DecimalFormat df = new DecimalFormat("#,###");
        LocalDate toDay = LocalDate.of(2023, 11, 25); // Lấy ngày hiện tại
        BigDecimal doanhThuToDay = reponThongKe.getDoanhThuNgayHienTai(toDay);
        lblDoanhThuHomNay.setText(df.format(doanhThuToDay));
    }

    //Doanh Thu 7 Ngày Gần Nhất
    private void showDoanhThu7NgayGanNhat() {
        BigDecimal doanhThu7NgayGanNhat = reponThongKe.getDoanhThu7NgayGanNhat();
        lblTongDoanhThu7NgayGanNhat.setText(df.format(doanhThu7NgayGanNhat));
    }

    //Doanh Thu Tháng Này
    private void showDoanhThuThangNay() {
        Calendar cld = Calendar.getInstance();
        int month = cld.get(Calendar.MONTH);
        String thang = "";
        if (month < 9) {
            thang = "0" + (month + 1);
        } else {
            thang = (month + 1) + "";
        }
        int year = cld.get(Calendar.YEAR);
        String firstDayInMonth = year + "-" + thang + "-01";
        LocalDate toDay = LocalDate.now();
        BigDecimal doanhThuThangNay = reponThongKe.getDoanhThuThangNay(firstDayInMonth, toDay);
        lblDoanhThuThangNay.setText(df.format(doanhThuThangNay));
    }

    // Load table hóa đơn
    private void loadTableDoanhThu(List<HoaDon> listHD) {
        modelDoanhThu = (DefaultTableModel) tblDoanhThu.getModel();
        modelDoanhThu.setRowCount(0);
                    int stt = 1;
        for (HoaDon hoaDon : listHD) {
            Object[] row = new Object[]{
                stt++,
                hoaDon.getMa(),
                date2String(hoaDon.getNgaytaohoadon()),
                df.format(hoaDon.getTongTienHoaDon())
            };
            modelDoanhThu.addRow(row);
        }
    }

    //Lọc Doanh Thu Theo Ngày
    private void locDoanhThuTheoNgay() {
        modelDoanhThu.setRowCount(0);
        Date from = dateNgayNgayBatDau.getDate();
        Date to = dateNgayNgayBatDau.getDate();
        if (from == null || to == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Đủ Dữ Liệu!");
        } else if (to.before(from)) {
            JOptionPane.showMessageDialog(this, "Sai Dữ Liệu");
        } else {
            SimpleDateFormat spdfm = new SimpleDateFormat("yyyy-MM-dd");
            String tu = spdfm.format(from);
            String den = spdfm.format(to);
            List<Object[]> list = reponThongKe.LocTheoDoanhThuNgay(tu, den);
            for (Object[] row : list) {
                modelDoanhThu.addRow(new Object[]{row[0], row[1], df.format(row[2])});
            }
        }
    }

}
