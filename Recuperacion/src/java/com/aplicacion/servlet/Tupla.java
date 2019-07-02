package com.aplicacion.servlet;

public class Tupla implements Comparable<Tupla>{
    private String cadena;
    private Double numero;
    
    public Tupla(String cadena, Double numero){
        setCadena(cadena);
        setNumero(numero);
    }
    
    public void setCadena(String cadena){
        this.cadena = cadena;
    }
    
    public void setNumero(Double numero){
        this.numero = numero;
    }
    
    public String getCadena(){
        return cadena;
    }
    
    public Double getNumero(){
        return numero;
    }
    
    public String toString(){
        return cadena + "  ---->  " + numero;
    }
    
    @Override
    public int compareTo(Tupla o) {
        int respuesta=-1;
        if(numero<o.numero)
            respuesta=1;
        else if(numero>o.numero)
            respuesta=-1;
        return respuesta;
    }
    
    @Override
    public boolean equals(Object o) {
        return false;
    }
}