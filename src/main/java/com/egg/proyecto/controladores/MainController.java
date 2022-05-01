package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class MainController {
    
    @GetMapping("/")
    public String index(@RequestParam(required = false) String login, ModelMap model) {
        if(login != null){
            model.put("exito", "Logueado con exito");
        }
        return "index";
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model){
        if(error != null){
            model.put("error", "Usuario o contraseña incorrectos");
        }
        if(logout != null){
            model.put("logout", "Desconectado correctamente");
        }
        return "login";
    }
    
    @GetMapping("/profile")
    public String perfil(ModelMap modelo, HttpSession session){
        try{
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            modelo.put("usuario", u);
        }catch(Exception e){
        }
        return "profile";
    }
        
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
}