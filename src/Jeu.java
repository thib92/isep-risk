import org.newdawn.slick.*;


public class Jeu extends BasicGame {


        public static int WIDTH = 1600;  //var static en maj
        public static int HEIGHT = 900;


        int transparence = 0;
        boolean up = true;

        Sound sound;



    public Jeu(String title) {
        super(title);
    }

        @Override
        public void init(GameContainer gameContainer) throws SlickException {

        try {
            sound = new Sound("sons/acceuil.ogg");
            sound.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


        @Override
        public void update(GameContainer gameContainer, int i) throws SlickException {


        if (up) {
            transparence += 4;
            if (transparence == 280) {
                up = false;
            }
        } else {
            transparence -= 4;
            if (transparence == 0) {
                up = true;
            }
        }
            Input input = gameContainer.getInput();

            if (input.isKeyPressed(Input.KEY_ENTER)){
                sound.stop();
            try {
                sound = new Sound("sons/gunshot.ogg");
                sound.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }

    }




        @Override
        public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawImage(new Image("images/bg_menu.jpg"), -100, 0); //image du menu
        graphics.setColor(Color.white);
        graphics.drawImage(new Image("images/logo.png"), 200, 100);

        graphics.setColor(new Color(0,0,0,this.transparence));
        graphics.fillRect(550, 800, 465, 32);

        java.awt.Font font = new java.awt.Font("Helvetica", java.awt.Font.BOLD, 32);
        graphics.setColor(new Color(255,255,255,this.transparence));
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        graphics.setFont(ttf);
        //ttf.drawString(600, 800,"Appuyer sur Entrer pour jouer");
        graphics.drawString("Appuyer sur Entrer pour jouer",550, 800);
    }
}
