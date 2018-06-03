package Game;

import Geography.Region;
import Geography.Territory;
import Play.Player;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Territory> territories = new ArrayList<>();
    private static ArrayList<Region> regions = new ArrayList<>();

    private static Phase phase;

    public static void initialize(ArrayList<Player> players) throws IOException {

        // PLAYERS
        World.players = players;

        // TERRITORIES
        BufferedReader br = new BufferedReader(new FileReader("config/territories.json"));
        String json = "";
        String line;
        while ((line = br.readLine()) != null) {
            json += line;
        }

        JSONArray allTerritoriesJson = new JSONArray(json);
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
            territories.add(territory);
        }

        for (int i = 0; i < territories.size(); i++) {
            JSONArray neighborsJson = allTerritoriesJson.getJSONObject(i).getJSONArray("neighbors");
            for (int j = 0; j < neighborsJson.length(); j++) {
                territories.get(i).getNeighbors().add(territories.get(neighborsJson.getInt(j)));
            }
        }

        World.territories = territories;

        // END TERRITORIES


        // PHASE
        World.phase = new Phase(GamePhase.RECEP_MISSION, World.players.get(0));

    }

    /**
     * Computes the next phase to be played, and returns it
     * @return the next phase to be played, containing the phase type and the player playing it
     */
    public static Phase getNextPhase() {
        switch (World.phase.getPhase()) {
            case RECEP_MISSION:
                // If the last player of queue is playing
                if(World.getPhase().getPlayer().getId() == World.getPlayers().size()) {
                    return new Phase(GamePhase.DISPATCH, World.getPlayers().get(0));
                } else {
                    return new Phase(GamePhase.RECEP_MISSION, World.getPlayers().get(World.getPhase().getPlayer().getId()+1));
                }
            case DISPATCH:
                return new Phase(GamePhase.MOV_ATK, World.getPhase().getPlayer());
            case MOV_ATK:
                if(World.getPhase().getPlayer().getId() == World.getPlayers().size()) {
                    return new Phase(GamePhase.RENFORTS, World.getPlayers().get(0));
                } else {
                    return new Phase(GamePhase.RENFORTS, World.getPlayers().get(World.getPhase().getPlayer().getId()+1));
                }
            default:
                return World.phase;
        }
    }

    /**
     * Goes to the next phase to be played and returns it
     * @return The next phase to be played
     */
    public static Phase nextPhase() {
        return World.phase = World.getNextPhase();
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
