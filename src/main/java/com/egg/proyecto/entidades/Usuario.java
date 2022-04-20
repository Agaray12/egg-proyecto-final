/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.proyecto.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuario
 */
 @Entity
public class Usuario {
    @Id
    private String nombreDeUsuario;
    
    private String contraseña;
    
    private String alias;
    
    private List<Poema> poemas;

    public Usuario() {
    }

    public Usuario(String nombreDeUsuario, String contraseña, String alias, List<Poema> poemas) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contraseña = contraseña;
        this.alias = alias;
        this.poemas = poemas;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Poema> getPoemas() {
        return poemas;
    }

    public void setPoemas(List<Poema> poemas) {
        this.poemas = poemas;
    }
    
    
}
