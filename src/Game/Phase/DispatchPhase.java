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

/**
 * This phase contains a single "dispatch" action
 * Which is a player putting a single Soldier in an empty territory to invade it
 */
public class DispatchPhase extends Phase {

    public DispatchPhase(Player player) {
        super(GamePhase.DISPATCH, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        // Handle a click of the player on a country
        // If a country is clicked, check that it is empty
        // If it is, deploy a troop to it, assign it to the current player, decrement the player's reinforcment count
        // And go to the next phase

        Input input = gameContainer.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Point mouse = new Point(input.getMouseX(), input.getMouseY());
            Territory territory = World.getTerritoryAt(mouse);
            if (territory != null) {
                if(territory.getUnits().size() == 0) {
                    territory.setPlayer(this.getPlayer());
                    Soldier unit = new Soldier(this.getPlayer(), territory);
                    territory.getUnits().add(unit);
                    this.getPlayer().getTerritories().add(territory);
                    this.getPlayer().decrementReinforcmentCount();
                    World.goToNextPhase();
                } else {
                    // @TODO Territory already occuped
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) {
        super.render(gameContainer, slickGame, graphics);
    }
}
