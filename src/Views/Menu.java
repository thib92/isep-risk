package Views;

import Graphics.Drawable;
import org.newdawn.slick.*;

public class Menu implements Drawable {

    private int transparence = 0;
    private boolean up = true;

    private Sound sound;
    private Image bgImage;
    private TrueTypeFont font;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            this.sound = new Sound("sons/acceuil.ogg");
            this.sound.loop();
            this.bgImage = new Image("images/bg_menu.jpg");
            this.font = new TrueTypeFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 32), true);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

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
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawImage(this.bgImage, -100, 0);
        graphics.setColor(Color.white);

        graphics.setColor(new Color(0,0,0,this.transparence));
        graphics.fillRect(550, 800, 465, 32);

        graphics.setColor(new Color(255,255,255,this.transparence));
        graphics.setFont(this.font);
        graphics.drawString("Appuyer sur Entrer pour jouer",550, 800);
    }
}
