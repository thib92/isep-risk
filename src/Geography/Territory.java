package Geography;

import Graphics.Boundary;

public class Territory {


    private String name;
    private Boundary boundary;

    public Territory(String name, Boundary boundary) {
        this.name = name;
        this.boundary = boundary;
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
}
