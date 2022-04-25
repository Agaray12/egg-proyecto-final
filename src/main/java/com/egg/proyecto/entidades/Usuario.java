package com.egg.proyecto.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id; 
    private String email;
    private String contrasenia;
    private String nombreUsuario;
    
    @OneToMany
    private List<Poema> poemas;

    public Usuario() {
    }

    public Usuario(String id, String email, String contrasenia, String nombreUsuario, List<Poema> poemas) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombreUsuario = nombreUsuario;
        this.poemas = poemas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contraseña) {
        this.contrasenia = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Poema> getPoemas() {
        return poemas;
    }

    public void setPoemas(List<Poema> poemas) {
        this.poemas = poemas;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", contrasenia=" + contrasenia + ", nombre de usuario=" + nombreUsuario + ", poemas=" + poemas + '}';
    }
}
