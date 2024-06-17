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
public class khuyeMaiVM {
  private UUID idKM;
    private UUID IDSP;
    private String maKM;
    private String TenKM;
    private float MucGiamGia;
    private Date ThoiGianBatDau;
    private Date ThoiGianKetThuc;
    private int TrangThai;
    private int SoLuong;

    public khuyeMaiVM() {
    }

    public khuyeMaiVM(UUID idKM, UUID IDSP, String maKM, String TenKM, float MucGiamGia, Date ThoiGianBatDau, Date ThoiGianKetThuc, int TrangThai, int SoLuong) {
        this.idKM = idKM;
        this.IDSP = IDSP;
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

    public UUID getIDSP() {
        return IDSP;
    }

    public void setIDSP(UUID IDSP) {
        this.IDSP = IDSP;
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
        return "khuyeMaiVM{" + "idKM=" + idKM + ", IDSP=" + IDSP + ", maKM=" + maKM + ", TenKM=" + TenKM + ", MucGiamGia=" + MucGiamGia + ", ThoiGianBatDau=" + ThoiGianBatDau + ", ThoiGianKetThuc=" + ThoiGianKetThuc + ", TrangThai=" + TrangThai + ", SoLuong=" + SoLuong + '}';
    }

    public boolean matches(SPCTVM spctvm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

   

    
    
    
}
