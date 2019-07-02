package com.aplicacion.servlet;

import java.util.TreeSet;
import java.util.ArrayList;

public class Probabilistico{
    
    private Probabilistico(){}
    
    public static TreeSet<Tupla> mostrar(String clave, String directorio){
        TreeSet<Tupla> resultado= new TreeSet<Tupla>();
        ArrayList<ArrayList<Double>> listaOcurrencia = Procesador.listasOcurrencias(Lector.convertir(directorio),clave);
        
        ArrayList<Double> listaResultados= proceso(listaOcurrencia);
        ArrayList<String> listaArchivos = Lector.listar(directorio);
        
        Tupla tupla;
        
        for(int i=0; i<listaResultados.size();i++){
            tupla = new Tupla(listaArchivos.get(i),listaResultados.get(i));
            resultado.add(tupla);
        }
        
        return resultado;
    }
    
    private static ArrayList<Double> proceso(ArrayList<ArrayList<Double>> xss){
        int numeroDocs = xss.get(0).size();
        int numeroDocsElegidos;
        ArrayList<ArrayList<Double>> respuesta = new ArrayList<>();
        ArrayList<Double> lista;
        
        double I,aux;
        
        for(ArrayList<Double> xs : xss){
            numeroDocsElegidos = sumarCantidad(xs);
            lista= new ArrayList<>();
            I= (((double)sumaElementos(xs))/((double)numeroDocs))/100.0;
            
            for(double x : xs){
                if(x!=0.0){
                    aux = Math.log10(Math.abs(I / (1-I)))+ Math.log10(Math.abs((1-((double)numeroDocsElegidos/(double)numeroDocs))/((double)numeroDocsElegidos/(double)numeroDocs)));
                    lista.add(aux);
                }else{
                    lista.add(0.0);
                    aux = 0;
                }
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
    
    private static int sumarCantidad(ArrayList<Double> xs){
        int suma=0;
        for(double x : xs)
            if(x!=0)
                suma += 1;
        return suma;
    }
}

