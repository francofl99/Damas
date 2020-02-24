package graph;

/**
 * Clase que representa un arco con nodo destino y peso, para un grafo.
 * @param <T> parametro de tipo T para el nodo destino.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Edge<T>{

    /**
     * Nodo destino
     */
    private Node<T> dest;
    
    /**
     * Peso del arco
     */
    private int weight;

    /**
     * Constructor de clase
     * @param newDest nodo destino
     */
    public Edge(Node<T> newDest, int weight){
        this.dest = newDest;
        this.weight = weight;
    }

    /**
     * @return el nodo destino
     */
    public Node<T> getDest(){
        return this.dest;
    }

    /**
     * odifica el nodo destino
     * @param newDest nodo nuevo por el que cambiar el anterior
     */
    public void setDest(Node<T> newDest){
        this.dest = newDest;
    }

    /**
     * @return el peso del arco
     */
    public int getWeight(){
        return this.weight;
    }

    /**
     * Modifica el peso del arco
     * @param weight nuevo peso a almacenar
     */
    public void setWeight(int weight){
        this.weight = weight;
    }

}