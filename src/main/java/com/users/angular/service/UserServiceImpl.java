package com.users.angular.service;
/**
 *
 * @author Marcio
 */
import com.users.angular.dao.IRolDAO;
import com.users.angular.dao.IUsuarioDAO;
import com.users.angular.model.Rol;
import com.users.angular.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional // con esto nos aseguramos que la consulta al repo usuario nos traiga los roles (que habiamos declarados como lazy)
public class UserServiceImpl implements UserService {

    @Autowired
    private IRolDAO roleRepository;
    @Autowired
    private IUsuarioDAO userRepository;

    // sobreescribe el metodo de UserDetails para agregarle roles, y demás info del usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set grantList = new HashSet();
        /* busca al usuario en la DB y si no lo encuentra lanza la excepcion 'UsernameNotFoundException' */
        Usuario usuario = userRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        usuario.getRoles().forEach(role -> {
            grantList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return new User(usuario.getUsername(), usuario.getPassword(), grantList);
        
    }

    // METODOS ABSTRACTOS QU SOLICITA LA CLASE
    @Override
    public Usuario getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario user) {
        Rol userRole = roleRepository.findByName("USER");
        Set<Rol> roles = new HashSet<>();
        roles.add(userRole);

        Usuario userToSave = Usuario.builder().username(user.getUsername()).password(user.getPassword()).roles(roles).build();

        return userRepository.save(userToSave);
    }

}
