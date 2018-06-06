package Game.Phase;

import Game.World;
import Graphics.Position;
import Geography.Territory;
import Play.Player;
import Troops.Unit;
import Utils.GraphicsUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class MovAtkPhase extends Phase {

    private Territory territory1;
    private Territory territory2;
    private ArrayList<int[]> textsCoords;
    private ArrayList<Unit> selectedUnits;
    private int[] movAtkButton;

    public MovAtkPhase(Player player) {
        super(GamePhase.MOV_ATK, player);
        this.territory1 = null;
        this.territory2 = null;
        this.textsCoords = null;
        this.movAtkButton = null;
        this.selectedUnits = new ArrayList<>();
        for (Unit unit : player.getTroops()) {
            unit.resetMoves();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());
        boolean isLeftMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
        boolean isRightMousePreesed = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);

        if (isLeftMousePressed) {
            Territory territorySelected = World.getTerritoryAt(mouse);
            if (territorySelected != null && territorySelected.getPlayer() == this.getPlayer()) {
                if (territorySelected == this.territory1) {
                    this.territory1 = null;
                } else {
                    this.territory1 = territorySelected;
                    this.selectedUnits = new ArrayList<>();
                }
            }
        }

        if(isRightMousePreesed) {
            Territory territorySelected2 = World.getTerritoryAt(mouse);
            if (territorySelected2 != null) {
                if (territorySelected2 == this.territory2) {
                    this.territory2 = null;
                } else {
                    this.territory2 = territorySelected2;
                }
            }
        }

        if (this.territory1 != null && this.textsCoords != null && isLeftMousePressed) {
            ArrayList<int[]> textsCoords1 = this.textsCoords;
            for (int j = 0; j < textsCoords1.size(); j++) {
                int[] textCoords = textsCoords1.get(j);
                if (GraphicsUtils.inRectangle(textCoords, mouse)) {
                    Unit unit = this.territory1.getUnits().get(j);
                    if (this.selectedUnits.contains(unit)) {
                        this.selectedUnits.remove(unit);
                    } else if (this.selectedUnits.size() < this.territory1.getUnits().size() - 1) {
                        this.selectedUnits.add(unit);
                    }
                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ENTER) && this.territory1 != null && this.territory2 != null && this.selectedUnits.size() > 0) {
            if (this.territory2.getPlayer() == this.getPlayer()) {
                this.moveTroops();
            } else {
                // Player is trying to attack
                // Check that the two countries are neighbors
                if (territory1.getNeighbors().contains(territory2)) {

                }
            }
        }


        if (input.isKeyPressed(Input.KEY_BACK)) {
            World.goToNextPhase();
        }

    }

    private void moveTroops() {
        int moves = this.checkTroops();
        if (moves > 0) {
            this.territory1.getUnits().removeAll(this.selectedUnits);
            this.territory2.getUnits().addAll(this.selectedUnits);
            for (Unit selectedUnit : this.selectedUnits) {
                selectedUnit.setMoves(selectedUnit.getMoves()-moves);
            }
            this.territory1 = null;
            this.territory2 = null;
            this.selectedUnits = new ArrayList<>();
        }
    }

    private int checkTroops() {
        if (this.territory1 == null || this.territory2 == null || this.selectedUnits.size() <= 0) return -1;
        int maxMoves = selectedUnits.get(0).getMoves();
        for (Unit selectedUnit : this.selectedUnits) {
            maxMoves = Math.min(maxMoves, selectedUnit.getMoves());
        }
        return this.checkTroops(this.territory1, maxMoves);
    }

    private int checkTroops(Territory territory, int maxMoves) {
        if (maxMoves == 0) return -1;
        if (territory.getNeighbors().contains(this.territory2)) {
            return 1;
        }

        int childrenChecked = -1;
        for (Territory neigbor : territory.getNeighbors()) {

            if (neigbor.getPlayer() != territory.getPlayer()) continue;

            int childChecked = this.checkTroops(neigbor, maxMoves-1);
            if (childChecked > 0) {
                if (childrenChecked == -1) childrenChecked = childChecked;
                else childrenChecked = Math.min(childrenChecked, childChecked);
            }

        }
        if (childrenChecked > 0) {
            return childrenChecked+1;
        } else {
            return -1;
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame slickGame, Graphics graphics) {
        super.render(gameContainer, slickGame, graphics);
        this.drawTroopsHud(graphics);
    }

    private void drawTroopsHud(Graphics graphics) {
        if (this.getTerritory1() == null) return;
        ArrayList<String> troops = new ArrayList<>();
        for (Unit unit : this.getTerritory1().getUnits()) {
            troops.add(unit.getTroupType() + " " + unit.getMoves());
        }

        ArrayList<String> troopsSelected = new ArrayList<>();
        for (Unit unit : this.selectedUnits) {
            troopsSelected.add(unit.getTroupType() + " " + unit.getMoves());
        }

        this.textsCoords = GraphicsUtils.drawTextsWithBackground(troops.toArray(new String[0]), graphics, Position.BottomLeft, Color.white, Color.lightGray, 20);
        GraphicsUtils.drawTextsWithBackground(troopsSelected.toArray(new String[0]), graphics, Position.BottomRight, Color.white, Color.lightGray, 20);
    }

    public Territory getTerritory1() {
        return territory1;
    }

    public void setTerritory1(Territory territory1) {
        this.territory1 = territory1;
    }

    public Territory getTerritory2() {
        return territory2;
    }

    public void setTerritory2(Territory territory2) {
        this.territory2 = territory2;
    }
}
