import java.util.ArrayList;

public class Partie {

    private ArrayList<Joueur> joueurs = new ArrayList<>();

    public Partie(int nbJoueurs){
        for (int it = 1; it < nbJoueurs; it++){
            Joueur joueur = new Joueur();
            this.joueurs.add(joueur);
        }
    }


    public void setPseudo(){

    }


}

