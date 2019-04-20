/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author NixonD
 */

import co.edu.uniminuto.pa.DAOs.VentaDAO;
import co.edu.uniminuto.pa.DTOs.Venta;
import co.edu.uniminuto.pa.bds.MySqlDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
 
@Controller
@RequestMapping("/")
public class VentaControllers {
 /*
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap) {
        System.out.println("ventaCrear");
        modelMap.put("mensajeVenta", "Pase por el controller de Venta");
        return "venta_crear";
    }
    */
@RequestMapping(method = RequestMethod.GET, value = "ventaCrear.htm")
    public String processSubmit(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        System.out.println("ventaCrear");
        model.put("mensajeVenta", "Pase por el controller de Venta:::"+req.getParameter("nombre"));
        return "venta_crear";
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "ventaRegistrar.htm")
    public String processSubmit1(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VentaDAO vDao = new VentaDAO();
            
        Logger.getLogger(VentaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit1...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        String preciot = req.getParameter("preciot");
        String cliente = req.getParameter("cliente");
        String vehiculo = req.getParameter("vehiculo");
        String descripcion = req.getParameter("descripcion");
        String fecha = req.getParameter("fecha");
         String tipog = req.getParameter("tipog");
        Venta v = new Venta();
        v.setId(id);
        v.setNumero(num);
        v.setPrecio_total(preciot);
        v.setId_persona(cliente);
        v.setId_vehiculo(vehiculo);
        v.setDescripcion(descripcion);
        v.setFecha(fecha);                                  
        v.setId_garantia(tipog);  
        boolean insert = vDao.crearVenta(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VentaControllers.class.getName()).log(Level.SEVERE, null, "Registrar + " + num + "-" + insert);
        
        if (insert)
            model.put("mensaje", "El registro fue creado satisfactoriamente!!!");
        else
            model.put("mensaje", "El registro NO fue creado, consulte con el administrador...");
        
        return "venta_crear";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "ventaConsultar.htm")
    public String processSubmit2(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(VentaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit2...");
        return "venta_consultar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "ventaConsultarForm.htm")
    public String processSubmit3(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VentaDAO vDao = new VentaDAO();
            
        Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit3...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        
        Venta v = new Venta();
        v.setId(id);
        v.setNumero(num);
            
        List<Venta> datos = vDao.consultarVenta(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VentaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + num + "-" + datos.size());
        
        model.put("listaVentas", datos);
        if (datos.size() > 0)
            model.put("mensaje", "La consulta se realizo satisfactoriamente!!!" + datos.size());
        else
            model.put("mensaje", "La consulta NO tiene resultados...");
        
        return "venta_consultar";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "ventaEditar.htm")
    public String processSubmit4(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(VentaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit4...");
        return "venta_editar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "ventaEditarForm1.htm")
    public String processSubmit5(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VentaDAO vDao = new VentaDAO();
            
        Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit5...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        
        Venta v = new Venta();
        v.setId(id);
        v.setNumero(num);
            
        List<Venta> datos = vDao.consultarVenta(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VentaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + num + "-" + datos.size());
        
        model.put("listaVentas", datos);
        
        
        return "venta_editar";
        
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "ventaEditarForm2.htm")
    public String processSubmit6(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VentaDAO vDao = new VentaDAO();
            
        Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit6...");

        int id = Integer.parseInt(req.getParameter("id"));
         String num = req.getParameter("numeroV");
        String preciot = req.getParameter("preciot");
        String cliente = req.getParameter("cliente");
        String vehiculo = req.getParameter("vehiculo");
        String descripcion = req.getParameter("descripcion");
        String fecha = req.getParameter("fecha");
        String tipog = req.getParameter("tipog");
        Logger.getLogger(VentaDAO.class.getName()).log(Level.INFO, "Id venta: " + id);
        
        Venta v = new Venta();
        v.setId(id);
        v.setNumero(num);
        v.setPrecio_total(preciot);
        v.setId_persona(cliente);
        v.setId_vehiculo(vehiculo);
        v.setDescripcion(descripcion);
        v.setFecha(fecha); 
        v.setId_garantia(tipog);    
        boolean res = vDao.editarVenta(v, MySqlDataSource.getConexionBD());                         
        
        if (res)
            model.put("mensaje", "Se edito satisfactoriamente!!!");
        else
            model.put("mensaje", "NO se guardaron los cambios...");
        
        return "venta_editar";
        
    }    
}