/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.bds;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author nixoduaa
 */
public class MySqlDataSource {
    private static MySqlDataSource miMySqlDS = null;
    private static PGPoolingDataSource ds = null;
    
    private MySqlDataSource(){
        ds = new PGPoolingDataSource();
        ds.setServerName("ec2-23-23-195-205.compute-1.amazonaws.com");
        ds.setPortNumber(5432);
        ds.setDatabaseName("dvj5f149di580");
        ds.setUser("tlyxlxvjejnhlf");
        ds.setPassword("f7f81b051e3cde9edd04563c90897003f52a7050c514fcc31bb71985f49cb79a");        
    }
    
    public static Connection getConexionBD()
    {
        try {
            if (miMySqlDS == null)
            {
                miMySqlDS = new MySqlDataSource();            
            }
            
            return ds.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    
    
}
