/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.bds;

import java.sql.*;
import javax.sql.*;
import org.postgresql.ds.PGPoolingDataSource;
public class Test_MySqlDataSource {
    
  public static void main(String [] args) {
    Connection con = null;
    try {

// Setting up the DataSource object
         PGPoolingDataSource ds 
        = new PGPoolingDataSource();
      ds.setServerName("ec2-23-23-195-205.compute-1.amazonaws.com");
        ds.setPortNumber(5432);
        ds.setDatabaseName("dvj5f149di580");
        ds.setUser("tlyxlxvjejnhlf");
        ds.setPassword("f7f81b051e3cde9edd04563c90897003f52a7050c514fcc31bb71985f49cb79a");

// Getting a connection object
      con = ds.getConnection();
      
// Getting database info
      DatabaseMetaData meta = con.getMetaData();
      System.out.println("Server name: " 
        + meta.getDatabaseProductName());
      System.out.println("Server version: "
        + meta.getDatabaseProductVersion());

// Closing the connection
      con.close();
    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
    }
  }
}

