/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChatLieu;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IChatLieuRepos {
    public ArrayList<ChatLieu> getListFormDB();
    public Boolean add(ChatLieu cl);
    public Boolean update(ChatLieu cl);
    public ArrayList<ChatLieu> search();
}
