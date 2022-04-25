package com.egg.proyecto.repositorios;

import com.egg.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarUsuarioPorEmail(@Param("email") String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuari = :nombreUsuario")
    public Usuario buscarUsuarioPorNombre(@Param("nombreUsuario") String nombreUsuario);
}
