package Play;

import java.util.ArrayList;

public class Game {


    private ArrayList<Player> players = new ArrayList<>();

    public Game(){

    }

    public Game(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }
}
