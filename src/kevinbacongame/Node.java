package kevinbacongame;

import java.util.ArrayList;

public class Node {
    protected String name;
    protected Node previous;
    private boolean visited;
    private final ArrayList<Node> neighbours;

    public Node(String name){
        this.name = name;
        this.visited = false;
        this.neighbours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setVisited(){
        visited = true;
    }
    public boolean isVisited(){
        return visited;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
    public void addNeighbour(Node n){
        neighbours.add(n);
    }


}
