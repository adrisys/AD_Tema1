package com.cantillana.app;

import com.cantillana.Lienzo.Circulo;
import com.cantillana.Lienzo.Escena;
import com.cantillana.Lienzo.Linea;
import com.cantillana.Lienzo.Rectangulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class FileManager {

    public FileManager() {

    }


    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // Solo llegamos aquí si no devuelve false
        return true;
    }

    public Boolean Exists(String file) {
        return new File(file).exists();
    }

    public Escena importFromText(String file) {
        Escena escena = new Escena();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            Scanner scanner;
            while ((linea = br.readLine()) != null) {
                scanner = new Scanner(linea);
                String keyword = scanner.next();

                // Procesa los datos según la palabra clave
                switch (keyword) {
                    case "dimensiones":
                        int ancho = scanner.nextInt();
                        int alto = scanner.nextInt();
                        escena.dimensiones(ancho, alto);
                        break;
                    case "rectángulo":
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        int anchoRect = scanner.nextInt();
                        int altoRect = scanner.nextInt();
                        String colorRect = scanner.next();
                        escena.add(new Rectangulo(x, y, anchoRect, altoRect, colorRect));
                        break;
                    case "circulo":
                        int xCirc = scanner.nextInt();
                        int yCirc = scanner.nextInt();
                        int radio = scanner.nextInt();
                        String colorCirc = scanner.next();
                        escena.add(new Circulo(xCirc, yCirc, radio, colorCirc));
                        break;
                    case "línea":
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        int x2 = scanner.nextInt();
                        int y2 = scanner.nextInt();
                        String colorLinea = scanner.next();
                        escena.add(new Linea(x1, y1, x2, y2, colorLinea));
                        break;
                    default:
                        System.out.println("Palabra clave desconocida: " + keyword);
                }

                scanner.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return escena;

    }


    public Escena importFromObj(String file) {

        /**
         * **********************************************************************
         * MÉTODO A IMPLEMENTAR. Lee el fichero e importa los objetos
         * **********************************************************************
         */
        
        Escena escena = null;

        return escena;

    }

    public Boolean exportText(Escena escena, String file) {

        /**
         * *********************************************************
         * MÉTODO A IMPLEMENTAR. Escribe el fichero de texto. Exporta las figuras a texto
         * **********************************************************
         */
        // Comentar o eliminar estas líneas al implementar el método
        boolean out = false;

        return out;

    }

    public Boolean exportObj(Escena escena, String file) {

       /**
         * *********************************************************
         * MÉTODO A IMPLEMENTAR. Escribe el fichero de objetos. Exporta las figuras 
         * **********************************************************
         */
        
        boolean out = false;

        return out;

    }

    public Boolean exportSVG(Escena escena, String file) {
        /**
         * **********************************************************
         * MÉTODO A IMPLEMENTAR. Exporta la figura a un SVG (XML) entendible por Inkscape
         * **********************************************************
         */
        /*
            <?xmlversion="1.0"encoding="UTF-8"standalone="no"?> 2 <svgheight="500"width="500">
            <rect fill="#ccccee" height="480" width="480" x="10" y="10"/>
            <circle cx="250" cy="250" fill="#aaaaaa" r="100"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="450" y1="250" y2="250"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="50" y1="50" y2="
            450"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="450" x2="450" y1="40" y2= "450"/>
            </svg>
         */

    	// Comentar o eliminar estas líneas al implementar el método
        boolean out = false;

        return out;

    }

    public Boolean exportJSON(Escena escena, String filename) {

        /**
         * **********************************************
         * MÉTODO A IMPLEMENTAR. Exporta la escena a JSON 
         * **********************************************
         */
    	// Comentar o eliminar estas líneas al implementar el método
        boolean out = false;

        return out;

    }

}
