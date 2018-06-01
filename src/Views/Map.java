package Views;

import Game.GameScreen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Map extends BasicGameState {


    private Image map;

    public Map() {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        System.out.println("Map Init");
        try {
            this.map = new Image("images/map.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {
        System.out.println("Map Update");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {
        System.out.println("Map Render");
        System.out.println(this.map);
        graphics.drawImage(this.map, 0, 0);
    }

    @Override
    public int getID() {
        return GameScreen.MAP.getId();
    }

}
