package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Poema;
import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.servicios.PoemaServicio;
import com.egg.proyecto.servicios.UsuarioServicio;
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
    @Autowired
    private UsuarioServicio usuarioService;
    
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
    public String guardarPoema(@RequestBody String titulo,@RequestBody String cuerpo,@RequestBody String email, Model model){
        Poema poema = new Poema();
        poema.setCuerpo(cuerpo);
        poema.setTitulo(titulo);
        Usuario autor = usuarioService.buscarUsuarioPorEmail(email);
        poema.setAutor(autor);
        if(poema.getTitulo() == null ||  poema.getCuerpo() == null || poema.getAutor().getId() == null){
            model.addAttribute("mensaje", "se pudrio todo");
            return "index";
        } else{
            poemaService.guardar(poema);
            model.addAttribute("mensaje", "el poema se guardo correctamente");
            return "index";
        }
    }
    
    @GetMapping("/editar")
    public String editarPoema(@RequestBody String titulo,@RequestBody String cuerpo,@RequestBody String id, Model model) throws Exception{
        poemaService.modificarPoema(id, titulo, cuerpo);
        model.addAttribute("msj","salio todo bien");
        return "profile";
    }
    
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPoema(@PathVariable String id){
        poemaService.eliminar(id);
        return "profile";
    }
}
