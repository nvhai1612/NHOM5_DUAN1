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
public class KhuyenMaiVM {
    private UUID idKM;
    private UUID IDHD;
    private String maKM;
    private String TenKM;
    private float MucGiamGia;
    private Date ThoiGianBatDau;
    private Date ThoiGianKetThuc;
    private int TrangThai;
    private int SoLuong;

    public KhuyenMaiVM() {
    }

    public KhuyenMaiVM(UUID idKM, UUID IDHD, String maKM, String TenKM, float MucGiamGia, Date ThoiGianBatDau, Date ThoiGianKetThuc, int TrangThai, int SoLuong) {
        this.idKM = idKM;
        this.IDHD = IDHD;
        this.maKM = maKM;
        this.TenKM = TenKM;
        this.MucGiamGia = MucGiamGia;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.TrangThai = TrangThai;
        this.SoLuong = SoLuong;
    }

    public UUID getIdKM() {
        return idKM;
    }

    public void setIdKM(UUID idKM) {
        this.idKM = idKM;
    }

    public UUID getIDHD() {
        return IDHD;
    }

    public void setIDHD(UUID IDHD) {
        this.IDHD = IDHD;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public float getMucGiamGia() {
        return MucGiamGia;
    }

    public void setMucGiamGia(float MucGiamGia) {
        this.MucGiamGia = MucGiamGia;
    }

    public Date getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(Date ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
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
      @Override
    public String toString() {
        return TenKM; // return the name or whatever you want to display in the JComboBox
    }

  

}
