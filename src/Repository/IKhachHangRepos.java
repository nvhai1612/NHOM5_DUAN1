/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IKhachHangRepos {
    public ArrayList<KhachHang> getAllKhachHang();
    public Boolean add(KhachHang kh);
    public Boolean update(KhachHang kh);
    public ArrayList<KhachHang> search(String ma);
}
