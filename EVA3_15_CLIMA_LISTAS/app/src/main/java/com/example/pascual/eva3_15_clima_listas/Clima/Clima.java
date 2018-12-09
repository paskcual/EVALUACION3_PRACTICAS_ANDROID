package com.example.pascual.eva3_15_clima_listas.Clima;

public class Clima {

    String nombreCiudad;
    int temperatura;
    String descripcion;
    int imagen;


    public Clima(String nombreCiudad, int temperatura, String descripcion, int imagen){

        this.nombreCiudad = nombreCiudad;
        this.temperatura = temperatura;
        this.descripcion = descripcion;
        this.imagen = imagen;

    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
