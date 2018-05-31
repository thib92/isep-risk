import Game.Game;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args) {


        try {
            AppGameContainer app = new AppGameContainer(new Game("Risk"));
            app.setDisplayMode(Game.WIDTH, Game.HEIGHT, false);
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
