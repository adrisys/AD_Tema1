package com.cantillana.app;

import com.cantillana.Lienzo.*;

import java.io.*;
import java.util.Scanner;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    //Método para comprobar que existe un fichero
    public Boolean Exists(String file) {
        return new File(file).exists();
    }

    //Método para recuperar las figuras desde un archivo de texto
    public Escena importFromText(String file) {
        Escena escena = new Escena();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            Scanner scanner;
            while ((linea = br.readLine()) != null) {
                scanner = new Scanner(linea);
                do {
                    String keyword = scanner.next();
                    // Procesa los datos según la palabra clave
                    switch (keyword) {
                        case "dimensiones":
                            int ancho = scanner.nextInt();
                            int alto = scanner.nextInt();
                            escena.dimensiones(ancho, alto);
                            break;
                        case "rectangulo":
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
                        case "linea":
                            int x1 = scanner.nextInt();
                            int y1 = scanner.nextInt();
                            int x2 = scanner.nextInt();
                            int y2 = scanner.nextInt();
                            String colorLinea = scanner.next();
                            escena.add(new Linea(x1, y1, x2, y2, colorLinea));
                            break;
                        //Si el texto no está bien formateado no se encuentra una palabra clave y lanzamos un mensaje
                        default:
                            System.out.println("Palabra clave desconocida: " + keyword);
                    }

                } while (scanner.hasNext());

                scanner.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return escena;

    }

    //Método para recuperar una escena guardada en un archivo obj
    public Escena importFromObj(String file) {
        Escena escena = null;
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file))) {
            escena = (Escena) oi.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Problema al importar la escena");
        }


        return escena;

    }

    //Método para exportar las figuras a un archivo de texto, se muestra una figura por línea.
    public void exportText(Escena escena, String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Figura f : escena.listaFiguras) {
                bw.write(f.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    //Método para exportar la escena a un archivo obj
    public void exportObj(Escena escena, String file) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(escena);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para exportar las figuras a un archivo svg, se hace uso de la librería apache.batik
    public void exportToSVG(Escena escena, String filename) {
        //Se crea el documento svg
        String svgNS = "http://www.w3.org/2000/svg";
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Crea un Graphics2D para el documento SVG
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        //Se exportan las dimensiones de la escena
        svgGenerator.setSVGCanvasSize(new java.awt.Dimension(escena.getX(), escena.getY()));

        // Se exporta cada figura
        for (Figura figura : escena.listaFiguras) {
            figura.exportSVG(svgGenerator);
        }

        // Se guarda el documento SVG en un archivo
        try {
            File svgFile = new File(filename);
            FileWriter fileWriter = new FileWriter(svgFile);
            Writer out = new BufferedWriter(fileWriter);
            svgGenerator.stream(out, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Método para exportar la escena a un archivo Json
    public void exportJSON(Escena escena, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        try {
            File file = new File(filename);
            objectMapper.writeValue(file, escena);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
