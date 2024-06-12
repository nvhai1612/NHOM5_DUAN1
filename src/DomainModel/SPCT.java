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
public class SPCT {
    private UUID id, IdSP, IdCL, IdKC, IdMS, IdTH;
    private String MaSPCT, MoTa, TenMS, TenCL, TenTH, TenKC, TenSP, NguoiTao ,KM;
    private int SoLuongTon, TrangThaiSPCT;
    private float DonGia;

    public SPCT() {
    }

    public SPCT(UUID id, UUID IdSP, UUID IdCL, UUID IdKC, UUID IdMS, UUID IdTH, String MaSPCT, String MoTa, String TenMS, String TenCL, String TenTH, String TenKC, String TenSP, String NguoiTao, String KM, int SoLuongTon, int TrangThaiSPCT, float DonGia) {
        this.id = id;
        this.IdSP = IdSP;
        this.IdCL = IdCL;
        this.IdKC = IdKC;
        this.IdMS = IdMS;
        this.IdTH = IdTH;
        this.MaSPCT = MaSPCT;
        this.MoTa = MoTa;
        this.TenMS = TenMS;
        this.TenCL = TenCL;
        this.TenTH = TenTH;
        this.TenKC = TenKC;
        this.TenSP = TenSP;
        this.NguoiTao = NguoiTao;
        this.KM = KM;
        this.SoLuongTon = SoLuongTon;
        this.TrangThaiSPCT = TrangThaiSPCT;
        this.DonGia = DonGia;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdSP() {
        return IdSP;
    }

    public void setIdSP(UUID IdSP) {
        this.IdSP = IdSP;
    }

    public UUID getIdCL() {
        return IdCL;
    }

    public void setIdCL(UUID IdCL) {
        this.IdCL = IdCL;
    }

    public UUID getIdKC() {
        return IdKC;
    }

    public void setIdKC(UUID IdKC) {
        this.IdKC = IdKC;
    }

    public UUID getIdMS() {
        return IdMS;
    }

    public void setIdMS(UUID IdMS) {
        this.IdMS = IdMS;
    }

    public UUID getIdTH() {
        return IdTH;
    }

    public void setIdTH(UUID IdTH) {
        this.IdTH = IdTH;
    }

    public String getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(String MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getTenMS() {
        return TenMS;
    }

    public void setTenMS(String TenMS) {
        this.TenMS = TenMS;
    }

    public String getTenCL() {
        return TenCL;
    }

    public void setTenCL(String TenCL) {
        this.TenCL = TenCL;
    }

    public String getTenTH() {
        return TenTH;
    }

    public void setTenTH(String TenTH) {
        this.TenTH = TenTH;
    }

    public String getTenKC() {
        return TenKC;
    }

    public void setTenKC(String TenKC) {
        this.TenKC = TenKC;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public String getKM() {
        return KM;
    }

    public void setKM(String KM) {
        this.KM = KM;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public int getTrangThaiSPCT() {
        return TrangThaiSPCT;
    }

    public void setTrangThaiSPCT(int TrangThaiSPCT) {
        this.TrangThaiSPCT = TrangThaiSPCT;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    
}
