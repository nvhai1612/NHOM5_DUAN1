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
    private String MaHD, TenNV, TenKH, MaSP, TenSP,Diachi,SDT,ngayTao,LyDoHuy;
    private int TrangThai, SoLuong;
    private float DonGia;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String MaHD, String TenNV, String TenKH, String MaSP, String TenSP, String Diachi, String SDT, String ngayTao, String LyDoHuy, int TrangThai, int SoLuong, float DonGia) {
        this.MaHD = MaHD;
        this.TenNV = TenNV;
        this.TenKH = TenKH;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Diachi = Diachi;
        this.SDT = SDT;
        this.ngayTao = ngayTao;
        this.LyDoHuy = LyDoHuy;
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

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getLyDoHuy() {
        return LyDoHuy;
    }

    public void setLyDoHuy(String LyDoHuy) {
        this.LyDoHuy = LyDoHuy;
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
        return "HoaDonDTO{" + "MaHD=" + MaHD + ", TenNV=" + TenNV + ", TenKH=" + TenKH + ", MaSP=" + MaSP + ", TenSP=" + TenSP + ", Diachi=" + Diachi + ", SDT=" + SDT + ", ngayTao=" + ngayTao + ", LyDoHuy=" + LyDoHuy + ", TrangThai=" + TrangThai + ", SoLuong=" + SoLuong + ", DonGia=" + DonGia + '}';
    }

    
}
