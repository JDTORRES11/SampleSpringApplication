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

import co.edu.uniminuto.pa.DAOs.GarantiaDAO;
import co.edu.uniminuto.pa.DTOs.Garantia;
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
public class GarantiaControllers {
 /*
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap) {
        System.out.println("garantiaCrear");
        modelMap.put("mensajeGarantia", "Pase por el controller de Garantia");
        return "garantia_crear";
    }
    */
@RequestMapping(method = RequestMethod.GET, value = "garantiaCrear.htm")
    public String processSubmit(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        System.out.println("garantiaCrear");
        model.put("mensajeGarantia", "Pase por el controller de Garantia:::"+req.getParameter("nombre"));
        return "garantia_crear";
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "garantiaRegistrar.htm")
    public String processSubmit1(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        GarantiaDAO vDao = new GarantiaDAO();
            
        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit1...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        String garantia = req.getParameter("garantia");
        Garantia v = new Garantia();
        v.setId(id);
        v.setNumero(num);
        v.setTipo_garantia(garantia); 
        boolean insert = vDao.crearGarantia(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.SEVERE, null, "Registrar + " + num + "-" + insert);
        
        if (insert)
            model.put("mensaje", "El registro fue creado satisfactoriamente!!!");
        else
            model.put("mensaje", "El registro NO fue creado, consulte con el administrador...");
        
        return "garantia_crear";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "garantiaConsultar.htm")
    public String processSubmit2(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit2...");
        return "garantia_consultar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "garantiaConsultarForm.htm")
    public String processSubmit3(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        GarantiaDAO vDao = new GarantiaDAO();
            
        Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit3...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        
        Garantia v = new Garantia();
        v.setId(id);
        v.setNumero(num);
            
        List<Garantia> datos = vDao.consultarGarantia(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + num + "-" + datos.size());
        
        model.put("listaGarantias", datos);
        if (datos.size() > 0)
            model.put("mensaje", "La consulta se realizo satisfactoriamente!!!" + datos.size());
        else
            model.put("mensaje", "La consulta NO tiene resultados...");
        
        return "garantia_consultar";
    }     
    
@RequestMapping(method = RequestMethod.GET, value = "garantiaEditar.htm")
    public String processSubmit4(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {      
        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.INFO, "Ejecutando processSubmit4...");
        return "garantia_editar";
    } 
    
@RequestMapping(method = RequestMethod.POST, value = "garantiaEditarForm1.htm")
    public String processSubmit5(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        GarantiaDAO vDao = new GarantiaDAO();
            
        Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit5...");

        int id = vDao.obtenerId(MySqlDataSource.getConexionBD());
        String num = req.getParameter("numeroV");
        
        Garantia v = new Garantia();
        v.setId(id);
        v.setNumero(num);
            
        List<Garantia> datos = vDao.consultarGarantia(v, MySqlDataSource.getConexionBD());

        Logger.getLogger(GarantiaControllers.class.getName()).log(Level.SEVERE, null, "Consultar + " + num + "-" + datos.size());
        
        model.put("listaGarantias", datos);
        
        
        return "garantia_editar";
        
    }    
    
@RequestMapping(method = RequestMethod.POST, value = "garantiaEditarForm2.htm")
    public String processSubmit6(HttpServletRequest req, SessionStatus status,ModelMap model) 
    {

        GarantiaDAO vDao = new GarantiaDAO();
            
        Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Ejecutando processSubmit6...");

        int id = Integer.parseInt(req.getParameter("id"));
         String num = req.getParameter("numeroV");
        String garantia = req.getParameter("garantia");
        Logger.getLogger(GarantiaDAO.class.getName()).log(Level.INFO, "Id garantia: " + id);
        
        Garantia v = new Garantia();
        v.setId(id);
        v.setNumero(num);
        v.setTipo_garantia(garantia);   
        boolean res = vDao.editarGarantia(v, MySqlDataSource.getConexionBD());                         
        
        if (res)
            model.put("mensaje", "Se edito satisfactoriamente!!!");
        else
            model.put("mensaje", "NO se guardaron los cambios...");
        
        return "garantia_editar";
        
    }    
}