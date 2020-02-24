package damas;

import java.util.ArrayList;

/**
 * Clase para testear la correcion del ejercicio.
 * 
 * Ejemplo 2.
 * 
 * Basado en la figura d) del pdf del trabajo.
 * 
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class EjemploDos{

    public static void main(String[] args) {

        ArrayList<Piece> whites = new ArrayList<Piece>();
        ArrayList<Piece> blacks = new ArrayList<Piece>();

        /**
         * Creacion y adicion de cada pieza en cada lista correspondiente.
         * 
         * Cada pieza tiene su tipo, blanca o negra, y su posicion en el tablero.
         */
        Piece winnerPiece = new Piece('w', 9, 0);
        whites.add(winnerPiece);
        Piece b1 = new Piece('b', 9, 6);
        blacks.add(b1);
        Piece b2 = new Piece('b', 8, 3);
        blacks.add(b2);
        Piece b3 = new Piece('b', 8, 5);
        blacks.add(b3);
        Piece b4 = new Piece('b', 6, 1);
        blacks.add(b4);
        Piece b5 = new Piece('b', 6, 5);
        blacks.add(b5);
        Piece b6 = new Piece('b', 6, 7);
        blacks.add(b6);
        Piece b7 = new Piece('b', 4, 1);
        blacks.add(b7);
        Piece b8 = new Piece('b', 4, 3);
        blacks.add(b8);
        Piece b9 = new Piece('b', 4, 7);
        blacks.add(b9);
        Piece b10 = new Piece('b', 2, 3);
        blacks.add(b10);
        Piece b11 = new Piece('b', 2, 5);
        blacks.add(b11);
        Piece w1 = new Piece('w', 4, 5);
        whites.add(w1);
        Piece w2 = new Piece('w', 3, 8);
        whites.add(w2);
        
        /**
         * Metodo que resuelve el ejercicio propuesto.
         */
        System.out.println(Game.eatAllBlackString(10, whites, blacks));
        


    }

}