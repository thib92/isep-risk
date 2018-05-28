import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            AppGameContainer app = new AppGameContainer(new Jeu("Risk"));
            app.setDisplayMode(Jeu.WIDTH,Jeu.HEIGHT, false);
            //app.setFullscreen(true); // Mettre en full screen (retirer la ligne au dessus)
            int maxFPS = 60;
            app.setTargetFrameRate(maxFPS);
            app.setShowFPS(true);
            app.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
