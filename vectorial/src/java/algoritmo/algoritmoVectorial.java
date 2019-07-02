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

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author linux
 */
public class algoritmoVectorial {
    public static ArrayList <Double> buscar(String palabra){
        ArrayList<String> palabrasAbuscar = separadorPalabras(palabra);
        ArrayList<ArrayList<String>> index = cargarArchivos();
        ArrayList<ArrayList<Integer>> listaCoincidencias = contarCoincidencias(palabrasAbuscar,index);
        ArrayList<ArrayList<Integer>> docs = separarDoc(listaCoincidencias);
        ArrayList<Integer> frec = getFrecuencias(listaCoincidencias);
        System.out.println(frec + "asdasdsa");
        ArrayList<Double> resultados =aplicarFuncion(docs,frec);
        ArrayList<Double> encontrados = ordenar(resultados);
        //ArrayList<Integer> encontrados = ordenar(resultados);
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
    private static ArrayList<String> getPalabrasTexto(String archivo, int a){
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
            Logger.getLogger(algoritmoVectorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(palabras.size()>0){ 
            int tam = palabras.size();
            ArrayList<String> texto = new ArrayList<>();
            for(int i=0;i<tam;i++){
                texto.addAll(separadorPalabras(palabras.get(i)));
            }
            return texto; 
        }else{
            return null;
        }
    }
   

    private static ArrayList<ArrayList<String>> cargarArchivos() {
        
        ArrayList<ArrayList<String>> archivos = new ArrayList<>();
        for(int i=1;i<11;i++){
            archivos.add(getPalabrasTexto("E:\\SEM 1 2018\\RECUPERACION DE LA INFORMACION\\vectorial\\web\\Textos\\" + i + ".txt",i));
        }
        return archivos;
        
    }
    private static ArrayList<ArrayList<Integer>>contarCoincidencias(
                ArrayList<String> aBuscar, ArrayList<ArrayList<String>> dondeBuscar){
        
        ArrayList<ArrayList<Integer>>resultados = new ArrayList<>();
        int tam = aBuscar.size();
        for(int i=0;i<tam;i++){
            String buscado = aBuscar.get(i);
            System.out.println(buscado);
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0;j<10;j++){
                int contador = 0;
                ArrayList<String> aux= dondeBuscar.get(j);
                int tamanho = aux.size();
                for(int k=0;k<tamanho;k++){
                    if(buscado.equals(aux.get(k))){
                        contador++;
                    }
                }
                list.add(contador);
            }
            resultados.add(list);
        }
        System.out.println(resultados);
        return resultados;
    }
    private static ArrayList<ArrayList<Integer>> separarDoc(ArrayList<ArrayList<Integer>> ls){
        ArrayList <ArrayList<Integer>> res = new ArrayList<>(); 
        for(int i=0;i<10;i++){
            ArrayList<Integer> aux= new ArrayList<>();
            for(int j=0;j<ls.size();j++){
                aux.add(ls.get(j).get(i));
            }
            res.add(aux);
        }
        System.out.println(res);
        return res;
    }
    private static ArrayList<Double> aplicarFuncion(ArrayList<ArrayList<Integer>> lista,ArrayList<Integer> frecTot){
        ArrayList<Double> resultados = new ArrayList<>();
        int tam = lista.get(0).size();
        for(int i=0;i<10;i++){
            ArrayList <Integer> li = lista.get(i);
            ArrayList<Double> res = new ArrayList<>();
            for(int j=0;j< tam; j++){
                if(li.get(j)>0){
                    double div = 10/(double)li.get(j);
                    res.add(Math.abs(frecTot.get(j)*( Math.log10(div))));
                }
            }
            System.out.println(res);
            resultados.add(sumar(res));
        }
        System.out.println(resultados);
        return resultados;
    }
    private static double sumar(ArrayList<Double>ls){
        double total = 0;
        for(Double n: ls){
            total +=n;
        }
        return total;
    }
    private static ArrayList<Integer> getFrecuencias(ArrayList<ArrayList<Integer>> palabras){
        ArrayList<Integer> resultados = new ArrayList<>();
        for(ArrayList <Integer> ls: palabras){
            int contador  = 0;
            for(Integer n : ls){
                contador += n;
            }
            resultados.add(contador);
        }
        return resultados;
    }
    private static ArrayList<Double> ordenar(ArrayList<Double> lis){    
        ArrayList <ArrayList<Object>> lista = new ArrayList<>();
        
        for(int i=0;i<10;i++){
            ArrayList<Object> ls = new ArrayList<>();
            if(lis.get(i)!=0){
                ls.add(lis.get(i));
                ls.add(i);
                lista.add(ls);
            }
        }
        for(int i=0;i<lista.size();i++){
            for(int j=0;j<lista.size()-1;j++){
                if((Double)lista.get(i).get(0)>(Double)lista.get(j).get(0)){
                    ArrayList<Object> lss = new ArrayList<>(lista.get(i));
                    lista.set(i,lista.get(j));
                    lista.set(j, lss);
                    System.out.println(lista.get(i)+" "+lista.get(j));
                }
            }
        }
        System.out.println(lista);
        ArrayList<Double> ordenado = new ArrayList<>();
        for(int i=0; i<lista.size();i++){
            ordenado.add((Double)lista.get(i).get(0));
        }
        //System.out.println(ordenado);
        return ordenado;
    }
}
    