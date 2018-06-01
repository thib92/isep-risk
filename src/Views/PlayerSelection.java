package Views;

import Play.Game;
import Play.Player;
import Game.SlickGame;
import Graphics.Drawable;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;

public class PlayerSelection implements Drawable {

    public Input input;

    public Game game = new Game();

    private String playerCountString = "2";
    private int currentPlayerCount = 2;


    private float[] pointsD = new float[]{1100, 400, 1200, 450, 1100, 500};
    private float[] pointsG = new float[]{500, 400, 400, 450, 500, 500};

    private Polygon triangleD = new Polygon(pointsD);
    private Polygon triangleG = new Polygon(pointsG);


    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        playerCountString = "2";
        currentPlayerCount = 2;

    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


        input = gameContainer.getInput();

        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

            int x = input.getMouseX();
            int y = input.getMouseY();

            currentPlayerCount = Integer.parseInt(playerCountString);

            if (x > 400 && x < 500 && y > 400 && y < 500) {

                if (currentPlayerCount > 2 ) {
                    playerCountString = String.valueOf(Integer.parseInt(playerCountString) - 1);
                }

            }
            else if(x > 1100 && x < 1200 && y > 400 && y < 500) {

                if (currentPlayerCount < 6) {
                    playerCountString = String.valueOf(Integer.parseInt(playerCountString) + 1);

                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ENTER)){

            currentPlayerCount = Integer.parseInt(playerCountString);

            ArrayList<Player> players = new ArrayList<>();

            for (int it = 1; it <= currentPlayerCount; it++){

                String pseudo = "Joueur"+it;
                Player player = new Player(pseudo);
                players.add(player);

            }

            game.setPlayers(players);
        }


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        GraphicsUtils.drawCenteredText("Sélection des joueurs", graphics, 800);

        graphics.fillRoundRect((SlickGame.WIDTH - 400)/2,(SlickGame.HEIGHT - 100)/2,400,100, 30);
        graphics.fill(triangleD);
        graphics.fill(triangleG);


        graphics.setColor(Color.black);
        GraphicsUtils.drawCenteredText(playerCountString, graphics, 433);




    }
}
