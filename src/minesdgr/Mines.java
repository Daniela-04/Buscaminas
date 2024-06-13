package minesdgr;

import minesdgr.controlador.Controlador;

/**
 * Clase que contiene el metodo para inicializar el programa
 */
public class Mines {
    /**
     * Funcion main de la clase minas que llama a intro para inicializar el programa
     * @param args indica los argumentos que tiene
     * @throws InterruptedException lanza una escepcion si hay un error con el sleep
     */
    public static void main(String[] args) throws InterruptedException {
        Controlador.intro();
    }
}