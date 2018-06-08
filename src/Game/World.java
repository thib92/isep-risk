package Game;

import Game.Phase.*;
import Geography.Region;
import Geography.Territory;
import Play.Player;

import org.json.JSONArray;
import org.json.JSONObject;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class World {

    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Territory> territories = new ArrayList<>();
    private static ArrayList<Region> regions = new ArrayList<>();

    private static Phase phase;

    public static void initialize(ArrayList<Player> players) throws IOException {

        /* ******* */
        /* PLAYERS */
        /* ******* */

        World.players = players;

        // HashMap of amount of reinforcment troops, based on the amount of players
        HashMap<Integer, Integer> reinforcmentCounts = new HashMap<>();
        //reinforcmentCounts.put(2, 40);
        //reinforcmentCounts.put(3, 35);
        //reinforcmentCounts.put(4, 30);
        //reinforcmentCounts.put(5, 25);
        //reinforcmentCounts.put(6, 20);

        // TEST VALUES FOR SHORTER TESTS
        reinforcmentCounts.put(2, 25);
        reinforcmentCounts.put(3, 16);
        reinforcmentCounts.put(4, 12);
        reinforcmentCounts.put(5, 10);
        reinforcmentCounts.put(6, 8);

        int reinforcmentCount = reinforcmentCounts.get(players.size());

        for (Player player : World.players) {
            player.setReinforcmentCount(reinforcmentCount);
        }


        /* *********** */
        /* TERRITORIES */
        /* *********** */

        BufferedReader br = new BufferedReader(new FileReader("config/territories.json"));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

        JSONArray allTerritoriesJson = new JSONArray(json.toString());
        ArrayList<Territory> territories = new ArrayList<>();

        for (int i = 0; i < allTerritoriesJson.length(); i++) {
            JSONObject territoryJson = allTerritoriesJson.getJSONObject(i);
            Territory territory = new Territory(territoryJson.getInt("id"), territoryJson.getString("name"));
            if(territoryJson.has("boundaries")) {
                JSONArray boundariesJson = territoryJson.getJSONArray("boundaries");
                for (int j = 0; j < boundariesJson.length(); j++) {
                    territory.getBoundary().addPoint(boundariesJson.getJSONArray(j).getInt(0), boundariesJson.getJSONArray(j).getInt(1));
                }
            }
            if(territoryJson.has("center")) {
                JSONArray centerJson = territoryJson.getJSONArray("center");
                territory.setCenter(new Point(centerJson.getInt(0), centerJson.getInt(1)));
            }
            territories.add(territory);
        }

        for (int i = 0; i < territories.size(); i++) {
            JSONArray neighborsJson = allTerritoriesJson.getJSONObject(i).getJSONArray("neighbors");
            for (int j = 0; j < neighborsJson.length(); j++) {
                territories.get(i).getNeighbors().add(territories.get(neighborsJson.getInt(j)));
            }
        }

        World.territories = territories;


        /* ***** */
        /* PHASE */
        /* ***** */

        World.phase = new DispatchPhase(World.players.get(0));

    }



    /**
     * Computes the next phase to be played, and returns it
     * @return the next phase to be played, containing the phase type and the player playing it
     */
    public static Phase getNextPhase() {
        switch (World.phase.getPhaseType()) {
            case RECEP_MISSION:
                // If all players have seen their mission, go to Dispatch phase
                // Else, show their mission to the next player
                return World.hasNextPlayer() ?
                    new DispatchPhase(World.getPlayers().get(0)) :
                    new ReceptMissionPhase(World.getPlayers().get(World.getPhase().getPlayer().getId()+1));
            case DISPATCH:
                // If all territories completed, go to Reinforcment phase with first player
                // Else, go to Dispatch phase for the next player
                return World.allTerritoriesCompleted() ?
                    new ReinforcmentPhase(World.getPlayers().get(0)) :
                    new DispatchPhase(World.nextPlayerCircle());
            case REINFORCE:
                // The next player to play is the one with the most units left to deploy
                // It should just be the next player circle, but may change in special circumstances
                Player nextPlayer = null;
                for (Player player : World.getPlayers()) {
                    if(player.getReinforcmentCount() <= 0) continue;
                    if (nextPlayer == null) nextPlayer = player;
                    else if (nextPlayer.getReinforcmentCount() < player.getReinforcmentCount()) nextPlayer = player;
                }

                // If no player is found, then every unit has been deployed by every player. Go to NEWTROOPS phase
                if(nextPlayer == null) return new NewTroopsPhase(World.getPlayers().get(0));
                // Else, return a REINFORCE phase with the next player
                return new ReinforcmentPhase(nextPlayer);
            case NEWTROOPS:
                return new MovAtkPhase(World.getPhase().getPlayer());
            case MOV_ATK:
                return new NewTroopsPhase(World.nextPlayerCircle());
            default:
                return World.phase;
        }
    }

    /**
     * Goes to the next phase to be played
     * First check if a player has won
     * Else, just go to the next phase
     */
    public static void goToNextPhase() {
        boolean winner = true;
        Player player = World.territories.get(0).getPlayer();
        for (Territory territory : World.territories) {
            if (territory.getPlayer() != player) {
                winner = false;
                break;
            };
        }
        if (winner) {
            System.out.println("Winner is player " + player.getColor());
        } else {
            World.phase = World.getNextPhase();
        }
    }

    /**
     * Returns the next player in a round circle way, starting from the current phase's player
     * @return Player
     */
    private static Player nextPlayerCircle() {
        return World.hasNextPlayer() ?
            World.getPlayers().get(0) :
            World.getPlayers().get(World.getPhase().getPlayer().getId()+1);
    }

    /**
     * Returns if the player playing the current phase is the last one on the list
     * @return
     */
    private static boolean hasNextPlayer() {
        return World.getPhase().getPlayer().getId() == World.getPlayers().size()-1;
    }

    public static Territory getTerritoryAt(Point point) {
        for (Territory territory : World.getTerritories()) {
            if (territory.getBoundary() != null && territory.getBoundary().getPointCount() != 0) {
                if (territory.getBoundary().contains(point)) {
                    return territory;
                }
            }
        }
        return null;
    }

    private static boolean allTerritoriesCompleted() {
        for (Territory territory : World.territories) {
            if(territory.getUnits().size() == 0) {
                 return false;
            }
        }
        return true;
    }

    public static Territory getTerritoryCliked(Input input) {
        Point mouse = new Point(input.getMouseX(), input.getMouseY());
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            return World.getTerritoryAt(mouse);
        }
        return null;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        World.players = players;
    }

    public static ArrayList<Territory> getTerritories() {
        return territories;
    }

    public static void setTerritories(ArrayList<Territory> territories) {
        World.territories = territories;
    }

    public static ArrayList<Region> getRegions() {
        return regions;
    }

    public static void setRegions(ArrayList<Region> regions) {
        World.regions = regions;
    }

    public static Phase getPhase() {
        return phase;
    }

    public static void setPhase(Phase phase) {
        World.phase = phase;
    }
}
