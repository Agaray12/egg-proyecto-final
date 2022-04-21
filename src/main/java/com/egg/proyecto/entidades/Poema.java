/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Poema {
    
    private Usuario autor;
    
    private String titulo;
    
    private String cuerpo;
    
    private String fecha;

    public Poema() {
    }

    public Poema(Usuario autor, String titulo, String cuerpo, String fecha) {
        this.autor = autor;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
