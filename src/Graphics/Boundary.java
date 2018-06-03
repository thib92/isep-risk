package Graphics;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

public class Boundary extends Polygon {

    /**
     * Return true if the given point is contained inside the boundary.
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     * See : https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
     * @param point The point to check
     * @return true if the point is inside the boundary, false otherwise
     *
     */
    public boolean contains(float[] point) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = this.getPointCount() - 1; i < this.getPointCount(); j = i++) {
            if ((this.getPoint(i)[1] > point[1]) != (this.getPoint(j)[1] > point[1]) &&
                    (point[0] < (this.getPoint(j)[0] - this.getPoint(i)[0]) * (point[1] - this.getPoint(i)[1]) / (this.getPoint(j)[1]-this.getPoint(i)[1]) + this.getPoint(i)[0])) {
                result = !result;
            }
        }
        return result;
    }

    public boolean contains(Point point) {
        return this.contains(new float[] {point.getX(), point.getY()});
    }

}
