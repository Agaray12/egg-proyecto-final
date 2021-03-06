package com.egg.proyecto.controladores;

import com.egg.proyecto.entidades.Poema;
import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.servicios.PoemaServicio;
import com.egg.proyecto.servicios.UsuarioServicio;
import com.egg.proyecto.utilidades.Comparadores;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired 
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private PoemaServicio poemaServicio;
    
    @GetMapping("/registro")
    public String formulario(){
        return "registro";
    }
    
    @PostMapping ("/registro")
    public String crearUsuario(ModelMap mod, @RequestParam String email, @RequestParam String nombreUsuario, @RequestParam String contrasenia1, @RequestParam String contrasenia2) throws Exception{
        try {
          usuarioServicio.crearUsuario(email, contrasenia1, contrasenia2, nombreUsuario);
          mod.put("Exito", "Usuario registrado con éxito!");
        }catch (Exception e){
            mod.put("Error", "Verifique los datos ingresados");
            return "registro";
        }
        return "lista_perfiles";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model, HttpSession session){
        try{
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
        }catch(Exception e){
        }
        return "editar_perfiles";
    }
    
    @PostMapping("/editar/{id}")
    public String editarPerfil(@PathVariable String id, @RequestParam String email, @RequestParam String nombreUsuario, @RequestParam String contrasenia1, @RequestParam String contrasenia2, RedirectAttributes redirectAttributes, ModelMap model){
        try{
            Usuario u = usuarioServicio.modificarUsuario(id, email, contrasenia1, contrasenia2, nombreUsuario);
            model.put("exito", "Usuario modificado con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
            return "lista_perfiles";
        }catch(Exception e){
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/editar";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap model){
        List<Usuario> usuarios = usuarioServicio.findAll();
        model.addAttribute("usuarios", usuarios);
        return "lista_perfiles";
    }
    
    @GetMapping("/{nombre}")
    public String profile(ModelMap model, @PathVariable String nombre){
        Usuario u2 = usuarioServicio.buscarUsuarioPorNombre(nombre);
        List<Poema> poemas = poemaServicio.listarPoemasPorUsuario(u2.getId());
        Collections.sort(poemas, Comparadores.ordernarPorFecha);
        model.put("usuario", u2);
        model.put("poemas", poemas);
        return "profile";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPoema(@PathVariable String id){
        usuarioServicio.eliminarUsuario(id);
        return "lista_perfiles";
    }
}
