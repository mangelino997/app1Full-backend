/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.dao;

import com.users.angular.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcio
 */
public interface IRolDAO extends JpaRepository<Rol, Long>{
    public Rol findOneByName(String username);
    public Rol findById(long id);
    
    Rol getRoleById(long id);
    Rol findByName(String name);
}
