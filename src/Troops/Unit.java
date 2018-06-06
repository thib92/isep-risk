package Troops;

import Geography.Territory;
import Play.Player;
import Utils.PowerRange;

public abstract class Unit {

    private int cost;
    private PowerRange powerRange;
    private int attack;
    private int defense;
    private int maxMoves;
    private int moves;
    private TroopType troupType;

    private Player player;
    private Territory territory;

    Unit(TroopType troupType, int cost, PowerRange powerRange, int attack, int defense, int maxMoves, Player player, Territory territory) {
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

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void resetMoves() {
        this.moves = this.maxMoves;
    }

    public Player getPlayer() {
        return player;
    }

    public TroopType getTroupType() {
        return troupType;
    }

    public Territory getTerritory() {
        return territory;
    }

}