package Game;

import Play.Game;
import Views.MapView;
import Views.MenuView;
import Views.PlayerSelectionView;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SlickGame extends StateBasedGame {

    public static int WIDTH = 1280;
    public static int HEIGHT = 900;

    private Game game;

    public SlickGame(String title) {
        super(title);
        this.game = new Game();

        this.addState(new MenuView());
        this.addState(new PlayerSelectionView());
        this.addState(new MapView());
        this.enterState(GameScreen.MENU.getId());
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        // @TODO : Handle that modafokin font
        gameContainer.setDefaultFont(
            new TrueTypeFont(
                new java.awt.Font("Helvetica", java.awt.Font.BOLD, 32),
                true
            )
        );
        this.getState(GameScreen.MENU.getId()).init(gameContainer, this);
        this.getState(GameScreen.PLAYER_SELECTION.getId()).init(gameContainer, this);
        this.getState(GameScreen.MAP.getId()).init(gameContainer, this);
    }
}
