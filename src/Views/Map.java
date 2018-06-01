package Views;

import Game.SlickGame;
import Graphics.Drawable;
import Play.Game;
import Play.Player;
import Troups.Soldier;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Map implements Drawable {


    private Image map;
    private Game game;
    private SlickGame slickGame;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


        this.map = new Image("images/map.png");
        this.game = this.slickGame.getGame();
        ArrayList<Player> playeurs = this.game.getPlayers();
        int playerNumber = playeurs.size();

        switch (playerNumber){
            case 1:
                ArrayList<Soldier> army1 = new ArrayList<>();
                ArrayList<Soldier> army2 = new ArrayList<>();

                for(int it = 0; it < 40; it++){
                    army1.add(new Soldier());
                    army2.add(new Soldier());
                }
                break;
            case 2:
                //ArrayList<Soldier> army1 = new
                break;
        }

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException{

        graphics.fillRect(0, 0, SlickGame.WIDTH, SlickGame.HEIGHT);
        graphics.drawImage(this.map, 0,0);
    }

}
