package com.edu.edu;

public class Model {

    String titulo;

    String dsc;

    int img;

    public Model(String titulo, String dsc, int img) {
        this.titulo = titulo;
        this.dsc = dsc;
        this.img = img;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDsc() {
        return this.dsc;
    }

    public int getImg() {
        return this.img;
    }
}
