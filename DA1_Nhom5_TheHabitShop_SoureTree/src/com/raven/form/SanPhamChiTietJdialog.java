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
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.SanPhamChiTietRespose;
import static com.raven.form.SanPhamView.maSPCT;
import com.raven.reponsitory.ChatLieuRepository;
import com.raven.reponsitory.DayGiayRepository;
import com.raven.reponsitory.DeGiayRepository;
import com.raven.reponsitory.KichCoRepository;
import com.raven.reponsitory.KieuDangRespository;
import com.raven.reponsitory.MauSacRepository;
import com.raven.reponsitory.NhanHieuRepository;
import com.raven.reponsitory.SanPhamChiTietRepository;
import com.raven.reponsitory.SanPhamRespository;
import com.raven.service.GetIdSPCTService_dat;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhDat
 */
public class SanPhamChiTietJdialog extends javax.swing.JDialog {

    private SanPhamChiTietRepository sp = new SanPhamChiTietRepository();
    private List<SanPhamChiTiet> listSPCT = new ArrayList<>();
    private SanPhamRespository sps = new SanPhamRespository();
    private int index = -1;
    private List<SanPham> listSP = new ArrayList<>();
    public SanPhamChiTietRepository spct = new SanPhamChiTietRepository();
    public static String tenSP;
    public static String maSPCT;
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
    private GetIdSPCTService_dat getid = new GetIdSPCTService_dat();

    /**
     * Creates new form SanPhamChiTiet
     */
    public SanPhamChiTietJdialog(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        setTitle("CHI TIẾT SẢN PHẨM");
        listSPCT = spct.getByMaSanPham(SanPhamChiTietJdialog.tenSP);
        listSPCT = spct.getByMaSanPham(SanPhamChiTietJdialog.maSPCT);
        listSP = sps.getAll();
        listSPCT = spct.getAll();
        listMS = ms.getAll();
        listCL = cl.getAll();
        listDG = dg.getAll();
        listKC = kc.getAll();
        listDay = day.getAll();
        listNH = nh.getAll();
        listKG = kg.getAll();
        //LoadtableSPCT(listSPCT);
        setComBoBoxTenSanPham();
        setComBoBoxTenMauSac();
        setComBoBoxTenChatLieu();
        setComBoBoxTenNhanHieu();
        setComBoBoxTenDeGiay();
        setComBoBoxTenKieuDang();
        setComBoBoxTenDayGiay();
        setComBoBoxTenKichCo();
        //nếu để đây k đc thì đổi vị trí
        setData();
        // hienThiAnhQR(SanPhamView.maSPCT);
    }

    public void hienThiAnhQR(String tenAnh) {
        // Tạo đường dẫn đến tập tin hình ảnh mã QR
        String duongDanAnh = "C:\\Users\\ADMIN\\Desktop\\Da1_TheHaBits_Nhom5_master\\DA1_Nhom5_TheHabitShop_SoureTree\\src\\com\\raven\\icon\\QRCode" + tenAnh + ".PNG";

        // Tạo một đối tượng ImageIcon từ tập tin hình ảnh
        ImageIcon icon = new ImageIcon(duongDanAnh);

        // Lấy hình ảnh từ đối tượng ImageIcon
        Image hinhGoc = icon.getImage();

        // Thu nhỏ hình ảnh để vừa với kích thước của JLabel
        Image hinhThuNho = hinhGoc.getScaledInstance(lbqrcode.getWidth(), lbqrcode.getHeight(), Image.SCALE_SMOOTH);

        // Tạo một ImageIcon mới với hình ảnh đã thu nhỏ
        ImageIcon iconThuNho = new ImageIcon(hinhThuNho);

        // Đặt ImageIcon đã thu nhỏ vào JLabel
        lbqrcode.setIcon(iconThuNho);

        // In thông báo đến console
        //   System.out.println("Đã hiển thị hình ảnh mã QR: " + tenAnh);
    }

//    public void UpQR(String image) {
//        ImageIcon icon1 = new ImageIcon("C:\\Users\\ThanhDat\\OneDrive\\DuAn1\\DA1\\src\\com\\raven\\icon\\qrcode\\" + image + ".PNG");
//        Image im = icon1.getImage();
//        ImageIcon icon = new ImageIcon(im.getScaledInstance(lbQRCODE.getWidth(), lbQRCODE.getHeight(), im.SCALE_SMOOTH));
//        lbQRCODE.setIcon(icon);
//        System.out.println("QR" + image);
//
//    }
    //set dữ liệu cho trường
    void setData() {
        cbbTenSPCT.setSelectedItem(SanPhamView.tenSP);
        cbbKichThuoc.setSelectedItem(SanPhamView.kichCo);
        cbbChatLieu.setSelectedItem(SanPhamView.chatLieu);
        cbbDayGiay.setSelectedItem(SanPhamView.dayGiay);
        cbbDeGiay.setSelectedItem(SanPhamView.deGiay);
        cbbKieuDang.setSelectedItem(SanPhamView.kieudang);
        cbbMauSac.setSelectedItem(SanPhamView.mauSac);
        cbbNhanHieu.setSelectedItem(SanPhamView.nhanHieu);

        // Sửa đoạn này
        DecimalFormat currencyFormat = new DecimalFormat("#.###");
//        txtDonGia.setText(currencyFormat.format(SanPhamView.dongia));
        txtDonGia.setText(String.valueOf(NumberFormat.getInstance().format(SanPhamView.dongia)));
        txtSoLuong.setText(String.valueOf(SanPhamView.soluong));
        hienThiAnhQR(SanPhamView.maSPCT);
        // lbQRCODE.setDisabledIcon(UpQR(SanPhamView.maSPCT));
        // UpQR(SanPhamView.maSPCT);
    }

    void setComBoBoxTenSanPham() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbTenSPCT.getModel();
        boxModel.removeAllElements();
        for (SanPham sp : listSP) {
            boxModel.addElement(sp.getTenSP());

        }
    }

    void setComBoBoxTenMauSac() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbMauSac.getModel();
        boxModel.removeAllElements();
        for (MauSac sp : listMS) {
            boxModel.addElement(sp.getTenMauSac());

        }
    }

    void setComBoBoxTenKichCo() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        boxModel.removeAllElements();
        for (KichCo sp : listKC) {
            boxModel.addElement(sp.getTenKichCo());

        }
    }

    void setComBoBoxTenKieuDang() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbKieuDang.getModel();
        boxModel.removeAllElements();
        for (KieuDang sp : listKG) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenDayGiay() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbDayGiay.getModel();
        boxModel.removeAllElements();
        for (DayGiay sp : listDay) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenDeGiay() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbDeGiay.getModel();
        boxModel.removeAllElements();
        for (DeGiay sp : listDG) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenChatLieu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbChatLieu.getModel();
        boxModel.removeAllElements();
        for (ChatLieu sp : listCL) {
            boxModel.addElement(sp.getTen());

        }
    }

    void setComBoBoxTenNhanHieu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbNhanHieu.getModel();
        boxModel.removeAllElements();
        for (NhanHieu sp : listNH) {
            boxModel.addElement(sp.getTen());

        }
    }

    private void huy() {
        System.exit(0);
    }

 private boolean validateFormSPCTRE() {
    // Kiểm tra không để trống số lượng
    String slText = txtSoLuong.getText().trim();
    if (slText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
        return false;
    }

    // Kiểm tra không để trống đơn giá
    String donGiaText = txtDonGia.getText().trim();
    if (donGiaText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá.");
        return false;
    }

    int soLuong;
    BigDecimal donGia;

    // Kiểm tra và parse số lượng
    try {
        soLuong = Integer.parseInt(slText);

        // Kiểm tra số lượng là số và lớn hơn 0
        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên lớn hơn 0.");
            return false; // Trả về false nếu có lỗi
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên lớn hơn 0.");
        return false; // Trả về false nếu có lỗi
    }

//    // Kiểm tra và parse đơn giá
//    try {
//        donGia = new BigDecimal(donGiaText);
//
//        // Kiểm tra đơn giá là số và lớn hơn 0
//        if (donGia.compareTo(BigDecimal.ZERO) <= 0) {
//            JOptionPane.showMessageDialog(this, "Đơn giá phải là một số thực lớn hơn 0.");
//            return false; // Trả về false nếu có lỗi
//        }
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(this, "Đơn giá phải là một số thực lớn hơn 0.");
//        return false; // Trả về false nếu có lỗi
//    }

    int trangThai = (soLuong == 0) ? 0 : 1;

    // Nếu không có lỗi, trả về true và tiếp tục xử lý
    return true;
}


// Hàm đọc dữ liệu từ form khi đã kiểm tra
    public SanPhamChiTietRespose readFormSPCTRE() {
        GenMaTuDong genTD = new GenMaTuDong() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "SPCT" + gen;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#.###");

        String maSp = genTD.maTuDong();
        maSPCT = maSp;

        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

        // Chuyển đổi đơn giá từ định dạng VND sang kiểu số (BigDecimal)
        BigDecimal donGia = null;
        try {
            donGia = BigDecimal.valueOf(currencyFormat.parse(txtDonGia.getText()).doubleValue());
        } catch (ParseException ex) {
            Logger.getLogger(SanPhamView.class.getName()).log(Level.SEVERE, null, ex);
        }

        int trangThai = (soLuong == 0) ? 0 : 1;

        return new SanPhamChiTietRespose(
                maSp,
                soLuong,
                trangThai,
                donGia,
                getid.getIDMauSac(cbbMauSac.getSelectedItem() + ""),
                getid.getIDChatLieu(cbbChatLieu.getSelectedItem() + ""),
                getid.getIDKichCo(cbbKichThuoc.getSelectedItem() + ""),
                getid.getIDNhanHieu(cbbNhanHieu.getSelectedItem() + ""),
                getid.getIDDeGiay(cbbDeGiay.getSelectedItem() + ""),
                getid.getIDDayGiay(cbbDayGiay.getSelectedItem() + ""),
                getid.getIDKieuDang(cbbKieuDang.getSelectedItem() + ""),
                getid.getIDSanPham(cbbTenSPCT.getSelectedItem() + "")
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbbKieuDang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbbNhanHieu = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbbDayGiay = new javax.swing.JComboBox<>();
        cbbTenSPCT = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        cbbDeGiay = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSuaCTSP = new javax.swing.JButton();
        lbqrcode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Tên Sản Phẩm:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Thông Tin Sản Phẩm");

        jLabel6.setText("Đơn Giá");

        jLabel13.setText("Kiểu Dáng");

        jLabel14.setText("Dây Giày");

        cbbKieuDang.setForeground(new java.awt.Color(255, 255, 255));
        cbbKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Màu Sắc");

        cbbMauSac.setForeground(new java.awt.Color(255, 255, 255));
        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbKichThuoc.setForeground(new java.awt.Color(255, 255, 255));
        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Kích Thước ");

        jLabel10.setText("Chất Liệu");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Nhãn Hiệu");

        cbbNhanHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Đế giày");

        cbbDayGiay.setForeground(new java.awt.Color(255, 255, 255));
        cbbDayGiay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbDeGiay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Số Lượng");

        jLabel3.setText("QR CODE: ");

        btnSuaCTSP.setBackground(new java.awt.Color(51, 153, 255));
        btnSuaCTSP.setText("Sửa");
        btnSuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTSPActionPerformed(evt);
            }
        });

        lbqrcode.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel5)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbChatLieu, 0, 142, Short.MAX_VALUE)
                                    .addComponent(cbbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbMauSac, 0, 234, Short.MAX_VALUE)
                                    .addComponent(cbbDeGiay, 0, 142, Short.MAX_VALUE)
                                    .addComponent(cbbTenSPCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 433, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbqrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel11))
                                            .addGap(21, 21, 21))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(27, 27, 27)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(cbbDayGiay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbKieuDang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbNhanHieu, 0, 231, Short.MAX_VALUE)
                                    .addComponent(txtSoLuong))))))
                .addGap(45, 45, 45))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbChatLieu, cbbDayGiay, cbbDeGiay, cbbKichThuoc, cbbKieuDang, cbbMauSac, cbbNhanHieu, txtDonGia});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cbbKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(cbbDayGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(cbbNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGap(21, 21, 21)
                            .addComponent(cbbDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lbqrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTSPActionPerformed
        if (validateFormSPCTRE()) {

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Sửa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                spct.updateSP(readFormSPCTRE(), SanPhamView.ma);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                SanPhamView spv = new SanPhamView();
                spv.loadTablePhanTrang(spct.selectPhanTrangAll(spv.trang));
                
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnSuaCTSPActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamChiTietJdialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietJdialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietJdialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietJdialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SanPhamChiTietJdialog dialog = new SanPhamChiTietJdialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDayGiay;
    private javax.swing.JComboBox<String> cbbDeGiay;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbKieuDang;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNhanHieu;
    private javax.swing.JComboBox<String> cbbTenSPCT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbqrcode;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
