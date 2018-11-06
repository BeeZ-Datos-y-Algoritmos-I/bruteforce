package analyzer.data;

import javafx.geometry.Point3D;

public class Bee3D extends Bee {

    private Bee3D father;
    private Bee3D child;

    public Bee3D(double x, double y, double z, Point3D cartesian) {
        super(x, y, z, cartesian);
    }

    public Bee3D check(Bee3D bee) {

        if(checkCollision(this, bee))
            return this;

        if(father == null)
            return null;

        return father.check(bee);

    }

    public void setFather(Bee3D father) {
        this.father = father;
    }

    public void setChild(Bee3D child) {
        this.child = child;
    }

    public boolean checkCollision(Bee3D bee1, Bee3D bee2) {

        double xDif = bee1.toCartesianPoint3D().getX() - bee2.toCartesianPoint3D().getX();
        double yDif = bee1.toCartesianPoint3D().getY() - bee2.toCartesianPoint3D().getY();

        double distanceSquared = xDif * xDif + yDif * yDif;
        boolean collision = distanceSquared < (100 + 100) * (100 + 100);

        return collision;

    }

}
