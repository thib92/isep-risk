package Game;

import Play.Player;

public class Phase {

    private GamePhase phase;
    private Player player;

    public Phase(GamePhase phase, Player player) {
        this.phase = phase;
        this.player = player;
    }

    public GamePhase getPhase() {
        return phase;
    }

    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
