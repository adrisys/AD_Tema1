package com.cantillana.Lienzo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.WRAPPER_OBJECT
//)
@JsonRootName("escena")
public class Escena implements Serializable {
    // tamaño de la escena
    private int tamX;
    private int tamY;

    //figuras que componen la escena
    @JsonProperty("figuras")
    public List<Figura> listaFiguras;

    public Escena() {
        // Constructor. Por defecto escena de 800x600;
        this.tamX = 800;
        this.tamY = 600;

        // Iniciamos las figuras
        listaFiguras = new ArrayList<Figura>();

    }

    public Escena(int x, int y) {

        // Constructor con un tamaño concreto

        this.tamX = x;
        this.tamY = y;


        listaFiguras = new ArrayList<Figura>();
    }

    public void dimensiones(int x, int y) {
        // Modificamos las dimensiones de la escena
        this.tamX = x;
        this.tamY = y;
    }

    // Getters del tamaño
    public int getX() {
        return this.tamX;
    }

    public int getY() {
        return this.tamY;
    }

    // Saber si la escena esta vacia
    public Boolean estaVacia() {
        return this.listaFiguras.isEmpty();
    }

    // añadir una figura a la escena
    public void add(Figura figura) {
        // La figura debe "caber" en la escena
        if ((figura.posicion.getX() < this.tamX && figura.posicion.getY() < this.tamY)) {
            comprobarColor(figura);
        } else {
            System.out.println(Colors.Red + "La imagen sale de la escena." + Colors.Reset);
        }

    }

    private void comprobarColor(Figura figura) {
        // Comprueba el color y añade la figura
        if (HexColorValidator.validate(figura.color)) {
            this.listaFiguras.add(figura);
            System.out.println(Colors.Green + "OK" + Colors.Reset);
        } else {
            // En caso contrario, mostramos el error
            System.out.println(Colors.Red + "Color no válido." + Colors.Reset);
        }
    }


    // muestra el texto del contenido de la escena
    public void renderText() {
        for (Figura f : this.listaFiguras) {
            f.renderText();
        }
    }

    // Dibuja la  escena en un contexto de JavaFX
    // Mas información del dibujo en
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html
    public void renderScene(GraphicsContext gc) {

        for (Figura f : this.listaFiguras) {
            f.render(gc);
        }

    }


}

