/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.KhachHang;
import ViewModel.KhachHangVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IKhachHangService {
    ArrayList<KhachHangVM> getAll();
    ArrayList<KhachHang> getAllDomain();
    void add(KhachHang kh);
    void update(KhachHang kh);
    public ArrayList<KhachHang> search(String ma);
}
