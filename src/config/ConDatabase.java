package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConDatabase {
  private static Connection connection;
  
  public static Connection getConnection() {
    if (connection == null)
      try {
    	  Class.forName("com.mysql.jdbc.Driver");
          
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jadwal_kapolres","root","");
      } catch (ClassNotFoundException|java.sql.SQLException ex) {
        Logger.getLogger(config.ConDatabase.class.getName()).log(Level.SEVERE, (String)null, ex);
      }  
    return connection;
  }
  
  public static void main(String[] args) {
    getConnection();
  }
}
