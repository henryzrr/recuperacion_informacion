package com.aplicacion.servlet;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Lector{
    private Lector(){}
    
    public static ArrayList<ArrayList<String>> convertir(){
        return convertir1(new File("/home/rudy/Documentos/TEXTOS"));
    }
    
    public static ArrayList<String> listar(){
        return listar("/home/rudy/Documentos/TEXTOS");
    }
    
    public static ArrayList<ArrayList<String>> convertir(String carpeta){
        return convertir1(new File(carpeta));
    }
    
    public static ArrayList<String> separador(String linea){
        ArrayList<String> resultados = new ArrayList<>();
        for(String el : linea.split(" "))
            resultados.add(el.toLowerCase());
        return resultados;
    }
    
    public static ArrayList<String> listar(String directorio){
        ArrayList<String> resultado=new ArrayList<String>();
        for(File archivo : listar(new File(directorio)))
            resultado.add(archivo.getName());
        return resultado;
    }
    
    private static ArrayList<ArrayList<String>> convertir1(final File carpeta){
        return convertir(listar(carpeta));
    }
    
    private static ArrayList<ArrayList<String>> convertir(ArrayList<File> archivos){
        ArrayList<ArrayList<String>> resultados= new ArrayList<ArrayList<String>>();
        for(File archivo : archivos)
            resultados.add(convertir(archivo));
        return resultados;
    }
    
    private static ArrayList<String> convertir(File archivo){
        ArrayList<String> resultado= new ArrayList<>();
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(archivo.getPath()));
            String linea;
            while((linea=buffer.readLine())!=null)
                resultado.addAll(separador(linea));
        }catch(IOException e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    private static ArrayList<File> listar(final File carpeta){
        ArrayList<File> resultado = new ArrayList<>();
        for(final File archivo : carpeta.listFiles())
            if(!archivo.isDirectory())
                resultado.add(archivo);
        return resultado;
    }
    
}