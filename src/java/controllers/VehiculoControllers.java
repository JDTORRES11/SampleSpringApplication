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

import co.edu.uniminuto.pa.DAOs.VehiculoDAO;
import co.edu.uniminuto.pa.DTOs.Vehiculo;
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
public class VehiculoControllers {
 /*
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap) {
        System.out.println("vehiculoCrear");
        modelMap.put("mensajeVehiculo", "Pase por el controller de Vehiculo");
        return "vehiculo_crear";
    }
    */
@RequestMapping(method = RequestMethod.GET, value = "vehiculoCrear.htm")
    public String processSubmit(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        System.out.println("vehiculoCrear");
        model.put("mensajeVehiculo", "Pase por el controller de Vehiculo:::"+req.getParameter("nombre"));
        return "vehiculo_crear";
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "vehiculoRegistrar.htm")
    public String processSubmit1(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VehiculoDAO vDao = new VehiculoDAO();
            
        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit1...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
         String mat = req.getParameter("matricula");
        String nombreV = req.getParameter("nombreV");
        String modelo = req.getParameter("modelo");
        String color = req.getParameter("color");
        String precio = req.getParameter("precio");
        String tipov = req.getParameter("tipov");

        
        Vehiculo v = new Vehiculo();
        v.setId(id);
        v.setMatricula(mat);
        v.setNombre_v(nombreV);
        v.setModelo(modelo);
        v.setColor(color);
        v.setPrecio(precio);
        v.setTipo_vehiculo(tipov);                                   
            
        boolean insert = vDao.crearVehiculo(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.SEVERE, null, "Registrar + " + mat + "-" + insert);
        
        if (insert)
            model.put("mensaje", "El registro fue creado satisfactoriamente!!!");
        else
            model.put("mensaje", "El registro NO fue creado, consulte con el administrador...");
        
        return "vehiculo_crear";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "vehiculoConsultar.htm")
    public String processSubmit2(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit2...");
        return "vehiculo_consultar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "vehiculoConsultarForm.htm")
    public String processSubmit3(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VehiculoDAO vDao = new VehiculoDAO();
            
        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit3...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String mat = req.getParameter("matricula");
        
        Vehiculo v = new Vehiculo();
        v.setId(id);
        v.setMatricula(mat);
            
        List<Vehiculo> datos = vDao.consultarVehiculo(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + mat + "-" + datos.size());
        
        model.put("listaVehiculos", datos);
        if (datos.size() > 0)
            model.put("mensaje", "La consulta se realizo satisfactoriamente!!!" + datos.size());
        else
            model.put("mensaje", "La consulta NO tiene resultados...");
        
        return "vehiculo_consultar";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "vehiculoEditar.htm")
    public String processSubmit4(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit4...");
        return "vehiculo_editar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "vehiculoEditarForm1.htm")
    public String processSubmit5(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VehiculoDAO vDao = new VehiculoDAO();
            
        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit5...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String mat = req.getParameter("matricula");
        
        Vehiculo v = new Vehiculo();
        v.setId(id);
        v.setMatricula(mat);
            
        List<Vehiculo> datos = vDao.consultarVehiculo(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(VehiculoControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + mat + "-" + datos.size());
        
        model.put("listaVehiculos", datos);
        
        
        return "vehiculo_editar";
        
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "vehiculoEditarForm2.htm")
    public String processSubmit6(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        VehiculoDAO vDao = new VehiculoDAO();
            
        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit6...");

        int id = Integer.parseInt(req.getParameter("id"));
        String mat = req.getParameter("matricula");
        String nombreV = req.getParameter("nombreV");
        String modelo = req.getParameter("modelo");
        String color = req.getParameter("color");
        String precio = req.getParameter("precio");
        String tipov = req.getParameter("tipov");
        
        Logger.getLogger(VehiculoDAO.class.getName()).log(Level.INFO, "Id vehiculo: " + id);
        
        Vehiculo v = new Vehiculo();
        v.setId(id);
        v.setMatricula(mat);
        v.setNombre_v(nombreV);
        v.setModelo(modelo);
        v.setColor(color);
        v.setPrecio(precio);
        v.setTipo_vehiculo(tipov);
            
        boolean res = vDao.editarVehiculo(v, MySqlDataSource.getConexionBD());                         
        
        if (res)
            model.put("mensaje", "Se edito satisfactoriamente!!!");
        else
            model.put("mensaje", "NO se guardaron los cambios...");
        
        return "vehiculo_editar";
        
    }    
}