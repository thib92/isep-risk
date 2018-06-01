package Game;

import Play.Game;
import Graphics.Drawable;
import Views.Map;
import Views.Menu;
import Views.PlayerSelection;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SlickGame extends StateBasedGame {

    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    private Game game;

    public SlickGame(String title) {
        super(title);
        this.game = new Game();

        this.addState(new Menu());
        this.addState(new PlayerSelection());
        this.addState(new Map());
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

    @Override
    public void keyPressed(int key, char c) {
        if(this.getCurrentStateID() == GameScreen.MENU.getId() && key == Input.KEY_ENTER) {
            this.enterState(GameScreen.PLAYER_SELECTION.getId());
            try {
                Sound gunShot = new Sound("sons/gunshot.ogg");
                gunShot.play();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        else if(this.getCurrentStateID() == GameScreen.PLAYER_SELECTION.getId() && key == Input.KEY_ENTER){
            this.enterState(GameScreen.MAP.getId());
        }
    }
}
