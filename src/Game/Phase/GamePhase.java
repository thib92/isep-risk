package Game.Phase;

public enum GamePhase {
    RECEP_MISSION(0, "Reception de mission"),
    DISPATCH(1, "Dispatch des troupes"),
    REINFORCE(2, "Renforcement"),
    RENFORTS(3, "Renforts"),
    MOV_ATK(4, "Mouvement & attaque"),
    FIGHT(5, "Combat");


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
