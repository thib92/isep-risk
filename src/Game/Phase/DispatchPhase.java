package Game.Phase;

import Game.World;
import Play.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class DispatchPhase extends Phase {

    public DispatchPhase(Player player) {
        super(GamePhase.DISPATCH, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {
        if (gameContainer.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            World.goToNextPhase();
        }
    }
}
