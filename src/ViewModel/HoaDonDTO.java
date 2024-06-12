/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author Admin
 */
public class HoaDonDTO {
    private String MaHD, TenNV, TenKH, MaSP, TenSP;
    private int TrangThai, SoLuong;
    private float DonGia;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String MaHD, String TenNV, String TenKH, String MaSP, String TenSP, int TrangThai, int SoLuong, float DonGia) {
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.TenKH = TenKH;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TrangThai = TrangThai;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
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

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    @Override
    public String toString() {
        return "hoadonDTO{" + "MaHD=" + MaHD + ", TenNV=" + TenNV + ", TenKH=" + TenKH + ", MaSP=" + MaSP + ", TenSP=" + TenSP + ", TrangThai=" + TrangThai + ", SoLuong=" + SoLuong + ", DonGia=" + DonGia + '}';
    }
}
