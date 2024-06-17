/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModel.KhuyenMai;
import DomainModel.SPCT;
import DomainModel.SanPham;
import Repository.IkhuyenMairepons;
import Utiliti.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class KhuyenMaiRepons implements IkhuyenMairepons {

    DBConnection Connection;

    @Override
    public ArrayList<KhuyenMai> getListFormDB() {
        ArrayList<KhuyenMai> khuyenmai = new ArrayList<>();

        try (Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(
                "Select ID,IDHD,MAKM,TENKM,MUCGIAMGIA,THOIGIANBATDAU,THOIGIANKETTHUC,TRANGTHAIKM,SOLUONG\n"
                + "from KHUYENMAI ")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setIdKM(rs.getObject(1, UUID.class));
                km.setIDHD(rs.getObject(2, UUID.class));
                km.setMaKM(rs.getString(3));
                km.setTenKM(rs.getString(4));
                km.setMucGiamGia(rs.getFloat(5));
                km.setThoiGianBatDau(rs.getDate(6));
                km.setThoiGianKetThuc(rs.getDate(7));
                km.setTrangThai(rs.getInt(8));
                km.setSoLuong(rs.getInt(9));

                khuyenmai.add(km);
            }

        } catch (Exception e) {
        }
        return khuyenmai;
    }
//    public List<SanPham>getKMjoinsp(String maSP){
//        ArrayList<SanPham> lisp=new ArrayList<>();
//        try (Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(
//                "select MASP,TENSP,TENKM,MUCGIAMGIA,TRANGTHAIKM   from KHUYENMAI join SANPHAM on KHUYENMAI.IDSP=SANPHAM.ID	 ")) {
//            ResultSet rs = ps.executeQuery();
//            ps.setString(1, maSP);
//            while (rs.next()) {
//             SanPham sp=new SanPham();
//             sp.setMaSP(rs.getString(1));
//             sp.setTenSP(rs.getString(1));
//             
//             
//            }
//
//        } catch (Exception e) {
//        }
//        return null;
//    }
    

//    @Override
//    public Boolean add(KhuyenMai km) {
//         Integer row = 0;
//
//        String sql = "insert into KHUYENMAI (IDSP,MAKM,TENKM,MUCGIAMGIA,THOIGIANBATDAU,THOIGIANKETTHUC,TRANGTHAIKM,SOLUONG) values \n"
//                + "(?,?,?,?,?,?,?,?,?)";
//
//        try {
//            row = JDBCHelper.executeUpdate(sql,
//                    km.getMaKM(),
//                    km.getTenKM(),
//                    km.getMucGiamGia(),
//                    km.getThoiGianBatDau(),
//                    km.getThoiGianKetThuc(),
//                    km.getTrangThai(),
//                    km.getSoLuong()
//                    
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @Override
    public Boolean add(KhuyenMai km) {

        try (
                Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement("insert into KHUYENMAI "
                + "(MAKM,TENKM,MUCGIAMGIA,THOIGIANBATDAU,THOIGIANKETTHUC,TRANGTHAIKM,SOLUONG) values (?,?,?,?,?,?,?)")) {
//                ps.setString(1, km.setIDSP(IDSP));
//            String UUIDSP=UUID.fromString(km.getIDSP());
            
            ps.setString(1, km.getMaKM());
            ps.setString(2, km.getTenKM());
            ps.setFloat(3, km.getMucGiamGia());
            ps.setDate(4, new Date(km.getThoiGianBatDau().getTime()));
            ps.setDate(5, new Date(km.getThoiGianKetThuc().getTime()));
            ps.setInt(6, km.getTrangThai());
            ps.setInt(7, km.getSoLuong());
            ps.executeUpdate();
//            System.out.println(SelectSPByTen(km.getTenKM()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(KhuyenMai KM) {
        try (
                Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE KHUYENMAI SET TENKM= ? ,MUCGIAMGIA= ? ,THOIGIANBATDAU= ? ,THOIGIANKETTHUC= ? ,TRANGTHAIKM= ?  WHERE MAKM= ?")) {
            ps.setString(6, KM.getMaKM());
            ps.setString(1, KM.getTenKM());
            ps.setFloat(2, KM.getMucGiamGia());
            ps.setDate(3, new Date(KM.getThoiGianBatDau().getTime()));
            ps.setDate(4, new Date(KM.getThoiGianKetThuc().getTime()));
            ps.setObject(5, KM.getTrangThai());
//            ps.setObject(7, KM.getSoLuong());

            ps.executeUpdate();
//            System.out.println(SelectSPByTen(km.getTenKM()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public ArrayList<KhuyenMai> search() {
        ArrayList<KhuyenMai> listkm = new ArrayList<>();

        try (Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement("select TENKM ,MUCGIAMGIA ,THOIGIANBATDAU ,THOIGIANKETTHUC ,TRANGTHAIKM, SOLUONG WHERE MAKM= ?");) {
            ps.executeUpdate();

            KhuyenMai km = new KhuyenMai();
            ps.setObject(1, km.getMaKM());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                km.setTenKM(rs.getString(1));
                km.setMucGiamGia(rs.getFloat(2));
                km.setThoiGianBatDau(rs.getDate(3));
                km.setThoiGianKetThuc(rs.getDate(4));
                km.setTrangThai(rs.getInt(5));
                km.setSoLuong(rs.getInt(6));

                listkm.add(km);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listkm;
    }
     public List<SPCT> getTransactionHistoryByCustomer(String maSPCT) {
        List<SPCT> sp = new ArrayList<>();
//        List<KhuyenMai>list = new ArrayList<>();
        try {
            Connection con = Connection.getConnection();
            String sql = "select MASPCT,TENSP,TENKC,TENMS,TENCL,t.TENTH,DONGIA,TENKM,IDTH,IDKM,DONGIA from KHUYENMAICHITIET kmct join KHUYENMAI km on kmct.IDKM=km.ID join SANPHAMCHITIET spct on spct.ID = kmct.IDSPCT \n" +
"									join THUONGHIEU th on th.ID = spct.IDTH\n" +
"									join CHATLIEU cl on cl.ID = spct.IDCL \n" +
"									join THUONGHIEU t on t.ID = spct.IDTH\n" +
"									join MAUSAC ms on ms.ID=spct.IDMS\n" +
"									join SANPHAM sp on sp.ID=spct.IDSP\n" +
"									join KICHCO kc on kc.ID=spct.IDKC where MAKM = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSPCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT();
//                KhuyenMai km=new KhuyenMai();
                spct.setMaSPCT(rs.getString("MASPCT"));
                spct.setTenSP(rs.getString("TENSP"));
                spct.setTenKC(rs.getString("TENKC"));
                spct.setTenMS(rs.getString("TENMS"));
                spct.setTenCL(rs.getString("TENCL"));
                spct.setTenTH(rs.getString("TENTH"));
                spct.setDonGia(Float.valueOf(rs.getString("DONGIA")));
                spct.setKM(rs.getString("TENKM"));
                sp.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

}
