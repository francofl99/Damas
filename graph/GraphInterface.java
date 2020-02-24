package graph;

/**
 * Interface que describe el comportamiento de un grafo
 * @param <T> tipo de la informacion que se va a almacenar en el grafo.
 * @author Fernandez Lorio Franco DNI: 41088241.
 */
public interface GraphInterface<T>{

    public Node<T> getNode();

    public void modifyNode(T info, T newInfo);

    public void insertNode(T info);

    public void removeNode(T info);

    public boolean searchNode(T info);

    public void connectNode(T origin, T destiny, int weight);

    public boolean connectedNode(T origin, T destiny);

    public int getWeight(T origin, T destiny);

    public void setWeight(T origin, T destiny, int weight);

}