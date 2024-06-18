/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.SPCT;
import DomainModel.SanPham;
import Repository.ISPCTRepos;
import Utiliti.DBConnection;
import Utiliti.SessionData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class SPCTRepos implements ISPCTRepos{
    private DBConnection connection;
    private SanPhamRepos SanPhamRepos = new SanPhamRepos();
    private SanPham sp = new SanPham();

    @Override
    public ArrayList<SPCT> getListFormDB() {
        ArrayList<SPCT> listSPCT = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT MASPCT,TENSP,SOLUONGTON,NGUOITAO,TRANGTHAISPCT,IDCL,IDKC,IDMS,IDTH,DONGIA FROM SANPHAMCHITIET"
                + " JOIN SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID JOIN CHATLIEU ON SANPHAMCHITIET.IDCL = CHATLIEU.ID JOIN"
                + " KICHCO ON SANPHAMCHITIET.IDKC = KICHCO.ID JOIN THUONGHIEU ON SANPHAMCHITIET.IDTH = THUONGHIEU.ID"
                + " JOIN MAUSAC ON SANPHAMCHITIET.IDMS = MAUSAC.ID")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SPCT spct = new SPCT();
                spct.setMaSPCT(rs.getString(1));
                spct.setTenSP(rs.getString(2));
                spct.setSoLuongTon(rs.getInt(3));
                spct.setNguoiTao(rs.getString(4));
                spct.setTrangThaiSPCT(rs.getInt(5));
                spct.setIdCL(rs.getObject(6, UUID.class));
                spct.setIdKC(rs.getObject(7, UUID.class));
                spct.setIdMS(rs.getObject(8, UUID.class));
                spct.setIdTH(rs.getObject(9, UUID.class));
                spct.setDonGia(rs.getFloat(10));
                listSPCT.add(spct);
            }

        } catch (Exception e) {
        }
        return listSPCT;
    }

    @Override
    public Boolean add(SPCT spct) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "INSERT INTO SANPHAMCHITIET"
                        + "(MASPCT,IDSP,SOLUONGTON,IDCL,IDKC,IDMS,IDTH,DONGIA,TRANGTHAISPCT,NGUOITAO) Values(?,?,?,?,?,?,?,?,?,?)")) {

            ps.setObject(1, ("SP0" + new Random().nextInt(10000)));
            ps.setObject(2, spct.getIdSP());
            ps.setObject(3, spct.getSoLuongTon());
            ps.setObject(4, spct.getIdCL());
            ps.setObject(5, spct.getIdKC());
            ps.setObject(6, spct.getIdMS());
            ps.setObject(7, spct.getIdTH());
            ps.setObject(8, spct.getDonGia());
            ps.setObject(9, spct.getTrangThaiSPCT());
            ps.setObject(10, SessionData.account);

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(SPCT spct) {
        int check;

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE SANPHAMCHITIET SET IDSP = ?,SOLUONGTON = ?,IDCL = ?,IDKC = ?, IDMS = ?,IDTH = ?,DONGIA = ?, TRANGTHAISPCT = ?, NGUOITAO = ? WHERE MASPCT = ?")) {

            ps.setObject(1, spct.getIdSP());
            ps.setObject(2, spct.getSoLuongTon());
            ps.setObject(3, spct.getIdCL());
            ps.setObject(4, spct.getIdKC());
            ps.setObject(5, spct.getIdMS());
            ps.setObject(6, spct.getIdTH());
            ps.setObject(7, spct.getDonGia());
            ps.setObject(8, spct.getTrangThaiSPCT());
            ps.setObject(9, spct.getNguoiTao());
            ps.setObject(10, spct.getMaSPCT());

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    
    }
    
    public Boolean updateSL(String maSp, int sl) {
        int check;
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE SANPHAMCHITIET SET SOLUONGTON = ? WHERE MASPCT = ?")) {

            ps.setObject(1, sl);
            ps.setObject(2, maSp);

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    

    }
    
    public Boolean updateTrangThaiSP(String maSpct, int TrangThaiSPCT) {
        int check;
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE SANPHAMCHITIET SET TrangThaiSPCT = ? WHERE MASPCT = ?")) {

            ps.setObject(1, TrangThaiSPCT);
            ps.setObject(2, maSpct);

            check = ps.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    

    }

    @Override
    public Boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SPCT> search(String MaSPCT) {
        ArrayList<SPCT> ListSPCT = new ArrayList<>();
        try {
            Connection con = connection.getConnection();
            PreparedStatement pr = con.prepareStatement("SELECT IDSP,SOLUONGTON,NGUOITAO,TRANGTHAISPCT,IDCL,IDKC,IDMS,IDTH,DONGIA FROM SANPHAMCHITIET WHERE MASPCT LIKE ? AND TENSP LIKE ?");
            pr.setString(1, "%" + MaSPCT + "%");

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT();
                spct.setIdSP(rs.getObject(1, UUID.class));
                spct.setSoLuongTon(rs.getInt(2));
                spct.setNguoiTao(rs.getString(3));
                spct.setTrangThaiSPCT(rs.getInt(4));
                spct.setIdCL(rs.getObject(5, UUID.class));
                spct.setIdKC(rs.getObject(6, UUID.class));
                spct.setIdMS(rs.getObject(7, UUID.class));
                spct.setIdTH(rs.getObject(8, UUID.class));
                spct.setDonGia(rs.getFloat(9));
                spct.setMaSPCT(rs.getString(10));
//                spct.setMaSPCT(rs.getString("MASPCT"));
//                spct.setTenSP(rs.getString("TenSP"));
//                spct.setSoLuongTon(rs.getInt("SoLuongTon"));
//                spct.setNguoiTao(rs.getString("NguoiTao"));
//                spct.setTrangThaiSPCT(rs.getInt("TrangThaiSPCT"));
//                spct.setTenCL(rs.getString("TENCL"));
//                spct.setTenKC(rs.getString("TENKC"));
//                spct.setTenMS(rs.getString("TENMS"));
//                spct.setTenTH(rs.getString("TENTH"));
//                spct.setDonGia(rs.getFloat("DonGia"));
                ListSPCT.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListSPCT;
    }
    
    public SPCT searchbyMaSp(String ma) {
        ArrayList<SPCT> listCTSP = new ArrayList<>();
        try (Connection con = connection.getConnection();
                PreparedStatement ps = 
                        con.prepareStatement
        ("SELECT MASPCT,IDSP,SOLUONGTON,IDCL,IDKC,IDMS,IDTH,DONGIA,SANPHAMCHITIET.id FROM SANPHAMCHITIET JOIN SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID JOIN CHATLIEU ON SANPHAMCHITIET.IDCL = CHATLIEU.ID JOIN KICHCO ON SANPHAMCHITIET.IDKC = KICHCO.ID JOIN THUONGHIEU ON SANPHAMCHITIET.IDTH = THUONGHIEU.ID JOIN MAUSAC ON SANPHAMCHITIET.IDMS = MAUSAC.ID WHERE MASPCT = ?");) {

                       ps.setObject(1, ma);

//            ps.executeUpdate();

            SPCT ctsp = new SPCT();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ctsp.setId(UUID.fromString(rs.getString(9)));
                ctsp.setMaSPCT(rs.getString(1));
                ctsp.setTenSP(rs.getString(2));
                ctsp.setSoLuongTon(rs.getInt(3));
                ctsp.setTenCL(rs.getString(4));
                ctsp.setTenKC(rs.getString(5));
                ctsp.setTenMS(rs.getString(6));
                ctsp.setTenTH(rs.getString(7));
                ctsp.setDonGia(rs.getFloat(8));
                return ctsp;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public UUID SelectSPByTen(String TenSP) {
        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT ID FROM SANPHAM WHERE MASP = ?")) {

            ps.setObject(1, TenSP);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                sp.setTenSP(TenSP);
                sp.setMaSP("SP0" + new Random().nextInt(10000));
                sp.setTrangThaiSP(1);
                System.out.println("hai");
                UUID a = SanPhamRepos.add(sp);
                System.out.println(a);
                return a;
            }
            while (rs.next()) {
                return rs.getObject(1, UUID.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String SelectCLById(UUID IdCL) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT TENCL FROM CHATLIEU WHERE ID = ?")) {
            ps.setObject(1, IdCL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }  

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String SelectKCById(UUID IdKC) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT TENKC FROM KICHCO WHERE ID = ?")) {
            ps.setObject(1, IdKC);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String SelectMSById(UUID IdMS) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT TENMS FROM MAUSAC WHERE ID = ?")) {
            ps.setObject(1, IdMS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String SelectTHById(UUID IdTH) {

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT TENTH FROM THUONGHIEU WHERE ID = ?")) {
            ps.setObject(1, IdTH);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<SPCT> getListForm() {
        ArrayList<SPCT> listSPCT = new ArrayList<>();

        try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT MASPCT,TENSP,SOLUONGTON,NGUOITAO,TRANGTHAISPCT,IDCL,IDKC,IDMS,IDTH,DONGIA FROM SANPHAMCHITIET"
                + " JOIN SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID JOIN CHATLIEU ON SANPHAMCHITIET.IDCL = CHATLIEU.ID JOIN"
                + " KICHCO ON SANPHAMCHITIET.IDKC = KICHCO.ID JOIN THUONGHIEU ON SANPHAMCHITIET.IDTH = THUONGHIEU.ID"
                + " JOIN MAUSAC ON SANPHAMCHITIET.IDMS = MAUSAC.ID where TrangThaiSPCT = 1")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SPCT spct = new SPCT();
                spct.setMaSPCT(rs.getString(1));
                spct.setTenSP(rs.getString(2));
                spct.setSoLuongTon(rs.getInt(3));
                spct.setNguoiTao(rs.getString(4));
                spct.setTrangThaiSPCT(rs.getInt(5));
                spct.setIdCL(rs.getObject(6, UUID.class));
                spct.setIdKC(rs.getObject(7, UUID.class));
                spct.setIdMS(rs.getObject(8, UUID.class));
                spct.setIdTH(rs.getObject(9, UUID.class));
                spct.setDonGia(rs.getFloat(10));
                listSPCT.add(spct);
            }

        } catch (Exception e) {
        }
        return listSPCT;
    }
    
    
}
