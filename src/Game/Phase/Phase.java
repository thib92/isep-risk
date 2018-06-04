package Game.Phase;

import Play.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Phase {

    private GamePhase phaseType;
    private Player player;

    public Phase(GamePhase phase, Player player) {
        this.phaseType = phase;
        this.player = player;
    }

    public abstract void update(GameContainer gameContainer, StateBasedGame slickGame, int i);

    public GamePhase getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(GamePhase phaseType) {
        this.phaseType = phaseType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getTitle() {
        return this.phaseType.getTitle();
    }
}
