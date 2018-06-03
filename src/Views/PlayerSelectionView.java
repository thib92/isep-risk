package Views;

import Play.Player;
import Game.World;
import Game.SlickGame;
import Game.GameScreen;
import Utils.GraphicsUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerSelectionView extends BasicGameState {

    private String playerCountString = "2";
    private int currentPlayerCount = 2;


    private Color[] colors = { Color.red, Color.blue, Color.yellow, Color.cyan, Color.green, Color.darkGray };


    private float[] pointsD = new float[]{1000, 400, 1100, 450, 1000, 500};
    private float[] pointsG = new float[]{300, 400, 200, 450, 300, 500};

    private Polygon triangleD = new Polygon(pointsD);
    private Polygon triangleG = new Polygon(pointsG);

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        this.playerCountString = "2";
        this.currentPlayerCount = 2;
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {


        Input input = gameContainer.getInput();

        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

            int x = input.getMouseX();
            int y = input.getMouseY();

            this.currentPlayerCount = Integer.parseInt(playerCountString);

            if (x > 200 && x < 300 && y > 400 && y < 500) {

                if (this.currentPlayerCount > 2 ) {
                    this.playerCountString = String.valueOf(Integer.parseInt(playerCountString) - 1);
                }

            }
            else if(x > 1000 && x < 1100 && y > 400 && y < 500) {

                if (this.currentPlayerCount < 6) {
                    this.playerCountString = String.valueOf(Integer.parseInt(this.playerCountString) + 1);

                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ENTER)){

            this.currentPlayerCount = Integer.parseInt(this.playerCountString);

            ArrayList<Player> players = new ArrayList<>();

            for (int it = 1; it <= this.currentPlayerCount; it++){

                String pseudo = "Joueur " + it;
                Color color = this.colors[it-1];
                Player player = new Player(pseudo, color);
                players.add(player);
            }

            System.out.println(players);

            // @TODO : Switch to State from StateBasedGame (?)
            //slickGame.getGame().setPlayers(players);
            try {
                World.initialize(players);
            } catch (IOException e) {
                e.printStackTrace();
            }
            slickGame.enterState(GameScreen.MAP.getId());
        }


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        GraphicsUtils.drawCenteredText("Sélection des joueurs", graphics, 800);

        graphics.fillRoundRect((SlickGame.WIDTH - 400)/2,(SlickGame.HEIGHT - 100)/2,400,100, 30);
        graphics.fill(triangleD);
        graphics.fill(triangleG);


        graphics.setColor(Color.black);
        GraphicsUtils.drawCenteredText(playerCountString, graphics, 433);

    }

    @Override
    public int getID() {
        return GameScreen.PLAYER_SELECTION.getId();
    }
}
