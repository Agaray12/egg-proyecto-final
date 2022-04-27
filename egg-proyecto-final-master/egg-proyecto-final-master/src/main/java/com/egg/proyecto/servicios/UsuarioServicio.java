package com.egg.proyecto.servicios;

import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario>findAll(){
        return usuarioRepositorio.findAll();
    }

    public Usuario crearUsuario (String email, String contrasenia1, String contrasenia2, String nombreUsuario) throws Exception{
        validator(email,contrasenia1, contrasenia2, nombreUsuario);
        Usuario usuario=new Usuario();
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia1);
        usuario.setNombreUsuario(nombreUsuario);
        return usuarioRepositorio.save(usuario);
    }

    public Usuario modificarUsuario(String id, String email, String contrasenia, String nombreUsuario) throws Exception {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            updateValidator(email, contrasenia, nombreUsuario);
            Usuario usuario = respuesta.get();
            usuario.setEmail(email);
            usuario.setContrasenia(contrasenia);
            usuario.setNombreUsuario(nombreUsuario);
            return usuarioRepositorio.save(usuario);
    }else{
            throw new Exception ("No existe ese usuario");
        }
    }

    public void eliminarUsuario(String id, String email, String contrasenia, String alias){
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
    
    public void updateValidator(String email, String contrasenia, String nombreUsuario) throws Exception{
        if(email == null || email.isEmpty()){
            throw new Exception("Ingrese un email válido");
        }
        if(contrasenia == null || contrasenia.isEmpty()){
            throw new Exception("Ingrese una contraseña válida");
        }
        if(nombreUsuario == null || nombreUsuario.isEmpty()){
            throw new Exception("Ingrese un nombre de usuario válido");
        }
    }
} 