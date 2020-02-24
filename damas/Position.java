package damas;

/**
 * Clase para representar una posicion con fila y columna.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Position{

    /**
     * Fila.
     */
    private int row;
    /**
     * Columna.
     */
    private int column;

    /**
     * Constructor de la clase.
     * @param newRow fila deseada.
     * @param newColumn columna deseada.
     */
    public Position(int newRow, int newColumn){
        this.row = newRow;
        this.column = newColumn;
    }

    /**
     * @return fila.
     */
    public int getRow(){
        return this.row;
    }

    /**
     * @return columna
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Setea la fila
     * @param newRow fila nueva a almacenar.
     */
    public void setRow(int newRow){
        this.row = newRow;
    }

    /**
     * Setea la columna
     * @param newColumn columna nueva a almacenar.
     */
    public void setColumn(int newColumn){
        this.column = newColumn;
    }

    /**
     * Dada una posicion retorna su diagonal hacia arriba y hacia la derecha
     * la cantidad de pasos que se especifique.
     * @param steps cantidad de pasos deseados a hacer en diagonal.
     * @return posicion resultante.
     */
    public Position upRight(int steps){
        
        Position diagonal = new Position(this.row + steps, this.column + steps);

        return diagonal;

    }

     /**
     * Dada una posicion retorna su diagonal hacia arriba y hacia la izquierda
     * la cantidad de pasos que se especifique.
     * @param steps cantidad de pasos deseados a hacer en diagonal.
     * @return posicion resultante.
     */
    public Position upLeft(int steps){

        Position diagonal = new Position(this.row + steps, this.column - steps);

        return diagonal;

    }

     /**
     * Dada una posicion retorna su diagonal hacia abajo y hacia la derecha
     * la cantidad de pasos que se especifique.
     * @param steps cantidad de pasos deseados a hacer en diagonal.
     * @return posicion resultante.
     */
    public Position backRight(int steps){

        Position diagonal = new Position(this.row - steps, this.column + steps);

        return diagonal;

    }

     /**
     * Dada una posicion retorna su diagonal hacia abajo y hacia la izquierda
     * la cantidad de pasos que se especifique.
     * @param steps cantidad de pasos deseados a hacer en diagonal.
     * @return posicion resultante.
     */
    public Position backLeft(int steps){

        Position diagonal = new Position(this.row - steps, this.column - steps);

        return diagonal;

    }

    /**
     * Metodo para comparar esta posicion con otra dada como parametro.
     * @param pos con la cual se quiere comparar.
     * @return true si son iguales sino false.
     */
    public boolean equals(Position pos){

        return this.getRow() == pos.getRow() && this.getColumn() == pos.getColumn();

    }

    /**
     * Metodo toString para la clase.
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("(" + this.row + ", " + this.column + ")");

        return sb.toString();

    }

}