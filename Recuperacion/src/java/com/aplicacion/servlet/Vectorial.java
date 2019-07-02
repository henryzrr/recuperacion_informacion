package com.aplicacion.servlet;

import java.util.TreeSet;
import java.util.ArrayList;

public class Vectorial{
    
    private Vectorial(){}
    
    public static TreeSet<Tupla> mostrar(String clave, String directorio){
        TreeSet<Tupla> resultado= new TreeSet<Tupla>();
        ArrayList<ArrayList<Double>> listaOcurrencia = Procesador.listasOcurrencias(Lector.convertir(directorio),clave);
        
        ArrayList<Double> listaResultados= proceso(listaOcurrencia);
        ArrayList<String> listaArchivos = Lector.listar(directorio);
        
        for(int i=0; i<listaResultados.size();i++){
            resultado.add(new Tupla(listaArchivos.get(i),listaResultados.get(i)));
        }
        
        return resultado;
    }

    private static ArrayList<Double> proceso(ArrayList<ArrayList<Double>> xss){
        double suma;
        int numeroDocs = xss.get(0).size();
        
        ArrayList<ArrayList<Double>> respuesta = new ArrayList<>();
        ArrayList<Double> lista;
        
        for(ArrayList<Double> xs : xss){
            suma = sumaElementos(xs);
            lista= new ArrayList<>();
            
            for(double x : xs){
                if(x!=0){
                    double aux= Math.abs(suma*Math.log10(numeroDocs/x));
                    lista.add(aux);
                }else
                    lista.add(0.0);
            }
            
            respuesta.add(lista);
        }
        
        return sumarColumnas(respuesta);
    }
    
    private static ArrayList<Double> sumarColumnas(ArrayList<ArrayList<Double>> xss){
        ArrayList<Double> ys= new ArrayList<>();
        
        for(double x : xss.get(0))
            ys.add(0.0);
            
        int i;
        double suma;
        
        for(ArrayList<Double> xs: xss){
            i=0;
            for(double x : xs){
                suma = ys.get(i) + x;
                ys.set(i,suma);
                i++;
            }
        }
        
        return ys;
    }
    
    private static int sumaElementos(ArrayList<Double> lista){
        int suma=0;
        for(double elemento : lista)
            suma += elemento;
        return suma;
    }
}
