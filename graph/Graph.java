package graph;

import java.util.HashMap;
import java.util.Iterator;

import graph.Edge;
import graph.InexistNodeException;
import graph.NotConnectedException;

/**
 * Clase para crear grafos dirigidos
 * @param <T> que extiende a la clase Comparable
 * @author Fernandez Lorio Franco. DNI: 41088241.
 */
public class Graph<T>{

    /**
     * HashMap usado para mapear los nodos.
     */
    private HashMap< Integer , Node<T> > nodes;

    /**
     * Constructor vacio de clase
     */
    public Graph(){
        this.nodes = new HashMap< Integer, Node<T> >();
    }

    /**
     * Constructor de clase que agrega un primer nodo
     * @param info informacion a agregar en el primer nodo
     */
    public Graph(T info){
        this.nodes = new HashMap< Integer, Node<T> >();
        insertNode(info);
    }

    /**
     * @return el hashMap que mapea a los nodos del grafo
     */
    public HashMap< Integer , Node<T> > getHashMapNodes(){
        return this.nodes;
    }

    /**
     * Recupera un nodo buscando por su informacion
     * @param info del nodo a recuperar
     * @return nodo con la informacion coincidente
     */
    public Node<T> getNode(T info){

        for (Object object : this.nodes.values()) {

            Node<T> node = (Node<T>) object;

            if(node.getInfo().equals(info)){
                
                return node;

            }
            
        }

        return null;

    }

    /**
     * Modifica la informacion del nodo
     * @param info informacion actual la cual se usa para encontrar el nodo
     * @param newInfo nueva informacion a almacenar en el nodo
     */
    public void modifyNode(T info, T newInfo) throws InexistNodeException{

        Node<T> node = getNode(info);

        if (node == null) {
            throw new InexistNodeException("inexist node");
        }

        node.setInfo(newInfo);

    }

    /**
     * Se inserta un nodo en el grafo
     * @param info informacion a almacenar en el nodo a crear
     */
    public void insertNode(T info){

        Node<T> node = new Node(info);

        this.nodes.put(node.hasCode(), node);

    }

    /**
     * Remueve un nodo del grafo
     * @param info informacion del nodo a remover, se utiliza para encontrar el nodo
     * @throws InexistNodeException si el nodo no existe en el grafo entonces se tira una excepcion
     */
    public void removeNode(T info) throws InexistNodeException{

        Node<T> node = getNode(info);

        if (node == null) {
            throw new InexistNodeException("inexist node");
        }

        this.nodes.remove(node.hasCode());
        
    }

    /**
     * Busca un nodo en el grafo a traves de su informacion
     * @param info informacion para encontrar el nodo
     * @return true si el nodo existe en el grafo sino flase
     */
    public boolean searchNode(T info){

        Node<T> node = getNode(info);

        return this.nodes.containsKey(node.hasCode());

    }

    /**
     * Conecta dos nodos dados, los mismos se dan a traves de su informacion
     * @param origin informacion del nodo a usar como origen
     * @param destiny informacion del nodo a usar como destino
     * @throws InexistNodeException si alguno de los dos nodos no existe en el grafo se tira excepcion
     */
    public void connectNode(T origin, T destiny, int weight) throws InexistNodeException{

        Node<T> nodeOrigin = getNode(origin);
        Node<T> nodeDestiny = getNode(destiny);

        if (nodeOrigin == null) {
            throw new InexistNodeException("inexist node origin");
        }

        if (nodeDestiny == null) {
            throw new InexistNodeException("inexist node destiny");
        }

        if (!connectedNode(origin, destiny)) { //si los nodos no estaban conectados previamente
            
            nodeOrigin.newAdjacent(nodeDestiny, weight);
            
        }

    }

    /**
     * Metodo para fijarse si dos nodos estan conectados o no
     * 
     * Puede darse el caso de que los nodos si esten conectados 
     * pero no en el orden en como se pasaron los parametros
     * en ese caso el metodo retornara false
     * 
     * @param origin informacion del nodo origen
     * @param destiny informacion del nodo destino
     * @return true si estan conectados en ese orden sino false
     * @throws InexistNodeException si alguno de los dos nodos no existe en el grafo se tira excepcion
     */
    public boolean connectedNode(T origin, T destiny) throws InexistNodeException{

        Node<T> nodeOrigin = getNode(origin);
        Node<T> nodeDestiny = getNode(destiny);

        if (nodeOrigin == null) {
            throw new InexistNodeException("inexist node origin");
        }

        if (nodeDestiny == null) {
            throw new InexistNodeException("inexist node destiny");
        }

        return nodeOrigin.searchAdjacent(nodeDestiny);

    }

    /**
     * Recupera un arco que relaciona dos nodos dados
     * 
     * Puede darse el caso de que los nodos si esten conectados 
     * pero no en el orden en como se pasaron los parametros
     * en ese caso se toma como que los nodos no estan conectados
     * y se tira una excepcion
     * 
     * @param origin informacion del nodo origen
     * @param destiny informacion del nodo destino
     * @return arco entre ambos nodos
     * @throws NotConnectedException si los nodos no estan conectados se tira una excepcion
     * @throws InexistNodeException si alguno de los nodos no existe en el grafo se tira una excepcion
     */
    public Edge getEdge(T origin, T destiny) throws NotConnectedException, InexistNodeException{

        Node<T> nodeOrigin = getNode(origin);
        Node<T> nodeDestiny = getNode(destiny);

        if (nodeOrigin == null) {
            throw new InexistNodeException("inexist node origin");
        }

        if (nodeDestiny == null) {
            throw new InexistNodeException("inexist node destiny");
        }

        if (!connectedNode(origin, destiny)) {
            throw new NotConnectedException("not connected nodes");
        }

        return nodeOrigin.getEdgeAdjacent(nodeDestiny);

    }

     /**
     * Recupera el peso de un arco que relaciona dos nodos dados
     * 
     * Puede darse el caso de que los nodos si esten conectados 
     * pero no en el orden en como se pasaron los parametros
     * en ese caso se toma como que los nodos no estan conectados
     * y se tira una excepcion
     * 
     * @param origin informacion del nodo origen
     * @param destiny informacion del nodo destino
     * @return peso del arco entre ambos nodos
     * @throws NotConnectedException si los nodos no estan conectados se tira una excepcion
     * @throws InexistNodeException si alguno de los nodos no existe en el grafo se tira una excepcion
     */
    public int getWeight(T origin, T destiny) throws NotConnectedException, InexistNodeException{

        Edge<T> edge = getEdge(origin, destiny);

        return edge.getWeight();
    }

     /**
     * Modifica el peso de un arco que relaciona dos nodos dados
     * 
     * Puede darse el caso de que los nodos si esten conectados 
     * pero no en el orden en como se pasaron los parametros
     * en ese caso se toma como que los nodos no estan conectados 
     * y se tira una excepcion
     * 
     * @param origin informacion del nodo origen
     * @param destiny informacion del nodo destino
     * @param weight nuevo peso a almacenar en el arco que relaciona ambos nodos 
     * @throws NotConnectedException si los nodos no estan conectados se tira una excepcion
     * @throws InexistNodeException si alguno de los nodos no existe en el grafo se tira una excepcion
     */
    public void setWeight(T origin, T destiny, int weight) throws NotConnectedException, InexistNodeException{

        Edge<T> edge = getEdge(origin, destiny);

        edge.setWeight(weight);

    }

    /**
     * Metodo toString definido para la clase grafo. 
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        Iterator< Node<T> > nodes = this.nodes.values().iterator();
        
        while(nodes.hasNext()){
            sb.append(nodes.next().toString()).append("\n \n");
        }

        return sb.toString();

    }

}