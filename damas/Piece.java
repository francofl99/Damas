package damas;

/**
 * Clase que representa una ficha de dama.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Piece{

    /**
     * Tipo de la ficha blanca o negra.
     * 
     * Blanca = 'w'
     * Negra = 'b'
     */
    private char type;
    /**
     * Posicion en la cual esta la ficha.
     */
    private Position pos;

    /**
     * Constructor de la clase.
     * @param type tipo de la ficha.
     * @param row fila en donde esta posicionada.
     * @param column columna en dondes esta posicionada.
     */
    public Piece(char type, int row, int column){
        this.type = type;
        this.pos = new Position(row, column);
    }

    /**
     * Constructor de la clase sin parametro de tipo.
     * Por defecto se crea una ficha blanca.
     * @param row fila en donde esta posicionada.
     * @param column columna en dondes esta posicionada.
     */
    public Piece(int row, int column){

        this('w', row, column);

    }

    /**
     * @return el tipo de la ficha.
     */
    public char getType(){
        return this.type;
    }

    /**
     * Modifica el tipo de la ficha.
     * @param newType tipo por el cual se quiere cambiar.
     */
    public void setType(char newType){
        this.type = newType;
    }

    /** 
     * @return la posicion de la ficha.
     */
    public Position getPos(){
        return this.pos;
    }

    /**
     * Modifica la posicion de la ficha
     * @param newPos posicion nueva por la cual se quiere cambiar.
     */
    public void setPos(Position newPos){
        this.pos = newPos;
    }

    /**
     * Metodo toString para la clase Piece.
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Piece ");

        if (this.type == 'w') {

            sb.append("white");

        }
        else{

            sb.append("black");

        }

        sb.append(" in position (").append(this.pos.getRow()).append(", ").append(this.pos.getColumn()).append(")");

        return sb.toString();

    }

}