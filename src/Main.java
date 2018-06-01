import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import Game.SlickGame;

public class Main {

    public static void main(String[] args) {


        try {
            AppGameContainer app = new AppGameContainer(new SlickGame("Risk"));
            app.setDisplayMode(SlickGame.WIDTH, SlickGame.HEIGHT, false);
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
