package Game.Phase;

import Game.World;
import Geography.Territory;
import Play.Player;
import Troops.Soldier;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

public class ReinforcmentPhase extends Phase {

    public ReinforcmentPhase(Player player) {
        super(GamePhase.REINFORCE, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());

        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Territory territory = World.getTerritoryAt(mouse);
            if (territory != null) {
                if(territory.getPlayer() == this.getPlayer()) {
                    Soldier troop = new Soldier(this.getPlayer(), territory);
                    territory.getUnits().add(troop);
                    this.getPlayer().decrementReinforcmentCount();
                    World.goToNextPhase();
                } else {
                    // @TODO Territory not yours
                }
            }
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) {
        super.render(gameContainer, slickGame, graphics);
    }
}
