/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacion.servlet;

import java.util.ArrayList;
import java.util.TreeSet;


public class Contenedor {
    
    private ArrayList<Tupla> lista;

    public Contenedor(){
        lista = new ArrayList<>();
    }
    
    public Contenedor(TreeSet<Tupla> arbol){
        lista = new ArrayList<>();
        for(Tupla tupla : arbol)
            lista.add(tupla);
    }
    
    public void aniadir(String palabra){
        if(palabra!=null){
            Tupla tupla = encuentra(palabra);
            if(tupla!=null){
                int numero= tupla.getNumero().intValue();
                tupla.setNumero(new Double(numero+1));
            }else
                lista.add(new Tupla(palabra,new Double(1)));
        }
    }
    
    public Tupla encuentra(String cadena){
        Tupla elegido =null;
        for(Tupla tupla : lista)
            if(cadena.equals(tupla.getCadena()))
                elegido= tupla;
        return elegido;
    }
    
    public TreeSet<Tupla> getLista(){
        TreeSet<Tupla> listaOrdenada = new TreeSet<>();
        for(Tupla tupla : lista)
            listaOrdenada.add(tupla);
        return listaOrdenada;
    }
    
    
}
