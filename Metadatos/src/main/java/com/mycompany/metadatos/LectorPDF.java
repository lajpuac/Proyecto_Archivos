package com.mycompany.metadatos;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class LectorPDF {

    //Atributos
    private RandomAccessFile archivos;
    private String acceso;
    private int res;
    private GuardarArchivo Info = new GuardarArchivo();
    //Constructor
    public LectorPDF() {//Cuando ya tiene datos no hay que enviarle información

    }
    public LectorPDF(String ruta){
        acceso = ruta;
    }
    public boolean VerificarDatos() throws FileNotFoundException{
        File Aux = new File("PDF.data");
        boolean Existe = false;
        if(Aux.length() > 0){//Va a leer si tiene al menos algun byte
            Existe = true;
        }
        return Existe;
    }
    public void SaveInformation() throws IOException{
        Lector algo = new Lector();
        algo.listFile(acceso);//Leo la carpeta que eligio
        algo.getPDF().mostrar();
        Pila data = algo.getPDF();
        while(data.tope() != null){
            this.GetPDF(data.eliminar());
        }
        Info.guardar();
    }
    public void GetPDF(String ruta) throws FileNotFoundException, IOException{
        RandomAccessFile lectura = new RandomAccessFile(ruta, "rw");
        short comparoIniciales = lectura.readShort();
        System.out.println("DATO A COMPARAR " + comparoIniciales);
        short Id3v2 = 18756; //49 44
        int  pdf = -5;  //FF FB ya que por el bit del signo este hace que se vuelva negativo todo 
        if (comparoIniciales == Id3v2){
            this.GetData(ruta);
        }else if(comparoIniciales == pdf){
            this.LecturaBinariaPDF(ruta);
        }else{
            System.out.println("Ningun formato coincide");
        }
        lectura.close();

    }
    public void GetData(String ruta) throws FileNotFoundException, IOException{
        RandomAccessFile lectura = new RandomAccessFile(ruta, "rw");
        byte [] caracteres = new byte[20];
        lectura.read(caracteres);
        String nombre = new String(caracteres);
        System.out.println("Etiqueta " + nombre);
        if(nombre.equals("ID3")){
            System.out.println("Parecidos");
        }
        int Version = lectura.readByte();
        System.out.println("Version " + Version);
        int NumPagina = lectura.readByte();
        System.out.println("#Revisión " + NumPagina);
        byte indicadores = lectura.readByte();
        short TamañoArchivo = lectura.readShort();
        TamañoArchivo = (short) (TamañoArchivo /8);
        System.out.println("Tamaño " + TamañoArchivo);
        char TamañoPagina=lectura.readChar();
        System.out.println("Tamaño de Pagina" + TamañoPagina);
        short NumeroPagina=lectura.readShort();
        System.out.println("Numero de Pagina" + NumeroPagina);
        String Titulo=lectura.readLine();
        System.out.println("Titulo" + Titulo);
        String Asunto=lectura.readLine();
        System.out.println("Asunto" + Asunto);
        String PalabrasClave=lectura.readLine();
        System.out.println("Palabras Clave" + PalabrasClave);
        char TipoArchivo=lectura.readChar();
        System.out.println("Tipo de Archivo" + TipoArchivo);
        double Versionpdf=lectura.readDouble();
        System.out.println("Version de PDF" + Versionpdf);
        String Aplicacion=lectura.readLine();
        System.out.println("Aplicacion" + Aplicacion);
        String Fuentes=lectura.readLine();
        System.out.println("Fuentes" + Fuentes);
        PDF siguiente=null;
        String [] datosAEncontrar = {"PDF"};
        String [] datosAQuerer = {"PDF"};
        while(lectura.getFilePointer() < TamañoArchivo){
            //Que se detenga si encuentra una imagen en el encabezado, si encuentra todos los datos o si llega al final al programa
            //System.out.println("Posicion de lectura " + lectura.getFilePointer());
            byte [] len = new byte[4];
            lectura.read(len);
            String tag = new String(len);
            if(tag.equals("APIC"))break;
            int tamString = lectura.readInt() + 1;//Ya que siempre le falta un byte de lectura
            byte [] lenString = new byte[tamString];
            lectura.read(lenString);
            String texto = new String(lenString);
            lectura.readByte();//Para que se acople
            int posicion = 0;
            for(String palabra : datosAEncontrar){
                if ( tag.toLowerCase().indexOf(palabra.toLowerCase()) != -1 ) {
                    //String tempWord = "  " + " "; 
                    String tempWord = "  "; 
                    texto = texto.replaceAll(tempWord, ""); 
                    datosAEncontrar[posicion] = texto;
                    //System.out.println("Esto meto en el array " + datosAEncontrar[posicion]);
                }
                posicion++;
            }

        }
        for(String palabra : datosAEncontrar){
            System.out.println("Info " + palabra);
        }
        for(int i = 0; i < datosAQuerer.length; i++){//Los datos no encontrados no estan identificados
            if(datosAEncontrar[i] == datosAQuerer[i]){
                datosAEncontrar[i] = "NI";
            }
        }
       PDF nuevo = new PDF(TamañoArchivo, TamañoPagina, NumeroPagina, Titulo, Asunto, PalabrasClave, TipoArchivo, Versionpdf,Aplicacion,Fuentes,siguiente);
       Info.AgregarPDF(nuevo,Titulo);
        lectura.close();                                                                        
    }  
    
    public void LecturaBinariaPDF(String ruta) throws FileNotFoundException, IOException{
        try {
            PDF nuevo =new PDF();
            File doc = new File(ruta);
            FileInputStream file = new FileInputStream(doc);
            DataInputStream data = new DataInputStream(file);
            byte nombreBytes[] = data.readNBytes(4);
            String Titulo = new String(nombreBytes);
            nuevo.setTitulo(Titulo);
            System.out.println(Titulo);
            short TamañoArchivo = data.readShort();
            nuevo.setTamañoArchivo(TamañoArchivo);
            System.out.println(TamañoArchivo);
            char TamañoPagina=data.readChar();
            nuevo.setTamañoPagina(TamañoPagina);
            System.out.println(TamañoPagina);
            short NumeroPagina=data.readShort(); 
            nuevo.setNumeroPagina(NumeroPagina);
            System.out.println(NumeroPagina);//3
            String Asunto=data.readLine();
            nuevo.setAsunto(Asunto);
            System.out.println(Asunto);
            String PalabrasClave=data.readLine();
            nuevo.setPalabrasClave(PalabrasClave);
            System.out.println(PalabrasClave);
            char TipoArchivo=data.readChar();
            nuevo.setTipoArchivo(TipoArchivo);
            System.out.println(TipoArchivo);
            double Versionpdf=data.readDouble();
            nuevo.setVersionpdf(Versionpdf);
            System.out.println(Versionpdf);
            String Aplicacion=data.readLine();
            nuevo.setAplicacion(Aplicacion);
            System.out.println(Aplicacion);
            String Fuentes=data.readLine();
            nuevo.setFuentes(Fuentes);
            System.out.println(Fuentes);
            PDF siguiente=null;
            PDF nuevo1 = new PDF(TamañoArchivo,TamañoPagina,NumeroPagina,Titulo,Asunto,PalabrasClave,TipoArchivo,Versionpdf,Aplicacion,Fuentes,siguiente);
            file.close();
            } catch (Exception e) {
                    System.out.println("Error - " + e.toString());
            }
    }
}
