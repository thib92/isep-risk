package Troops;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public class Soldier extends Unit {

    public Soldier(Player player, Territory territory) {
        super(TroopType.SOLDIER, 1, new PowerRange(1, 6),2,1,2, player, territory);
    }

    @Override
    public int compareTo(Unit unit) {
        return unit instanceof Soldier ? 0 : -1;
    }
}
