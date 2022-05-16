package com.egg.proyecto.servicios;

import com.egg.proyecto.entidades.Poema;
import com.egg.proyecto.entidades.Usuario;
import com.egg.proyecto.repositorios.PoemaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PoemaServicio {
    @Autowired
    private PoemaRepositorio poemaRepositorio;
    
    @Transactional(readOnly = true)
    public List<Poema> listarPoemas(){
        return poemaRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Poema> listarPoemasPorUsuario(String idUsuario){
        return poemaRepositorio.listarPoemasPorUsuario(idUsuario);
    }
    
    @Transactional
    public void guardar (String titulo, String cuerpo, Date fecha, Usuario autor) throws Exception{
        validaciones(titulo, cuerpo);
        Poema poema = new Poema();
        poema.setTitulo(titulo);
        poema.setCuerpo(cuerpo);
        poema.setFecha(fecha);
        poema.setAutor(autor);
        poemaRepositorio.save(poema);
    }
    @Transactional
    public void eliminar (String id){
        poemaRepositorio.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Poema buscarPorId(Poema poema){
        return poemaRepositorio.findById(poema.getId()).orElse(null);
    }
    public Poema modificarPoema(String id, String titulo, String cuerpo) throws Exception {
    Optional<Poema> respuesta = poemaRepositorio.findById(id);
      if (respuesta.isPresent()) {
          Poema poema = respuesta.get();
          poema.setTitulo(titulo);
          poema.setCuerpo(cuerpo);
          return poemaRepositorio.save(poema);
          }else{
            throw new Exception ("No existe ese poema");
        }
    }
    
    public void validaciones(String titulo, String cuerpo) throws Exception{
       if(titulo == null || cuerpo.isEmpty()){
           throw new Exception("El titulo es nulo o está vacío");
       }
       if(cuerpo == null || cuerpo.isEmpty()){
           throw new Exception("El cuerpo es nulo o está vacío");
       }
    }
}
