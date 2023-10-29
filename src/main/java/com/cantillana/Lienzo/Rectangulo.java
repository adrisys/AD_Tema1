package com.cantillana.Lienzo;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangulo extends Figura  {


    private Integer largo;
    private Integer alto;

    // Constructor por defecto
    public Rectangulo() {
        super(); 
        this.largo = 0;
        this.largo = 0;
    }

    // constructor general
    public Rectangulo(int x, int y, int largo, int alto, String color) {
        super(x, y, color); 
        this.largo = largo; 
        this.alto = alto; 
    }

    @Override
    public void renderText() {
        System.out.println("Rectangulo en (" + this.posicion.getX() + "," + this.posicion.getY() + ") de largo " + this.largo + ", altura " + this.alto + " y color " + this.color);
    }

    
    @Override
    public void render(GraphicsContext gc) {
        // Dibujamos el rectángulo
        Color c = Color.web(this.color);
        gc.setFill(c);
        gc.fillRect(this.posicion.getX(), this.posicion.getY(), this.largo, this.alto);
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("rectangulo");
        sb.append(this.posicion.getX()).append(" ");
        sb.append(this.posicion.getY()).append(" ");
        sb.append(this.largo).append(" ");
        sb.append(this.alto).append(" ");
        sb.append(this.color);
        return sb.toString();
    }


}
