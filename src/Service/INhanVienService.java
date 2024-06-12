/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.NhanVien;
import ViewModel.NhanVienVM;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface INhanVienService {

    ArrayList<NhanVienVM> getAll();

    public ArrayList<NhanVien> getAllDoMain();

    public void add(NhanVien nv);

    public void update(NhanVien nv);

     public ArrayList<NhanVien> search(String MaNV);

    public Boolean existsByMaNV(String MaNV);

    Optional<NhanVien> CheckLogin(String Email, String MatKhau);

}
