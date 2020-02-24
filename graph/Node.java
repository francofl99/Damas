package graph;

import java.util.ArrayList;
import java.util.Iterator;

import graph.Edge;
import graph.InexistNodeException;

/**
 * Clase que representa un nodo.
 * @param <T> informacion de tipo T para el nodo.
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Node<T>{

    /**
     * Campo de informacion de tipo T del nodo
     */
    private T info;
    /**
     * Lista de arcos los cuales cada uno contiene un nodo adyacente a este 
     */
    private ArrayList< Edge<T> > adjacents;

    /**
     * Constructor de clase
     * @param newInfo informacion para el primer nodo
     */
    public Node(T newInfo){
        this.info = newInfo;
        this.adjacents = new ArrayList< Edge<T> >();
    } 

    /**
     * @return el campo info de este nodo
     */
    public T getInfo(){
        return this.info;
    }

    /**
     * Modifica el campo info de este nodo
     * @param newInfo nueva informacion a almacenar
     */
    public void setInfo(T newInfo){
        this.info = newInfo;
    }

    /**
     * @return la lista de arcos que relaciona este nodo con sus adyacentes
     */
    public ArrayList< Edge<T> > getAdjacents(){
        return this.adjacents;
    }

    /**
     * Modifica toda la lista de adyacentes a este nodo
     * @param newAdjacents nueva lista con la cual se va a reemplazar por la anterior
     */
    public void setAdjacents(ArrayList< Edge<T> > newAdjacents){
        this.adjacents = newAdjacents;
    }

    /**
     * @return cantidad de nodos adyacentes a este
     */
    public int getCantAdjacents(){
        return this.adjacents.size();
    }

    /**
     * Inserta un nuevo adyacente
     * @param newAdjacent nuevo nodo adyacente a insertar
     */
    public void newAdjacent(Node<T> newAdjacent, int weight){

        Edge<T> newEdge = new Edge(newAdjacent, weight);

        if (!searchAdjacent(newAdjacent)){

            this.adjacents.add(newEdge);
            
        }

    }

    /**
     * Remueve la relacion de adyacencia con un nodo dado
     * @param adjacent adyacente a remover 
     * @return nodo eliminado
     * @throws InexistNodeException si el nodo no es adyacente de este
     */
    public Node<T> removeAdjacent(Node<T> adjacent) throws InexistNodeException{

        Edge<T> edgeAdjacent = getEdgeAdjacent(adjacent);

        if (edgeAdjacent == null) {
            throw new InexistNodeException("node is not adjacent");
        }

        this.adjacents.remove(edgeAdjacent);

        return edgeAdjacent.getDest();

    }

    /**
     * Recupera el arco entre este nodo y un nodo dado como adyacente
     * @param adjacent nodo adyacente a este 
     * @return arco que relaciona este nodo con el adyacente dado si el mismo existe sino null
     */
    public Edge<T> getEdgeAdjacent(Node<T> adjacent){

        Iterator<Edge <T> > cursor = this.adjacents.iterator();

        while (cursor.hasNext()) {

            Edge<T> edge = cursor.next();

            if(edge.equals(adjacent)){
                return edge;
            }

        }

        return null;
        
    }

    /**
     * Busca si un nodo dado es adyacente 
     * @param adjacent nodo dado
     * @return true si es efectivamente un adyacente sino false
     */
    public boolean searchAdjacent(Node<T> adjacent){

        Iterator< Edge<T> > iter = this.adjacents.iterator();

        while (iter.hasNext()) {

            if(iter.next().getDest().equals(adjacent)){
                return true;
            }

        }

        return false;
        
    }

    /**
     * @return codigo hash para este nodo
     */
    public Integer hasCode(){
        return this.info.hashCode();
    }

    /**
     * Metodo toString para la clase.
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        Iterator< Edge<T> > iter = this.adjacents.iterator();

        sb.append("Node " + this.info + " Destinations: ");

        while (iter.hasNext()) {
            sb.append(iter.next().getDest().getInfo()).append(" ");
        }

        return sb.toString();

    }

}