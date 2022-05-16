package com.egg.proyecto.utilidades;

import com.egg.proyecto.entidades.Poema;
import java.util.Comparator;


public class Comparadores {
    public static Comparator<Poema> ordernarPorFecha = new Comparator<Poema>() {
        @Override
        public int compare(Poema p1, Poema p2) {       
            return p2.getFecha().compareTo(p1.getFecha()); 
        }
    }; 
}
