/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular;
/*
import com.users.angular.dao.IRolDAO;
import com.users.angular.dao.IUsuarioDAO;
import com.users.angular.model.Rol;
import com.users.angular.model.Usuario;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Marcio
 */ 
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoWebApplicationTests {

    @Autowired
    private IUsuarioDAO IURepo;

    @Autowired
    private IRolDAO IRol;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    

    @Test
    public void crearUsuarioTest() {
        Rol r = IRol.findByName("ADMIN");
        Set<Rol> roles = new HashSet();
        roles.add(r);
        Usuario u = new Usuario(null, "marcio", "marcio", "tmarcio@gmail.com", "marcio", bcrypt.encode("marcio"), bcrypt.encode("marcio"), roles);
        u.setRoles(roles);

         Usuario retorno = IURepo.save(u);
        assertTrue(retorno.getPassword().equalsIgnoreCase(u.getPassword())); 
     }
}
*/