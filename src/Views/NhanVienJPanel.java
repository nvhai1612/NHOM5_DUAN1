/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Service.Impl.ChucVuService;
import Service.Impl.NhanVienService;
import ViewModel.ChucVuVM;
import ViewModel.NhanVienVM;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienJPanel extends javax.swing.JPanel {

    List<NhanVien> list = new ArrayList<>();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmCV = new DefaultTableModel();
    private NhanVienService nhanVienService = new NhanVienService();
    private ChucVuService chucVuService = new ChucVuService();
    private DefaultComboBoxModel dcbbm;

    /**
     * Creates new form NhanVienJPanel
     */
    public NhanVienJPanel() {
        initComponents();
        list = nhanVienService.getAllDoMain();
        dcbbm = (DefaultComboBoxModel) cbbChucVu.getModel();
        dcbbm.addAll(chucVuService.getAll());
        for (int i = 0; i < dcbbm.getSize(); i++) {
            System.out.println(dcbbm.getElementAt(i) instanceof ChucVuVM);
        }

        loadTableCV();
        loadTable();
    }

    private void loadTable() {
        dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.setRowCount(0);
        ArrayList<NhanVienVM> listNV = nhanVienService.getAll();

        for (NhanVienVM nhanVien : listNV) {
            dtm.addRow(new Object[]{
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ",
                nhanVien.getNgaySinh(),
                nhanVien.getCCCD(),
                nhanVien.getDiaChi(),
                nhanVien.getSDT(),
                nhanVien.getEmail(),
                nhanVien.getTenCV(),
                nhanVien.getTrangThaiNV() == 1 ? "Đang Làm" : "Đã Nghỉ"
            });
        }

    }

    private void loadTableCV() {
        dtmCV = (DefaultTableModel) tblChucVu.getModel();
        dtmCV.setRowCount(0);

        ArrayList<ChucVuVM> list = chucVuService.getAll();

        for (ChucVuVM cv : list) {
            dtmCV.addRow(new Object[]{
                cv.getId(),
                cv.getMaCV(),
                cv.getTenCV(),}
            );
        }
    }

    private void LamMoicv() {
        txtMaCV.setText("");
        txtTenCV.setText("");
        loadTableCV();
    }

    public void LamMoi() {
        txtMa.setText("");
        txtTen.setText("");
        rdNam.setSelected(true);
        txtNgaySinh.setText("");
        txtCCCD.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        cbbChucVu.setSelectedIndex(0);
        rdDangLam.setSelected(true);
        txtMatKhau.setText("");
        loadTable();
    }

    public boolean checkTrong() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên không để trống");
            txtMa.requestFocus();
            return false;
        }
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Nhân Viên không được để trống");
            txtTen.requestFocus();
            return false;
        }
        if (!rdNam.isSelected() && !rdNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn Giới Tính");
            return false;
        }
        if (!rdDangLam.isSelected() && !rdDaNghi.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn Trạng Thái");
            return false;
        }
        if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại không được để trống");
            txtSDT.requestFocus();
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            txtDiaChi.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống ");
            txtEmail.requestFocus();
            return false;
        }

        String SDT = txtSDT.getText().trim();
        if (!SDT.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại không quá 10 số ");
            txtSDT.requestFocus();
        }
        String CCCD = txtCCCD.getText().trim();
        if (!CCCD.matches("\\d+") || Long.parseLong(CCCD) <= 0) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân phải là số dương");
            txtCCCD.requestFocus();
        }
        String ngaySinh = txtNgaySinh.getText().trim();
        if (!checkNgaySinh(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (yyyy/MM/dd)");
            txtNgaySinh.requestFocus();
            return false;
        }

        String email = txtEmail.getText().trim();
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            txtEmail.requestFocus();
            return false;
        }

        return true;
    }

    private boolean checkNgaySinh(String ngaySinh) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(ngaySinh);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChucVuDialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaCV = new javax.swing.JTextField();
        txtTenCV = new javax.swing.JTextField();
        btnThemCV = new javax.swing.JButton();
        btnSuaCV = new javax.swing.JButton();
        btnLamMoiCV = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        txtSearchByMa = new javax.swing.JTextField();
        btnTimCV = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtNgaySinh = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnChucVu = new javax.swing.JButton();
        rdDangLam = new javax.swing.JRadioButton();
        rdDaNghi = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        ChucVuDialog.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel5.setBackground(new java.awt.Color(222, 229, 226));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ CHỨC VỤ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        jPanel5.setMinimumSize(new java.awt.Dimension(380, 600));

        jPanel8.setBackground(new java.awt.Color(222, 229, 226));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("Mã CV :");

        jLabel13.setText("Tên CV :");

        txtTenCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCVActionPerformed(evt);
            }
        });

        btnThemCV.setText("Thêm");
        btnThemCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCVActionPerformed(evt);
            }
        });

        btnSuaCV.setText("Sửa");
        btnSuaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCVActionPerformed(evt);
            }
        });

        btnLamMoiCV.setText("Làm mới");
        btnLamMoiCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtMaCV))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnThemCV)
                .addGap(35, 35, 35)
                .addComponent(btnSuaCV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoiCV)
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCV)
                    .addComponent(btnSuaCV)
                    .addComponent(btnLamMoiCV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(222, 229, 226));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnTimCV.setText("Tìm");
        btnTimCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimCVActionPerformed(evt);
            }
        });

        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã CV", "Tên CV"
            }
        ));
        tblChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChucVu);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtSearchByMa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimCV, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 132, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchByMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimCV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChucVuDialogLayout = new javax.swing.GroupLayout(ChucVuDialog.getContentPane());
        ChucVuDialog.getContentPane().setLayout(ChucVuDialogLayout);
        ChucVuDialogLayout.setHorizontalGroup(
            ChucVuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ChucVuDialogLayout.setVerticalGroup(
            ChucVuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(222, 231, 227));

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QUẢN LÝ NHÂN VIÊN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel41.setText("Mã NV :");

        jLabel42.setText("Tên NV :");

        jLabel43.setText("Giới tính :");

        jLabel44.setText("Ngày sinh :");

        jLabel45.setText("CCCD :");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel46.setText("Địa chỉ :");

        jLabel47.setText("SĐT :");

        jLabel48.setText("Email :");

        jLabel49.setText("Chức vụ :");

        jLabel50.setText("Trạng thái :");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Chức Vụ ..." }));

        btnChucVu.setText("CV");
        btnChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChucVuActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdDangLam);
        rdDangLam.setText("Đang làm");

        buttonGroup2.add(rdDaNghi);
        rdDaNghi.setText("Đã nghỉ");

        jLabel1.setText("Mật khẩu :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtCCCD)
                            .addComponent(txtTen)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdNu)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh)))
                .addGap(102, 102, 102)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cbbChucVu, 0, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdDangLam)
                        .addGap(18, 18, 18)
                        .addComponent(rdDaNghi))
                    .addComponent(txtEmail)
                    .addComponent(txtSDT)
                    .addComponent(txtDiaChi))
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChucVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(rdDangLam)
                    .addComponent(rdDaNghi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(222, 231, 227));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH NHÂN VIÊN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblNhanVien.setBackground(new java.awt.Color(222, 231, 227));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Họ tên ", "Giới tính", "Ngày sinh ", "CCCD", "Địa chỉ ", "SDT ", "Email", "Chức Vụ ", "Trạng thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel51.setText("Tìm theo mã");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            return;
        }

        if (Optional.ofNullable(tblNhanVien.getValueAt(row, 4)).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được null");
            return;
        }

        String MaNV = tblNhanVien.getValueAt(row, 0).toString();
        String TenNV = tblNhanVien.getValueAt(row, 1).toString();
        String GioiTinh = tblNhanVien.getValueAt(row, 2).toString();
        String NgaySinh = tblNhanVien.getValueAt(row, 3).toString();
        String CCCD = tblNhanVien.getValueAt(row, 4).toString();
        String DiaChi = tblNhanVien.getValueAt(row, 5).toString();
        String SDT = tblNhanVien.getValueAt(row, 6).toString();
        String Email = tblNhanVien.getValueAt(row, 7).toString();
        String TenCV = tblNhanVien.getValueAt(row, 8).toString();
        String TrangThaiNV = tblNhanVien.getValueAt(row, 9).toString();

        txtMa.setText(MaNV);
        txtTen.setText(TenNV);
        if (GioiTinh.equals("Nam")) {
            this.rdNam.setSelected(true);
        } else {
            this.rdNu.setSelected(true);
        }
        txtNgaySinh.setText(NgaySinh);
        txtCCCD.setText(CCCD);
        txtDiaChi.setText(DiaChi);
        txtSDT.setText(SDT);
        txtEmail.setText(Email);
        dcbbm.setSelectedItem(chucVuService.getAll().stream().filter(i -> i.getTenCV().equals(TenCV)).findFirst().get());
        if (TrangThaiNV.equalsIgnoreCase("Đang làm")) {
            this.rdDangLam.setSelected(true);
        } else if (TrangThaiNV.equalsIgnoreCase("Đã nghỉ")) {
            this.rdDaNghi.setSelected(true);
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void txtTenCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCVActionPerformed

    private void btnThemCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCVActionPerformed
        String MaCV = txtMaCV.getText();
        String TenCV = txtTenCV.getText();

        ChucVu cv = new ChucVu();
        cv.setMaCV(MaCV);
        cv.setTenCV(TenCV);

        this.chucVuService.add(cv);
        LamMoicv();
    }//GEN-LAST:event_btnThemCVActionPerformed

    private void btnSuaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCVActionPerformed
        String MaCV = txtMaCV.getText();
        String TenCV = txtTenCV.getText();

        ChucVu chucVu = new ChucVu();
        chucVu.setMaCV(MaCV);
        chucVu.setTenCV(TenCV);

        this.chucVuService.update(chucVu);
        LamMoicv();
    }//GEN-LAST:event_btnSuaCVActionPerformed

    private void btnLamMoiCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCVActionPerformed
        LamMoicv();
    }//GEN-LAST:event_btnLamMoiCVActionPerformed

    private void tblChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVuMouseClicked
        int row = tblChucVu.getSelectedRow();
        if (row == -1) {
            return;
        }

        String MaCV = tblChucVu.getValueAt(row, 1).toString();
        String TenCV = tblChucVu.getValueAt(row, 2).toString();

        txtMaCV.setText(MaCV);
        txtTenCV.setText(TenCV);

    }//GEN-LAST:event_tblChucVuMouseClicked

    private void btnChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChucVuActionPerformed
        ChucVuDialog.setVisible(true); // goi den bang chuc vu
        ChucVuDialog.setLocationRelativeTo(null); // set up bang chuc vu giua man hinh
    }//GEN-LAST:event_btnChucVuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!checkTrong()) {
            return;
        }
        String MaNV = txtMa.getText();
        String TenNV = txtTen.getText();
        int GioiTinh = rdNam.isSelected() == true ? 1 : 0;
        Date NgaySinh = Date.valueOf(txtNgaySinh.getText());
        String CCCD = txtCCCD.getText();
        String DiaChi = txtDiaChi.getText();
        String SDT = txtSDT.getText();
        String Email = txtEmail.getText();
        String matKhau = txtMatKhau.getText();
        UUID TenCV = ((ChucVuVM) cbbChucVu.getSelectedItem()).getId();
        int TrangThai = rdDangLam.isSelected() == true ? 1 : 0;

        if (nhanVienService.existsByMaNV(MaNV)) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên đã tồn tại, vui lòng chọn mã khác.");
            txtMa.requestFocus();
            return;
        }
          if (txtMatKhau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật Khẩu không được để trống");
            txtMatKhau.requestFocus();
            return ;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(MaNV);
        nv.setTenNV(TenNV);
        nv.setGioiTinh(GioiTinh);
        nv.setNgaySinh(NgaySinh);
        nv.setCCCD(CCCD);
        nv.setDiaChi(DiaChi);
        nv.setSDT(SDT);
        nv.setEmail(Email);
        nv.setMatKhau(matKhau);
        nv.setIdCV(TenCV);
        nv.setTrangThaiNV(TrangThai);

        System.out.println(nv);

        this.nhanVienService.add(nv);
        LamMoi();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa.");
            return;
        }
        if (!checkTrong()) {
            return;
        }

        String MaNV = txtMa.getText();
        String TenNV = txtTen.getText();
        int GioiTinh = rdNam.isSelected() == true ? 1 : 0;
        Date NgaySinh = Date.valueOf(txtNgaySinh.getText());
        String CCCD = txtCCCD.getText();
        String DiaChi = txtDiaChi.getText();
        String SDT = txtSDT.getText();
        String Email = txtEmail.getText();
        UUID TenCV = ((ChucVuVM) cbbChucVu.getSelectedItem()).getId();
        int TrangThai = rdDangLam.isSelected() == true ? 1 : 0;

         if (nhanVienService.existsByMaNV(MaNV)) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên đã tồn tại, vui lòng chọn mã khác.");
            txtMa.requestFocus();
            return;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(MaNV);
        nv.setTenNV(TenNV);
        nv.setGioiTinh(GioiTinh);
        nv.setNgaySinh(NgaySinh);
        nv.setCCCD(CCCD);
        nv.setDiaChi(DiaChi);
        nv.setSDT(SDT);
        nv.setEmail(Email);
        nv.setIdCV(TenCV);
        nv.setTrangThaiNV(TrangThai);

        System.out.println(nv);
        this.nhanVienService.update(nv);
        LamMoi();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        LamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimCVActionPerformed

    }//GEN-LAST:event_btnTimCVActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String maNV = txtTimKiem.getText().trim();
        if (maNV != null) {
            dtm = (DefaultTableModel) tblNhanVien.getModel();
            dtm.setRowCount(0);
            ArrayList<NhanVien> listNV = nhanVienService.search(maNV);
            for (NhanVien nhanVien : listNV) {
                dtm.addRow(new Object[]{
                    nhanVien.getMaNV(),
                    nhanVien.getTenNV(),
                    nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ",
                    nhanVien.getNgaySinh(),
                    nhanVien.getCCCD(),
                    nhanVien.getDiaChi(),
                    nhanVien.getSDT(),
                    nhanVien.getEmail(),
                    nhanVien.getTenCV(),
                    nhanVien.getTrangThaiNV() == 1 ? "Đang Làm" : "Đã Nghỉ"
                });
            }
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ChucVuDialog;
    private javax.swing.JButton btnChucVu;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiCV;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaCV;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCV;
    private javax.swing.JButton btnTimCV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdDaNghi;
    private javax.swing.JRadioButton rdDangLam;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaCV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchByMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenCV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
