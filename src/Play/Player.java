package Play;

import org.newdawn.slick.Color;

public class Player {


    public Player(){

    }

    private String pseudo;
    private Color color;

    public Player(String pseudo, Color color) {
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
}
