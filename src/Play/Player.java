package Play;

import org.newdawn.slick.Color;

public class Player {

    private String pseudo;
    private Color color;
    private int id;

    public Player(int id, String pseudo, Color color) {
        this.id = id;
        this.pseudo = pseudo;
        this.color = color;
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

    public int getId() {
        return id;
    }
}
