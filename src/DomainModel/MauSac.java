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
public class MauSac {
    private UUID id;
    private String MaMS;
    private String TenMS;
    private int TrangThaiMS;

    public MauSac() {
    }

    public MauSac(UUID id, String MaMS, String TenMS, int TrangThaiMS) {
        this.id = id;
        this.MaMS = MaMS;
        this.TenMS = TenMS;
        this.TrangThaiMS = TrangThaiMS;
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

    public int getTrangThaiMS() {
        return TrangThaiMS;
    }

    public void setTrangThaiMS(int TrangThaiMS) {
        this.TrangThaiMS = TrangThaiMS;
    }
}
