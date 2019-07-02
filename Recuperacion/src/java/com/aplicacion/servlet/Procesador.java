package com.aplicacion.servlet;

import java.util.ArrayList;

public class Procesador{
    
    private Procesador(){}
    
    public static ArrayList<ArrayList<Double>> listasOcurrencias(ArrayList<ArrayList<String>> lista, String clave){
        ArrayList<ArrayList<Double>> resultados = new ArrayList<>();
        for(String subClave : clave.split(" "))
            resultados.add(listaOcurrencias(lista,subClave));
        return resultados;
    }
    
    public static ArrayList<ArrayList<Boolean>> listaExistencia(ArrayList<ArrayList<String>> lista, String clave){
        ArrayList<ArrayList<Boolean>> resultados = new ArrayList<>();
        ArrayList<ArrayList<Double>> ocurrencias = listasOcurrencias(lista,clave);
        ArrayList<Boolean> auxiliar;
        for(ArrayList<Double> ocurrencia : ocurrencias){
            auxiliar= new ArrayList<>();
            for(double el : ocurrencia){
                if(el==0)
                    auxiliar.add(false);
                else
                    auxiliar.add(true);
            }
            resultados.add(auxiliar);
        }
        return resultados;
    }
    
    private static ArrayList<Double> listaOcurrencias(ArrayList<ArrayList<String>> lista, String clave){
        ArrayList<Double> resultados = new ArrayList<>();
        int numero;
        for(ArrayList<String> el : lista){
            numero = ocurrencias(el,clave);
            resultados.add(new Double(numero));
        }
        return resultados;
    }
    
    private static int ocurrencias(ArrayList<String> lista, String clave){
        int respuesta=0;
        for(String el : lista)
            if(el.equals(clave))
                respuesta++;
        return respuesta;
    }
}