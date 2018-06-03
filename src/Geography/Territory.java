package Geography;

import Graphics.Boundary;

import java.util.ArrayList;

public class Territory {

    private int id;
    private String name;
    private Boundary boundary;
    private ArrayList<Territory> neighbors;

    public Territory(int id, String name) {
        this.id = id;
        this.name = name;
        this.neighbors = new ArrayList<Territory>();
    }

    public Territory(int id, String name, Boundary boundary) {
        this.id = id;
        this.name = name;
        this.boundary = boundary;
        this.neighbors = new ArrayList<Territory>();
    }

    public Territory(int id, String name, Boundary boundary, ArrayList<Territory> neighbors) {
        this.id = id;
        this.name = name;
        this.boundary = boundary;
        this.neighbors = neighbors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public ArrayList<Territory> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Territory> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        String output = "Territory n° " + String.valueOf(this.id) + " : " + this.name + "\n";
        for (int i = 0; i < this.neighbors.size(); i++) {
            output += neighbors.get(i).getName() + "\n";
        }
        return output;
    }
}
