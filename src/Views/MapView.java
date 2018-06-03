package Views;

import Game.GameScreen;
import Game.SlickGame;
import Game.World;
import Graphics.Boundary;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {

        this.fond.draw(0, 0, SlickGame.WIDTH, SlickGame.HEIGHT);
        graphics.drawImage(this.map, 0, 0);

        Input input = gameContainer.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        graphics.setColor(Color.black);
        //GraphicsUtils.drawCenteredText(String.valueOf(mouseX), graphics, 20);
        //GraphicsUtils.drawCenteredText(String.valueOf(mouseY), graphics, 50);

        Point mouse = new Point(input.getMouseX(), input.getMouseY());

        /*World.getTerritories().forEach(territory -> {
            if (territory.getBoundary() != null && territory.getBoundary().getPointCount() != 0) {
                graphics.setColor(Color.red);
                graphics.draw(territory.getBoundary());

                if (territory.getBoundary().contains(mouse)) {
                    GraphicsUtils.drawCenteredText(territory.getName(), graphics, 80);
                }
            }
        });*/

        World.getTerritories().forEach(territory -> {
            if (territory.getBoundary().contains(mouse)) {
                graphics.setColor(Color.white);
                GraphicsUtils.drawCenteredText(territory.getName(), graphics, 80);
            }
        });
    }

    @Override
    public int getID() {
        return GameScreen.MAP.getId();
    }

}
