package Troops;

public enum TroopType {
    SOLDIER("Soldier"),
    HORSEMAN("Horseman"),
    CANNON("Cannon");

    private String type;

    TroopType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
