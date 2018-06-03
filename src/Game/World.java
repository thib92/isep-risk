package Game;

import Geography.Region;
import Geography.Territory;
import Play.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static ArrayList<Player> players;
    private static ArrayList<Territory> territories;
    private static ArrayList<Region> regions;

    public World(ArrayList<Player> players, ArrayList<Territory> territories, ArrayList<Region> regions) {
        World.players = players;
        World.territories = territories;
        World.regions = regions;
    }

    public static void initialize(ArrayList<Player> players) throws IOException {
        World.players = players;
        BufferedReader br = new BufferedReader(new FileReader("config/territories.json"));
        String json = "";
        String line;
        while ((line = br.readLine()) != null) {
            json += line;
        }

        JSONArray allTerritoriesJson = new JSONArray(json);
        Gson gson = new Gson();
        ArrayList<Territory> territories = new ArrayList<>();

        for (int i = 0; i < allTerritoriesJson.length(); i++) {
            JSONObject territoryJson = allTerritoriesJson.getJSONObject(i);
            Territory territory = new Territory(territoryJson.getInt("id"), territoryJson.getString("name"));
            territories.add(territory);
        }

        for (int i = 0; i < territories.size(); i++) {
            ArrayList<Territory> neighbors = new ArrayList<>();
            JSONArray neighborsJson = allTerritoriesJson.getJSONObject(i).getJSONArray("neighbors");
            for (int j = 0; j < neighborsJson.length(); j++) {
                System.out.println("i" + String.valueOf(i));
                System.out.println("j" + String.valueOf(j));
                territories.get(i).getNeighbors().add(territories.get(neighborsJson.getInt(j)));
            }
        }

        //ArrayList<Territory> territories = gson.fromJson(json, new TypeToken<ArrayList<Territory>>(){}.getType());

        territories.forEach(territory -> {
            System.out.println(territory);
            System.out.println("");
        });
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        World.players = players;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        World.territories = territories;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        World.regions = regions;
    }

}
