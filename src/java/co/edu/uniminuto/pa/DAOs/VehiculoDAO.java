/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.DAOs;

import co.edu.uniminuto.pa.DTOs.Vehiculo;
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
public class VehiculoDAO {
    
    
    
    public boolean crearVehiculo(Vehiculo v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando crearVehiculo...");
            
            pstmt = con.prepareStatement("INSERT INTO vehiculo "
                    + " VALUES (?,?,?,?,?,?,?)");
            
            pstmt.setInt(1,v.getId());
            pstmt.setString(2, v.getMatricula());
            pstmt.setString(3, v.getNombre_v());
            pstmt.setString(4, v.getModelo());
            pstmt.setString(5, v.getColor());
            pstmt.setString(6, v.getPrecio());
            pstmt.setString(7, v.getTipo_vehiculo());
            pstmt.execute();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }

    public ArrayList<Vehiculo> consultarVehiculo(Vehiculo v, Connection con)
    {
        
        ArrayList<Vehiculo> datos = new ArrayList();
        
        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando consultarVehiculo...");
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select matricula, nombre_v,modelo, "
                    + " color, precio,tipo_vehiculo, id_vehiculo "
                    + " from vehiculo "
                    + " where "
                    + " matricula='" + v.getMatricula()+"'");
            
            while (rs.next())
            { 
                Vehiculo veh = new Vehiculo();
                veh.setMatricula(rs.getString(1));
                veh.setNombre_v(rs.getString(2));
                veh.setModelo(rs.getString(3));
                veh.setColor(rs.getString(4));
                veh.setPrecio(rs.getString(5));
                veh.setTipo_vehiculo(rs.getString(6));
                veh.setId(rs.getInt(7));
                
                datos.add(veh);
                
            }
            
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando consultarVehiculo fin..." + datos.size());
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
    
    
    public int obtenerId(Connection con)
    {
        int id = -1;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select max(id_vehiculo)+1 from vehiculo");
            
            while (rs.next())
            { 
                id = rs.getInt(1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
         
    public boolean editarVehiculo(Vehiculo v, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando editarVehiculo...");
            
            pstmt = con.prepareStatement("UPDATE vehiculo "
                    + " SET "
                    + " matricula=?"
                    + " , nombre_v=?"
                    + " , modelo=?"
                    + " , color=?"
                    + " , precio=?"
                    + " , tipo_vehiculo=?"
                    + " where id_vehiculo=?");
                        
            pstmt.setString(1, v.getMatricula());
            pstmt.setString(2, v.getNombre_v());
            pstmt.setString(3, v.getModelo());
            pstmt.setString(4,v.getColor());
            pstmt.setString(5,v.getPrecio());
            pstmt.setString(6,v.getTipo_vehiculo());
            pstmt.setInt(7, v.getId());
            
            pstmt.executeUpdate();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }
}