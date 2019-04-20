/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.DAOs;

import co.edu.uniminuto.pa.DTOs.Venta;
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
public class VentaDAO {
    
    
    
    public boolean crearVenta(Venta v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando crearVenta...");
            
            pstmt = con.prepareStatement("INSERT INTO venta "
                    + " VALUES (?,?,?,?,?,?,?,?)");
            
            pstmt.setInt(1,v.getId());
            pstmt.setString(2, v.getNumero());
            pstmt.setString(3, v.getPrecio_total());
             pstmt.setString(4, v.getDescripcion());
            pstmt.setString(5, v.getFecha());
            pstmt.setString(6, v.getId_persona());
            pstmt.setString(7, v.getId_vehiculo());
           pstmt.setString(8, v.getId_garantia());
            
            pstmt.execute();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }

    public ArrayList<Venta> consultarVenta(Venta v, Connection con)
    {
        
        ArrayList<Venta> datos = new ArrayList();
        
        Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando consultarVenta...");
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select numero, precio_total, descripcion,fecha,id_persona, "
                    + " id_vehiculo,id_garantia, id_venta "
                    + " from venta "
                    + " where "
                    + " numero='" + v.getNumero()+"'");
            
            while (rs.next())
            { 
                Venta ven = new Venta();
                ven.setNumero(rs.getString(1));
                ven.setPrecio_total(rs.getString(2));
                ven.setDescripcion(rs.getString(3));
                ven.setFecha(rs.getString(4));
                ven.setId_persona(rs.getString(5));
                ven.setId_vehiculo(rs.getString(6));
                ven.setId_garantia(rs.getString(7));
                ven.setId(rs.getInt(8));
                
                datos.add(ven);
                
            }
            
            Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando consultarVenta fin..." + datos.size());
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
    
    
    public int obtenerId(Connection con)
    {
        int id = -1;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select max(id_venta)+1 from venta");
            
            while (rs.next())
            { 
                id = rs.getInt(1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
         
    public boolean editarVenta(Venta v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando editarVenta...");
            
            pstmt = con.prepareStatement("UPDATE venta "
                    + " SET "
                    + " numero=?"
                    + " , precio_total=?"
                    + " , descripcion=?"
                    + " , fecha=?"
                    + " , id_persona=?"
                    + " , id_vehiculo=?"
                    + ", id_garantia=?"
                    + " where id_venta=?");
                        
            pstmt.setString(1, v.getNumero());
            pstmt.setString(2, v.getPrecio_total());
            pstmt.setString(3,v.getDescripcion());
            pstmt.setString(4,v.getFecha());
            pstmt.setString(5, v.getId_persona());
            pstmt.setString(6,v.getId_vehiculo());
            pstmt.setString(7,v.getId_garantia());
            pstmt.setInt(8, v.getId());
            
            pstmt.executeUpdate();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }
}