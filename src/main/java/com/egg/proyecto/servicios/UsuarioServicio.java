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

    public Usuario crearUsuario (String email, String contrasenia, String alias) throws Exception{
        validator(email,contrasenia,alias);
        Usuario usuario=new Usuario();
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setAlias(alias);
        return usuarioRepositorio.save(usuario);
    }

    public Usuario modificarUsuario(String id, String email, String contrasenia, String alias) throws Exception {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            validator(email, contrasenia, alias);
            Usuario usuario = respuesta.get();
            usuario.setEmail(email);
            usuario.setContrasenia(contrasenia);
            usuario.setAlias(alias);
            return usuarioRepositorio.save(usuario);         
    }else{
            throw new Exception ("No existe ese usuario");
        }
    }

    public void eliminarUsuario(String id, String email, String contrasenia, String alias){
        usuarioRepositorio.deleteById(id);
    }

    public void validator(String email, String contrasenia, String alias) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Ingrese un email válido");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new Exception("Ingrese una contraseña válida");
        }
        if (alias == null || alias.isEmpty()) {
            throw new Exception("Ingrese un alias válido");
        }
    }
} 