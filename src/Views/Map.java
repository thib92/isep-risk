package Views;

import Graphics.Drawable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map implements Drawable {


    private Image map;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        this.map = new Image("images/map.png");

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException{

        graphics.drawImage(this.map, 0, 0);

    }

}
