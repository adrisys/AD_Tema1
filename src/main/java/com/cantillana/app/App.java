package com.cantillana.app;

// Importaciones para la entrada de datos

import com.cantillana.Lienzo.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.Scanner;

// Importaciones para la gestión de listas


//La clase App  es una subclase de Application, para tener el contexto gráfico que ofrece
public class App extends Application {

    static Stage primaryStage; // PrimaryStage es el contenedor de la escena en JavaFX
    static Escena AppEscena; // Escena es la clase que representará  nuestra escena

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        FileManager fm; // FileManager será el gestor de almacenamiento
        int width, height; // Tamaño de la escena

        try {
            // Si vienen en los argumentos
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);

        } catch (Exception e) {
            // Sino, por defecto 800x600
            width = 800;
            height = 600;
        }

        // Inicio de la Escena a width x height
        AppEscena = new Escena(width, height);

        // Bucle infinito hasta dibujar
        String figura;

        do {

            // Preguntamos la siguiente figura a guardar
            System.out.print(Colors.Blue + "# Figura: " + Colors.Reset);
            String orden = keyboard.nextLine();

            // Dividimos la orden introducida
            String[] componentes = orden.split(" ");

            figura = componentes[0];

            switch (figura) {
                case "circulo":
                    try {
                        // sacamos las dimensiones
                        int x = Integer.parseInt((componentes[1]));
                        int y = Integer.parseInt((componentes[2]));
                        int radi = Integer.parseInt((componentes[3]));
                        String color = componentes[4];

                        // lo creamos
                        Circulo nuevoCirculo = new Circulo(x, y, radi, color);
                        // Añadimos a la escena
                        AppEscena.add(nuevoCirculo);


                    } catch (Exception e) {
                        System.out.println("\u001B[31m Error de sintaxis. La sintaxis correcta ss:\nccirculo x y radio color \u001B[0m");
                    }

                    break;

                case "rectangulo":
                    try {
                        // sacamos las dimensiones
                        int x = Integer.parseInt((componentes[1]));
                        int y = Integer.parseInt((componentes[2]));
                        int llarg = Integer.parseInt((componentes[3]));
                        int alt = Integer.parseInt((componentes[4]));
                        String color = componentes[5];

                        // lo creamos
                        Rectangulo nuevoRectangulo = new Rectangulo(x, y, llarg, alt, color);
                        // lo añadimos
                        AppEscena.add(nuevoRectangulo);


                    } catch (Exception e) {
                        System.out.println("\u001B[31m Error de sintaxis. La sintaxis correcta és:\nrectangulo x y largo ancho color \u001B[0m");
                    }

                    break;

                case "linea":
                    try {
                        // sacamos las dimensiones
                        int x = Integer.parseInt((componentes[1]));
                        int y = Integer.parseInt((componentes[2]));
                        int x2 = Integer.parseInt((componentes[3]));
                        int y2 = Integer.parseInt((componentes[4]));
                        String color = componentes[5];

                        // la creamos
                        Linea nuevaLinea = new Linea(x, y, x2, y2, color);
                        // la añadimos
                        AppEscena.add(nuevaLinea);


                    } catch (Exception e) {
                        System.out.println("\u001B[31m Error de sintaxis. La sintaxis correcta es:\nlinia x1 y1 x2 y2 color \u001B[0m");
                    }

                    break;

                case "dimensiones":
                    // Redimensiona el marco de la escena
                    try {
                        int x = Integer.parseInt((componentes[1]));
                        int y = Integer.parseInt((componentes[2]));
                        AppEscena.dimensiones(x, y);
                        System.out.println("\u001B[32m OK \u001B[0m");
                    } catch (Exception e) {
                        System.out.println("\u001B[31m Error de sintaxis. La sintaxis correcta es:\ndimensiones x y \u001B[0m");
                    }

                    break;

                case "import":
                    // Creamos el gestor de almacenamiento
                    fm = new FileManager();

                    // leera el fichero indicado en el primer parámetro y creará una nueva escena
                    if (!fm.Exists(componentes[1]))
                        System.out.println("El fichero no existe");
                    else {
                        System.out.println("Importando fichero...");

                        String extension = componentes[1].substring(componentes[1].length() - 4);


                        switch (extension) {
                            case ".txt": {
                                Escena nuevaEscena = fm.importFromText(componentes[1]);
                                if (!nuevaEscena.estaVacia()) AppEscena = nuevaEscena;
                            }
                            break;
                            case ".obj": {
                                Escena nuevaEscena = fm.importFromObj(componentes[1]);
                                if (!nuevaEscena.estaVacia()) AppEscena = nuevaEscena;
                            }

                            break;

                            default:
                                System.out.println("Formato no reconocido..");
                                break;

                        }
                        AppEscena.renderText();

                    }

                    break;

                case "export":
                    try {
                        String extension = componentes[1].substring(componentes[1].length() - 4);

                        fm = new FileManager();
                        if (fm.Exists(componentes[1])) {
                            // Si el fichero existe error
                            System.out.println("\u001B[31m El fichero ya existe\u001B[0m");
                            break;

                        }

                        switch (extension) {
                            case ".txt":
                                fm.exportText(AppEscena, componentes[1]);
                                //System.out.println(Colors.Bright_Cyan + "Ok. Exportación Correcta a TXT" + Colors.Reset);
                                //System.out.println(Colors.Bright_Red + "Error. Exportación Incorrecta" + Colors.Reset);
                                break;

                            case ".obj":
                                fm.exportObj(AppEscena, componentes[1]);
                                //System.out.println(Colors.Bright_Cyan + "Ok. Exportación Correcta a OBJ" + Colors.Reset);
                                //System.out.println(Colors.Bright_Red + "Error. Exportación Incorrecta" + Colors.Reset);
                                break;

                            case ".svg":
                                fm.exportToSVG(AppEscena, componentes[1]);
                                //System.out.println(Colors.Bright_Cyan + "Ok. Exportación Correcta a SVG" + Colors.Reset);
                                //System.out.println(Colors.Bright_Red + "Error. Exportació Incorrecta" + Colors.Reset);
                                break;

                            case "json":
                                fm.exportJSON(AppEscena, componentes[1]);
//                                    System.out.println(Colors.Bright_Cyan + "Ok. Exportación Correcta a JSON" + Colors.Reset);
//                                    System.out.println(Colors.Bright_Red + "Error. Exportación Incorrecta" + Colors.Reset);
                                break;

                            default:
                                System.out.println("\u001B[31m Formato no soportado\u001B[0m");
                                break;


                        }

                    } catch (Exception e) {
                        System.out.println("\u001B[31m Error de exportación\u001B[0m");
                    }

                    break;

                case "lista":
                    // Si la orden indicada es lista, se imprime por pantalla
                    AppEscena.renderText();
                    break;

                case "draw":
                    // Si la orden es draw, cargamos la aplicación JavaFX
                    // implementaremos el método start (con @override), que es
                    // quien dibuja nuestra escena
                    launch(args);
                    System.exit(0);

                    break;

                case "quit":
                    System.out.println(Colors.Magenta + "Acabando el programa." + Colors.Reset);
                    break;
                default:

                    System.out.println(Colors.Bright_Red + "Figura no reconocida" + Colors.Reset);
            }


        } while (!figura.equals("quit"));
        System.exit(0);
    }


    /*
     * Dibujamos la escena
     */
    @Override
    public void start(Stage primaryStage) {


        // Preparamos el contexto gráfico (GraphicsContent) 
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(App.AppEscena.getX(), App.AppEscena.getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Se lo pasamos al método renderScene de nuestra escena, 
        // para que dibuje sobre el context los diferentes elementos.
        App.AppEscena.renderScene(gc);

        // Añadimos a la ventana el lienzo
        // establecemos algunas propiedades y lo dibujamos (Show)
        root.getChildren().add(canvas);
        primaryStage.setTitle("Escena");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}