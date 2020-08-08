/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.dao;

import com.users.angular.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcio
 */
public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{

    public Usuario findByUsername(String username);
    public Usuario findById(long id);
    
    Usuario findByFirstName(String name);

}
