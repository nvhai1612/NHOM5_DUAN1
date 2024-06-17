/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.NhanVien;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface INhanVienRepos {

    public ArrayList<NhanVien> getListFormDB();

    public Boolean add(NhanVien nv);

    public Boolean update(NhanVien nv);

    public ArrayList<NhanVien> search(String MaNV);

    public Boolean existsByMaNV(String MaNV);

    public Optional<NhanVien> CheckLogin(String Email, String MatKhau);

    public Optional<NhanVien> findByEmail(String email);

    public Boolean updateMatKhau(String maNV, String newMatKhau);
    
    public Boolean checkMK(String maNV, String matKhauCu);

}
