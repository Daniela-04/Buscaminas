package minesdgr.vista;


/**
 * clase que contiene los metodos para gestionar lo que ve el usuario
 */
public class Vista {
    /**
     * Muestra el campo de minas que se le pase como parametro
     * @param campMines indica el campo de minas a mostrar
     */
    public static void mostrarCampMines(char[][] campMines){

        int num=1;
        char letra='A';
        String emoji="🫡";

//        imprime los numeros que identifican las columnas

        System.out.printf("%2s ",emoji);
        for (int i = 2; i < campMines[0].length; i++) {
            if (i>10){
                System.out.printf("|%-2d|",num);
            } else {
                System.out.printf("|%-1d| ", num);
            }
            num++;
        }
        System.out.println();


        for (int i = 1; i < campMines.length-1; i++) {
//          pone las letras al inicio de cada fila
            System.out.print(letra);
            System.out.print('|');
            letra++;

//            imprime las casillas del campo sustituyendo los diferentes marcadores por emojis o el valor de la casilla adiente
            for (int j = 1; j < campMines[0].length-1; j++) {
                String bomba= "😼";
                String explosion="🙀";
                String bandera="🎏";
                String banderaMalPuesta="🐶";
                String vacio="🐾";

                if (campMines[i][j]== '*'){
                    System.out.printf(" %2s ",bomba);
                } else if (campMines[i][j]== 'P') {
                    System.out.printf(" %2s ",bandera);
                } else if (campMines[i][j]== 'X') {
                    System.out.printf(" %2s",banderaMalPuesta);
                } else if (campMines[i][j]== '#') {
                    System.out.printf(" %2s",explosion);
                } else if (campMines[i][j]== '0') {
                    System.out.printf(" %2s ",vacio);
                } else{
                    System.out.printf(" %2s ",campMines[i][j]);
                }



            }
            System.out.println();
        }

    }

    /**
     * Imprime el mensaje que se le pase
     * @param missatge indica mensaje a imprimir
     */
    public static void mostrarMissatge(String missatge){
        System.out.println(missatge);
    }
}
