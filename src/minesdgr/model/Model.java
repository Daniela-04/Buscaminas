package minesdgr.model;

import megaLibreria.utilities;
import minesdgr.vista.Vista;


import static minesdgr.vista.Vista.mostrarCampMines;
import static minesdgr.vista.Vista.mostrarMissatge;

/**
 * Clase que contiene los metodos para gestionar las operaciones internas del programa con los datos proporcionados
 */
public class Model {
    /**
     * variable que indica el numero de filas que tiene el tablero
     */
    private static int numfiles;
    /**
     * variable que indica el numero de columnas que tiene el tablero
     */
    private static int numcolumnes;
    /**
     * variable que indica el numero de bombes que tiene el tablero
     */
    private static int numbombes;
    /**
     * variable que indica si el juego ha finalizado o no
     */
    private static boolean jocFinalitzat;
    /**
     * array bidimensional que indica el campo con el que se trabajara internamente en el programa
     */
    private static char[][] campOcult;
    /**
     * array bidimensional que indica el campo que es visible para el usuario
     */
    public static char[][] campVisible;

    /**
     * Ejecuta y llama todas las funciones para que el juego funcione
     * @param files indica las filas que tendra el tablero
     * @param columnes indica las columnas que tendra el tablero
     * @param bombes indica el numero de bombas que se pondrán en el tablero
     */

    public static void inicialitzarJoc(int files, int columnes, int bombes){

//    inicializar variables globales para poderlas usar con lo que introducirá el usuario
        numfiles=files;
        numcolumnes=columnes;
        numbombes=bombes;

//    inicializar las arrays con dos casillas demas para facilitar la identificacion de bombas
        campOcult = new char[files+2][columnes+2];
        campVisible =new char[files+2][columnes+2];

//   inicializar los campos oculto y visible: 1-llenando cada casilla con un espacio o un punto respectivamente 2- poniendo bombas 3-sustituyendo los espacios del campo oculto por el numero de bombas
        initcialitzarCampMines(campOcult,' ');
        initcialitzarCampMines(campVisible,'·');
        posarBombes();
        comptarBombes();

//    mostrar campo oculto dentro de una region para facil localizacion y eliminacion posterior

        //region solamenta para pruebas
//        mostrarCampMines(campOcult);
//        System.out.println();
        //endregion

//    mostrar campo de minas visible empleando la funcion que hay en vista
        mostrarCampMines(campVisible);



    }

    /**
     * se asegura de que las filas y columnas introducidas por el usuario sean correctas
     * @param fila indica la fila introducida
     * @param columna indica la columna introducida
     * @return retorna true si tanto la fila como la columna son correctas y false si alguna no lo es
     */
    public static boolean verificarFiC(int fila, int columna){
        boolean correcte;
        boolean fC=true;
        boolean cC=true;
//          verifica que la fila introducida sea correcta
            if (fila >= 'A' && fila <= '@' + numfiles) {
//              verifica que la columna introducida sea correcta
            } else {
                fC=false;


            }
        if (columna < 1 || columna > numcolumnes) {
            cC = false;

        }
//          avisa la usuario del error cometido
             if (cC && !fC){
                 mostrarMissatge("Fila incorrecta");
             }
             if (!cC && fC){
                 mostrarMissatge("Columna incorrecta");
             }else if(!cC && !fC){
                 mostrarMissatge("Introdueix una posicio valida");
             }

//      retorna true si tanto la fila como la columna son correctas y false si no lo son, despues de mostrar el mensaje correspondiente
        if (cC && fC) {
                 correcte=true;
             } else{
                 correcte=false;
             }

        return correcte;

    }

    /**
     * Inicializa cada una de las casillas del campo que se le pase con un caracter concreto
     * @param campMines array bidimensional que indica el campo
     * @param caracter indica el caracter con el que se llenaran las casillas
     */
    private static void initcialitzarCampMines(char[][] campMines, char caracter){
//        tacha las casillas de al rededor que solo se usaran internamente
        for (int i = 1; i < numfiles+2 ; i++) {
            for (int j = 1; j < numcolumnes+2; j++) {
                campMines[i][j]='/';
            }
        }
//      inicializa las casillasvisibles con el caracter que se le pase
        for (int i = 1; i < campMines.length-1 ; i++) {
            for (int j = 1; j < campMines[0].length-1; j++) {
                campMines[i][j]=caracter;
            }
        }


    }

    /**
     * pone las bombas en el campo oculto de manera aleatoria
     */
    private static void posarBombes(){

        char bom= '*';
        int num1;
        int num2;
        int bombas=numbombes;

        for (int i = 0; i < bombas; i++) {
//            genera dos numeros aleatorios entre el numero de columnas y filas y 1, estos seran los componentes i y j que indiquen la casilla donde poner la bomba
            num1=utilities.generarNumAleatori(numfiles,1);
            num2=utilities.generarNumAleatori(numcolumnes,1);
//            se asegura de que siempre haya el numero de bombas correspondiente si en l casilla generada ya hay una bomba
                if(campOcult[num1][num2]== bom){
                    bombas++;

                } else {
                campOcult[num1][num2]= bom;
                }


        }
    }

    /**
     * Cuenta cuantas bombas tiene al rededor cada casilla y pone ese valor en ella
     */
    private static void  comptarBombes(){
        // variable con el caracter de la bomba
        char car='*';

        for (int i = 1; i < campOcult.length-1; i++) {

            for (int j = 1; j < campOcult[0].length-1; j++) {

                /*
                +---------+-------+--------+
                | i-1 j-1 | i-1 j | i-1 j+1|
                +---------+-------+--------+
                |  i j-1  |  i j  | i j+1  |
                +---------+-------+--------+
                | i+1 j-1 | i+1 j | i+1 j+1|
                +-------+---------+--------+
                */

                if (campOcult[i][j]!=car) {
                    int num=0;

//                  casilla identificadas con regiones y el dibujo para mejor entendimiento y navegacion

                    //region fila superior de la casila

                    /*
                    +-------+-------+----------+
                    | i-1 j-1 | i-1 j | i-1 j+1|
                    +-------+-------+----------+
                    */

                    if (campOcult[i-1][j-1]==car){
                        num++;
                    }
                    if (campOcult[i-1][j]==car){
                        num++;
                    }
                    if (campOcult[i-1][j+1]==car){
                        num++;
                    }
                    //endregion

                    //region Costados izquierdo y derecho de la casilla

                    /*
                    +-------+-------+---------+
                    | i j-1 |       |  i j+1  |
                    +-------+-------+---------+
                    */

                    if (campOcult[i][j-1]==car){
                        num++;
                    }
                    if (campOcult[i][j+1]==car){
                        num++;
                    }
                    //endregion

                    //region fila inferior de la casilla

                   /*
                    +---------+-------+--------+
                    | i+1 j-1 | i+1 j | i+1 j+1|
                    +-------+---------+--------+
                    */

                    if (campOcult[i+1][j-1]==car){
                        num++;
                    }
                    if (campOcult[i+1][j]==car){
                        num++;
                    }
                    if (campOcult[i+1][j+1]==car){
                        num++;
                    }
                    //endregion

                    campOcult[i][j]= (char) (num+'0');
                }

            }
        }

    }

    /**
     * Se encarga de gestionar las casillas que seran pisadas
     * @param fila indica la fila en la que se encuentra la casilla a pisar
     * @param columna indica la columna en la que se encuentra la casilla a pisar
     */
    public static void trepitjar(int fila, int columna){
        int fil=fila-'@';
        jocFinalitzat=false;

//      comprueba si ya se ha puesto una bandera y por tanto no se puede pisar la casilla
        if (campVisible[fil][columna]=='P'){
            mostrarMissatge("Aquí hi ha una bandera ⚠️");
        }else {
//            si se pisa una casilla con una bomba acaba el juego y muestra la solucion sustituyendo la bomba pisada por un # para posterior puesta de icono
            if (campOcult[fil][columna]=='*'){
                mostrarMissatge(" Has espantat un gatet😭");

                System.out.println();
                mostrarMissatge("Solucio:");

                campVisible[fil][columna]='#';
                mostrarSolucio();
                System.exit(0);
            } else {
//              en el caso de que no haya una bomba destapa la casilla mostrando las bombas al rededor
                trepitjaRecursiu(fil,columna);

            }
        }
//      llama a la funcion guanyar para comprovar si se ha ganado
        guanyat();


    }

    /**
     * se encarga de destapar la casilla pisada y en caso de que esta sea '0' destapa las de al rededor, repite el proceso con todos los ceros que se encuentren al rededor de la casilla pisada
     * @param fila indica la fila en la que se encuentra la casilla
     * @param columna indica la columna en la que se encuentra la casilla
     */
    public static void trepitjaRecursiu(int fila, int columna){

        if (!(fila >= 1 && fila <= numfiles)){
            if (!(columna >= 1 && columna <= numcolumnes)){
                return;
            }

        }
        if (!(campVisible[fila][columna]=='·')){
            return;
        }

        campVisible[fila][columna] = campOcult[fila][columna];

        if (campOcult[fila][columna]=='0') {

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    trepitjaRecursiu(fila + i, columna + j);
                }
            }
        }






    }

    /**
     * gestiona las casillas en las que se pondrá una bandera
     * @param fila indica la fila en la que se pondra la bandera
     * @param columna indica la columna en la que se pondra la bandera
     */
    public static void posarBandera(int fila,int columna){

        char vacio='·';
        char bandera='P';
        int fil=fila-'@';
//      Si hay una bandera la quita y si no la pone, pero si una casilla ya esta pisada avisa que no se puede poner una bandera y no hace nada
        if (campVisible[fil][columna]==bandera){
            campVisible[fil][columna]=vacio;
        }else{
            if(campVisible[fil][columna]=='·') {
                campVisible[fil][columna]=bandera;

            }else if (campVisible[fil][columna]!='·') {
                Vista.mostrarMissatge("No pots posar una bandera a una casella trapitjada");
                System.out.println();
            }
        }
//      comprueba si se ha ganado
        guanyat();


    }

    /**
     * consulta si el juego ha acabado, si no imprime el campo visible para que pueda ser usado
     * @return retorna true si si y false si no
     */
    public static boolean consultarJocAcabat(){
        boolean finalitzat=false;

        if (jocFinalitzat){
            finalitzat=true;
        }else{
            Vista.mostrarCampMines(campVisible);
        }
        return finalitzat;
    }

    /**
     * gestiona si el jugador ha ganado, es decir ha descubierto todas las casillas sin pisar ninguna bomba y ha puesto las banderas donde corresponde
     * @return retorna true si ha ganado y false si no
     */
    private static boolean guanyat(){
        boolean guanyat=true;
        int banderas=0;

        for (int i = 1; i < campVisible.length-1; i++) {
            for (int j = 1; j < campVisible[0].length-1; j++) {
                if (campVisible[i][j]=='P') {
                    banderas++;
                }
            }
        }

        for (int i = 1; i < campVisible.length-1; i++) {
            for (int j = 1; j < campVisible[0].length-1; j++) {
                if (banderas!=numbombes){
                    guanyat=false;
                }
                if (campVisible[i][j]!='P' && campVisible[i][j]!=campOcult[i][j]) {
                    guanyat = false;

                }


            }

        }
//        si se ha ganado felicita al usuario y acaba el juego
        if(guanyat){
            Vista.mostrarCampMines(campVisible);
            Vista.mostrarMissatge("🥇 Felicitats has trobat tots el Gatets 😸 🥇");
            System.exit(0);
        }


        return guanyat;
    }

    /**
     * Se encarga de mostrar la solucion una vez se pierde
     */
    private static void mostrarSolucio(){

        for (int i = 1; i < campVisible.length-1; i++) {
            for (int j = 1; j < campVisible[0].length-1; j++) {
                if(campOcult[i][j]=='*'&& campVisible[i][j]!='*' ){
//                    si la casilla no es una bomba que explotó (#) imprime las bombas que sobran
                    if (campVisible[i][j]!='#') {
                        campVisible[i][j] = campOcult[i][j];
                    }
                }
//              si el usuario ha puesto una bandera en un casilla donde no habia bomba le muestra el error con una X
                if (campOcult[i][j]!='*' && campVisible[i][j]=='P'){
                    campVisible[i][j]='X';
                }
            }
        }
        mostrarCampMines(campVisible);
    }


}





