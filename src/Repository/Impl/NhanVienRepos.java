/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.NhanVien;
import Repository.INhanVienRepos;
import Utiliti.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhanVienRepos implements INhanVienRepos {

    DBConnection connection;

    @Override
    public ArrayList<NhanVien> getListFormDB() {
        ArrayList<NhanVien> list = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV = CHUCVU.ID";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MANV"));
                nhanVien.setTenNV(rs.getString("TENNV"));
                nhanVien.setGioiTinh(rs.getInt("GIOITINH"));
                nhanVien.setNgaySinh(rs.getDate("NGAYSINH"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setDiaChi(rs.getString("DIACHI"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setEmail(rs.getString("EMAIL"));
                nhanVien.setMatKhau(rs.getString("MATKHAU"));
                nhanVien.setTrangThaiNV(rs.getInt("TRANGTHAINV"));
                nhanVien.setIdCV(rs.getObject("IDCV", UUID.class));
                list.add(nhanVien);
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public Boolean add(NhanVien nv) {
        int check;
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO NHANVIEN(MANV, TENNV, GIOITINH, NGAYSINH, CCCD, DIACHI, SDT, EMAIL,MATKHAU, TRANGTHAINV, IdCV ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setInt(3, nv.getGioiTinh());
            ps.setDate(4, (Date) nv.getNgaySinh());
            ps.setString(5, nv.getCCCD());
            ps.setString(6, nv.getDiaChi());
            ps.setString(7, nv.getSDT());
            ps.setString(8, nv.getEmail());
            ps.setString(9, nv.getMatKhau());
            ps.setInt(10, nv.getTrangThaiNV());
            ps.setObject(11, nv.getIdCV());
            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(NhanVien nv) {
        int check;
        try (Connection conn = connection.getConnection()) {
            String sql = "UPDATE NHANVIEN SET TENNV=?, GIOITINH=?, NGAYSINH=?, CCCD=?, DIACHI=?, SDT=?, EMAIL=?, TRANGTHAINV=?, IdCV=? WHERE MANV=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nv.getTenNV());
            ps.setInt(2, nv.getGioiTinh());
            ps.setDate(3, (Date) nv.getNgaySinh());
            ps.setString(4, nv.getCCCD());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getSDT());
            ps.setString(7, nv.getEmail());
//            ps.setString(8, nv.getMatKhau());
            ps.setInt(8, nv.getTrangThaiNV());
            ps.setObject(9, nv.getIdCV());
            ps.setString(10, nv.getMaNV());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<NhanVien> search(String MaNV) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT "
                + "NHANVIEN.MANV,"
                + "NHANVIEN.TENNV,"
                + "NHANVIEN.GIOITINH,"
                + "NHANVIEN.NGAYSINH,"
                + "NHANVIEN.CCCD,"
                + "NHANVIEN.DIACHI,"
                + "NHANVIEN.SDT,"
                + "NHANVIEN.EMAIL,"
                + "NHANVIEN.TRANGTHAINV,"
                + "CHUCVU.TenCV "
                + "FROM NHANVIEN "
                + "JOIN CHUCVU ON NHANVIEN.IDCV = CHUCVU.ID "
                + "WHERE NHANVIEN.MANV LIKE ?"; // Thay đổi điều kiện tìm kiếm
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + MaNV + "%");
//            ps.setString(2, "%" + MaNV + "%"); // Thêm điều kiện tìm kiếm cho tên nhân viên

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(rs.getString("MANV"));
                    nhanVien.setTenNV(rs.getString("TENNV"));
                    nhanVien.setGioiTinh(rs.getInt("GIOITINH"));
                    nhanVien.setNgaySinh(rs.getDate("NGAYSINH"));
                    nhanVien.setCCCD(rs.getString("CCCD"));
                    nhanVien.setDiaChi(rs.getString("DIACHI"));
                    nhanVien.setSDT(rs.getString("SDT"));
                    nhanVien.setEmail(rs.getString("EMAIL"));
                    nhanVien.setTrangThaiNV(rs.getInt("TRANGTHAINV"));
                    nhanVien.setTenCV(rs.getString("TenCV"));
                    list.add(nhanVien);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String SelectById(UUID id) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT TENCV FROM CHUCVU WHERE ID = ?")) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Boolean existsByMaNV(String MaNV) {
        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE MANV = ?";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<NhanVien> CheckLogin(String Email, String MatKhau) {
        String sql = "SELECT * FROM NHANVIEN WHERE EMAIL = ? AND MATKHAU = ?";
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, Email);
            ps.setString(2, MatKhau);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(UUID.fromString(rs.getString("Id")));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getInt("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTrangThaiNV(rs.getInt("TrangThaiNV"));
                nv.setIdCV(UUID.fromString(rs.getString("IdCV")));
                nv.setMaNV(rs.getString("MaNV"));
                return Optional.of(nv);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public UUID SelectByname(String id) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID FROM NHANVIEN WHERE TENNV = ? or email = ?")) {
            if (id.contains("@")) {
                ps.setObject(2, id);
                ps.setObject(1, "");

            } else {
                ps.setObject(1, id);
                ps.setObject(2, "");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return UUID.fromString(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<NhanVien> findByEmail(String email) {
        String sql = "SELECT * FROM NHANVIEN WHERE EMAIL = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(UUID.fromString(rs.getString("Id")));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getInt("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTrangThaiNV(rs.getInt("TrangThaiNV"));
                nv.setIdCV(UUID.fromString(rs.getString("IdCV")));
                nv.setMaNV(rs.getString("MaNV"));
                return Optional.of(nv);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Boolean updateMatKhau(String maNV, String newMatKhau) {
    String sql = "UPDATE NHANVIEN SET MATKHAU = ? WHERE MANV = ?";
    try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, newMatKhau);
        ps.setString(2, maNV);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    @Override
    public Boolean checkMK(String maNV, String matKhauCu) {
       String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE MANV = ? AND MATKHAU = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNV);
            stmt.setString(2, matKhauCu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
