package Troupes;

import Utils.PowerRange;

abstract class Unit {

    private int cost;

    private PowerRange powerRange;

    private int attack;

    private int defense;

    private int maxMoves;

    Unit(int cost, PowerRange powerRange, int attack, int defense, int maxMoves) {
        this.cost = cost;
        this.powerRange = powerRange;
        this.attack = attack;
        this.defense = defense;
        this.maxMoves = maxMoves;
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
}