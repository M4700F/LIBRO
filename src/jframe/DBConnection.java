/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author spooksy
 */
public class DBConnection {
    static Connection conn = null;
    
    // used static to make instanceless method access possible
    public static Connection getConnection(){
        try{
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("dbconfig.properties");
            props.load(in);
            in.close();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
  
            conn = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return conn;
    }
}
