package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
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
    public String perfil(Model model, HttpSession session){
        try{
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.addAttribute("usuario", u);
        }catch(Exception e){
        }
        return "profile";
    }
        
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
}