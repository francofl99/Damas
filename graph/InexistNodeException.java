package graph;

/**
 * Excepcion usada para expresar que un un nodo no existe en el grafo.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class InexistNodeException extends Exception{

    public InexistNodeException(){

        super();

    }

    public InexistNodeException(String string){

        super(string);

    }

}