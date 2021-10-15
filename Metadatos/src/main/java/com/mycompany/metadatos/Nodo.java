package com.mycompany.metadatos;

public class Nodo {
private String valor;
    private Nodo siguiente;
    
    public Nodo(String dato){
        this.valor = dato;
        this.siguiente = null;
    }
    
    public String getvalor(){
        return valor;
    }
    
    
    public Nodo getsiguiente(){
        return siguiente;
    }
    
     public void setsiguiente(Nodo elemento){
        siguiente = elemento;
    }
}
