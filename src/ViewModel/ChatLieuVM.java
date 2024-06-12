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
public class ChatLieuVM {
    private UUID id;
    private String MaCL;
    private String TenCL;
    private int TrangThaiCL;

    public ChatLieuVM() {
    }

    public ChatLieuVM(UUID id, String MaCL, String TenCL, int TrangThaiCL) {
        this.id = id;
        this.MaCL = MaCL;
        this.TenCL = TenCL;
        this.TrangThaiCL = TrangThaiCL;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaCL() {
        return MaCL;
    }

    public void setMaCL(String MaCL) {
        this.MaCL = MaCL;
    }

    public String getTenCL() {
        return TenCL;
    }

    public void setTenCL(String TenCL) {
        this.TenCL = TenCL;
    }

    public int getTrangThaiCL() {
        return TrangThaiCL;
    }

    public void setTrangThaiCL(int TrangThaiCL) {
        this.TrangThaiCL = TrangThaiCL;
    }

    @Override
    public String toString() {
        return TenCL;
    }
}
