package Troups;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public class Cannon extends Unit {

    public Cannon(Player player, Territory territory) {
        super(TroupType.CANNON, 7, new PowerRange(4, 9), 3, 2, 1, player, territory);
    }

}
