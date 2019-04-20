/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniminuto.pa.DTOs;

/**
 *
 * @author nixoduaa
 */
public class Venta {

            int id = 0;
            String numero = null;
            String precio_total = null;
            String descripcion=null;
            String fecha=null;
            String id_persona = null;
            String id_vehiculo = null;
            String id_garantia=null;
           
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }        
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
     public String getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(String precio_total) {
        this.precio_total = precio_total;
    }

     public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }
      public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
          public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
           public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
           public String getId_garantia() {
        return id_garantia;
    }

    public void setId_garantia(String id_garantia) {
        this.id_garantia = id_garantia;
    }
}