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

    public static void initialize(ArrayList<Player> players) throws IOException {
        World.players = players;
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

}
