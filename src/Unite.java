public class Unite {


    private int cout;

    private int[] puissance;

    private int attaque;

    private int defense;

    private int deplacement;

    public Unite(){

    }


    public Unite(int cout, int[] puissance, int attaque, int defense, int deplacement) {
        this.cout = cout;
        this.puissance = puissance;
        this.attaque = attaque;
        this.defense = defense;
        this.deplacement = deplacement;
    }


    public int[] getPuissance() {
        return puissance;
    }

    public void setPuissance(int[] puissance) {
        this.puissance = puissance;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setattaque(int attaque) {
        attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}
