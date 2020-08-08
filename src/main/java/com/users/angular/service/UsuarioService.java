/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.service;

import com.users.angular.dao.IUsuarioDAO;
import org.springframework.stereotype.Service;
import com.users.angular.model.Usuario;
/**
 *
 * @author Marcio
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {

    ////Define la referencia al dao usuario
    @Autowired
    IUsuarioDAO elementoDAO;

    //Obtiene una lista completa
    public List<Usuario> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene un usuario por unsername
    public Usuario listarPorUsername(String username) {
        return elementoDAO.findByUsername(username);
    }

    //Agrega un usuario
    public Usuario agregar(Usuario user) {
        Usuario u = elementoDAO.saveAndFlush(user);
        return u;
    }

    //Actualiza un usuario
    public Usuario actualizar(Usuario user) {
        Usuario u = elementoDAO.save(user);
        return u;
    }

    //Elimina un usuario
    public Usuario eliminar(long id) {
        elementoDAO.deleteById(id);
        return elementoDAO.findById(id);
    }
}
