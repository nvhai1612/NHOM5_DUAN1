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
public class KichCoVM {
    private UUID id;
    private String MaKC;
    private String TenKC;
    private int TrangThai;

    public KichCoVM() {
    }

    public KichCoVM(UUID id, String MaKC, String TenKC, int TrangThai) {
        this.id = id;
        this.MaKC = MaKC;
        this.TenKC = TenKC;
        this.TrangThai = TrangThai;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaKC() {
        return MaKC;
    }

    public void setMaKC(String MaKC) {
        this.MaKC = MaKC;
    }

    public String getTenKC() {
        return TenKC;
    }

    public void setTenKC(String TenKC) {
        this.TenKC = TenKC;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenKC;
    }
}
