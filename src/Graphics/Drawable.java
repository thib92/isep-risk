package Graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Drawable {

    void init(GameContainer gameContainer) throws SlickException;
    void update(GameContainer gameContainer, int i) throws SlickException;
    void render(GameContainer gameContainer, Graphics graphics) throws SlickException;

}
