package com.cantillana.Lienzo;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class Linea extends Figura {

    //El fin de la linea, la posición de figura es el primero
    private Punto vector;

    
   public  Linea() {
        super(); 
        this.vector = new Punto(0, 0);
    }

    public Linea(int x, int y, int x2, int y2, String color) {
        // Con parámetros:
        super(x, y, color); 
        this.vector = new Punto(x2, y2); 
    }

    @Override
    public void renderText() {
        System.out.println("Linea de (" + this.posicion.getX() + "," + this.posicion.getY() + ") hasta  (" + this.vector.getX() + ", " + this.vector.getY() + ") y color " + this.color);
    }

    

    @Override
    public void render(GraphicsContext gc) {
        Color c = Color.web(this.color);
        gc.setLineWidth(3); // Grosor por defecto de la línea: 3
        gc.setStroke(c);
        gc.strokeLine(this.posicion.getX(), this.posicion.getY(), this.vector.getX(), this.vector.getY());

    }

}
