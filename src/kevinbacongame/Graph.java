package kevinbacongame;


import java.util.HashMap;
import java.util.LinkedList;


/**
 * Enkelriktad graf-klass
 */

public class Graph {
    private static final String KEVIN_BACON = "Bacon, Kevin (I)";


    private final HashMap<String, ActorNode> actors;
    private final HashMap<String, Node> movies;

    public Graph() {
        actors = new HashMap<>();
        movies = new HashMap<>();
    }

    /**
     *  Metod som skapar noder utifrån namnet på en skådespelare och namnet på en film
     *  och lägger dom i grafen.
     * @param actor namnet på skådespelaren som skall läggas till i grafen
     * @param movie namnet på filmen som skall läggas till i grafen
     */
    public void add(String actor, String movie) {

        if (!actors.containsKey(actor)) {  // Om skådespelaren inte redan finns -> skapa en ActorNode med dess namn
            ActorNode an = new ActorNode(actor);
            actors.put(actor, an);
        }
        if (!movies.containsKey(movie)) {   // Filmen inte redan finns -> Skapa en Nod med dess namn
            Node mn = new Node(movie);
            movies.put(movie, mn);
        }
        actors.get(actor).addNeighbour(movies.get(movie));  //Lägg till filmen i skådespelarens neighbours
        movies.get(movie).addNeighbour(actors.get(actor)); // Lägg till skådespelaren i filmens neighbours
    }

    /**
     *
     * @param s namnet på skådespelaren vars bacon-nummer ska hittas
     * @return en sträng innehållande skådespelarens bacon-nummer samt vägen mellan denne och Keving Bacon
     */
    public String getBaconData(String s) {
        ActorNode n = actors.get(s);
        if(!n.isSearched()){        // Om skådespelaren tidigare har vart med i en sökning behöver den
                                    // inte sökas igenom igen då den redan har datan som krävs för att hitta en väg.
            BreadthFirstSearch(n);
        }
        n.calculatePath();
        n.calculateBaconNumber();
        return n.toString();
    }


    /**
     * Kollar om grafen innehåller en viss skådespelare med hjälp av hash-mapen actors
     * @param actor namnet på skådespelaren.
     * @return true om den finns, annars false
     */
    public boolean containsActor(String actor) {
        return actors.containsKey(actor);
    }

        /**
    * Bredden först-sökning för att hitta den kortaste vägen från en skådespelare till en annan.
    * @param toActor noden som innehåller den skådespelare till vilken vägen ska hittas
     */

    private void BreadthFirstSearch(Node toActor) {
        ActorNode startNode = actors.get(KEVIN_BACON);
        HashMap<String, Boolean> visited = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(startNode);
        visited.put(startNode.getName(), true);
        while (!queue.isEmpty()) {
            Node current = queue.removeFirst(); //
            if (current == toActor) {
                break;                  //Bryt loopen om den efterfrågade skådespelaren hittas
            }
            for (Node n : current.getNeighbours()) { // Gå igenom alla grannar till den nuvarande noden och lägg till de som inte readan har besökts
                if (!visited.containsKey(n.getName())) {
                    queue.addLast(n);
                    visited.put(n.getName(), true);
                    n.setPrevious(current);         //Sätter den nuvarande noden som föregående nod
                    if(n instanceof ActorNode){
                        ((ActorNode) n).markAsSearched();
                    }
                }
            }
        }
    }


}
