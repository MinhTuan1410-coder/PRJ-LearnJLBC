/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Product;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Trần Minh Tuấn
 */
public class UserDAO {
    public List<User> getAllProducts() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;//Ket noi database dua tren user name và pass.. chua lay du lieu
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();// ket noi
            if (conn != null) {
                ptm = conn.prepareStatement("select * from tblUsers");// chuan bi cau lenh de thuc thi
                rs = ptm.executeQuery();// thuc hien roi luu vao rs neu co
                // dua tren tung dong 
                while (rs.next()) {
                    String userID =      rs.getString("userId");
                    String fullName =           rs.getString("fullname");
                    String password =    rs.getString("password");
                    String roleID =           rs.getString("roleID");
                    boolean status =        rs.getBoolean("status");
                    list.add(new User(userID, fullName, password, roleID, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public User checkLogin(String userID, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT userID, fullName, roleID, password,status FROM tblUsers WHERE userID = ? AND password = ?");
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                
                if (rs.next()) {
//                    String userId=rs.getString("userId");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    boolean status =rs.getBoolean("status");
                    user = new User(userID, fullName, roleID, null,status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
    
   
    
}
