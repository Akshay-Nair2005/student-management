
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentm;
import studentm.databaseCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Student {
    Connection conn=databaseCon.connection();
    PreparedStatement ps;
    
    //get table max row
    public int getMax(){
        
        int id =1;
        Statement st;
        
        try {
            st=conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT (MAX(id)+1) FROM studenth");
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        
        // insert date into student table

    /**
     *
     * @param id
     * @param namee
     * @param datee
     * @param genderr
     * @param emaill
     * @param phone
     * @param fathername
     * @param mothername
     * @param add
     * @param imagePath
     */
    }
 // insert data into student table
// insert data into student table
public void insert(int id, String namee, String datee, String genderr, String emaill, String phone,
                   String fathername, String mothername, String add, String imagePath) {
    String sql = "INSERT INTO studenth VALUES (?,?,?,?,?,?,?,?,?,?)";
    try {
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namee);
        ps.setString(3, datee);
        ps.setString(4, genderr);
        ps.setString(5, emaill);
        ps.setString(6, phone);
        ps.setString(7, fathername);
        ps.setString(8, mothername);
        ps.setString(9, add);
        ps.setString(10, imagePath);

        // Set a default value for the 11th parameter, or adjust your SQL query accordingly
//        ps.setString(11, "default_value");

        // Execute the SQL statement
        if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null, "New Student added successfully");

    }
    } catch (SQLException ex) {
        Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Close resources in the finally block
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
    public boolean isIdExist(int id){
        try {
            ps = conn.prepareStatement("SELECT * FROM STUDENTH WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean isEmailExist(String email){
        try {
            ps = conn.prepareStatement("SELECT * FROM STUDENTH WHERE EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Student phone number exists or not
    public boolean isPhoneExist(String phone){
        try {
            ps = conn.prepareStatement("SELECT * FROM STUDENTH WHERE PHONE = ?");
            ps.setString(1,phone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //Student values from the database
    public void getStudentValue(JTable table,String searchValue){
        String sql = "SELECT * FROM STUDENTH WHERE CONCAT(ID,NAME,EMAIL,PHONE) LIKE ? ORDER BY ID DESC";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+searchValue+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()){
                row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
//                row[10] = rs.getString(11);
                model.addRow(row);     
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(int id, String namee, String datee, String genderr, String emaill, String phone,
                   String fathername, String mothername, String add, String imagePath){
        String sql= "UPDATE STUDENTH SET NAME=?,DOB=?,GENDER=?,EMAIL=?,PHONE=?,FATHERNAME=?,MOTHERNAME=?,ADDRESS=?,IMAGEPATH=? WHERE ID=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,namee);
            ps.setString(2,datee);
            ps.setString(3,genderr);
            ps.setString(4,emaill);
            ps.setString(5,phone);
            ps.setString(6,fathername);
            ps.setString(7,mothername);
            ps.setString(8,add);
            ps.setString(9,imagePath);
            ps.setInt(10,id);
            
            if(ps.executeUpdate()>0){
//            JOptionPane.showMessageDialog(null, "Student Data Updated successfully");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
}


