package com.egg.proyecto.servicios;

import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.enums.Role;
import com.egg.proyecto.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public List<Usuario>findAll(){
        return usuarioRepositorio.findAll();
    }
    
    public Usuario crearUsuario (String email, String contrasenia1, String contrasenia2, String nombreUsuario) throws Exception{
        validator(email,contrasenia1, contrasenia2, nombreUsuario);
        Usuario usuario=new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setEmail(email);
        usuario.setContrasenia(encoder.encode(contrasenia1));
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setRole(Role.USER);
        return usuarioRepositorio.save(usuario);
    }

    public Usuario modificarUsuario(String id, String email, String contrasenia1, String contrasenia2, String nombreUsuario) throws Exception {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            updateValidator(email, contrasenia1, contrasenia2, nombreUsuario);
            Usuario usuario = respuesta.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setEmail(email);
            usuario.setContrasenia(encoder.encode(contrasenia1));
            usuario.setNombreUsuario(nombreUsuario);
            return usuarioRepositorio.save(usuario);
        }else{
            throw new Exception ("No existe ese usuario");
        }
    }
    
    public void eliminarUsuario(String id){
        usuarioRepositorio.deleteById(id);
    }
    
    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepositorio.buscarUsuarioPorEmail(email);
    }
    
    public Usuario buscarUsuarioPorNombre(String nombreUsuario){
        return usuarioRepositorio.buscarUsuarioPorNombre(nombreUsuario);
    }
    
    public void validator(String email, String contrasenia1, String contrasenia2, String nombreUsuario) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Ingrese un email válido");
        }
        if(buscarUsuarioPorEmail(email) != null){
            throw new Exception("El email ya está en uso");
        }
        if (contrasenia1 == null || contrasenia1.isEmpty()) {
            throw new Exception("Ingrese una contraseña válida");
        }
        if (contrasenia2 == null || contrasenia2.isEmpty()){
            throw new Exception("Ingrese una contraseña válida");
        }
        if (!(contrasenia1.equals(contrasenia2))){
            throw new Exception("Las contraseñas no coinciden");
        }
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new Exception("Ingrese un nombre de usuario válido");
        }
        if(buscarUsuarioPorNombre(nombreUsuario) != null){
            throw new Exception("El nombre de usuario ya está en uso");
        }
    }
    
    public void updateValidator(String email, String contrasenia1, String contrasenia2, String nombreUsuario) throws Exception{
        if(email == null || email.isEmpty()){
            throw new Exception("Ingrese un email válido");
        }
        if(contrasenia1 == null || contrasenia1.isEmpty()){
            throw new Exception("Ingrese una contraseña válida");
        }
        if(contrasenia2 == null || contrasenia2.isEmpty()){
            throw new Exception("Ingrese una contraseña válida");
        }
        if(!(contrasenia1.equals(contrasenia2))){
            throw new Exception("Las contraseñas no coinciden");
        }
        if(nombreUsuario == null || nombreUsuario.isEmpty()){
            throw new Exception("Ingrese un nombre de usuario válido");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = buscarUsuarioPorEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getEmail(), usuario.getContrasenia(), permisos);
            return user;

        } else {
            return null;
        }
    }
} 