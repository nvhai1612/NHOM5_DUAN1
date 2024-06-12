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
public class KichCo {
    private UUID id;
    private String MaKC;
    private String TenKC;
    private int TrangThaiKC;

    public KichCo() {
    }

    public KichCo(UUID id, String MaKC, String TenKC, int TrangThaiKC) {
        this.id = id;
        this.MaKC = MaKC;
        this.TenKC = TenKC;
        this.TrangThaiKC = TrangThaiKC;
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

    public int getTrangThaiKC() {
        return TrangThaiKC;
    }

    public void setTrangThaiKC(int TrangThaiKC) {
        this.TrangThaiKC = TrangThaiKC;
    }
}
