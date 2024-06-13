package minesdgr.controlador;


import megaLibreria.utilities;
import minesdgr.model.Model;
import minesdgr.vista.Vista;

import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Clase que contiene los metodos de control del juego
 */
public class Controlador {
    /**
     * Variable para escanear lo que el usuario escribe
     */
    private static Scanner scan=new Scanner(System.in);

    /**
     * Pone en marcha el programa luego de selleccionar el nivel
     * @param files indica las filas dependiendo del nivel
     * @param columnes indica las columnas dependiendo del nivel
     * @param bombes indica el numero de bombas que tendra el tablero
     */
    public static void jugar(int files, int columnes, int bombes){
        String[] opcions = {"T- Trepitjar 🚶", "B- Bandera 🚩", "A- Acabar 🏃‍♂️"};
        String[] sortir = {"S- Si 😶‍🌫️", "N- No 😵‍💫"};



            Model.inicialitzarJoc(files, columnes, bombes);
            System.out.println();

        do {
            String input;
            String numero;
            int lletra;
            char l;
            int num=0;
            boolean correcte = true;
            boolean verificar;
            boolean digit=true;

//          Muestra el menu con las opciones de trepitjar,posar bandera o acabar y si no se selecciona una opcion correcta la vuelve a pedir

            utilities.menu("Que vols fer?", opcions);
            System.out.print("🔜 ");

            do {
                correcte=true;
                input = scan.next().toUpperCase();
                switch (input) {
                    case "T":
//                        llama a la funcion de verificar hasta que la casilla a trepitjar es correcta y luego llama a trepitjar para que se pise la casilla
                        do {
                            digit=true;
                            System.out.print("Quina casilla vols trepitjar 🚶?: ");
                            input = scan.next().toUpperCase();
                            lletra = input.charAt(0);
                            numero = input.substring(1);

                            for (char c : numero.toCharArray()){
                                if (!(Character.isDigit(c))){
                                    digit=false;
                                    break;
                                }
                            }
                            if (digit){
                                num= Integer.parseInt(input.substring(1));
                            }

                            verificar= Model.verificarFiC(lletra, num);
                        }while (!verificar);

                        l= (char) (lletra);
                        Model.trepitjar(l, num);


                        break;
                    case "B":
//                        llama a la funcion de verificar hasta que la casilla de la bandera es correcta y luego llama posarBandera para ponerla o quitarla
                        do {
                            System.out.print("On vols posar la bandera 🚩?: ");

                            input = scan.next().trim().toUpperCase();
                            lletra = input.charAt(0);
                            numero = input.substring(1);

                            for (char c : numero.toCharArray()){
                                if (!(Character.isDigit(c))){
                                    digit=false;
                                    break;
                                }
                            }
                            if (digit){
                                num= Integer.parseInt(input.substring(1));
                            }

                            verificar= Model.verificarFiC(lletra, num);
                        }while (!verificar);

                        l= (char) (lletra);
                        Model.posarBandera(l,num);

                        break;
                    case "A":
//                      pide confirmacion para salir, si se pone que si se acaba el programa y si se pone que no, se sale del bucle volviendo a mostrar el tablero con la funcion consultarJocAcabat()
                        utilities.menu("Segur que vols sortir 🤔?", sortir);
                        System.out.print("🔜 ");
                        input = scan.next().trim().toUpperCase();

                        switch (input) {
                            case "N":
                                break;
                            case "S":
                                Vista.mostrarMissatge("Gracies per jugar 👋");
                                System.exit(-1);
                        }
                    break;
                    default:
                        Vista.mostrarMissatge("Selecciona una opcion correcta!");
                        correcte = false;
                        System.out.print("🔜 ");


                }

            } while (!correcte);

        } while (!Model.consultarJocAcabat());


    }

    /**
     * Permite al usuario escoger el nivel que quiere
     * @throws InterruptedException lanza una escepcion si hay un error con el sleep
     */
    public static void seleccionarNivell() throws InterruptedException {
        String[] opcions={"1️⃣ 8x8 (10 \uD83D\uDE3F)","2️⃣ 16x16 (40 \uD83D\uDE3F)","3️⃣ 16x30 (99 \uD83D\uDE3F)","4️⃣ Sortir 🏃‍♂"};
        utilities.menu("Nivels:",opcions);
        int opcio= utilities.introducirNumeroEntero(scan, "Selecciona el nivell",opcions.length,1);

        switch (opcio){
            case 1:
                Vista.mostrarMissatge("Has seleccionat el nivell 1, Sort 😎!");
                System.out.println();
                sleep(1600);

                jugar(8,8,10);
            break;
            case 2:
                Vista.mostrarMissatge("Has seleccionat el nivell 2,  Molta sort 🧐!");
                System.out.println();
                sleep(1600);

                jugar(16,16,40);
                break;
            case 3:
                Vista.mostrarMissatge("Has seleccionat el nivell 3, Que la força t'acompanyi 😅");
                System.out.println();
                sleep(1600);
                
                jugar(16,30,99);
                break;
            case 4:
                Vista.mostrarMissatge("Gracies per jugar 👋");
                System.out.println();
                System.exit(-2);
        }


    }

    /**
     * Muestra el titulo del juego y la pequeña historia
     * @throws InterruptedException lanza una escepcion si hay un error con el sleep
     */
    public static void intro() throws InterruptedException {
        System.out.println("           ____                         ____       _       \n" +
                "          | __ ) _   _ ___  ___ __ _   / ___| __ _| |_ ___ \n" +
                "          |  _ \\| | | / __|/ __/ _` | | |  _ / _` | __/ __|\n" +
                "          | |_) | |_| \\__ \\ (_| (_| | | |_| | (_| | |_\\__ \\\n" +
                "          |____/ \\__,_|___/\\___\\__,_|  \\____|\\__,_|\\__|___/\n" +
                "                                                           ");

        sleep(1000);
        System.out.println();
        System.out.println("Hi ha molts gatets per aquí però s'espanten massa depresa 😿");
        sleep(800);
        System.out.println("Trobal's tots sense espantar a ningú per guanyar!");
        sleep(1600);
        System.out.println();
        seleccionarNivell();
    }
}
