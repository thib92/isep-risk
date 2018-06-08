package Game.Phase;

/**
 * This enum contains all phases of a battle, and provides a title for it
 */
public enum GamePhase {
    RECEP_MISSION(0, "Reception de mission"),
    DISPATCH(1, "Dispatch des troupes"),
    REINFORCE(2, "Renforcement"),
    NEWTROOPS(3, "New troops"),
    MOV_ATK(4, "Mouvement & attaque");


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
