package graph;

/**
 * Excepcion usada para expresar que dos nodos no estan conectados en un grafo.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class NotConnectedException extends Exception{

    public NotConnectedException(){
        super();
    }

    public NotConnectedException(String string){
        super(string);
    }

}