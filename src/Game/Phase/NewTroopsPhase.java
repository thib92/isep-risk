package Game.Phase;

import Game.World;
import Geography.Territory;
import Play.Player;
import Troups.Soldier;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

public class NewTroopsPhase extends Phase {

    public NewTroopsPhase(Player player) {
        super(GamePhase.NEWTROOPS, player);

        player.setReinforcmentCount(
            player.getReinforcmentCount() +
            Math.floorDiv(player.getTerritories().size(), 3
            )
        );

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Territory territory = World.getTerritoryAt(mouse);
            if (territory != null && territory.getPlayer() == this.getPlayer()) {
                territory.getUnits().add(new Soldier(this.getPlayer(), territory));
                this.getPlayer().decrementReinforcmentCount();
                if(this.getPlayer().getReinforcmentCount() <= 0) {
                    World.goToNextPhase();
                }
            }
        }
    }
}
