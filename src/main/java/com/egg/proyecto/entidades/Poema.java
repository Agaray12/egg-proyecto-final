package com.egg.proyecto.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "poemas")
public class Poema {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Usuario autor;
    private String titulo;
    private String cuerpo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Poema() {
    }

    public Poema(String id, Usuario autor, String titulo, String cuerpo, Date fecha) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Poema{" + "id=" + id + ", autor=" + autor + ", titulo=" + titulo + ", cuerpo=" + cuerpo + ", fecha=" + fecha + '}';
    }  
}
