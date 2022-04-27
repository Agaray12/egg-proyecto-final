package com.egg.proyecto.controladores;

import com.egg.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class UsuarioControlador {
@Autowired 
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/login")
    public String formulario(){
        return "login";
    }
    
    @PostMapping ("/login")
    public String crearUsuario(ModelMap mod,@RequestParam String id, @RequestParam String email, @RequestParam String contrasenia, @RequestParam String nombreUsuario) throws Exception{
        try {
          usuarioServicio.crearUsuario(email, contrasenia, contrasenia, nombreUsuario);
          mod.put("Exito", "Usuario registrado con éxito!");
          return "login";
        }catch (Exception e){
            mod.put("Error", "Verifique los datos ingresados");
            return "login";
        }
        
    }
}
