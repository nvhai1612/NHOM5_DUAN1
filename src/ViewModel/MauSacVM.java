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
public class MauSacVM {
    private UUID id;
    private String MaMS;
    private String TenMS;
    private int TrangThai;

    public MauSacVM() {
    }

    public MauSacVM(UUID id, String MaMS, String TenMS, int TrangThai) {
        this.id = id;
        this.MaMS = MaMS;
        this.TenMS = TenMS;
        this.TrangThai = TrangThai;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaMS() {
        return MaMS;
    }

    public void setMaMS(String MaMS) {
        this.MaMS = MaMS;
    }

    public String getTenMS() {
        return TenMS;
    }

    public void setTenMS(String TenMS) {
        this.TenMS = TenMS;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenMS;
    }
}
