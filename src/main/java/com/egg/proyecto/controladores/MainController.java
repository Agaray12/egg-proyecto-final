package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Poema;
import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.servicios.PoemaServicio;
import com.egg.proyecto.utilidades.Comparadores;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    private PoemaServicio poemaServicio;
    
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
       @GetMapping("/registro")
    public String registro(){
       return "registro";
    }
    

    @GetMapping("/profile")
    public String perfil(Model model, HttpSession session){

        try{
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            List<Poema> poemas = poemaServicio.listarPoemasPorUsuario(u.getId());
            Collections.sort(poemas, Comparadores.ordernarPorFecha);
            model.addAttribute("usuario", u);
            model.addAttribute("poemas", poemas);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "index";
        }
        return "profile";
    }
        
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
    

    
}
