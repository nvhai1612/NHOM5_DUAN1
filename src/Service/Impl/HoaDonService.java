/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.HoaDon;
import DomainModel.SPCT;
import Repository.Impl.HDCTRepos;
import Repository.Impl.HoaDonRepos;
import Repository.Impl.NhanVienRepos;
import Service.IHoaDonService;
import ViewModel.HoaDonVM;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IHoaDonService{
    private HoaDonRepos hoadonrepos = new HoaDonRepos();
    private HDCTRepos hdctrepos = new HDCTRepos();
    private NhanVienRepos NhanVienRepos = new NhanVienRepos();

    @Override
    public ArrayList<HoaDonVM> getAll() {
        ArrayList<HoaDon> listHD = hoadonrepos.getListFormDB();
        ArrayList<HoaDonVM> listVM = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonVM hdvm = new HoaDonVM(hd.getMaHD(), hd.getTenNV(), hd.getTenKH(), hd.getNgayTao(), hd.getTrangThaiHD());
            listVM.add(hdvm);
        }
        return listVM;
    }        
        public ArrayList<HoaDonVM> getAllHoaDon() {
        ArrayList<HoaDon> listHD = hoadonrepos.getListHoaDonFormDB();
        ArrayList<HoaDonVM> listVM = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonVM hdvm = new HoaDonVM(hd.getMaHD(), hd.getTenNV(),hd.getNgayTao(), hd.getTrangThaiHD());
            listVM.add(hdvm);
        }
        return listVM;
    }

    @Override
    public ArrayList<HoaDon> getAllDomain() {
        return hoadonrepos.getListFormDB();
    }
    
    public void add(HoaDon hd, Map<String, Integer> maSPs) {
        HoaDon hd2 = hoadonrepos.findHoaDonByMa(hd.getMaHD());
        hd.setId(hd2.getId());
        hd2.setTrangThaiHD(hd.getTrangThaiHD());
//        hd.setIdKH(khachhngRespo.SelectById(hd2.getTenKH()));
//        hd.setIdNV(NhanVienRepos.SelectByname(hd2.getTenNV()));
        hoadonrepos.update(hd2);
        hdctrepos.add(hd2, maSPs);
    }
    
    public void updateTrangThaiHoaDon(String maHDCT,Integer TrangThaiHD, Float TongTien, String mahd){
        hoadonrepos.updateTrangThaiHoaDon(maHDCT,TrangThaiHD, TongTien, mahd);
    }
    
    public ArrayList<SPCT> HoaDonCho(String MaHD){
        return hoadonrepos.HoaDonCho(MaHD);
    }
    
    public void UpdateSPGH(String MaHD, String MaSPCT, Integer SL, Integer SLTon, Integer SLGHNEW){
        hoadonrepos.UpdateSPGH(MaHD, MaSPCT, SL, SLTon, SLGHNEW);
    }
    
    public void DeleteSPGH(String MaHD, String MaSPCT, Integer SL, Integer SLTon){
        hoadonrepos.DeleteSPGH(MaHD, MaSPCT, SL, SLTon);
    }
    
    public void HuyThanhToan(String MaHD, String LyDoHuy, Integer SL, Integer SLTon, String MaSPCT){
        hoadonrepos.HuyThanhToan(MaHD, LyDoHuy, SL, SLTon, MaSPCT);
    }

    public void update(HoaDon hd, Map<String, Integer> maSPs) {
        HoaDon hd2 = hoadonrepos.findHoaDonByMa(hd.getMaHD());
        hd.setId(hd2.getId());
//        hd.setIdKH(khachhngRespo.SelectById(hd2.getTenKH()));
//        hd.setIdNV(nhanvienRespon.SelectByname(hd2.getTenNV()));
        hoadonrepos.update(hd2);
        hdctrepos.update(hd2, maSPs);
    }
    
}
