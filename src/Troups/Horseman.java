package Troups;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public class Horseman extends Unit {

    public Horseman(Player player, Territory territory) {
        super(TroupType.HORSEMAN, 3, new PowerRange(2, 7),1,3,3, player, territory);
    }
}
