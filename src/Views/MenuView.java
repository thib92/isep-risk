package Views;

import Game.GameScreen;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuView extends BasicGameState {

    private int transparence = 0;
    private boolean up = true;

    private Sound sound;
    private Image bgImage;
    private Image logoImage;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        try {
            this.sound = new Sound("sons/acceuil.ogg");
            // @TODO re-enable sound
            //this.sound.loop();
            this.bgImage = new Image("images/bg_menu.jpg");
            this.logoImage = new Image("images/logo.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {

        // Text blink
        if (this.up) {
            this.transparence += 4;
            if (this.transparence == 280) {
                this.up = false;
            }
        } else {
            this.transparence -= 4;
            if (this.transparence == 0) {
                this.up = true;
            }
        }

        Input input = gameContainer.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER)) {
            slickGame.enterState(GameScreen.PLAYER_SELECTION.getId());
            try {
                Sound gunShot = new Sound("sons/gunshot.ogg");
                gunShot.play();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {
        graphics.drawImage(this.bgImage, -100, 0);
        graphics.drawImage(this.logoImage, 200, 200 );

        graphics.setColor(new Color(0,0,0, this.transparence));
        graphics.fillRect(570, 800, 465, 32);

        graphics.setColor(new Color(255,255,255, this.transparence));
        GraphicsUtils.drawCenteredText("Appuyez sur Entrer pour jouer", graphics, 800);
    }

    @Override
    public int getID() {
        return GameScreen.MENU.getId();
    }
}
