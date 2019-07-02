package com.aplicacion.servlet;

import java.util.TreeSet;
import java.util.ArrayList;

public class Booleano{
    
    private Booleano(){}
    
    public static TreeSet<Tupla> mostrar(String clave, String op, String directorio){
        TreeSet<Tupla> resultado= new TreeSet<Tupla>();
        ArrayList<ArrayList<Boolean>> listaExistencia = Procesador.listaExistencia(Lector.convertir(directorio),clave);
        ArrayList<Boolean> listaBooleanos;
        
        if(op.equals("and"))
            listaBooleanos= operacionAND(listaExistencia);
        else if(op.equals("or"))
            listaBooleanos= operacionAND(listaExistencia);
        else
            listaBooleanos= new ArrayList<Boolean>();
            
        ArrayList<String> listaArchivos = Lector.listar(directorio);
        
        boolean aux;
        
        for(int i=0; i<listaArchivos.size(); i++){
            if(listaBooleanos.get(i))
                resultado.add(new Tupla(listaArchivos.get(i),new Double(1)));
            else
                resultado.add(new Tupla(listaArchivos.get(i),new Double(0)));
        }
        return resultado;
    }
    
    public static ArrayList<Boolean> operacionAND(ArrayList<ArrayList<Boolean>> xss){
        ArrayList<Boolean> base = new ArrayList<>();
        for(int i=0; i<xss.get(0).size(); i++)
            base.add(true);
            
        for(ArrayList<Boolean> xs : xss)
            and(base,xs);
        return base;
    }
    
    
    public static void and(ArrayList<Boolean> base, ArrayList<Boolean> lista){
        if(base.size()==lista.size()){
            Boolean aux;
            for(int i=0; i<base.size(); i++){
                aux= base.get(i) && lista.get(i);
                base.set(i,aux);
            }
        }
    }
    
    public static ArrayList<Boolean> operacionOR(ArrayList<ArrayList<Boolean>> xss){
        ArrayList<Boolean> base = new ArrayList<>();
        for(int i=0; i<xss.get(0).size(); i++)
            base.add(false);
        for(ArrayList<Boolean> xs : xss)
            or(base,xs);
        return base;
    }
    
    public static void or(ArrayList<Boolean> base, ArrayList<Boolean> lista){
        if(base.size()==lista.size()){
            Boolean aux;
            for(int i=0; i<base.size(); i++){
                aux= base.get(i) || lista.get(i);
                base.set(i,aux);
            }
        }
    }
}
