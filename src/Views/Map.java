package Views;

import Game.GameScreen;
import Play.Game;
import Play.Player;
import Troups.Soldier;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Map extends BasicGameState {


    private Image map;
    private Image map;
    private Game game;
    private Game.SlickGame slickGame;


    public Map() {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame slickGame) throws SlickException {
        this.map = new Image("images/map.png");
        this.game = this.slickGame.getGame();
        ArrayList<Player> playeurs = this.game.getPlayers();
        int playerNumber = playeurs.size();

        switch (playerNumber){
            case 2:
                ArrayList<Soldier> army2_1 = new ArrayList<>();
                ArrayList<Soldier> army2_2 = new ArrayList<>();

                for(int it = 0; it < 40; it++){
                    army2_1.add(new Soldier());
                    army2_2.add(new Soldier());
                }
                break;
            case 3:
                ArrayList<Soldier> army3_1 = new ArrayList<>();
                ArrayList<Soldier>
                break;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) throws SlickException {
        System.out.println("Map Update");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) throws SlickException {
        graphics.fillRect(0, 0, Game.SlickGame.WIDTH, Game.SlickGame.HEIGHT);
        graphics.drawImage(this.map, 0,0);
    }

    @Override
    public int getID() {
        return GameScreen.MAP.getId();
    }

}
