package com.egg.proyecto.repositorios;

import com.egg.proyecto.entidades.Poema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemaRepositorio extends JpaRepository<Poema, String>{
    
}
