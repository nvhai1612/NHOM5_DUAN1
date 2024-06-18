/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.HoaDon;
import Repository.IHDCTRepos;
import Utiliti.DBConnection;
import ViewModel.HoaDonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class HDCTRepos implements IHDCTRepos {

    private DBConnection connection;

    private SPCTRepos SPCTRepos = new SPCTRepos();
    private HoaDonRepos HoaDonRepos = new HoaDonRepos();

    @Override
    public ArrayList<HoaDonDTO> getListFormDB() {
        ArrayList<HoaDonDTO> ListHDDTO = new ArrayList<>();

        try (Connection con = connection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT MAHD, TENNV, TENKH, kh.SDT, kh.DIACHI, TRANGTHAIHD,hd.NGAYTAO FROM HOADON hd  LEFT JOIN KHACHHANG kh ON kh.ID = hd.IDKH LEFT JOIN NHANVIEN nv ON nv.ID = hd.IDNV ")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonDTO hddto = new HoaDonDTO();
                hddto.setMaHD(rs.getString(1));
                hddto.setTenNV(rs.getString(2));
                hddto.setTenKH(rs.getString(3));
                hddto.setSDT(rs.getString(4));
                hddto.setDiachi(rs.getString(5));
                hddto.setTrangThai(rs.getInt(6));
                hddto.setNgayTao(rs.getString(7));            
                ListHDDTO.add(hddto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListHDDTO;
    }

    @Override
    public Boolean add(HoaDon hd, Map<String, Integer> ctsps) {
        ctsps.keySet().forEach((t) -> {
            System.out.println(t);
        });
        for (String maSp : ctsps.keySet()) {
            try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO hoadonct(idhd,idspct,mahdct,dongia,soluong) Values(?,?,?,?,?)")) {

                ps.setObject(1, hd.getId());
                ps.setObject(2, SPCTRepos.searchbyMaSp(maSp).getId());
                ps.setObject(3, new Random().nextInt(10000));
                ps.setObject(4, SPCTRepos.searchbyMaSp(maSp).getDonGia());
                ps.setObject(5, ctsps.get(maSp));

                ps.executeUpdate();
//            return check > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Boolean update(HoaDon hd, Map<String, Integer> ctsps) {
        ctsps.keySet().forEach((t) -> {
            System.out.println(t);
        });
        for (String maSp : ctsps.keySet()) {
            try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                    "UPDATE hoadonct SET idhd = ?, idspct = ?, mahdct = ?, dongia = ?, soluong = ? WHERE Id = ?")) {

                ps.setObject(1, hd.getId());
                ps.setObject(2, SPCTRepos.searchbyMaSp(maSp).getId());
                ps.setObject(3, HoaDonRepos.searchbyMaHDCT(maSp));
                ps.setObject(4, SPCTRepos.searchbyMaSp(maSp).getDonGia());
                ps.setObject(5, ctsps.get(maSp));

                ps.executeUpdate();
//            return check > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Boolean delete(HoaDon hd, Map<String, Integer> ctsps) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public HoaDon searchbyMaHDCT(String ma) {
        ArrayList<HoaDon> listHDCT = new ArrayList<>();
        try (Connection con = connection.getConnection(); PreparedStatement ps
                = con.prepareStatement("select hd.mahdct from hoadonct hd join sanphamchitiet sp on hd.idspct = sp.id join hoadon h on h.id = hd.idhd where sp.maspct = ? and h.mahd = ?");) {

            ps.setObject(1, ma);

//            ps.executeUpdate();
            HoaDon hdct = new HoaDon();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hdct.setId(UUID.fromString(ma));
                return hdct;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> search(String ma) {
        ArrayList<HoaDonDTO> hoadonseach = new ArrayList<>();
        try {
            Connection coon = connection.getConnection();
            String sql = "select MaHD,TENKH,TENNV,h.TRANGTHAIHD,MASP,TENSP,SOLUONG,hc.DONGIA from hoadonct hc left join hoadon h on h.id = hc.IDHD left join SANPHAMCHITIET sc on sc.id = hc.IDSPCT left join sanpham s on s.id = sc.IDSP left join NHANVIEN on NHANVIEN.ID=h.IDNV left join  KHACHHANG on KHACHHANG.ID=h.IDKH where MAHD like ?";
            PreparedStatement prsm = coon.prepareStatement(sql);
            prsm.setString(1, "%" + ma + "%");

            ResultSet rs = prsm.executeQuery();
            while (rs.next()) {
                HoaDonDTO kh = new HoaDonDTO();
                kh.setMaHD(rs.getString("MaHD"));
                kh.setTrangThai(Integer.valueOf(rs.getString("TRANGTHAIHD")));
                kh.setTenNV(rs.getString("TENNV"));
                kh.setTenKH(rs.getString("TENKH"));

                hoadonseach.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoadonseach;
    }

}
