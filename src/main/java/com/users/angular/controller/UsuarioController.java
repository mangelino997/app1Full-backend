/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.users.angular.model.Usuario;
import com.users.angular.service.UsuarioService;
/**
 *
 * @author Marcio
 */
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200") habilita al front que corre en otro puerto a comunicarse con este servidor
public class UsuarioController {

    //Crea una instancia del servicio
    @Autowired
    UsuarioService elementoService;

    // es accesible por todos los roles
    @GetMapping(value = "/usuarios")
    @ResponseBody
    public List<Usuario> usuarios() {
        return elementoService.listar();
    }

    // es accesible por el rol = ROLE_USER; rol = ROLE_ADMIN
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/usuario/{username}")
    @ResponseBody
    public Usuario obtenerPorUsername(@PathVariable String username) {
        return elementoService.listarPorUsername(username);
    }

    // es accesible por el rol = ROLE_ADMIN
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/usuario")
    public ResponseEntity<Usuario> agregar(@Valid @RequestBody Usuario user, BindingResult result) {
        try{
            Usuario u = elementoService.agregar(user);
            return new ResponseEntity(u, HttpStatus.CREATED);
        }catch(Error e){
            return new ResponseEntity(result.getFieldError(), HttpStatus.CONFLICT);
        }
        
    }

    // es accesible por el rol = ROLE_ADMIN
    @PutMapping(value = "/usuario")
    public Usuario actualizar(@RequestBody Usuario user) {
        return elementoService.actualizar(user);
    }
    
    // es accesible por el rol = ROLE_ADMIN
    /* ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos 
    de estado permitiéndonos total libertad de configurar la respuesta que queremos que se envié desde nuestros endpoints.*/
    @DeleteMapping(value = "/usuario/{id}")
    public Usuario eliminar(@PathVariable long id) {  
        Usuario u = elementoService.eliminar(id);
            return u;
    }
    
}
