package Game;

public enum GamePhase {
    RECEP_MISSION(0),
    DISPATCH(1),
    RENFORTS(2),
    MOV_ATK(3),
    FIGHT(4);


    private int id;

    GamePhase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
