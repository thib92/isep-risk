import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args) {


        try {
            AppGameContainer app = new AppGameContainer(new Partie("Risk"));
            app.setDisplayMode(Partie.WIDTH,Partie.HEIGHT, false);
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
