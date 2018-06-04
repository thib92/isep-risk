package Troups;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public abstract class Unit {

    private int cost;
    private PowerRange powerRange;
    private int attack;
    private int defense;
    private int maxMoves;
    private TroupType troupType;

    private Player player;
    private Territory territory;

    Unit(TroupType troupType, int cost, PowerRange powerRange, int attack, int defense, int maxMoves, Player player, Territory territory) {
        this.troupType = troupType;
        this.cost = cost;
        this.powerRange = powerRange;
        this.attack = attack;
        this.defense = defense;
        this.maxMoves = maxMoves;
        this.player = player;
        this.territory = territory;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PowerRange getPowerRange() {
        return powerRange;
    }

    public void setPowerRange(PowerRange powerRange) {
        this.powerRange = powerRange;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    public TroupType getTroupType() {
        return troupType;
    }
}