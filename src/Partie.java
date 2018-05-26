import org.newdawn.slick.*;
import javax.sound.sampled.*;

import java.awt.Font;
import java.io.File;
import java.io.IOException;


public class Partie extends BasicGame {

    public static int WIDTH = 1600;  //var static en maj
    public static int HEIGHT = 900;


    int transparence = 0;
    boolean up = true;


    public Partie(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        try {
            Sound sound = new Sound("sons/acceuil.ogg");
            sound.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


        if (up) {
            transparence += 4;
            if (transparence == 280) {
                up = false;
            }
        } else {
            transparence -= 4;
            if (transparence == 0) {
                up = true;
            }
        }



    }





    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawImage(new Image("images/bg_menu.jpg"), -100, 0); //image du menu
        graphics.setColor(Color.white);
        graphics.drawImage(new Image("images/logo.png"), 200, 100);

        graphics.setColor(new Color(0,0,0,transparence));
        graphics.fillRect(550, 800, 465, 32);

        Font font = new Font("Helvetica", Font.BOLD, 32);
        graphics.setColor(new Color(255,255,255,transparence));
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        graphics.setFont(ttf);
        System.out.println(transparence);
        //ttf.drawString(600, 800,"Appuyer sur Entrer pour jouer");
        graphics.drawString("Appuyer sur Entrer pour jouer",550, 800);
    }



}
