/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.controller;

import com.users.angular.model.Rol;
import com.users.angular.service.RoleService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcio
 */
@RestController
public class RoleController {

    //Crea una instancia del servicio
    @Autowired
    RoleService elementoService;

    @GetMapping(value = "/roles")
    @ResponseBody
    public List<Rol> roles() {
        return elementoService.listar();
    }

    /* ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos 
    de estado permitiéndonos total libertad de configurar la respuesta que queremos que se envié desde nuestros endpoints.*/
    @PostMapping(value = "/role")
    public ResponseEntity<Rol> agregar(@Valid @RequestBody Rol role, BindingResult result) {
        try {
            Rol u = elementoService.agregar(role);
            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Error e) {
            return new ResponseEntity(result.getFieldError(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/role")
    public Rol actualizar(@RequestBody Rol role) {
        return elementoService.actualizar(role);
    }

    @DeleteMapping(value = "/role/{id}")
    public Rol eliminar(@PathVariable long id) {
        Rol role = elementoService.eliminar(id);
        return role;
    }
}
