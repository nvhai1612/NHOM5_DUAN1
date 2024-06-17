/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.KhachHang;
import DomainModel.SPCT;
import Repository.Impl.HDCTRepos;
import Service.Impl.HoaDonService;
import ViewModel.HoaDonDTO;
import ViewModel.HoaDonVM;
import ViewModel.KhachHangVM;
import ViewModel.SPCTVM;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class LichSuJPanel extends javax.swing.JPanel {

    private HDCTRepos hDCTCTRepos = new HDCTRepos();
    DefaultTableModel dtmsp = new DefaultTableModel();
    DefaultTableModel dtmgh = new DefaultTableModel();
    DefaultTableModel dtmhd = new DefaultTableModel();
    private HoaDonService hoaDonService = new HoaDonService();
    private ArrayList<SPCTVM> listSPCT = new ArrayList<>();

    /**
     * Creates new form HoaDonJPanel
     */
    public LichSuJPanel() {
        initComponents();
        LoadTable();
    }

    private void LoadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);

        ArrayList<HoaDonDTO> listDTO = hDCTCTRepos.getListFormDB();

        for (HoaDonDTO hdct : listDTO) {
            dtm.addRow(new Object[]{
                hdct.getMaHD(),
                hdct.getTenNV(),
                hdct.getTenKH(),
                //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hdvm.getNgayTao()),
                hdct.getTrangThai() == 1 ? "Đã thanh toán" : "Hủy",});
        }
    }
//       private void LoadTableHoaDon() {
//        dtmhd = (DefaultTableModel) tblHoaDon.getModel();
//        dtmhd.setRowCount(0);
//
//        ArrayList<HoaDonVM> listHDVM = hoaDonService.getAll().isEmpty() ? hoaDonService.getAllHoaDon() : hoaDonService.getAll();
//        for (HoaDonVM hdvm : listHDVM) {
//            dtmhd.addRow(new Object[]{
//                hdvm.getMaHD(),
//                hdvm.getTenNV(),
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hdvm.getNgayTao()),
//                hdvm.getTrangThaiHD() == 1 ? "Chờ thanh toán" : "Đã thanh toán"
//            });
//        }
//    }

    private void LoadHoaDonCho(String MaHD) {
        dtmgh = (DefaultTableModel) tblTTSP.getModel();
        dtmgh.setRowCount(0);

        ArrayList<SPCT> listHDC = hoaDonService.HoaDonCho(MaHD);
        for (SPCT spct : listHDC) {
            dtmgh.addRow(new Object[]{
                spct.getMaSPCT(),
                spct.getTenSP(),
                spct.getSoLuongTon(),
                spct.getDonGia(),
                new BigDecimal(spct.getDonGia() * spct.getSoLuongTon()),});
        }
        TinhTien();
    }

    public void TinhTien() {

        float CanThanhToan = 0;
        float ThanhTien = 0;
        double Khuyenmai = 0.20f * 100;
        int Tienkhachdua = 0;
        float Tienthua = 0;
        for (int i = 0; i < tblTTSP.getRowCount(); i++) {
            ThanhTien += Float.valueOf(tblTTSP.getValueAt(i, 4).toString());
            CanThanhToan = (float) (ThanhTien - (ThanhTien * Khuyenmai / 100));
            Tienthua = Tienkhachdua - CanThanhToan;
        }

        txtTongTien.setText(String.valueOf(CanThanhToan));
//        txtKhuyenMai.setText(String.valueOf(Khuyenmai));
//        txtCanThanhToan.setText(String.valueOf(CanThanhToan));
//        txtTienKhachDua.setText(String.valueOf(Tienkhachdua));
//        txtTienThua.setText(String.valueOf(Tienthua));
//        txtTrangThai.setText(String.valueOf("đã thanh toán"));
    }

    private void showTable(int row) {
        HoaDonDTO kh = hDCTCTRepos.getListFormDB().get(row);
        txtMa.setText(kh.getMaHD());
        txtTenNV.setText(kh.getTenNV());
        txtTenKH.setText(kh.getTenKH());
        txtSDT.setText(kh.getSDT());
        txtDiaChi.setText(kh.getDiachi());
        int trangThai = kh.getTrangThai();
        String trangThaiStr = (trangThai == 1) ? "Đã thanh toán" : "Hủy";
        txtTrangThai.setText(trangThaiStr);
        txtNgayThanhToan.setText(kh.getNgayTao());
//        txtNgayThanhToan.setText(DATE.toString(kh.getNgayThanhToan()));
        
//       txtTrangThai.setText(Integer.toString(kh.getTrangThai())== 1 ? "Đã thanh toán" : "Hủy") ;

//         txtTrangThai.setText(Integer.valueOf(kh.getTrangThai()));
//        txtTenKH.setText(kh.getTenKH());
//        txtNgaySinh.setText(kh.getNgaySinh());
//         System.out.println("GioiTinh: " + kh.getGioiTinh());
//        if (kh.getGioiTinh().equals(1)){
//            rdoNam.setSelected(true);
//        }else{
//            rdoNu.setSelected(true);
//        }
//        txtSdt.setText(kh.getSdt());
//        taDiaChi.setText(kh.getDiaChi());
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTTSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMa = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JLabel();
        txtSDT = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        txtNgayThanhToan = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cboTrangThai = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(222, 231, 227));
        jPanel1.setMaximumSize(new java.awt.Dimension(825, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(825, 520));

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        jPanel2.setToolTipText("");

        tblHoaDon.setBackground(new java.awt.Color(222, 231, 227));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Tên nhân viên", "Tên khách hàng", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel3.setBackground(new java.awt.Color(222, 231, 227));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblTTSP.setBackground(new java.awt.Color(222, 231, 227));
        tblTTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblTTSP);

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Mã HD");

        jLabel2.setText("Tên nhân viên");

        jLabel3.setText("Tên KH");

        jLabel4.setText("SĐT");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Tổng tiền");

        jLabel7.setText("Ngày TT");

        jLabel8.setText("Trạng thái");

        txtMa.setText("-");

        txtTenNV.setText("-");

        txtTenKH.setText("-");

        txtSDT.setText("-");

        txtDiaChi.setText("-");

        txtTongTien.setText("-");

        txtNgayThanhToan.setText("-");

        txtTrangThai.setText("-");

        jPanel4.setBackground(new java.awt.Color(222, 231, 227));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm theo mã"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTim.setText("Tìm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTim)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(222, 231, 227));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm theo mã"));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Trạng thái -", "Đã huỷ", "Đã hoàn thành" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTrangThai, 0, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMa))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenNV))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKH))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiaChi))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTien))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgayThanhToan))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTrangThai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        listSPCT.clear();

        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaHD = tblHoaDon.getValueAt(row, 0).toString();

        hoaDonService.HoaDonCho(MaHD);
        LoadHoaDonCho(MaHD);
        showTable(row);

        LoadTable();

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String keyword = txtTimKiem.getText().trim();
        if (keyword != null) {
            DefaultTableModel tableModel = (DefaultTableModel) tblHoaDon.getModel();
            tableModel.setRowCount(0);
            ArrayList<HoaDonDTO> list = hDCTCTRepos.search(keyword);
            for (HoaDonDTO kh : list) {
                tableModel.addRow(new Object[]{
                    kh.getMaHD(),
                    kh.getTenKH(),
                    kh.getTenNV(),
                    kh.getTrangThai() == 1 ? "Đã thanh toán" : "Hủy",});
            }
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
         String keyword = cboTrangThai.getActionCommand().trim();
        if (keyword != null) {
            DefaultTableModel tableModel = (DefaultTableModel) tblHoaDon.getModel();
            tableModel.setRowCount(0);
            ArrayList<HoaDonDTO> list = hDCTCTRepos.search(keyword);
            for (HoaDonDTO kh : list) {
                tableModel.addRow(new Object[]{
                    kh.getMaHD(),
                    kh.getTenKH(),
                    kh.getTenNV(),
                    kh.getTrangThai() == 1 ? "Đã thanh toán" : "Hủy",});
            }
        }
    }//GEN-LAST:event_cboTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTim;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblTTSP;
    private javax.swing.JLabel txtDiaChi;
    private javax.swing.JLabel txtMa;
    private javax.swing.JLabel txtNgayThanhToan;
    private javax.swing.JLabel txtSDT;
    private javax.swing.JLabel txtTenKH;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
