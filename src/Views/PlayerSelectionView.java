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
import java.util.HashMap;

public class PlayerSelectionView extends BasicGameState {

    private int currentPlayerCount = 2;


    private String[] colorRefs = new String[] {
        "rouge", "bleu", "jaune", "cyan", "vert", "gris"
    };
    private HashMap<String, Color> colors;

    private float[] pointsD = new float[]{1000, 400, 1100, 450, 1000, 500};
    private float[] pointsG = new float[]{300, 400, 200, 450, 300, 500};

    private Polygon triangleD = new Polygon(pointsD);
    private Polygon triangleG = new Polygon(pointsG);

    public PlayerSelectionView() {
        this.colors = new HashMap<>();
        this.colors.put("rouge", Color.red);
        this.colors.put("bleu", Color.blue);
        this.colors.put("jaune", Color.yellow);
        this.colors.put("cyan", Color.cyan);
        this.colors.put("vert", Color.green);
        this.colors.put("gris", Color.darkGray);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        this.currentPlayerCount = 2;
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {


        Input input = gameContainer.getInput();

        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

            int x = input.getMouseX();
            int y = input.getMouseY();

            if (x > 200 && x < 300 && y > 400 && y < 500) {

                if (this.currentPlayerCount > 2 ) {
                    this.currentPlayerCount--;
                }

            }
            else if(x > 1000 && x < 1100 && y > 400 && y < 500) {

                if (this.currentPlayerCount < 6) {
                    this.currentPlayerCount++;
                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ENTER)){

            ArrayList<Player> players = new ArrayList<>();

            for (int j = 0; j < this.currentPlayerCount; j++){
                Color color = this.colors.get(this.colorRefs[j]);
                String pseudo = "Joueur " + this.colorRefs[j];
                Player player = new Player(j, pseudo, color);
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
        GraphicsUtils.drawCenteredText(String.valueOf(this.currentPlayerCount), graphics, 433);

    }

    @Override
    public int getID() {
        return GameScreen.PLAYER_SELECTION.getId();
    }
}
