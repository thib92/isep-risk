package Game.Phase;

import Play.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class MovAtkPhase extends Phase {


    public MovAtkPhase(Player player) {
        super(GamePhase.MOV_ATK, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

    }
}
