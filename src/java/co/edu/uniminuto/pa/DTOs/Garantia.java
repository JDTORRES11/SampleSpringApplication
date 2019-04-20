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
public class Garantia {

            int id = 0;
            String numero = null;
            String tipo_garantia = null;

    public String getTipo_garantia() {
        return tipo_garantia;
    }

    public void setTipo_garantia(String tipo_garantia) {
        this.tipo_garantia = tipo_garantia;
    }
           
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
}