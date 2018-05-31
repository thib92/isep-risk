package Game;

import Graphics.Drawable;
import Views.Map;
import Views.Menu;
import Views.PlayerSelection;
import org.newdawn.slick.*;

public class SlickGame extends BasicGame implements Drawable {

    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    private GameScreen screen;

    /* SCREENS */
    private Menu menu;
    private PlayerSelection playerSelection;
    private Map map;

    public SlickGame(String title) {
        super(title);
        this.screen = GameScreen.MENU;

        /* SCREENS */
        this.menu = new Menu();
        this.playerSelection = new PlayerSelection();
        this.map = new Map();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setDefaultFont(
                new TrueTypeFont(
                        new java.awt.Font("Helvetica", java.awt.Font.BOLD, 32),
                        true
                )
        );
        switch (this.screen) {
            case MENU:
                this.menu.init(gameContainer);
                break;
            case PLAYER_SELECTION:
                this.playerSelection.init(gameContainer);
                break;
            case MAP:
                this.map.init(gameContainer);
                break;
        }
    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        switch (this.screen) {
            case MENU:
                this.menu.update(gameContainer, i);
                break;
            case PLAYER_SELECTION:
                this.playerSelection.update(gameContainer, i);
                break;
            case MAP:
                this.map.update(gameContainer, i);
                break;
        }
    }


    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setFont(gameContainer.getDefaultFont());
        switch (this.screen) {
            case MENU:
                this.menu.render(gameContainer, graphics);
                break;
            case PLAYER_SELECTION:
                this.playerSelection.render(gameContainer, graphics);
                break;
            case MAP:
                this.map.render(gameContainer, graphics);
                break;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if(this.screen.equals(GameScreen.MENU) && key == Input.KEY_ENTER) {
            this.screen = GameScreen.PLAYER_SELECTION;
            try {
                Sound gunShot = new Sound("sons/gunshot.ogg");
                gunShot.play();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        else if(this.screen.equals(GameScreen.PLAYER_SELECTION) && key == Input.KEY_ENTER){
            this.screen = GameScreen.MAP;
        }
    }
}
