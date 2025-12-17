/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Product;
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
public class ProductDAO {
    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;//Ket noi database dua tren user name và pass.. chua lay du lieu
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();// ket noi
            if (conn != null) {
                ptm = conn.prepareStatement("select * from tblProduct");// chuan bi cau lenh de thuc thi
                rs = ptm.executeQuery();// thuc hien roi luu vao rs neu co
                // dua tren tung dong 
                while (rs.next()) {
                    String productID =      rs.getString("id");
                    String name =           rs.getString("name");
                    String description =    rs.getString("description");
                    float price =           rs.getFloat("price");
                    String size =           rs.getString("size");
                    boolean status =        rs.getBoolean("status");
                    list.add(new Product(productID, name, description, price, size, status));
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
    
    public boolean addProduct(Product product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO tblProduct (id, name, description, price, size, status) VALUES (?, ?, ?, ?, ?, ?)");
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getName());
                ptm.setString(3, product.getDescription());
                ptm.setFloat(4, product.getPrice());
                ptm.setString(5, product.getSize());
                ptm.setBoolean(6, true); // Default status is true
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
