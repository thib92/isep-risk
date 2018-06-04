package Troups;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public class Soldier extends Unit {

    public Soldier(Player player, Territory territory) {
        super(TroupType.SOLDIER, 1, new PowerRange(1, 6),2,1,2, player, territory);
    }

}
