package Views;

import Graphics.Drawable;
import Utils.GraphicsUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class PlayerSelection implements Drawable {

    private int humanAccount = 0;
    private int botAccount = 0;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        GraphicsUtils.drawCenteredText("Sélection des joueurs", graphics, 800);
    }
}
