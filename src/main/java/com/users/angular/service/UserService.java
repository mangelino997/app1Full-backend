/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.service;

/**
 *
 * @author Marcio
 */
import com.users.angular.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	Usuario getUser(long id);

	Usuario save(Usuario user);
}