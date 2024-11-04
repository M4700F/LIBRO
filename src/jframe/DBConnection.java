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
 * @author Admin
 */
public class DBConnection {
    static Connection conn = null;
    
    // why static is used ? when we use a method as static we don't have to create instances of that class to gain access to that method. Simply we write DBConncection.getConnection()
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
