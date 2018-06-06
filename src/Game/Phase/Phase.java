package Game.Phase;

import Game.World;
import Geography.Territory;
import Graphics.Position;
import Play.Player;
import Utils.GraphicsUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public abstract class Phase {

    private GamePhase phaseType;
    private Player player;

    public Phase(GamePhase phase, Player player) {
        this.phaseType = phase;
        this.player = player;
    }

    public abstract void update(GameContainer gameContainer, StateBasedGame slickGame, int i);

    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) {
        graphics.setColor(Color.white);

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());

        World.getTerritories().forEach(territory -> {
            if (territory.getBoundary().contains(mouse)) {
                GraphicsUtils.drawCenteredText(territory.getName(), graphics, 80);
            }
        });

        ArrayList<String> topRightHud = new ArrayList<>();

        topRightHud.add(World.getPhase().getPlayer().getPseudo());
        topRightHud.add(World.getPhase().getTitle());
        if (World.getPhase() instanceof MovAtkPhase) {
            Territory territorySelected = ((MovAtkPhase) World.getPhase()).getTerritory1();
            if (territorySelected != null) {
                topRightHud.add(territorySelected.getName());
            }
            Territory territoryTarget = ((MovAtkPhase) World.getPhase()).getTerritory2();
            if(territoryTarget != null) {
                topRightHud.add(territoryTarget.getName());
            }
        }

        GraphicsUtils.drawTexts(topRightHud.toArray(new String[0]), graphics, Position.TopRight, 20);

        // Bottom-left HUD for players
        GraphicsUtils.drawTexts(World.getPlayers().stream().map(player -> player.getPseudo() + " - " + player.getReinforcmentCount()).toArray(String[]::new), graphics, Position.BottomRight, 20);

    }

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
