package Game.Phase;

import Game.World;
import Graphics.Position;
import Geography.Territory;
import Play.Player;
import Troops.Unit;
import Troops.UnitWithScore;
import Utils.GraphicsUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This phase class is in charge of all the troops moves and attack actions
 * It is the most important phase of the game
 */
public class MovAtkPhase extends Phase {

    // Holds the territory that is left-clicked
    // Must belong to the current user
    private Territory territory1;
    // Holds the target territory
    // Can be owned by the current player (move troops)
    // Or by an opponent (attack)
    private Territory territory2;
    // Holds all thz coords of the troops to be selected
    // Is used to select / deselect troops to move or to attack with
    private ArrayList<int[]> textsCoords;
    // Holds an array of the selected troops to move or to attack with
    private ArrayList<Unit> selectedUnits;

    public MovAtkPhase(Player player) {
        super(GamePhase.MOV_ATK, player);
        this.territory1 = null;
        this.territory2 = null;
        this.textsCoords = null;
        this.selectedUnits = new ArrayList<>();
        // At the beggining of each round, reset the moves of all of the player's troops
        for (Territory territory : player.getTerritories()) {
            for (Unit unit : territory.getUnits()) {
                unit.resetMoves();
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame slickGame, int i) {

        Input input = gameContainer.getInput();
        Point mouse = new Point(input.getMouseX(), input.getMouseY());
        boolean isLeftMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
        boolean isRightMousePreesed = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);

        // Handle the selection of territory1
        // It is the territory to move or attack from
        if (isLeftMousePressed) {
            Territory territorySelected = World.getTerritoryAt(mouse);
            if (territorySelected != null && territorySelected.getPlayer() == this.getPlayer()) {
                if (territorySelected == this.territory1) {
                    // If the current territory1 is clicked again, deselect it and clear thhe selected units
                    this.territory1 = null;
                    this.selectedUnits = new ArrayList<>();
                } else {
                    this.territory1 = territorySelected;
                    // If a new territory1 is selected, clear the selected units
                    this.selectedUnits = new ArrayList<>();
                }
            }
        }

        // Handle the selection of territory2
        // It is the territory to move to, or to attack
        if(isRightMousePreesed) {
            Territory territorySelected2 = World.getTerritoryAt(mouse);
            if (territorySelected2 != null) {
                if (territorySelected2 == this.territory2) {
                    // Right-click the same country twice to deselect it
                    this.territory2 = null;
                } else {
                    this.territory2 = territorySelected2;
                }
            }
        }

        // Handle the selection of troops to move, or to attack with
        // A territory1 must be selected first, in order to display the selectable units
        if (this.territory1 != null && this.textsCoords != null && isLeftMousePressed) {
            // Iterate through each displayed unit
            for (int j = 0; j < this.textsCoords.size(); j++) {
                int[] textCoords = this.textsCoords.get(j);
                // Check if the user clicked on the current troop of the loop
                if (GraphicsUtils.inRectangle(textCoords, mouse)) {
                    // If so, toggle it in the selected units array
                    // If it has been selected already, deselect it
                    // Else, add it to the selected units, but only if there is still at least a unit left on territory1
                    Unit unit = this.territory1.getUnits().get(j);
                    if (this.selectedUnits.contains(unit)) {
                        this.selectedUnits.remove(unit);
                    } else if (this.selectedUnits.size() < this.territory1.getUnits().size() - 1) {
                        this.selectedUnits.add(unit);
                    }
                }
            }
        }

        // If the user presses <ENTER>, he is trying to move troops or to attack
        // Check that he selected a territory1, a territory2, and at least one unit
        if (input.isKeyPressed(Input.KEY_ENTER) && this.territory1 != null && this.territory2 != null && this.selectedUnits.size() > 0) {
            // If territory2 belongs to the current user, this is a move
            if (this.territory2.getPlayer() == this.getPlayer()) {
                this.moveTroops();
            } else {
                // Player is trying to attack
                // Check that the two countries are neighbors
                // If so, go to attack mode
                if (territory1.getNeighbors().contains(this.territory2)) {
                    this.attack();
                }
            }
        }

        // If the player presses <BACK>, he is ending his turn. This can happen at any time in the phase
        if (input.isKeyPressed(Input.KEY_BACK)) {
            World.goToNextPhase();
        }

    }

    /**
     * Moves the selected troops from their territory (territory1) to territory2
     * Starts by checking the shortest path and its moves count
     * And moves the troops if they have enough moves
     */
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

    /**
     * @return the amount of moves necessary to go from territory1 to territory2
     * First, it gets the moves count from the troop that has the smallest moves count
     * For example, if the player selected a Soldier with 2 moves left, and a Soldier with 1 move left,
     * It will stop its search after one move
     * If there is no path to go from territory1 to territory2, it will return -1
     * Else, it will return the minimal amount of moves to go to territory2
     */
    private int checkTroops() {
        // If there is an issue with the selection, return -1 now
        if (this.territory1 == null || this.territory2 == null || this.selectedUnits.size() <= 0) return -1;

        // Iterate through each selected unit to find the smallest amount of moves available
        int maxMoves = selectedUnits.get(0).getMoves();
        for (Unit selectedUnit : this.selectedUnits) {
            maxMoves = Math.min(maxMoves, selectedUnit.getMoves());
        }

        // Use the recursive checkTroops function
        // to find the amount of moves of shortest path from territory1 to territory2
        return this.checkTroops(this.territory1, maxMoves);
    }

    /**
     * Computes the minimal amount of moves to go from territory to territory2 recursively
     * First, if maxMoves is 0, then the path finding recursive algorithm could not find a path in this branch
     * Then, it checks its direct neigbors for territory2. If it has territory2 as a neigbot, it returns 1
     * Which means a unit needs 1 move to go from the territory to territory2
     * If it does not have it as a neigbor, it checks this function with his neigbors and passes the response upwards :
     * It gets the shortest path from its neigbors, excluding -1 (which means no path available)
     * If a neigbor has an available path (considering the maxMoves condition), it passes it upwards, after incrementing
     * by one the moves count value
     * If no neighbor finds a path, it passes -1, which means it does not have a path to territory2
     *
     * @param territory the territory to go to this.territory2 from
     * @param maxMoves the maximum amount of moves to go to territory2 from territory
     * @return the minimum amount of moves to go from territory to territory2 or -1 if the amout is larger that maxMoves
     */
    private int checkTroops(Territory territory, int maxMoves) {
        // Stop condition for the recursive function : if there are no moves left, return -1 (no path available)
        if (maxMoves == 0) return -1;
        // If territory has this.territory2 as a neigbor, return 1 (1 move needed to go from one to the other)
        if (territory.getNeighbors().contains(this.territory2)) {
            return 1;
        }

        // Else, check recursively its neigbors
        // Store -1 (no path) by default
        int childrenChecked = -1;
        for (Territory neigbor : territory.getNeighbors()) {

            // Skip every neigbor that is not controlled by the player
            if (neigbor.getPlayer() != territory.getPlayer()) continue;

            // Use the recursive function on the current neighbor, after decrementing maxMoves
            // to take into account territory's path
            int childChecked = this.checkTroops(neigbor, maxMoves-1);
            // If the neigbor found a path
            if (childChecked > 0) {
                // If there was no path found in the previous neigbors, store this value now
                if (childrenChecked == -1) childrenChecked = childChecked;
                // Else, keep the shortest path
                // This is the part that allows this algorithm to always use the shortest path between the territories
                else childrenChecked = Math.min(childrenChecked, childChecked);
            }

        }

        // If a path was found, pass it along, after incrementing the moves counter to take territory's path
        // into account
        // Else, return -1, because no path was found to go from territory to this.territory2
        if (childrenChecked > 0) {
            return childrenChecked+1;
        } else {
            return -1;
        }
    }

    /**
     * This method contains all the logic for an attack and its battle
     */
    private void attack() {
        // First, check that all the environment if fine
        // Both territories are selected and neigbors
        // That there will be at least one troop remaining in the attacking territory
        // And that the attacker does not have more than 3 units attacking
        if (
            this.territory1 == null ||
            this.territory2 == null ||
            !this.territory1.getNeighbors().contains(this.territory2) ||
            this.selectedUnits.size() >= this.territory1.getUnits().size() ||
            this.selectedUnits.size() > 3
        ) return;

        // Then, we check that no attacking troop has 0 moves left
        // If there is, cancel the attack
        for (Unit selectedUnit : this.selectedUnits) {
            if (selectedUnit.getMoves() == 0) return;
        }

        // Get all the attackers and defenders in arrays
        Unit[] attackers = this.selectedUnits.toArray(new Unit[0]);
        Unit[] defenders = this.territory2.getTroopsToDefend();

        // Generate scores for all attackers and defenders

        UnitWithScore[] attackersScores = new UnitWithScore[attackers.length];
        for (int i = 0; i < attackers.length; i++) {
            Unit attacker = attackers[i];
            attackersScores[i] = new UnitWithScore(attacker);
        }

        UnitWithScore[] defendersScores = new UnitWithScore[defenders.length];
        for (int i = 0; i < defenders.length; i++) {
            Unit defender = defenders[i];
            defendersScores[i] = new UnitWithScore(defender);
        }

        // Sort attackers and defenders by highest score
        Arrays.sort(attackersScores);
        Arrays.sort(defendersScores);

        // Display all scores for debug
        System.out.println("Attackers scores");
        for (UnitWithScore attackerScore : attackersScores) {
            System.out.println(attackerScore.toString());
        }
        System.out.println("Defenders scores");
        for (UnitWithScore defenderScore : defendersScores) {
            System.out.println(defenderScore.toString());
        }

        for (int i = 0; i < Math.min(attackersScores.length, defendersScores.length); i++) {
            // If the attacker wins, kill the
            if (attackersScores[i].getScore() > defendersScores[i].getScore()) {
                this.territory2.getUnits().remove(defendersScores[i].getUnit());
            } else {
                this.territory1.getUnits().remove(attackersScores[i].getUnit());
            }
        }

        // Once the battle is done, check if the defending country has any troops left
        if (this.territory2.getUnits().size() == 0) {
            // If so, move attacking troops to it and re-assign the country's owner
            for (Unit attacker : attackers) {
                attacker.setMoves(attacker.getMoves()-1);
            }
            this.territory1.getUnits().removeAll(Arrays.asList(attackers));
            this.territory2.getUnits().addAll(Arrays.asList(attackers));
            this.territory2.setPlayer(this.getPlayer());
            this.territory1 = null;
            this.territory2 = null;
        }

        this.selectedUnits = new ArrayList<>();

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
