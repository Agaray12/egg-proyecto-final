package com.egg.proyecto.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErroresController implements ErrorController{

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String pagErrores(HttpServletRequest httpServletRequest, ModelMap model){
        String msjError = "";
        int codigo = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        switch(codigo){
            case 400:
                msjError = "El recurso solicitado no existe";
                break;
            case 401:
                msjError = "No se encuentra autorizado";
                break;
            case 403:
                msjError = "No tiene permisos para acceder al recurso";
                break;
            case 404:
                msjError = "No se encuentra el recurso solicitado";
                break;
            case 500:
                msjError = "El servidor no pudo ejecutar la peticion con exito";
                break;
                
        }
        model.put("error", msjError);
        model.put("codigo", codigo);
        return "error";
    }
}
