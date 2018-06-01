package Geography;

import Graphics.Boundary;

import java.util.ArrayList;

public class Territory {


    private String name;
    private Boundary boundary;
    private ArrayList<Territory> neighbors;

    public Territory(String name, Boundary boundary) {
        this.name = name;
        this.boundary = boundary;
        this.neighbors = new ArrayList<Territory>();
    }

    public Territory(String name, Boundary boundary, ArrayList<Territory> neighbors) {
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
}
