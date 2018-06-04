package Game.Phase;

import Game.World;
import Geography.Territory;
import Play.Player;
import Troups.Soldier;
import Troups.Unit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

public class DispatchPhase extends Phase {

    public DispatchPhase(Player player) {
        super(GamePhase.DISPATCH, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Territory territory = World.getTerritoryAt(mouse);
            if (territory != null) {
                if(territory.getUnits().size() == 0) {
                    territory.setPlayer(this.getPlayer());
                    territory.getUnits().add(new Soldier(this.getPlayer(), territory));
                    this.getPlayer().getTerritories().add(territory);
                    this.getPlayer().decrementReinforcmentCount();
                    World.goToNextPhase();
                } else {
                    // @TODO Territory already occuped
                }
            }
        }
    }
}
