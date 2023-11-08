package com.cantillana.Lienzo;

// Librerias para poder dibujar

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.canvas.GraphicsContext;
import org.apache.batik.svggen.SVGGraphics2D;

import java.io.Serializable;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Linea.class, name = "linea"),
        @JsonSubTypes.Type(value = Circulo.class, name = "circulo"),
        @JsonSubTypes.Type(value = Rectangulo.class, name = "rectangulo")
})
public abstract class Figura implements Serializable {


    protected Punto posicion;
    protected String color;

    // Constructores:
    // Se inicia la posición y el color

    // La posición es de tipo punto, clase definida en la aplicación
    // El color es un string en formato hexadecimal: #ff0000=rojo, #00ff00=verde, #0000ff=azul

    public Figura() {
        // Constructor por defecto
        this.posicion = new Punto();
        this.color = "#000000";
    }

    public Figura(int x, int y) {
        // Constructor con la posición
        this.posicion = new Punto(x, y);
        this.color = "#000000";
    }

    ;

    Figura(int x, int y, String color) {
        //  Constructor con posición y color
        this.posicion = new Punto(x, y);
        this.color = color;
    }

    public Punto getPosicion() {
        return posicion;
    }

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Métodos abstractos que deben implementar las clases herederas
    public abstract void renderText(); // Para mostrar una descripción de la figura geométrica en texto

    public abstract void render(GraphicsContext gc); // Para dibujar en un contexto gráfico

    public abstract String toString();

    public abstract String toSVG();

    public abstract void exportSVG(SVGGraphics2D svgGenerator);


}

