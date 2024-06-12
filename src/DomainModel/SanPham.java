/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class SanPham {
    private UUID id;
    private String MaSP;
    private String TenSP;
    private int TrangThaiSP;

    public SanPham() {
    }

    public SanPham(UUID id, String MaSP, String TenSP, int TrangThaiSP) {
        this.id = id;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TrangThaiSP = TrangThaiSP;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getTrangThaiSP() {
        return TrangThaiSP;
    }

    public void setTrangThaiSP(int TrangThaiSP) {
        this.TrangThaiSP = TrangThaiSP;
    }
}
