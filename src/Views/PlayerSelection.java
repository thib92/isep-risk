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

    private Game game;

    private String playerCountString = "2";
    private int currentPlayerCount = 2;


    private ArrayList<Color> colors = new ArrayList<>();


    private float[] pointsD = new float[]{1100, 400, 1200, 450, 1100, 500};
    private float[] pointsG = new float[]{500, 400, 400, 450, 500, 500};

    private Polygon triangleD = new Polygon(pointsD);
    private Polygon triangleG = new Polygon(pointsG);

    public PlayerSelection(Game game) {
        this.game = game;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        this.playerCountString = "2";
        this.currentPlayerCount = 2;

        colors.add(Color.red);
        colors.add(Color.blue);
        colors.add(Color.yellow);
        colors.add(Color.cyan);
        colors.add(Color.green);
        colors.add(Color.darkGray);

    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


        this.input = gameContainer.getInput();

        if(this.input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

            int x = this.input.getMouseX();
            int y = this.input.getMouseY();

            this.currentPlayerCount = Integer.parseInt(playerCountString);

            if (x > 400 && x < 500 && y > 400 && y < 500) {

                if (this.currentPlayerCount > 2 ) {
                    this.playerCountString = String.valueOf(Integer.parseInt(playerCountString) - 1);
                }

            }
            else if(x > 1100 && x < 1200 && y > 400 && y < 500) {

                if (this.currentPlayerCount < 6) {
                    this.playerCountString = String.valueOf(Integer.parseInt(this.playerCountString) + 1);

                }
            }
        }

        if (this.input.isKeyPressed(Input.KEY_ENTER)){

            this.currentPlayerCount = Integer.parseInt(this.playerCountString);

            ArrayList<Player> players = new ArrayList<>();

            for (int it = 1; it <= this.currentPlayerCount; it++){

                String pseudo = "Joueur " + it;
                Color color = this.colors.get(i);
                Player player = new Player(pseudo,color);
                players.add(player);
            }

            this.game.setPlayers(players);
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
