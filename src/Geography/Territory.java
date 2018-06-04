package Geography;

import Graphics.Boundary;
import Play.Player;
import Troups.*;

import Utils.GraphicsUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

public class Territory {

    private int id;
    private String name;
    private Boundary boundary;
    private Point center;
    private ArrayList<Territory> neighbors;
    private Player player;
    private ArrayList<Unit> units;

    public Territory(int id, String name) {
        this.id = id;
        this.name = name;
        this.boundary = new Boundary();
        this.neighbors = new ArrayList<>();
        this.player = null;
        this.units = new ArrayList<>();
    }

    public Territory(int id, String name, Boundary boundary) {
        this.id = id;
        this.name = name;
        this.boundary = boundary;
        this.neighbors = new ArrayList<>();
        this.player = null;
        this.units = new ArrayList<>();
    }

    public Territory(int id, String name, Boundary boundary, ArrayList<Territory> neighbors) {
        this.id = id;
        this.name = name;
        this.boundary = boundary;
        this.neighbors = neighbors;
    }

    public void drawTroops(Graphics graphics) {
        if(this.getPlayer() == null) {
            return;
        }
        int soldiers = 0;
        int horsmen = 0;
        int cannons = 0;
        for (Unit unit : this.units) {
            switch (unit.getTroupType()) {
                case SOLDIER:
                    soldiers++;
                    break;
                case HORSEMAN:
                    horsmen++;
                    break;
                case CANNON:
                    cannons++;
                    break;
            }
        }
        String text = String.format("S%dH%dC%d", soldiers, horsmen, cannons);
        graphics.setColor(this.getPlayer().getColor());
        GraphicsUtils.drawTextWithBackground(text, graphics, this.getCenter().getX(), this.getCenter().getY(), Color.black, 10);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Territory n° " + String.valueOf(this.id) + " : " + this.name + "\n");
        for (Territory neighbor : this.neighbors) {
            output.append(neighbor.getName()).append("\n");
        }
        return output.toString();
    }
}
