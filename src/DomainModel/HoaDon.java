/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private UUID Id, IdNV, IdKH;
    private String MaHD,TenNV,TenKH;
    private Date NgayTao,NgayThanhToan;
    private float TongTien;
    private int TrangThaiHD;

    public HoaDon() {
    }
    
    public HoaDon(String maHd, int TrangThaiHD, Date ngayTao) {
         this.MaHD = maHd;
         this.TrangThaiHD = TrangThaiHD;
         this.NgayTao = ngayTao;
    }
    
    public HoaDon(String maHd, String TenNV, String tenKH, Date ngayTao, int TrangThaiHD) {
        this.MaHD = maHd;
        this.TenNV = TenNV;
        this.TenKH = tenKH;
        this.NgayTao = ngayTao;
        this.TrangThaiHD = TrangThaiHD;
    }
    
    public HoaDon(String maHd, String TenNV, String tenKH, Date ngayTao, Date NgayThanhToan, int TrangThaiHD) {
        this.MaHD = maHd;
        this.TenNV = TenNV;
        this.TenKH = tenKH;
        this.NgayTao = ngayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TrangThaiHD = TrangThaiHD;
    }

    public HoaDon(UUID Id, UUID IdNV, UUID IdKH, String MaHD, String TenNV, String TenKH, Date NgayTao, Date NgayThanhToan, float TongTien, int TrangThaiHD) {
        this.Id = Id;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.TenKH = TenKH;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TongTien = TongTien;
        this.TrangThaiHD = TrangThaiHD;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID Id) {
        this.Id = Id;
    }

    public UUID getIdNV() {
        return IdNV;
    }

    public void setIdNV(UUID IdNV) {
        this.IdNV = IdNV;
    }

    public UUID getIdKH() {
        return IdKH;
    }

    public void setIdKH(UUID IdKH) {
        this.IdKH = IdKH;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(int TrangThaiHD) {
        this.TrangThaiHD = TrangThaiHD;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "Id=" + Id + ", IdNV=" + IdNV + ", IdKH=" + IdKH + ", MaHD=" + MaHD + ", TenNV=" + TenNV + ", TenKH=" + TenKH + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", TongTien=" + TongTien + ", TrangThaiHD=" + TrangThaiHD + '}';
    }
}
