package com.mycompany.metadatos;

import java.io.File;

public class Lector {
 private Pila PDF = new Pila();
    public void listFile(String pathname) {
        File f = new File(pathname);
        File[] listfiles = f.listFiles();
        for (int i = 0; i < listfiles.length; i++) {
            if (listfiles[i].isDirectory()) {
                File[] internalFile = listfiles[i].listFiles();
                for (int j = 0; j < internalFile.length; j++) {
//                    System.out.println(internalFile[j]);
                    if (internalFile[j].getAbsolutePath().endsWith(".pdf") || internalFile[j].getAbsolutePath().endsWith(".PDF"))
                    {
                        PDF.insertar(internalFile[j].getAbsolutePath());
                    }
                    if (internalFile[j].isDirectory()) {
                        String name = internalFile[j].getAbsolutePath();
                        listFile(name);
                    }

                }
            } else {//Para directorio padre y el de arriba para directorio hijo
                String files = listfiles[i].getAbsolutePath();;
                if (files.endsWith(".pdf") || files.endsWith(".PDF"))
                {
                    PDF.insertar(files);
                }
//                System.out.println(listfiles[i]);
            }    
        }
    } 
     public Pila getPDF() {
        return PDF;
    }
}
