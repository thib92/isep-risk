package Game.Phase;

import Play.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class FightPhase extends Phase {
    public FightPhase(Player player) {
        super(GamePhase.FIGHT, player);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

    }
}