import org.newdawn.slick.*;
import javax.sound.sampled.*;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import org.newdawn.slick.Input;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Partie {

    ArrayList<Joueur> joueurs = new ArrayList<>();

    public Partie(ArrayList<Joueur> joueurs){
        this.joueurs = joueurs;
    }


    public ArrayList<Joueur> nouvellePartie(int nbJoueurs){

        for (int it = 1; it < nbJoueurs; it++){
            Joueur joueur = new Joueur();
            this.joueurs.add(joueur);
        }
        return this.joueurs;
    }


    public void setPseudo(){

    }


}

