/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.service;

import com.users.angular.dao.IRolDAO;
import com.users.angular.model.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcio
 */
@Service
public class RoleService {
    
////Define la referencia al dao role
    @Autowired
    IRolDAO elementoDAO;
    
    //Obtiene una lista completa
    public List<Rol> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un role
    public Rol agregar(Rol role){
        Rol u = elementoDAO.saveAndFlush(role);
        return u;
    }
    
    //Actualiza un role
    public Rol actualizar(Rol role){
        Rol r = elementoDAO.save(role);
        return r;
    }
    
    //Elimina un role
    public Rol eliminar(long id){
         elementoDAO.deleteById(id);
         return elementoDAO.findById(id);
    }
}

