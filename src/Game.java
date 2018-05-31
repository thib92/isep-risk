import Views.Menu;
import Views.PlayerSelection;
import org.newdawn.slick.*;

public class Game extends BasicGame {

    static int WIDTH = 1600;
    static int HEIGHT = 900;

    private GameScreen screen;

    /* SCREENS */
    private Menu menu;
    private PlayerSelection playerSelection;

    Game(String title) {
        super(title);
        this.screen = GameScreen.MENU;

        /* SCREENS */
        this.menu = new Menu();
        this.playerSelection = new PlayerSelection();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        switch (this.screen) {
            case MENU:
                this.menu.init(gameContainer);
                break;
            case PLAYER_SELECTION:
                this.playerSelection.init(gameContainer);
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
        }
    }


    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        switch (this.screen) {
            case MENU:
                this.menu.render(gameContainer, graphics);
                break;
            case PLAYER_SELECTION:
                this.playerSelection.render(gameContainer, graphics);
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
    }
}
