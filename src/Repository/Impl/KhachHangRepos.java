/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.HoaDon;
import DomainModel.KhachHang;
import Repository.IKhachHangRepos;
import Utiliti.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class KhachHangRepos implements IKhachHangRepos{

    DBConnection connection;
    @Override
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> listKH = new ArrayList<>();

        try (Connection con = connection.getConnection();
               PreparedStatement ps = con.prepareStatement("SELECT MaKH, TenKH, NgaySinh, GioiTinh, Sdt, DiaChi FROM KhachHang")) {
                ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MAKH"));
                kh.setTenKH(rs.getString("TENKH"));
                kh.setNgaySinh(rs.getString("NGAYSINH"));
                kh.setGioiTinh(rs.getInt("GIOITINH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DIACHI"));
           
                listKH.add(kh);
            }

        } catch (Exception e) {
        }
        return listKH;
    }

    @Override
    public Boolean add(KhachHang kh) {
        int check;
        try {
            Connection coon = connection.getConnection();
            PreparedStatement prsm = coon.prepareStatement("INSERT INTO KHACHHANG (maKH, tenKH, ngaySinh, gioiTinh, sdt, diaChi) VALUES (?, ?, ?, ?, ?, ?)");
                prsm.setString(1, kh.getMaKH());
                prsm.setString(2, kh.getTenKH());
                prsm.setString(3, kh.getNgaySinh());
                prsm.setInt(4, kh.getGioiTinh());
                prsm.setString(5, kh.getSdt());
                prsm.setString(6, kh.getDiaChi());
                check = prsm.executeUpdate();
                return check>0;
        } catch (Exception e) {
             e.printStackTrace();
            return false;
        }    
    }
    @Override
    public Boolean update(KhachHang kh) {
        int check;
        try {
            Connection coon = connection.getConnection();
            PreparedStatement prsm = coon.prepareStatement("UPDATE KHACHHANG SET TENKH = ?, NGAYSINH = ?, GIOITINH = ?, SDT = ?, DIACHI = ? WHERE MAKH = ?");
            prsm.setString(6, kh.getMaKH());
            prsm.setString(1, kh.getTenKH());               
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(kh.getNgaySinh());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            prsm.setDate(2, sqlDate);          
            prsm.setInt(3, kh.getGioiTinh());
            prsm.setString(4, kh.getSdt());
            prsm.setString(5, kh.getDiaChi());
            
            check = prsm.executeUpdate();
            return check > 0;
        } catch (Exception e) {
             e.printStackTrace();
            return false;
        }    
    }


    @Override
    public ArrayList<KhachHang> search(String ma) {
        ArrayList<KhachHang> khachHangList = new ArrayList<>();
    try {
        Connection coon = connection.getConnection();
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH LIKE ?";
        PreparedStatement prsm = coon.prepareStatement(sql);
        prsm.setString(1, "%" + ma + "%");

        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            KhachHang kh = new KhachHang();
            kh.setId(UUID.fromString(rs.getString("id")));
            kh.setMaKH(rs.getString("MAKH"));
            kh.setTenKH(rs.getString("TENKH"));
            kh.setNgaySinh(rs.getString("NGAYSINH"));
            kh.setGioiTinh(rs.getInt("GIOITINH"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DIACHI"));
            khachHangList.add(kh);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return khachHangList;
    }
    
    public List<HoaDon> getLichSuaGiaoDich(String maKH) {
        List<HoaDon> lichSu = new ArrayList<>();
        try {
            Connection con = connection.getConnection();
            String sql = "select hd.MAHD,hd.NGAYTAO,hd.TONGTIEN from KHACHHANG kh join HOADON hd on hd.IDKH = kh.ID where kh.maKH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MAHD"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setTongTien(rs.getFloat("TONGTIEN"));
                lichSu.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lichSu;
    }
    
}
