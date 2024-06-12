/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IChucVuRepos {
    public ArrayList<ChucVu> getListFormDB();
    public Boolean add(ChucVu cv);
    public Boolean update(ChucVu cv);
    public ArrayList<ChucVu> search(String maCV);
    public ArrayList<NhanVien> searchbyCV(String TenCV);
}
