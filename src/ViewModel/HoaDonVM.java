/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class HoaDonVM {
    private UUID Id, IdNV, IdKH;
    private String MaHD,TenNV,TenKH;
    private Date NgayTao;
    private int TrangThaiHD;

    public HoaDonVM() {
    }
    
    public HoaDonVM(String maHD, String TenNV, Date ngayTao, int trangThaiHD) {
        this.MaHD = maHD;
        this.TenNV = TenNV;
        this.NgayTao = ngayTao;
        this.TrangThaiHD = trangThaiHD;
    }
    
    public HoaDonVM(String MaHD, String TenNV, String TenKH, Date NgayTao, int TrangThaiHD) {
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.TenKH = TenKH;
        this.NgayTao = NgayTao;
        this.TrangThaiHD = TrangThaiHD;
    }

    public HoaDonVM(UUID Id, UUID IdNV, UUID IdKH, String MaHD, String TenNV, String TenKH, Date NgayTao, int TrangThaiHD) {
        this.Id = Id;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.TenKH = TenKH;
        this.NgayTao = NgayTao;
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

    public int getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(int TrangThaiHD) {
        this.TrangThaiHD = TrangThaiHD;
    }

    @Override
    public String toString() {
        return "HoaDonVM{" + "Id=" + Id + ", IdNV=" + IdNV + ", IdKH=" + IdKH + ", MaHD=" + MaHD + ", TenNV=" + TenNV + ", TenKH=" + TenKH + ", NgayTao=" + NgayTao + ", TrangThaiHD=" + TrangThaiHD + '}';
    }
}
