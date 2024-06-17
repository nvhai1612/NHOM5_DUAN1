/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class KhachHangVM {
    private UUID id;
    private String MaKH;
    private String TenKH;
    private String NgaySinh;
    private Integer GioiTinh;
    private String sdt;
    private String DiaChi;

    public KhachHangVM() {
    }

    public KhachHangVM(UUID id, String MaKH, String TenKH, String NgaySinh, Integer GioiTinh, String sdt, String DiaChi) {
        this.id = id;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.sdt = sdt;
        this.DiaChi = DiaChi;
    }

    public UUID getId() {
        return id;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public Integer getGioiTinh() {
        return GioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setGioiTinh(Integer GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
}
