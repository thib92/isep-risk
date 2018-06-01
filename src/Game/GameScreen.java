package Game;

public enum GameScreen {
    MENU(0),
    PLAYER_SELECTION(1),
    MAP(2);

    private int id;


    GameScreen(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
