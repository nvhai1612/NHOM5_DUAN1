/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author Admin
 */
public class KhachHang_Respon {
public String tenKH;
public String sdt;
public String maKH;

    public KhachHang_Respon(String tenKH, String sdt, String maKH) {
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    
    public String getTenKH() {
        return tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


}
