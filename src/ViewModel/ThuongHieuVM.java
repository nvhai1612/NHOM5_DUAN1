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
public class ThuongHieuVM {
    private UUID id;
    private String MaTH;
    private String TenTH;
    private int TrangThaiTH;

    public ThuongHieuVM() {
    }

    public ThuongHieuVM(UUID id, String MaTH, String TenTH, int TrangThaiTH) {
        this.id = id;
        this.MaTH = MaTH;
        this.TenTH = TenTH;
        this.TrangThaiTH = TrangThaiTH;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaTH() {
        return MaTH;
    }

    public void setMaTH(String MaTH) {
        this.MaTH = MaTH;
    }

    public String getTenTH() {
        return TenTH;
    }

    public void setTenTH(String TenTH) {
        this.TenTH = TenTH;
    }

    public int getTrangThaiTH() {
        return TrangThaiTH;
    }

    public void setTrangThaiTH(int TrangThaiTH) {
        this.TrangThaiTH = TrangThaiTH;
    }

    @Override
    public String toString() {
        return TenTH;
    }
}
