package Views;

import Game.GameScreen;
import Game.Phase.MovAtkPhase;
import Game.SlickGame;
import Game.World;
import Geography.Territory;
import Graphics.Position;
import Play.Player;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class MapView extends BasicGameState {


    private Image map;
    private Image fond;

    public MapView() {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        try {
            this.map = new Image("images/map.png");
            this.fond = new Image("images/fond-carte.jpg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {
        World.getPhase().update(gameContainer, slickGame, i);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {

        this.fond.draw(0, 0, SlickGame.WIDTH, SlickGame.HEIGHT);
        graphics.drawImage(this.map, 0, 0);

        World.getTerritories().forEach(territory -> {
            if (territory.getBoundary() != null && territory.getBoundary().getPointCount() != 0) {
                //graphics.setColor(Color.white);
                //graphics.draw(territory.getBoundary());
                territory.drawTroops(graphics);
            }
        });


        /* *** */
        /* HUD */
        /* *** */

        World.getPhase().render(gameContainer, slickGame, graphics);

    }

    @Override
    public int getID() {
        return GameScreen.MAP.getId();
    }

}
