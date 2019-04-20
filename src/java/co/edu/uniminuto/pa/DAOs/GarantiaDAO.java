/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.DAOs;

import co.edu.uniminuto.pa.DTOs.Garantia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nixoduaa
 */
public class GarantiaDAO {
    
    
    
    public boolean crearGarantia(Garantia v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando crearGarantia...");
            
            pstmt = con.prepareStatement("INSERT INTO garantia "
                    + " VALUES (?,?,?)");
            
            pstmt.setInt(1,v.getId());
            pstmt.setString(2, v.getNumero());
            pstmt.setString(3, v.getTipo_garantia());
            
            pstmt.execute();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }

    public ArrayList<Garantia> consultarGarantia(Garantia v, Connection con)
    {
        
        ArrayList<Garantia> datos = new ArrayList();
        
        Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando consultarGarantia...");
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select numero, tipo_garantia, "
                    + "  id_garantia "
                    + " from garantia "
                    + " where "
                    + " numero='" + v.getNumero()+"'");
            
            while (rs.next())
            { 
                Garantia ven = new Garantia();
                ven.setNumero(rs.getString(1));
                ven.setTipo_garantia(rs.getString(2));
                ven.setId(rs.getInt(3));
                
                datos.add(ven);
                
            }
            
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando consultarGarantia fin..." + datos.size());
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
    
    
    public int obtenerId(Connection con)
    {
        int id = -1;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select max(id_garantia)+1 from garantia");
            
            while (rs.next())
            { 
                id = rs.getInt(1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
         
    public boolean editarGarantia(Garantia v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando editarGarantia...");
            
            pstmt = con.prepareStatement("UPDATE garantia "
                    + " SET "
                    + " numero=?"
                    + " , tipo_garantia=?"
                    + " where id_garantia=?");
                        
            pstmt.setString(1, v.getNumero());
            pstmt.setString(2, v.getTipo_garantia());
            pstmt.setInt(3, v.getId());
            
            pstmt.executeUpdate();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(GarantiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }
}