/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.HoaDon;
import DomainModel.SPCT;
import Repository.IHoaDonRepos;
import Utiliti.DBConnection;
import ViewModel.HoaDonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class HoaDonRepos implements IHoaDonRepos{
    DBConnection connection;

    @Override
    public ArrayList<HoaDon> getListFormDB() {
        ArrayList<HoaDon> listHD = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * from HoaDon left join NHANVIEN NV on HOADON.IDNV = NV.ID WHERE TRANGTHAIHD = 1")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(4));
                hd.setNgayTao(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5)));
                hd.setTenNV(rs.getString(11));
                hd.setTrangThaiHD(rs.getInt(8));
                listHD.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listHD;
    }
    
    public ArrayList<HoaDon> getListHoaDonFormDB() {
        ArrayList<HoaDon> listHD = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("select MAHD,IDNV,NGAYTAO,TRANGTHAIHD from HoaDon where TRANGTHAIHD = 0 order by TRANGTHAIHD asc")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(4));
//                hd.setTenNV(rs.getString(11));
                hd.setNgayTao(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5)));
                hd.setTrangThaiHD(rs.getInt(8));
                listHD.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listHD;
    }

    public HoaDon findHoaDonByMa(String maHd) {
        ArrayList<HoaDon> listCV = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * from HoaDon where mahd = ?")) {
            ps.setObject(1, maHd);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//                System.out.println(rs.getDate(4));
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(4));
                hd.setId(UUID.fromString(rs.getString(1)));
                hd.setNgayTao(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(5)));
                hd.setTrangThaiHD(rs.getInt(8));
                return hd;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean add(HoaDon hd) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO HOADON (IDNV, NGAYTAO, TRANGTHAIHD, MAHD) VALUES (?, ?, ?,?)")) {

            ps.setObject(1, hd.getIdNV());
            ps.setObject(2, hd.getNgayTao());
            ps.setObject(3, hd.getTrangThaiHD());
            ps.setObject(4, hd.getMaHD());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(HoaDon hd) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE hoadon SET TRANGTHAIHD = ?, IDNV = ?, IDKH = ? where MAHD = ?")) {

            ps.setObject(2, hd.getIdNV());
            ps.setObject(3, hd.getIdKH());
            ps.setObject(1, hd.getTrangThaiHD());
            ps.setObject(4, hd.getMaHD());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public HoaDonDTO searchbyMaHDCT(String ma) {
        ArrayList<HoaDonDTO> listHDCT = new ArrayList<>();
        try (Connection con = connection.getConnection(); PreparedStatement ps
                = con.prepareStatement("SELECT * FROM     HOADON INNER JOIN\n"
                        + "                  HOADONCT ON HOADON.ID = HOADONCT.IDHD INNER JOIN\n"
                        + "                  SANPHAMCHITIET ON HOADONCT.IDSPCT = SANPHAMCHITIET.ID Join\n"
                        + "				  SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID WHERE MAHDCT = ?");) {
            ps.setObject(1, ma);

//            ps.executeUpdate();
            HoaDonDTO dto = new HoaDonDTO();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dto.setMaSP(rs.getString(31));
                dto.setTenSP(rs.getString(32));
                dto.setSoLuong(rs.getInt(17));
                dto.setDonGia(rs.getFloat(29));
                return dto;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public ArrayList<SPCT> HoaDonCho(String MaHD) {
        ArrayList<SPCT> listSPCT = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT MASPCT,TENSP,SOLUONG,SANPHAMCHITIET.DONGIA FROM HOADON INNER JOIN\n"
                + "                  HOADONCT ON HOADON.ID = HOADONCT.IDHD INNER JOIN\n"
                + "                  SANPHAMCHITIET ON HOADONCT.IDSPCT = SANPHAMCHITIET.ID Join\n"
                + "				  SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID WHERE MAHD = ? ")) {

            ps.setObject(1, MaHD);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SPCT spct = new SPCT();
                spct.setMaSPCT(rs.getString(1));
                spct.setTenSP(rs.getString(2));
                spct.setSoLuongTon(rs.getInt(3));
                spct.setDonGia(rs.getFloat(4));
                listSPCT.add(spct);
            }

        } catch (Exception e) {
        }
        return listSPCT;
    }

    public String findMaaHDCtBySpct(String maSPCT, String ma) {
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "select hd.mahdct from hoadonct hd "
                + " join sanphamchitiet sp on hd.idspct = sp.id"
                + " join hoadon h on h.id = hd.idhd where sp.maspct = ? and h.mahd = ?")) {

            ps.setObject(1, maSPCT);
            ps.setObject(2, ma);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateSlHdCT(String maHDCT, Integer sl, Double donGia, String mahd) {
        String mahdct = findMaaHDCtBySpct(maHDCT, mahd);
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("update hoadonct set soluong = ?, dongia = ? where mahdct = ?")) {

            ps.setObject(2, donGia);
            ps.setObject(3, mahdct);
            ps.setObject(1, sl);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findHdctByMaHdct(String maHDCT, String ma) {
        String mahdct = findMaaHDCtBySpct(maHDCT, ma);
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("select hd.soluong from hoadonct hd "
                + "join sanphamchitiet sp on sp.id = hd.idspct"
                + "join hoadon h on h.id = hd.idhd where sp.maspct = ? and h.mahd = ?")) {

            ps.setObject(1, maHDCT);
            ps.setObject(2, ma);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void updateTrangThaiHoaDon(String maHDCT, Integer TrangThaiHD, Float TongTien, String mahd) {
        String mahdct = findMaaHDCtBySpct(maHDCT, mahd);
        try (Connection con = connection.getConnection(); 
                PreparedStatement ps = con.prepareStatement("UPDATE HOADON SET TRANGTHAIHD = 0, TONGTIEN = ? WHERE MAHD = ?")) {

            ps.setObject(1, TongTien);
            ps.setObject(2, mahd);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateXoaSPGH(String MaHD, String MaHDCT, String MaSPCT, Integer SL){
        
    }

    @Override
    public Boolean delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
