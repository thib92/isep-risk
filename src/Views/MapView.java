package Views;

import Game.GameScreen;
import Game.SlickGame;
import Game.World;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class MapView extends BasicGameState {


    private Image map;

    public MapView() {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        try {
            this.map = new Image("images/map.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            World.initialize(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {
        graphics.drawImage(this.map, 0, 0);

        Input input = gameContainer.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        graphics.setColor(Color.white);
        GraphicsUtils.drawCenteredText(String.valueOf(mouseX), graphics, 20);
        GraphicsUtils.drawCenteredText(String.valueOf(mouseY), graphics, 50);
    }

    @Override
    public int getID() {
        return GameScreen.MAP.getId();
    }

}
