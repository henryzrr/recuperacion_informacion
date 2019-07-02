package com.aplicacion.servlet;

import java.util.TreeSet;

public class Principal{
    private Principal(){}
    
    
    public static TreeSet<Tupla> getResultados(String clave, String modelo, String op, String carpeta){
        TreeSet<Tupla> resultados;
        if(modelo.equals("booleano"))
            resultados = Booleano.mostrar(clave,op,carpeta);
        else if(modelo.equals("vectorial"))
            resultados = Vectorial.mostrar(clave,carpeta);
        else if(modelo.equals("probabilistico"))
            resultados = Probabilistico.mostrar(clave,carpeta);
        else
            resultados = new TreeSet<>();
        return resultados;
    }
    
    public static void getResultados(){
        for(Tupla el : getResultados("programa proceso","booleano","and","/home/rudy/Documentos/TEXTOS"))
            System.out.println(el);
    }
    
}