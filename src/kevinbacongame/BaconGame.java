package kevinbacongame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BaconGame {
    private static final String TEST_FILE_PATH = "resources/testfile.txt";
    private static final String MOVIE_DATA_FILE_PATH = "resources/moviedata.txt";

    private final Graph graph;

    public BaconGame(){
        this.graph = new Graph();
    }


    private void loadData() throws IOException{
        try{
            String currentActor = "";
            FileReader fileReader = new FileReader(MOVIE_DATA_FILE_PATH);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while((line = br.readLine()) != null){
                if(line.startsWith("<a>")){
                    currentActor = line.substring(3);
                } else if(line.startsWith("<t>")){
                    graph.add(currentActor, line.substring(3));
                }
            }
            br.close();
            fileReader.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void getBaconNumber(String actor){
        System.out.println(graph.getBaconData(actor));
    }

    public void start(){
        System.out.println("Welcome to the Kevin Bacon Game!");
        System.out.println("Loading data...");
        try {
            loadData();
        }catch (IOException e){
            System.out.println("Data file not found: " + e.getMessage());
        }
    }

    public void command(){
        System.out.println("'q' = quit");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of an actor: ");
        String cmd;
        cmd = scanner.nextLine();
        if(cmd.equals("q")){
            return;
        }

        do{
            if(!graph.containsActor(cmd)){
                System.out.println("Couldn't find actor, please use the following format: [Surname, First name].");
            } else{
                getBaconNumber(cmd);
            }
            System.out.println("'q' = quit");
            System.out.print("Enter the name of an actor: ");
            cmd = scanner.nextLine();
        }while(!cmd.equals("q"));

        System.out.println("Quitting...");

    }




    public static void main(String[] args) {
        BaconGame b = new BaconGame();
        b.start();
        b.command();

    }

}
