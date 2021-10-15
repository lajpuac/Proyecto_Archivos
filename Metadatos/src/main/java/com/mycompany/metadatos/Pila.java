package com.mycompany.metadatos;

public class Pila {
     private Nodo tope;
    private int size;
    
    public Pila(){
        tope = null;
        size = 0;
    }
    
    public void insertar(String dato){
        Nodo elemento = new Nodo(dato);
        if (tope == null){
            tope = elemento;
            size ++;
        }
        else{
            Nodo aux = tope;
            elemento.setsiguiente(aux);
            size ++;
            tope = elemento;
        }
    }
    
    public int size(){
        return size;
    }       
    
    public Nodo tope(){
        return tope;
    }
    
    public String eliminar(){
        String Adevolver;
        if(tope != null){
            Adevolver = tope.getvalor();
            Nodo aux = tope;
            tope = aux.getsiguiente();
            aux.setsiguiente(null);
            size --;
        }else{
            Adevolver = "No hay datos";
        }
        return Adevolver;
    }        
    
    public Boolean buscar(String n){
        Boolean ver = false;
        Pila pilaux = new Pila();
        Nodo busqueda = tope;
        while(busqueda != null){
            if(busqueda.getvalor() == n){                
                ver = true;
                break;                
            }            
            pilaux.insertar(busqueda.getvalor());
            busqueda = busqueda.getsiguiente();            
            eliminar();             
        }
        Nodo aux = pilaux.tope(); 
        while(aux !=null){
            
            String x = aux.getvalor();
            insertar(x);
            aux = aux.getsiguiente();
        }
        return ver;
    }
    
    public void mostrar(){
        Nodo aux = this.tope;
        System.out.println("Tamaño " + size);
        for(int i =0; i < size; i++){
            System.out.println("Ruta " + aux.getvalor());
            if(aux.getsiguiente() == null){
                break;
            }
            aux = aux.getsiguiente();
        }
    }
    
    public boolean esta_vacia(){
        boolean vacia;
        if (tope == null){
            vacia=true;
            return vacia;
        }
        else{
            vacia = false;
            return vacia;
        }
    }

}
