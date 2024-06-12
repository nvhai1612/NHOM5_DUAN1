/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.SanPham;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepos {
    public ArrayList<SanPham> getListFormDB();
    public UUID add(SanPham sp);
    public Boolean update(SanPham sp);
    public ArrayList<SanPham> search();
}
