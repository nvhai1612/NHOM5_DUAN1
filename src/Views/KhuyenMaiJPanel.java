/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.KhuyenMai;
import DomainModel.SPCT;
import DomainModel.SanPham;

import Service.Impl.SPCTService;
import Service.Impl.SanPhamService;
import java.sql.Date;
import Service.Impl.khuyenmaiseviec;

import ViewModel.SPCTVM;

import ViewModel.khuyeMaiVM;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhuyenMaiJPanel extends javax.swing.JPanel {

    ArrayList<KhuyenMai> list = new ArrayList<>();
    SPCTService seviec2 = new SPCTService();
    khuyenmaiseviec seviec = new khuyenmaiseviec();
   
    DefaultTableModel dtm;

    public KhuyenMaiJPanel() {
        initComponents();
        this.LoadTable();
//        LoadTableSPCT();
    }

    private void LoadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblKhuyenMai.getModel();
        dtm.setRowCount(0);
        ArrayList<khuyeMaiVM> listSPVM = seviec.getAll();

        for (khuyeMaiVM x : listSPVM) {
            dtm.addRow(new Object[]{
                //                x.getIdKM(),
                //                x.getIDSP(),
                x.getMaKM(),
                x.getTenKM(),
                x.getMucGiamGia(),
                x.getThoiGianBatDau(),
                x.getThoiGianKetThuc(),
                x.getTrangThai() == 1 ? "Hoạt động" : "Dừng Hoạt động",
                x.getSoLuong()
            });
        }
    }

//    private void LoadTableSPCT() {
//        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
//        dtm.setRowCount(0);
//
//        ArrayList<SPCTVM> listSPCTVM = SPCTService.getAll();
//
//        for (SPCTVM spctvm : listSPCTVM) {
//            dtm.addRow(new Object[]{
//                spctvm.getMaSPCT(),
//                spctvm.getTenSP(),
//                spctvm.getSoLuongTon(),
//                spctvm.getNguoiTao(),
//                spctvm.getTrangThaiSPCT() == 1 ? "Đang Hoạt động" : "Dừng Hoạt động",
//                spctvm.getTenKC(),
//                spctvm.getTenMS(),
//                spctvm.getTenTH(),
//                spctvm.getTenCL(),
//                spctvm.getDonGia(),});
//            System.out.println(spctvm);
//        }
//    }
//        
//    
//        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
//        dtm.setRowCount(0);
//
//        ArrayList<KhuyenMaiChiTietVM> listkmct = KMCTseviec.getAll();
//
//        for (KhuyenMaiChiTietVM kmctvm : listkmct) {
//            dtm.addRow(new Object[]{
//                kmctvm.getMASPCT(),
//                kmctvm.getMAKMCT(),
//                kmctvm.getTENKM(),
//                kmctvm.getDonGia(),
//                kmctvm.getTrangthaiKMCT()  == 1 ? "Đang Hoạt động" : "Dừng Hoạt động"
//              
//            }
//                    }
//    }
//        
//            }     
//            
//                    }
//    }
//     private void LoadTableSPCT() {
//        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
//        dtm.setRowCount(0);
//        ArrayList<khuyeMaiVM> KMVM=seviec.getAll();
//        ArrayList<SPCTVM> listSPCTVM = seviec2.getAll();
//
//        for (SPCTVM spctvm : listSPCTVM) {
//            String tenkm="Khuyenmai11";
//            for (khuyeMaiVM spctvm1 : KMVM) {
//                    if(spctvm.machest(KMVM)){
//                        tenkm=spctvm1.getTenKM();
//                        break;
//                    }
//            }
//            dtm.addRow(new Object[]{
//                spctvm.getMaSPCT(),
//                spctvm.getTenSP(),
//                spctvm.getSoLuongTon(),
//                spctvm.getTenMS(),
//                spctvm.getTenCL(),
//                spctvm.getTenTH(),
//                spctvm.getDonGia(),});
//
//            System.out.println(spctvm);
//        }
//    }
    public void showdatatable(List<KhuyenMai> list) {
        dtm.setRowCount(0);
        for (KhuyenMai km : list) {
            dtm.addRow(km.toDataRow());

        }
    }

    public boolean checkvailidate() {
        if (txtmakm.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập mã");
            return false;
        }
        if (txtTenChuongTrinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập tên");
            return false;
        }
        if (txtMucGiaGiam.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập mức giảm giá");
            return false;
        }
        if (txtThoiGianBatDau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập thời gian bắt đâuuf");
            return false;
        }
        if (txtThoiGianKetThuc1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "bạn cần nhập thời gian kết thúc");
            return false;

        }
//        if (btnsoluong.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "bạn cần nhập số lượng");
//            return false;
//
//        }
        return true;
    }

    public boolean getform() {
        String makm = txtmakm.getText();
        String tensp = txtTenChuongTrinh.getText();
        String mucgiangia = txtMucGiaGiam.getText();
        int TrangThai = rdoDangHoatDong.isSelected() == true ? 1 : 0;
        Date ngaybatdau = Date.valueOf(txtThoiGianBatDau.getText());
        Date ngayketthuc = Date.valueOf(txtThoiGianKetThuc1.getText());
        String soluong = btnsoluong.getText();
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(makm);
        km.setTenKM(tensp);
        km.setMucGiamGia(Float.valueOf(mucgiangia));
        km.setThoiGianBatDau(ngaybatdau);
        km.setThoiGianKetThuc(ngayketthuc);
        km.setTrangThai(TrangThai);
        km.setSoLuong(Integer.valueOf(soluong));
        this.seviec.add(km);
        Moi();
        this.LoadTable();
        return true;
    }

    public boolean updateform() {
        String makm = txtmakm.getText();
        String tensp = txtTenChuongTrinh.getText();
        String mucgiangia = txtMucGiaGiam.getText();
        int TrangThai = rdoDangHoatDong.isSelected() == true ? 1 : 0;
        Date ngaybatdau = Date.valueOf(txtThoiGianBatDau.getText());
        Date ngayketthuc = Date.valueOf(txtThoiGianKetThuc1.getText());
//        String soluong=btnsoluong.getText();
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(makm);
        km.setTenKM(tensp);
        km.setMucGiamGia(Float.valueOf(mucgiangia));
        km.setThoiGianBatDau(ngaybatdau);
        km.setThoiGianKetThuc(ngayketthuc);
        km.setTrangThai(TrangThai);
//        km.setSoLuong(Integer.valueOf(soluong));
        this.seviec.update(km);
        Moi();
        this.LoadTable();
        return true;
    }

    public void lammoi() {
        txtmakm.setText("");
        txtTenChuongTrinh.setText("");
        txtMucGiaGiam.setText("");
        txtThoiGianBatDau.setText("");
        txtThoiGianKetThuc1.setText("");
        btnsoluong.setText("");

    }

    private void showSPKM(String maSPCT) {
        List<SPCT> list = seviec.getSPCT(maSPCT);
        DefaultTableModel tableModel1HoaDon = (DefaultTableModel) tblSanPham.getModel();
        tableModel1HoaDon.setRowCount(0);
        for (SPCT sp : list) {
            tableModel1HoaDon.addRow(new Object[]{
                sp.getMaSPCT(),
                sp.getTenSP(),
                sp.getTenKC(),
                sp.getTenMS(),
                sp.getTenCL(), sp.getTenTH(), sp.getDonGia(), sp.getKM()

            });
        }

    }

//    public void kiemtradulieu() {
//        java.util.Date NgayBatDau;
//        java.util.Date NgayKetThuc;
//        SimpleDateFormat bd = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat kt = new SimpleDateFormat("yyyy-MM-dd");
//        String tenkm = txtTenChuongTrinh.getText().trim();
//        float mucgiamgia = Float.parseFloat(txtMucGiaGiam.getText().trim());
//        int trangthai = Integer.valueOf(rdoDangHoatDong.getText());
//        String ngaybatdau= txtThoiGianBatDau.Date().
//        if (tenkm.length() == 0) {
//            JOptionPane.showMessageDialog(this, "Không được để trống Teen Khuyến Mại");
//            return null;
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cboLocDongSP = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenChuongTrinh = new javax.swing.JTextField();
        txtMucGiaGiam = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        rdoDangHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        txtThoiGianBatDau = new javax.swing.JTextField();
        txtThoiGianKetThuc1 = new javax.swing.JTextField();
        txtmakm = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnsoluong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txttimkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        jPanel1.setBackground(new java.awt.Color(222, 231, 227));
        jPanel1.setMaximumSize(new java.awt.Dimension(825, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(825, 520));

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblSanPham.setBackground(new java.awt.Color(222, 231, 227));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma San pham", "tenSP", "Kich Thuoc", "mau sac", "chat lieu", "thuong hieu ", "đơn giá", "khuyen mai"
            }
        ));
        tblSanPham.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblSanPham);
        tblSanPham.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("Lọc");

        cboLocDongSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocDongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocDongSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(cboLocDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 331, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboLocDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(222, 231, 227));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hình thức khuyến mại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel5.setText("Tên chương trình :");

        jLabel6.setText("Mức giảm giá (%) :");

        jLabel7.setText("Thời gian bắt đầu");

        jLabel8.setText("Ngày kết thúc :");

        txtMucGiaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucGiaGiamActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCapNhat.setText("Cập nhật ");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDangHoatDong);
        rdoDangHoatDong.setSelected(true);
        rdoDangHoatDong.setText("Đang hoạt động");
        rdoDangHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangHoatDongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNgungHoatDong);
        rdoNgungHoatDong.setText("Ngưng hoạt động");
        rdoNgungHoatDong.setToolTipText("");
        rdoNgungHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgungHoatDongActionPerformed(evt);
            }
        });

        txtThoiGianKetThuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoiGianKetThuc1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Ma khuyen mai");

        btnsoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsoluongActionPerformed(evt);
            }
        });

        jLabel10.setText("Số Lượng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMucGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel9))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtmakm, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtThoiGianKetThuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rdoDangHoatDong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNgungHoatDong)
                        .addGap(12, 12, 12))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMucGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtThoiGianKetThuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDangHoatDong)
                    .addComponent(rdoNgungHoatDong))
                .addGap(31, 31, 31)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(222, 231, 227));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblKhuyenMai.setBackground(new java.awt.Color(222, 231, 227));
        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaKM", "TênKM", "MucGiamGia", "thoi gian bat dau", "thoi gian ket thuc", "trang thai", "so luong"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        jPanel5.setBackground(new java.awt.Color(222, 231, 227));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khuyến mại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        btntimkiem.setText("Tìm kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btntimkiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 203, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboLocDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocDongSPActionPerformed

    }//GEN-LAST:event_cboLocDongSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//         String makm = txtmakm.getText();   
//        String tensp = txtTenChuongTrinh.getText();
//        String mucgiangia = txtMucGiaGiam.getText();
//        int TrangThai = rdoDangHoatDong.isSelected() == true ? 1 : 0;
//        Date ngaybatdau=Date.valueOf(txtThoiGianBatDau.getText());
//        Date ngayketthuc=Date.valueOf(txtThoiGianKetThuc1.getText());
//        KhuyenMai km = new KhuyenMai();
//        km.setMaKM(makm);
//        km.setTenKM(tensp);
//        km.setMucGiamGia(Float.valueOf(mucgiangia));
//        km.setThoiGianBatDau(ngaybatdau);
//        km.setThoiGianKetThuc(ngayketthuc);  
//        km.setTrangThai(TrangThai);
//        this.seviec.add(km);
//        Moi();
//        this.LoadTable();
        if (checkvailidate()) {
            boolean km = this.getform();
            if (seviec.add(km) != null) {
                JOptionPane.showMessageDialog(this, "them thành công");
                this.LoadTable();

            } else {
                JOptionPane.showMessageDialog(this, "thâme thất bại");
            }

        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
//         String makm = txtmakm.getText();   
//        String tensp = txtTenChuongTrinh.getText();
//        String mucgiangia = txtMucGiaGiam.getText();
//        int TrangThai = rdoDangHoatDong.isSelected() == true ? 1 : 0;
//        Date ngaybatdau=Date.valueOf(txtThoiGianBatDau.getText());
//        Date ngayketthuc=Date.valueOf(txtThoiGianKetThuc1.getText());
//        KhuyenMai km = new KhuyenMai();
//        km.setMaKM(makm);
//        km.setTenKM(tensp);
//        km.setMucGiamGia(Float.valueOf(mucgiangia));
//        km.setThoiGianBatDau(ngaybatdau);
//        km.setThoiGianKetThuc(ngayketthuc);  
//        km.setTrangThai(TrangThai);
//        this.seviec.update(km);
//        Moi();
//        this.LoadTable();
        if (checkvailidate()) {
            boolean km = this.updateform();
            if (seviec.update(km) != null) {
                JOptionPane.showMessageDialog(this, "them thành công");
                this.LoadTable();

            } else {
                JOptionPane.showMessageDialog(this, "thâme thất bại");
            }

        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lammoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void rdoDangHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDangHoatDongActionPerformed

    private void rdoNgungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgungHoatDongActionPerformed

    }//GEN-LAST:event_rdoNgungHoatDongActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked

        int row = tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtmakm.setText(tblKhuyenMai.getValueAt(row, 0).toString());
        txtTenChuongTrinh.setText(tblKhuyenMai.getValueAt(row, 1).toString());
        txtMucGiaGiam.setText(tblKhuyenMai.getValueAt(row, 2).toString());
        txtThoiGianBatDau.setText(tblKhuyenMai.getValueAt(row, 3).toString());
        txtThoiGianKetThuc1.setText(tblKhuyenMai.getValueAt(row, 4).toString());
        btnsoluong.setText(tblKhuyenMai.getValueAt(row, 6).toString());
//        txtsoluong.setText(tblKhuyenMai.getValueAt(row, 7).toString());

        String t = tblKhuyenMai.getValueAt(row, 5).toString();

        if (t.equals("Yes")) {
            rdoDangHoatDong.setSelected(true);
        } else {
            rdoNgungHoatDong.setSelected(true);

        }
        String maSP = tblKhuyenMai.getValueAt(row, 0).toString();
        showSPKM(maSP);

    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void txtMucGiaGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucGiaGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucGiaGiamActionPerformed

    private void txtThoiGianKetThuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoiGianKetThuc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiGianKetThuc1ActionPerformed

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed


    }//GEN-LAST:event_txttimkiemActionPerformed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
//        String km = txttimkiem.getText().trim();
//        list = seviec.search(km);
//        DefaultTableModel dtm = (DefaultTableModel) tblKhuyenMai.getModel();
//        showdatatable(list);


    }//GEN-LAST:event_txttimkiemKeyReleased

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        ArrayList<khuyeMaiVM> km = seviec.getAll();

        String TimKiem = this.txttimkiem.getText();
        int index = -1;
        for (khuyeMaiVM listkm : km) {
            if (TimKiem.equalsIgnoreCase(listkm.getMaKM())) {
                index = km.indexOf(listkm);
            }
        }

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy !");
            return;
        }
        txtmakm.setText(km.get(index).getMaKM());
        txtTenChuongTrinh.setText(km.get(index).getTenKM());
        txtMucGiaGiam.setText(String.valueOf(km.get(index).getMucGiamGia()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtThoiGianBatDau.setText(sdf.format(km.get(index).getThoiGianBatDau()));
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        txtThoiGianKetThuc1.setText(sdt.format(km.get(index).getThoiGianKetThuc()));
        btnsoluong.setText(String.valueOf(km.get(index).getSoLuong()));


    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnsoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsoluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsoluongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JTextField btnsoluong;
    private javax.swing.JButton btntimkiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLocDongSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDangHoatDong;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMucGiaGiam;
    private javax.swing.JTextField txtTenChuongTrinh;
    private javax.swing.JTextField txtThoiGianBatDau;
    private javax.swing.JTextField txtThoiGianKetThuc1;
    private javax.swing.JTextField txtmakm;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void Moi() {
        txtmakm.setText("");
        txtTenChuongTrinh.setText("");
        txtMucGiaGiam.setText("");
        txtThoiGianBatDau.setText("");
        txtThoiGianKetThuc1.setText("");
        rdoDangHoatDong.setSelected(true);
        btnsoluong.setText("");

    }

}
