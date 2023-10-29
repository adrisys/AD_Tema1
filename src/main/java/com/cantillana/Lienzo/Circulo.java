package com.cantillana.Lienzo;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class Circulo extends Figura  {

    private Integer radio;

    // Constructores
    public Circulo() {
        super(); 
        this.radio = 0;
    }

    public Circulo(int x, int y, int radio, String color){
        super(x,y,color);
        this.radio = radio; // Indica el valor del radio
        // Nota: La posición del círculo será su centro
        // por eso le restamos el radio
    }

    public Integer getRadio() {
        return radio;
    }

    public void setRadio(Integer radio) {
        this.radio = radio;
    }

    public void renderText() {
        System.out.println("Círculo en (" + this.posicion.getX() + "," + this.posicion.getY() + ") de radio " + this.radio + " y color " + this.color);
    }

    

    public void render(GraphicsContext gc) {
    
        Color c = Color.web(this.color);
        gc.setFill(c);
        gc.fillOval(this.posicion.getX(), this.posicion.getY(), this.radio * 2, this.radio * 2);
    }

   
}