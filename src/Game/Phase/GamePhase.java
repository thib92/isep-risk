package Game.Phase;

public enum GamePhase {
    RECEP_MISSION(0, "Reception de mission"),
    DISPATCH(1, "Dispatch des troupes"),
    RENFORTS(2, "Renforts"),
    MOV_ATK(3, "Mouvement & attaque"),
    FIGHT(4, "Combat");


    private int id;
    private String title;

    GamePhase(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
