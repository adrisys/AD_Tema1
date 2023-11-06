package com.cantillana.Lienzo;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Element;


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

    public String toSVG() {
        return "<rect x=\"" + posicion.getX() + "\" y=\"" + posicion.getY() + "\" width=\"" + this.largo+ "\" height=\"" + this.alto + "\" fill=\"" + color + "\" stroke=\"" + color  + "\" />";
    }

    @Override
    public void exportSVG(SVGGraphics2D svgGenerator) {
        Element rect = svgGenerator.getDOMFactory().createElement("rect");
        rect.setAttribute("x", Double.toString(this.posicion.getX()));
        rect.setAttribute("y", Double.toString(this.posicion.getY()));
        rect.setAttribute("width", Double.toString(this.largo));
        rect.setAttribute("height", Double.toString(this.alto));
        svgGenerator.getTopLevelGroup().appendChild(rect);
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
