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
public class NhanVienVM {
     private UUID Id, IdCV;
    private String MaNV, TenNV, CCCD, DiaChi, SDT, Email, TenCV;
    private int GioiTinh, TrangThaiNV;
    private Date NgaySinh;

    public NhanVienVM() {
    }

    public NhanVienVM(UUID Id, UUID IdCV, String MaNV, String TenNV, String CCCD, String DiaChi, String SDT, String Email, String TenCV, int GioiTinh, int TrangThaiNV, Date NgaySinh) {
        this.Id = Id;
        this.IdCV = IdCV;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TenCV = TenCV;
        this.GioiTinh = GioiTinh;
        this.TrangThaiNV = TrangThaiNV;
        this.NgaySinh = NgaySinh;
    }

    public NhanVienVM(UUID Id, String MaNV, String TenNV, String CCCD, String DiaChi, String SDT, String Email, String TenCV, int GioiTinh, int TrangThaiNV, Date NgaySinh) {
        this.Id = Id;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TenCV = TenCV;
        this.GioiTinh = GioiTinh;
        this.TrangThaiNV = TrangThaiNV;
        this.NgaySinh = NgaySinh;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID Id) {
        this.Id = Id;
    }

    public UUID getIdCV() {
        return IdCV;
    }

    public void setIdCV(UUID IdCV) {
        this.IdCV = IdCV;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getTrangThaiNV() {
        return TrangThaiNV;
    }

    public void setTrangThaiNV(int TrangThaiNV) {
        this.TrangThaiNV = TrangThaiNV;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    @Override
    public String toString() {
        return "NhanVienVM{" + "Id=" + Id + ", IdCV=" + IdCV + ", MaNV=" + MaNV + ", TenNV=" + TenNV + ", CCCD=" + CCCD + ", DiaChi=" + DiaChi + ", SDT=" + SDT + ", Email=" + Email + ", TenCV=" + TenCV + ", GioiTinh=" + GioiTinh + ", TrangThaiNV=" + TrangThaiNV + ", NgaySinh=" + NgaySinh + '}';
    }


    
    
    
}
