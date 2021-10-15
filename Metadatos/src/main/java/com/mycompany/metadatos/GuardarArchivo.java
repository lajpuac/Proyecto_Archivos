
package com.mycompany.metadatos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuardarArchivo {
   private String nombreDeArchivo;
    private ArrayList<PDF> listadoRegistros;
    public GuardarArchivo() {
        this.listadoRegistros = new ArrayList<>();
          }
    public void AgregarPDF(PDF nuevo,String nombre){
        this.listadoRegistros.add(nuevo);
        //this.indiceRegistros.add(new IndiceNombre((short)0, apellido));
    }
    public void guardar() throws IOException{
        new FileOutputStream("PDF.data").close();//Limpio el archivo antes de insertar
        try {    
            RandomAccessFile archivo = new RandomAccessFile("PDF.data", "rw");
            short punteroAIndice = 0;
            archivo.writeShort(punteroAIndice);
            int contadorIndice = 0;
            for(PDF nuevo : listadoRegistros){try {//recorre el array
                //indiceRegistros.get(contadorIndice++).setPosicion((short)archivo.getFilePointer());//se incrementa despues de hacer esa linea
                //archivo.getFilePointer(); //en que posicion está escribiendo
                archivo.write(nuevo.getTitulo().length());//es un byte
                archivo.writeChars(nuevo.getTitulo());
                System.out.println("Longtitud de " + nuevo.getTitulo() + " ES " + nuevo.getTitulo().length());
                archivo.write(nuevo.getPalabrasClave().length());
                archivo.writeChars(nuevo.getPalabrasClave());
                
                archivo.write(nuevo.getNumeroPagina());
                archivo.writeChars(nuevo.toString());
                
                archivo.write(nuevo.getAplicacion().length());
                archivo.writeChars(nuevo.getAplicacion());
                
                archivo.write(nuevo.getTamañoArchivo());
                archivo.writeChars(nuevo.toString());
                
                archivo.write(nuevo.getFuentes().length());
                archivo.writeChars(nuevo.getFuentes());
                
                archivo.write(nuevo.getAsunto().length());
                archivo.writeChars(nuevo.getAsunto());
                System.out.println(nuevo.getAsunto());
                                System.out.println("LONG " + nuevo.getTamañoArchivo());
                archivo.write(nuevo.getTamañoArchivo());
                archivo.writeChars(nuevo.toString());
                } catch (IOException ex) {
                    Logger.getLogger(GuardarArchivo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            /*punteroAIndice = (short)archivo.getFilePointer();
            for (int i = 0; i < indiceRegistros.size(); i++) //Organizamos el arreglo del indice
            {
                for (int j = i + 1; j < indiceRegistros.size(); j++) 
                {
                    if (indiceRegistros.get(i).NombreCancion().compareTo(indiceRegistros.get(j).NombreCancion()) > 0) 
                    {
                        String temp = indiceRegistros.get(i).NombreCancion();
                        indiceRegistros.get(i).SetNombreCancion(indiceRegistros.get(j).NombreCancion());
                        indiceRegistros.get(j).SetNombreCancion(temp);
                        
                        short temp1 = indiceRegistros.get(i).getPosicion();
                        indiceRegistros.get(i).setPosicion(indiceRegistros.get(j).getPosicion());
                        indiceRegistros.get(j).setPosicion(temp1);
                    }
                }
            }*/
            
            /*for(IndiceNombre indice: indiceRegistros){
                    archivo.writeShort(indice.getPosicion());
                    archivo.writeByte(indice.NombreCancion().length());
                    archivo.writeChars(indice.NombreCancion()); 
            }
            archivo.seek(0);//regreso a la posición 0
            archivo.writeShort(punteroAIndice);
            */
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(GuardarArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GuardarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
        

    public void setNombreDeArchivo(String nombreDeArchivo) {
        this.nombreDeArchivo = nombreDeArchivo;
    }

    public void setListadoRegistros(ArrayList<PDF> listadoRegistros) {
        this.listadoRegistros = listadoRegistros;
    }

    /*public void setIndiceRegistros(ArrayList<IndiceNombre> indiceRegistros) {
        this.indiceRegistros = indiceRegistros;
    }*/

    public String getNombreDeArchivo() {
        return nombreDeArchivo;
    }

    public ArrayList<PDF> getListadoRegistros() {
        return listadoRegistros;
    }

    /*public ArrayList<IndiceNombre> getIndiceRegistros() {
        return indiceRegistros;
    }*/
}
