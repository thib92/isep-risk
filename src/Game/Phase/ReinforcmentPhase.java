package Game.Phase;

import Game.World;
import Geography.Territory;
import Play.Player;
import Troups.Soldier;
import org.newdawn.slick.GameContainer;
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

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Territory territory = World.getTerritoryAt(mouse);
            if (territory != null) {
                if(territory.getPlayer() == this.getPlayer()) {
                    territory.getUnits().add(new Soldier(this.getPlayer(), territory));
                    this.getPlayer().decrementReinforcmentCount();
                    World.goToNextPhase();
                } else {
                    // @TODO Territory not yours
                }
            }
        }

    }
}
