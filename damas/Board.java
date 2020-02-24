package damas;

import java.util.ArrayList;

/**
 * Clase que representa un tablero de damas.
 */
public class Board{

    /**
     * Matriz de M x M para representar el tablero.
     * @author Fernandez Lorio Franco. DNI: 41088241.
     */
    private Piece[][] board;
    /**
     * Cantidad de fichas blancas, negras y totales respectivamente.
     */
    private int cantWhite;
    private int cantBlack;
    /**
     * Tamaño M del tablero.
     */
    private int size;

    /**
     * Constructor de la clase que recibe un tamaño M para el tablero 
     * y una lista de piezas blancas y negras
     * cada pieza conteniendo su posicion deseada en el tablero.
     * @param size tamaño M para el tablero.
     * @param whites lista de piezas blancas a colocar en el tablero.
     * @param blacks lista de piezas negras a colocar en el tablero.
     */
    public Board(int size, ArrayList<Piece> whites, ArrayList<Piece> blacks){

        board = new Piece[size][size];

        this.cantWhite = this.cantBlack = 0;

        this.size = size;

        /**
         * Colocacion de las fichas en el tablero segun su posicion\
         * y calculo de la cantidad de fichas negras y blancas.
         */
        init(whites, blacks);
      
    }

    /**
     * Metodo para inicializar el juego segun los parametros recibidos.
     * 
     * Recibe una lista de piezas blancas y una lista de piezas negras.
     * 
     * @param whites lista de piezas blancas a colocar en el tablero.
     * @param blacks lista de piezas negras a colocar en el tablero.
     */
    public void init(ArrayList<Piece> whites, ArrayList<Piece> blacks){

        /**
         * Coloca las fichas blancas.
         */
        for (int i = 0; i < whites.size(); i++) {
            
            Piece piece = whites.get(i);

            this.board[piece.getPos().getRow()][piece.getPos().getColumn()] = piece;

            this.cantWhite++;

        }

        /**
         * Coloca las fichas negras.
         */
        for (int i = 0; i < blacks.size(); i++) {
            
            Piece piece = blacks.get(i);

            this.board[piece.getPos().getRow()][piece.getPos().getColumn()] = piece;

            this.cantBlack++;

        }

    }

    /**
     * @return cantidad de piezas blancas en el tablero.
     */
    public int getCantWhite(){
        return this.cantWhite;
    }

    /**
     * Modifica la cantidad de piezas blancas en el tablero.
     * @param newCant Nueva cantidad de piezas blancas en el tablero.
     */
    public void setCantWhite(int newCant){

        this.cantWhite = newCant;

    }

    /**
     * @return cantidad de piezas negras en el tablero.
     */
    public int getCantBlack(){
        return this.cantBlack;
    }

     /**
     * Modifica la cantidad de piezas negras en el tablero.
     * @param newCant Nueva cantidad de piezas negras en el tablero.
     */
    public void setCantBlack(int newCant){

        this.cantBlack = newCant;

    }

    /**
     * @return el tablero del juego.
     */
    public Piece[][] getBoard(){
        return this.board;
    }

    /**
     * Modifica el tablero del juego.
     * @param newBoard tablero nuevo con el cual se va a reemplazar el anterior.
     */
    public void setBoard(Piece[][] newBoard){
        this.board = newBoard;
    }

    /**
     * Metodo que verifica si una posicion dada existe en el tablero.
     * @param pos posicion a verificar
     * @return True si la posicion existe en el tablero sino False.
     */
    public boolean correctPos(Position pos){

        return (0 <= pos.getRow()) && (pos.getRow() < this.size) && (0 <= pos.getColumn()) && (pos.getColumn() < this.size);

    }

    /**
     * Recupera una ficha segun su posicion
     * @param pos en la cual esta la ficha.
     * @return ficha deseada, si la ficha no existe entonces se retorna null.
     */
    public Piece getPiece(Position pos){

        return this.board[pos.getRow()][pos.getColumn()];
       
    }

    /**
     * Modifica una ficha dada su posicion.
     * @param newPiece ficha por la cual intercambiar.
     * @param pos posicion de la ficha anterior.
     * @throws NotValidPositionException si la posicion dada como parametro no es valida
     * se tira una excepcion. 
     */
    public void setPiece(Piece newPiece, Position pos) throws NotValidPositionException{

        if (!correctPos(pos)){

           throw new NotValidPositionException();

        }

        this.board[pos.getRow()][pos.getColumn()] = newPiece;

    }

    /**
     * @return el tamaño del tablero.
     */
    public int getSize(){

        return this.size;
    }
    
    /**
     * Verifica si una posicion dada como parametro esta vacia en el tablero
     * es decir no existe una ficha en esa posicion del tablero.
     * @param pos posicion dada.
     * @return true si no existe una ficha en esa posicion sino false.
     */
    public boolean emptyPos(Position pos){

        return this.getPiece(pos) == null;

    }

}