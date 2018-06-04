package Play;

import Geography.Territory;
import org.newdawn.slick.Color;

import java.util.ArrayList;

public class Player {

    private int id;
    private String pseudo;
    private Color color;
    private int reinforcmentCount;
    private ArrayList<Territory> territories;

    public Player(int id, String pseudo, Color color) {
        this.id = id;
        this.pseudo = pseudo;
        this.color = color;
        this.reinforcmentCount = 0;
        this.territories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getReinforcmentCount() {
        return reinforcmentCount;
    }

    public void setReinforcmentCount(int reinforcmentCount) {
        this.reinforcmentCount = reinforcmentCount;
    }

    public void decrementReinforcmentCount(int count) {
        this.setReinforcmentCount(this.getReinforcmentCount() - count);
    }

    public void decrementReinforcmentCount() {
        this.decrementReinforcmentCount(1);
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }
}
