package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class MainController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login(){
       return "login";
    }
     @GetMapping("/profile")
    public String profile(){
       return "profile";
    }
    
    @PostMapping("/profile")
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
