package kevinbacongame;

import javax.naming.Name;
import java.util.LinkedList;

public class ActorNode extends Node {


    private final LinkedList<Node> path;
    private int baconNumber;
    private boolean searched;


    public ActorNode(String name) {
        super(name);
        this.baconNumber = 0;
        this.path = new LinkedList<>();
        this.searched = false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("'" + name + "'" + " has a bacon-number of " + baconNumber +
                ", through the following connections: \n");
        for (int i = 0; i < path.size() - 1; i++) {
            s.append("* ").append(path.get(i).name).append(" - ").append(path.get(i + 1).name).append("\n");
        }
        return s.toString();
    }

    public void calculatePath(){
        if(path.size() < 1) {
            calculatePath(this);
        }
    }

    private void calculatePath(Node n) {
        path.add(n);
        if (n.previous != null) {
            calculatePath(n.previous);
        }
    }


    public void calculateBaconNumber(){
        baconNumber = path.size()/2;
    }

    public void markAsSearched(){
        searched = true;
    }

    public boolean isSearched(){
        return searched;
    }



}
