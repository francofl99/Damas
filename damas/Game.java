package damas;

import java.util.Map;
import java.util.ArrayList;

import graph.*;

/**
 * Clase principal para el juego de las damas.
 * Clase principal para la resolucion del trabajo de Estructura de datos/Algoritmos 1.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Game{

    /**
     * Tablero donde se alojan las fichas.
     */
    private Board board;
    /**
     * Grafo correspondiente al estado del juego.
     */
    private Graph<Piece> state;

    /**
     * Crea un juego con una cantidad de fichas y posiciones dadas.
     * @param size tamaño del tablero.
     * @param whites lista de posiciones de fichas blancas.
     * @param blacks lista de posiciones de fichas negras.
     */
    public Game(int size, ArrayList<Piece> whites, ArrayList<Piece> blacks) {

        /**
         * Crea el tablero colocando las fichas en las posiciones correspondientes.
         */
        this.board =  new Board(size, whites, blacks);
        /**
         * Crea el grafo que se va a usar para seguir el estado del juego.
         */
        this.state = new Graph<Piece>();

        /**
         * Inicializa los nodos en el grafo.
         */
        initState();

        /**
         * Calcula los adyacentes a cada pieza en el tablero.
         */
        calculateAdjacents();

    }

    /**
     * @return el tablero del juego, el mismo ya incluye las fichas y sus posiciones.
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * @return el grafo que tiene el estado del juego.
     */
    public Graph<Piece> getSate(){
        return this.state;
    }

    /**
     * Crea todos los nodos del tablero en el grafo.
     */
    private void initState() {

        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {

                Position pos = new Position(i, j);

                Piece piece = this.board.getPiece(pos);

                if (piece != null) { //Crea el nodo siempre que la ficha exista.
                 
                    this.state.insertNode(piece);
                    
                }
                
            }
        }

    }

    /**
     * Calcula en el grafo todas las fichas adyacentes a cada ficha en el tablero.
     * 
     * Es decir, para cada ficha, calcula todos las fichas adyacentes a la misma.
     * 
     * Entendiendose como ficha adyacente cuando se cumple que
     * es adyacente a la ficha seleccionada en cualquier posicion diagonal 
     * es de distinto tipo, y ademas, que la posicion diagonal seguida a la misma esta vacia.
     */
    private void calculateAdjacents() {

        for (Map.Entry<Integer, Node<Piece> > entry: this.state.getHashMapNodes().entrySet()) {
            
            Node<Piece> nodePiece = entry.getValue();

            Piece piece = this.board.getPiece(nodePiece.getInfo().getPos());

            /**
             * Metodo recursivo para encontrar cada adyacente posible.
             */
            adjacents(piece, nodePiece, piece.getPos());

        }

    }

    /**
     * Metodo usado de manera auxiliar para verificar si una ficha cumple la condicion de adyacencia.
     * 
     * Si la ficha cumple la condicion de adyacencia entonces se verifica si la misma ya se esta
     * relacionando con la ficha correspondiente en el grafo, si no lo esta, entonces se agrega
     * la relacion en el mismo.
     * 
     * @param piece pieza seleccionada para buscar sus adyacentes.
     * @param nodePiece nodo de pieza correspondiente al grafo.
     * @param pos posicion dada en el momento de la invocacion al metodo, esta posicion no necesariamente
     * es la que ocupa la ficha en el tablero en ese estado del juego, sino es una posicion supuesta
     * que se genera cuando la ficha seleccionada come a una adyacente y salta a su diagonal correspondiente.
     * @param flag bandera para indicar hacia donde se busca el adyacente.
     * 1 - indica que se busca el adyacente en diagonal hacia arriba a la derecha.
     * 2 - indica que se busca el adyacente en diagonal hacia arriba a la izquierda.
     * 3 - indica que se busca el adyacente en diagonal hacia abajo a la derecha.
     * 4 - indica que se busca el adyacente en diagonal hacia abajo a la izquierda.
     * @return true si en la posicion dada como parametro se cumple que efectivamente existe un adyacente
     * a la ficha dada sino false.
     */
    private boolean isPossibleAdjacent(Piece piece, Node<Piece> nodePiece, Position pos, int flag){

        /**
         * Se verifica hacia donde se tiene que buscar el adyacente
         */
        if (flag == 1) {

            /**
             * Se crea la supuesta pieza de adyacencia.
             */
            Piece adjacent = this.board.getPiece(pos.upRight(1));
            /**
             * Se crea su respectivo nodo.
             */
            Node<Piece> adjacentNode = this.state.getNode(adjacent);
        
            /**
             * Se verifica si la pieza cumple con todas las condiciones necesarias
             * para ser adyacente a la pieza seleccionada.
             * 
             * Las mismas son:
             * - La pieza tiene que existir en esa posicion del tablero.
             * - La posicion en diagonal a la pieza tiene que estar vacia.
             * - El tipo de la pieza, es decir si es negra o blanca, debe ser distinto
             *   al tipo de la pieza seleccionada. 
             */
            if (adjacent != null && this.board.emptyPos(pos.upRight(2)) && adjacent.getType() != piece.getType()){

                /**
                 * Suponiendo que lo anterior se cumple, se verifica ahora
                 * si la pieza no esta agregada como una adyacente a la pieza seleccionada
                 * es decir si no estan relacionadas en el grafo.
                 */
                if (!nodePiece.searchAdjacent(adjacentNode)) { 
                    
                    return true;
                    
                }

            }
            
        }
        else if(flag == 2){

            Piece adjacent = this.board.getPiece(pos.upLeft(1));

            Node<Piece> adjacentNode = this.state.getNode(adjacent);
        
            if (adjacent != null && this.board.emptyPos(pos.upLeft(2)) && adjacent.getType() != piece.getType()){

                if ( !nodePiece.searchAdjacent(adjacentNode)) {

                    return true;
                    
                }
              
            }

        }
        else if(flag == 3){

            Piece adjacent = this.board.getPiece(pos.backRight(1));

            Node<Piece> adjacentNode = this.state.getNode(adjacent);
        
            if (adjacent != null && this.board.emptyPos(pos.backRight(2)) && adjacent.getType() != piece.getType()){

                if (!nodePiece.searchAdjacent(adjacentNode)) {
                    
                    return true;

                }

            }

        }
        else if(flag == 4){

            Piece adjacent = this.board.getPiece(pos.backLeft(1));

            Node<Piece> adjacentNode = this.state.getNode(adjacent);
        
            if (adjacent != null && this.board.emptyPos(pos.backLeft(2)) && adjacent.getType() != piece.getType()){

                if (!nodePiece.searchAdjacent(adjacentNode)) {

                    return true;
                    
                }

            }

        }

        return false;

    }

    /**
     * Metodo para agregar un adyacente a una pieza seleccionada.
     * 
     * Es decir la pieza seleccionada pasa a relacionarse de manera dirijida con la pieza adyacente.
     * 
     * @param piece pieza seleccionada a la cual se le va a añadir su adyacente.
     * @param nodePiece nodo de la pieza seleccionada perteneciente al grafo. 
     * @param pos posicion supuesta, no necesariamente real, en la cual se encuentra la pieza seleccionada.
     * Es decir la posicion dada no necesariamente representa a la posicion acutal en el tablero
     * de la pieza seleccionada, sino una supuesta posicion a la cual llego tras haber comido a sus piezas adyacentes.
     * @param flag bandera de tipo entera que en que direccion diagonal esta la ficha a añadir.
     * 1 - indica que se busca el adyacente en diagonal hacia arriba a la derecha.
     * 2 - indica que se busca el adyacente en diagonal hacia arriba a la izquierda.
     * 3 - indica que se busca el adyacente en diagonal hacia abajo a la derecha.
     * 4 - indica que se busca el adyacente en diagonal hacia abajo a la izquierda.
     */
    private void Add(Piece piece, Node<Piece> nodePiece, Position pos, int flag){

        /**
         * Se verifica la direccion o posicion de la ficha a añadir como adyacente.
         */
        if (flag == 1) {

            Node<Piece> newAdjacent = this.state.getNode(this.board.getPiece(pos.upRight(1)));

            nodePiece.newAdjacent(newAdjacent, 0);
            
        }
        else if(flag == 2){

            Node<Piece> newAdjacent = this.state.getNode(this.board.getPiece(pos.upLeft(1)));

            nodePiece.newAdjacent(newAdjacent, 0);

        }
        else if(flag == 3){

            Node<Piece> newAdjacent = this.state.getNode(this.board.getPiece(pos.backRight(1)));

            nodePiece.newAdjacent(newAdjacent, 0);

        }
        else if(flag == 4){

            Node<Piece> newAdjacent = this.state.getNode(this.board.getPiece(pos.backLeft(1)));

            nodePiece.newAdjacent(newAdjacent, 0);


        }

    }

    /**
     * Metodo recursivo para caluclar las fichas adyacentes a una ficha dada.
     * @param piece pieza dada para calcular y añadir sus adyacentes.
     * @param nodePiece nodo de la pieza dada perteneciente al grafo.
     * @param pos posicion actual en el tablero de la ficha dada.
     */
    private void adjacents(Piece piece, Node<Piece> nodePiece, Position pos) {

        /**
         * Se verifica que la posicion en diagonal hacia arriba a la derecha (dos pasos)
         * pertenezca al tablero.
         */
       if (this.board.correctPos(pos.upRight(2))) {
           
            /**
             * Se verifica si en la posicion adyacente se tiene una ficha adyacente a la seleccionada.
             */
            if ( isPossibleAdjacent(piece, nodePiece, pos, 1) ){

                /**
                 * Suponiendo que se cumplen las condiciones para que la ficha sea adyacente
                 * se añade como adyacente a la ficha seleccionada, es decir, se crea la relacion 
                 * de adyacencia en el grafo.
                 */
                Add(piece, nodePiece, pos, 1);
                        
                /**
                 * Llamada recursiva, se "mueve" la ficha a una posicion correcta
                 * suponiendo que la misma se comio a su adyacente.
                 * 
                 * La ficha nunca es movida, es decir, nunca se modifica la posicion de la ficha
                 * solamente se supone que la misma se va a mover ahi una vez que tenga la posibilidad
                 * de comer a la ficha adyacente, esto se hace para crear todo un camino posible 
                 * que permita, en un solo turno, comer o eliminar a todas las fichas posibles
                 * dada su posicion actual en el tablero.
                 */
                adjacents(piece, nodePiece, pos.upRight(2));
    
            }
         
        }
                
        if (this.board.correctPos(pos.upLeft(2)) ) {

            if ( isPossibleAdjacent(piece, nodePiece, pos, 2) ){

                Add(piece, nodePiece, pos, 2);
                        
                adjacents(piece, nodePiece, pos.upLeft(2));
    
            }
            
        }

        if (this.board.correctPos(pos.backRight(2)) ) {

            if ( isPossibleAdjacent(piece, nodePiece, pos, 3) ){

                Add(piece, nodePiece, pos, 3);
                        
                adjacents(piece, nodePiece, pos.backRight(2));
    
            }
            
        }
            
        if (this.board.correctPos(pos.backLeft(2))){

            if ( isPossibleAdjacent(piece, nodePiece, pos, 4) ){

                Add(piece, nodePiece, pos, 4);
                        
                adjacents(piece, nodePiece, pos.backLeft(2));
    

            }
        
        }
        
    }
    

    /**
     * Metodo que resuelve lo especificado en el ejercicio.
     * 
     * Indica si es posible capturar todas las piezas negras en un solo turno
     * dando como parametro una lista de piezas blanclas y una lista de piezas negras 
     * en las cuales, cada pieza contiene su posicion actual en el tablero
     * y su tipo, es decir, si es es negra o blanca.
     * @param size tamaño del tablero del juego.
     * @param whites lista de piezas blancas.
     * @param blacks lista de piezas negras.
     * @return true si existe al menos una pieza blanca tal que pueda eliminar en un solo turno
     * a todas las piezas negras existentes en el tablero si esto no se da entonces false
     */
    public static boolean eatAllBlacks(int size, ArrayList<Piece> whites, ArrayList<Piece> blacks){

        /**
         * Se crea el juego.
         */
        Game game = new Game(size, whites, blacks);

        /**
         * Se verifica lo expresado por el enunciado.
         */
        Piece piece = winWay(game);

        if (piece != null) {

            return true;
            
        }

        return false;

    }

    /**
     * Mismo metodo exacto que el anterior con la diferencia que retorna un string
     * indicando la ficha ganadora si esta existe junto con una pantalla de win
     * o una pantalla de lose si no no existe ficha ganadora.
     * @return a win or lose screen en formato de string.
     */
    public static String eatAllBlackString(int size, ArrayList<Piece> whites, ArrayList<Piece> blacks){

        /**
         * Se crea el juego.
         */
        Game game = new Game(size, whites, blacks);

        Piece piece = winWay(game);

        if (piece != null) { //Existe ficha ganadora.

            /**
             * Busca su camino ganador.
             */
            ArrayList<Position> wayPositions = wayForm(game, game.getSate().getNode(piece));
            /**
             * Retorna la pantalla de win.
             */
            return winScreen(piece, wayPositions);
            
        }

        return loseScreen();

    }
    
    /**
     * Metodo auxiliar que indica si para una ficha blanca dada existe un camino en el cual
     * pueda eliminar a todas las fichas negras del tablero y asi ganar la partida.
     *
     * @param game juego actual 
     * @return la pieza ganadora si esta existe sino null.
     */
    private static Piece winWay(Game game){

        /**
         * Iteracion de todos los nodos (fichas) pertenecientes al grafo.
         */
        for (Map.Entry<Integer, Node <Piece> > entry: game.getSate().getHashMapNodes().entrySet()) {

            Node<Piece> piece = entry.getValue();

            if (piece.getInfo().getType() == 'w') { //Si el nodo seleccionado es una ficha blanca

                /**
                 * Por el funcionamiento del algoritmo 
                 * una pieza es adyacente si es de distinto tipo
                 * esto asegura que todos los adyacentes de una ficha blanca
                 * sean fichas negras todas distintas entre si.
                 * 
                 * Dicho esto, una ficha blanca va a poder eliminar, en un solo turno,
                 * a todas las fichas negras en el tablero si y solo si la cantidad de adyacentes
                 * de la misma es igual a la cantidad de fichas negras actualmente en el tablero.
                 */
                if (piece.getCantAdjacents() == game.getBoard().getCantBlack()) {
                    
                    return piece.getInfo();

                }
                
            }

        }

        return null;

    }

    /**
     * Metodo que agrupa en una lista, las posiciones de las piezas adyacentes a una pieza dada.
     * @param game estado del juego actual
     * @param piece nodo perteneciente al grafo representando a una pieza del tablero.
     * @return lista de las posiciones de sus piezas adyacentes
     */
    private static ArrayList<Position> wayForm(Game game, Node<Piece> piece){

        ArrayList<Position> wayPos = new ArrayList<Position>();

        for (int i = 0; i < piece.getAdjacents().size(); i++) {

            Piece adjacent = piece.getAdjacents().get(i).getDest().getInfo();

            wayPos.add(adjacent.getPos());
            
        }

        return wayPos;

    }

    /**
     * Pantalla de ficha ganadora.
     * @param piece ficha ganadora.
     * @return stringBuilder.
     */
    private static String winScreen(Piece piece, ArrayList<Position> wayPositions){

        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        sb.append("(⌐■_■)").append(" Congratulations! you win! ").append("(⌐■_■)")
        .append("\n\n").append("").append(" The winner piece is: ").append(piece.toString())
        .append("\n\n Thanks for playing ").append("(/•-•)/");

        sb.append("\n\n");

        sb.append(" The list of positions of the black pieces that can be eaten are: \n");

        for (int i = 0; i < wayPositions.size(); i++) {

            if (i % 3 == 0) {

                sb.append("\n");
                
            }

            if (i != wayPositions.size() - 1) {
                
                sb.append(" ").append(wayPositions.get(i).toString()).append(", ");

            }
            else{

                sb.append(" ").append(wayPositions.get(i).toString()).append("\n");

            }

        }

        return sb.toString();

    }

    /**
     * Pantalla en caso de que no exista ficha ganadora.
     * @return stringBuilder.
     */
    private static String loseScreen(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        sb.append(" There is no winning piece ").append("\n\n")
        .append(" Try again please... Good luck!");

        sb.append("\n");

        return sb.toString();

    }

}