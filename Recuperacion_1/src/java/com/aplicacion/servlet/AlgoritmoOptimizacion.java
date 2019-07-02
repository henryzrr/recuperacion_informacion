/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacion.servlet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author debian
 */
public class AlgoritmoOptimizacion {
    ArrayList <ArrayList<String>> sinonimos;
    ArrayList <String> palabrasRaiz;
    ArrayList <ArrayList<String>> desambiguacion;
    ArrayList <String> sugerenciaBusqueda;
    public AlgoritmoOptimizacion(){
        sinonimos = new ArrayList<>();
        anhadirSinonimos();
        palabrasRaiz = new ArrayList<>();
        anhadirPalabrasRaiz();
        desambiguacion = new ArrayList<>();
        anhadirDesambiguacion();
        sugerenciaBusqueda = new ArrayList<>();
        anhadirSugerencias();
    }

    private void anhadirSinonimos() {
        sinonimos.add(new ArrayList<>(Arrays.asList("pipocas", "palomitas", "pochoclos",
               "cabritas", "cofutas" )));
        sinonimos.add(new ArrayList<>(Arrays.asList("trabajo", "pega", "chamba",
               "laburo" )));
        sinonimos.add(new ArrayList<>(Arrays.asList("maiz", "choclo", "elote")));
       
        sinonimos.add(new ArrayList<>(Arrays.asList("micro", "colectivo", "autobus",
               "camion")));
        sinonimos.add(new ArrayList<>(Arrays.asList("amigo", "pana", "cuate",
               "parner", "compinche" )));
    }
    private void anhadirPalabrasRaiz(){
        palabrasRaiz.add("gat"); //gato
        palabrasRaiz.add("lim"); //lima
        palabrasRaiz.add("escuadr"); //puma
        palabrasRaiz.add("cuat"); //cura
        palabrasRaiz.add("capit"); //capital
        palabrasRaiz.add("pat"); //pato
        palabrasRaiz.add("flor"); //flor
        palabrasRaiz.add("caj"); //caja
        palabrasRaiz.add("tortug"); //tortuga
        palabrasRaiz.add("milit"); //maleta
 
    }
    private void anhadirDesambiguacion(){
        desambiguacion.add(new ArrayList<>(Arrays.asList("lima", "lima fruta", "lima capital",
                "lima herramienta")));
        desambiguacion.add(new ArrayList<>(Arrays.asList("escuadra", "escuadra militar", 
                "escuadra geometrica")));
        desambiguacion.add(new ArrayList<>(Arrays.asList("cura", "cura de sanar",
                "cura sacerdote")));
        desambiguacion.add(new ArrayList<>(Arrays.asList("capital", "capital economia", 
                "capital pais")));
        desambiguacion.add(new ArrayList<>(Arrays.asList("gato", "gato mecanico", 
                "gato mascota")));
    }
    private void anhadirSugerencias(){
        sugerenciaBusqueda.add("trabajo");
        sugerenciaBusqueda.add("escuadra");
        sugerenciaBusqueda.add("capital");
        sugerenciaBusqueda.add("pandereta");
        sugerenciaBusqueda.add("tijera");
        sugerenciaBusqueda.add("taladro");
        sugerenciaBusqueda.add("cuadrado");
        sugerenciaBusqueda.add("potosi");
        sugerenciaBusqueda.add("solsticio");
        sugerenciaBusqueda.add("mecanico");
    }
    public void retornarSinonimos(ArrayList<String> aBuscar){
        ArrayList<String> resultados = new ArrayList<>();
        for(String ls : aBuscar){
            for(ArrayList<String> sinon : sinonimos ){
                if (sinon.contains(ls)){
                    resultados.addAll(sinon);
                }
            }
            if (!resultados.contains(ls)){
                resultados.add(ls);
            }
        }
        aBuscar.clear();
        aBuscar.addAll(resultados);
    }
    public ArrayList<String>getOpcionesDesambiguacion(String aBuscar){
         ArrayList<String> resultados = new ArrayList<>();
         for(ArrayList<String> desamb : desambiguacion ){
            if (desamb.get(0).equals(aBuscar)){
                for(int i=1;i<desamb.size();i++){
                    resultados.add(desamb.get(i));
                }
            }
         }
        return resultados;
    }
    public ArrayList<String> verificarPalabra(ArrayList <String> aBuscar){
        ArrayList<String> respuesta = new ArrayList<>();
        for (String elem:aBuscar){
            char [] palabra1 = elem.toCharArray();
            boolean cambiado =false;
            for(String sugerencia: sugerenciaBusqueda ){
                char[] palabra2 = sugerencia.toCharArray();
                int res;
                if(palabra1.length>palabra2.length){
                    res = 3;
                }else{
                    res = compararListas(palabra1, palabra2, palabra1.length);
                }
                switch(res){
                    case 0:
                        respuesta.add(sugerencia);
                        cambiado = true;
                        break;
                    case 1:
                        respuesta.add(sugerencia);
                        cambiado = true;
                        break;
                    case 2:
                        respuesta.add(sugerencia);
                        cambiado = true;
                        break;
                    default:
                        break;
                }
                
            }
            if (cambiado == false){
                //respuesta.add(elem);
            }
        }
        return respuesta;
    }
    private int compararListas(char [] menor, char [] mayor, int tamM){
        int resp=0;
        int tam2 = mayor.length;
        int diferencia = tam2-tamM;
        if(diferencia<=2){
            int aux = 0;
            int cont = 0;
            int nocoincide = 0;
            while(cont<tamM && nocoincide<3 ){
                if(menor[cont]!=mayor[cont+aux]){
                    nocoincide++;
                    if(cont+1<tamM){
                        if(menor[cont+1]!=mayor[cont+1]){
                            if(menor[cont]!=mayor[cont+aux]){
                                aux++;
                            }else{
                                cont++;
                            }
                        }else{
                            cont++;
                        }
                        
                    }else{
                        cont++;
                    }
                    
                }else{
                    cont++;
                }
            }
            if(nocoincide>2){
                resp=-1;
            }else{
                resp = nocoincide;
            }
        }else {
            resp = -1;
        }
        return resp;
    }
    public void getRaizPalabra (ArrayList<String> lista){
        int tam = lista.size();
        for( int i=0;i<tam;i++){
            for(String raiz: palabrasRaiz){
                if(lista.get(i).contains(raiz)){
                    lista.set(i, raiz);
                }
            }
        }
    }
}
