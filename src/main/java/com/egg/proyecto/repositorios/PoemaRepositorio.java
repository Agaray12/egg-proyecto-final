package com.egg.proyecto.repositorios;

import com.egg.proyecto.entidades.Poema;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemaRepositorio extends JpaRepository<Poema, String>{
    
    @Query("SELECT p FROM Poema p WHERE p.autor.id = :id")
    public List<Poema> listarPoemasPorUsuario(@Param("id") String id);
}
