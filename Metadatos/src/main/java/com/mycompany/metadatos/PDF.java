package com.mycompany.metadatos;

public class PDF {
    //Atributos
    private short TamañoArchivo; //4
    private char TamañoPagina;     //2
    private short NumeroPagina;   //3
    private String Titulo;  //4
    private String Asunto;
    private String PalabrasClave;
    private char TipoArchivo;
    private double Versionpdf;
    private String Aplicacion;
    private String Fuentes;
    private PDF siguiente;
    //Consrtructor

    public PDF(short TamañoArchivo, char TamañoPagina, short NumeroPagina, String Titulo, String Asunto, String PalabrasClave, char TipoArchivo, double Versionpdf, String Aplicacion, String Fuentes, PDF siguiente) {
        this.TamañoArchivo = TamañoArchivo;
        this.TamañoPagina = TamañoPagina;
        this.NumeroPagina = NumeroPagina;
        this.Titulo = Titulo;
        this.Asunto = Asunto;
        this.PalabrasClave = PalabrasClave;
        this.TipoArchivo = TipoArchivo;
        this.Versionpdf = Versionpdf;
        this.Aplicacion = Aplicacion;
        this.Fuentes = Fuentes;
        this.siguiente=null;
    }
    public PDF()
    {
        
    }
    public short getTamañoArchivo() {
        return TamañoArchivo;
    }

    public void setTamañoArchivo(short TamañoArchivo) {
        this.TamañoArchivo = TamañoArchivo;
    }

    public char getTamañoPagina() {
        return TamañoPagina;
    }

    public void setTamañoPagina(char TamañoPagina) {
        this.TamañoPagina = TamañoPagina;
    }

    public short getNumeroPagina() {
        return NumeroPagina;
    }

    public void setNumeroPagina(short NumeroPagina) {
        this.NumeroPagina = NumeroPagina;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getPalabrasClave() {
        return PalabrasClave;
    }

    public void setPalabrasClave(String PalabrasClave) {
        this.PalabrasClave = PalabrasClave;
    }

    public char getTipoArchivo() {
        return TipoArchivo;
    }

    public void setTipoArchivo(char TipoArchivo) {
        this.TipoArchivo = TipoArchivo;
    }

    public double getVersionpdf() {
        return Versionpdf;
    }

    public void setVersionpdf(double Versionpdf) {
        this.Versionpdf = Versionpdf;
    }

    public String getAplicacion() {
        return Aplicacion;
    }

    public void setAplicacion(String Aplicacion) {
        this.Aplicacion = Aplicacion;
    }

    public String getFuentes() {
        return Fuentes;
    }

    public void setFuentes(String Fuentes) {
        this.Fuentes = Fuentes;
    }

    public PDF getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(PDF siguiente) {
        this.siguiente = siguiente;
    }
    
}
