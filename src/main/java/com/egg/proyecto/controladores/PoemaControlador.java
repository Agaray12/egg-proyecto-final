package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Poema;
import com.egg.proyecto.servicios.PoemaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poema")
public class PoemaControlador {

    @Autowired
    private PoemaServicio poemaService;
    
    @GetMapping("/lista")
    public String poemas(Model model){
        List<Poema> poemas = poemaService.listarPoemas();
        model.addAttribute("poemas", poemas);
        return "index";
    }
    
    @GetMapping("/crear")
    public String guardarPoema(){
        return "crear_poema";
    }
    
    @PostMapping("/guardarPoema") //action = "localhost8080/guardarPoema" method="POST"
    public String guardarPoema(@RequestBody Poema poema, Model model){
        
        if(poema.getTitulo() == null ||  poema.getCuerpo() == null){
            model.addAttribute("mensaje", "se pudrio todo");
            return "index";
        } else{
            poemaService.guardar(poema);
            model.addAttribute("mensaje", "el poema se guardo correctamente");
            return "index";
        }
    }
    
    @GetMapping("/editar")
    public String editarAutor(Poema poema, Model model){
        poema = poemaService.buscarPorId(poema);
        model.addAttribute("poema",poema);
        return "modificarPoema";
    }
    
    @GetMapping("/editar/{id}")
    public String editarAutor2(Poema poema,Model model){
        poema = poemaService.buscarPorId(poema);
        model.addAttribute("poema", poema);
        return "editarPoema";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPoema(@PathVariable String id){
        poemaService.eliminar(id);
        return "profile";
    }
}
