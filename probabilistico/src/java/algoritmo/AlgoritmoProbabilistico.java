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
import javax.swing.JOptionPane;


/**
 *
 * @author linux
 */
public class AlgoritmoProbabilistico {
    public static ArrayList <Integer> buscar(String palabra){
        ArrayList<String> palabrasAbuscar = separadorPalabras(palabra);
        ArrayList<ArrayList<String>> index = cargarArchivos();
        ArrayList<ArrayList<Integer>> listaCoincidencias = contarCoincidencias(palabrasAbuscar,index);
        ArrayList<ArrayList<Integer>> docs = separarDoc(listaCoincidencias);
        ArrayList<Integer> frec = getFrecuencias(listaCoincidencias);
        System.out.println(frec + "asdasdsa");
        ArrayList<Double> prom = getPromedio(listaCoincidencias);
        ArrayList<Double> resultados =aplicarFuncion(docs,frec,prom);
        ArrayList<Integer> encontrados = ordenar(resultados);
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
            Logger.getLogger(AlgoritmoProbabilistico.class.getName()).log(Level.SEVERE, null, ex);
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
            archivos.add(getPalabrasTexto("E:\\SEM 1 2018\\RECUPERACION DE LA INFORMACION\\probabilistico\\web\\Textos\\" + i + ".txt",i));
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
    private static ArrayList<Double> aplicarFuncion(ArrayList<ArrayList<Integer>> lista,ArrayList<Integer> frecTot
                            , ArrayList<Double>promedio){
        ArrayList<Double> resultados = new ArrayList<>();
        int tam = lista.get(0).size();
        for(int i=0;i<10;i++){
            ArrayList <Integer> li = lista.get(i);
            ArrayList<Double> res = new ArrayList<>();
            for(int j=0;j< tam; j++){
                if(li.get(j)>0){
                    double peso1 = Math.abs(Math.log10(promedio.get(j)/(1-promedio.get(j))));
                    double peso2 = Math.abs(Math.log10((1-((double)frecTot.get(j)/10.0))/(((double)frecTot.get(j))/10.0)));
              
                    res.add(peso1+peso2);
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
                if(n>0){
                    contador++;
                }
            }
            resultados.add(contador);
        }
        return resultados;
    }
    private static ArrayList<Double> getPromedio(ArrayList<ArrayList<Integer>> palabras){
        ArrayList<Double> resultados = new ArrayList<>();
        for(ArrayList <Integer> ls: palabras){
            int contador  = 0;
            int contador2 = 0;
            for(Integer n : ls){
                if (n>0){
                    contador +=n;
                    contador2 +=1;
                }
            }
            System.out.println("ocurrencias "+ contador + "nro docs "+ contador2);
            double ress= (((double)contador)/(double)contador2)*0.1;
            resultados.add(ress);
            System.out.println("incertidumbre promedio" + ress);
            //JOptionPane.showMessageDialog(null, "ocurrencias "+ contador + "nro docs "+ contador2);
            
        }
        return resultados;
    }
    private static ArrayList<Integer> ordenar(ArrayList<Double> lis){    
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
                   // System.out.println(lista.get(i)+" "+lista.get(j));
                }
            }
        }
        System.out.println(lista);
        ArrayList<Integer> ordenado = new ArrayList<>();
        for(int i=0; i<lista.size();i++){
            ordenado.add((Integer)lista.get(i).get(1)+1);
        }
        //System.out.println(ordenado);
        return ordenado;
    }
}
    