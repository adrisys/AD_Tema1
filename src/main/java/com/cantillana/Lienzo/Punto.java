package com.cantillana.Lienzo;

import java.io.Serializable;


public class Punto implements Serializable {
    // Clase que representa una ubicación en la escena
    private int x;
    private int y;

    Punto() {
        //  Constructor
        this.x = 0;
        this.y = 0;
    }

    Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //  getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //  setter
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    ;

}

