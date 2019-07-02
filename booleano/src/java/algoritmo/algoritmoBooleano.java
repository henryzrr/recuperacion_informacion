/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author linux
 */
public class algoritmoBooleano {
    public static ArrayList <Integer> buscar(String palabra){
        ArrayList<String> palabrasAbuscar = separadorPalabras(palabra);
        ArrayList<HashMap<String,Integer>> index = cargarArchivos();
        ArrayList <boolean [] > resultados = buscarCoincidencias(palabrasAbuscar,index);
        ArrayList<Integer> encontrados =operadorBooleano(resultados);
        return encontrados;
    }
    private static ArrayList<String> separadorPalabras(String cadena){
        String [] palabras = cadena.split(" ");
        ArrayList<String> lista = new ArrayList<>();
        for (String elem:palabras){
            if(!elem.equals("")){
                lista.add(elem.toLowerCase());
            }
        }
        return lista;
    }
    private static HashMap<String,Integer> getPalabrasTexto(String archivo, int a){
        ArrayList<String> palabras = new ArrayList<>();
        try {
            FileReader f = new FileReader(archivo);
            String cadena;
            try (BufferedReader b = new BufferedReader(f)) {
                while((cadena = b.readLine())!=null) {
                    palabras.add(cadena);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(algoritmoBooleano.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(palabras.size()>0){ 
            int tam = palabras.size();
            HashMap<String,Integer> texto = new HashMap<>();
            for(int i=0;i<tam;i++){
                addArrayList(texto,separadorPalabras(palabras.get(i)),a);
            }
            return texto; 
        }else{
            return null;
        }
    }
    private static void addArrayList(HashMap <String,Integer> ls, ArrayList<String> cad,int idtexto){
        for(String value:cad){
            ls.put(value, idtexto);
        }
    }

    private static ArrayList<HashMap<String,Integer>> cargarArchivos() {
        
        ArrayList<HashMap<String,Integer>> archivos = new ArrayList<>();
        for(int i=1;i<11;i++){
            archivos.add(getPalabrasTexto("C:\\Users\\linux\\Documents\\NetBeansProjects\\WebApplication3\\web\\Textos\\" + i + ".txt", i));
        }
        return archivos;
    }

    private static ArrayList <boolean []> buscarCoincidencias(ArrayList<String> palabrasAbuscar, ArrayList<HashMap<String,Integer>> index) {
        ArrayList<boolean []> listaEncontrados = new ArrayList<>();
        int tam = palabrasAbuscar.size();
        int tam2 = index.size();
        for(int i=0;i<tam;i++){
            String aBuscar = palabrasAbuscar.get(i);
            boolean existencia [] = new boolean [10] ; 
            for(int j=0;j<tam2;j++){
                HashMap<String,Integer> aux = index.get(j);
                if(aux.containsKey(aBuscar)){
                    existencia[j] = true; 
                }else{
                    existencia[j]=false;
                }
            }
            listaEncontrados.add(existencia);
        }
        return listaEncontrados;
    }

    private static ArrayList<Integer> operadorBooleano(ArrayList<boolean[]> resultados) {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<10;i++){
            boolean or = false;
            for(boolean[] var:resultados){
                or = or||var[i];
            }
            if(or){
                res.add(i+1);
            }
        }
            
        return res;
    }
}
    