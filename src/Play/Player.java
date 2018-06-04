package Play;

import org.newdawn.slick.Color;

public class Player {

    private int id;
    private String pseudo;
    private Color color;
    private int reinforcmentCount;

    public Player(int id, String pseudo, Color color) {
        this.id = id;
        this.pseudo = pseudo;
        this.color = color;
        this.reinforcmentCount = 0;
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
}
