package Graphics;

import org.newdawn.slick.geom.Polygon;

public class Boundary extends Polygon {

    /**
     * Return true if the given point is contained inside the boundary.
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     * See : https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
     * @param test The point to check
     * @return true if the point is inside the boundary, false otherwise
     *
     */
    public boolean contains(float[] test) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((this.getPoint(i)[1] > test[1]) != (this.getPoint(j)[1] > test[1]) &&
                    (test[0] < (this.getPoint(j)[0] - this.getPoint(i)[0]) * (test[1] - this.getPoint(i)[1]) / (this.getPoint(j)[1]-this.getPoint(i)[1]) + this.getPoint(i)[0])) {
                result = !result;
            }
        }
        return result;
    }

}
