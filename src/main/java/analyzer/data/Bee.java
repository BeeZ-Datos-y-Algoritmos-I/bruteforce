package analyzer.data;

import javafx.geometry.Point3D;
import util.CoordUtil;

public class Bee {

    public static final double RADIUS = 100.0;

    private double x, y, z;
    private boolean collide = false;

    private Point3D cartesian;

    public Bee(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.cartesian = CoordUtil.getPointXYZfromLatLongDegress(toPoint3D());

    }

    public Bee(double x, double y, double z, Point3D cartesian) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.cartesian = cartesian;

    }

    public void setCollide(boolean collide) {
        this.collide = collide;
        return;
    }

    public boolean hasCollide() {
        return collide;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getCartesianX() {
        return cartesian.getX();
    }

    public double getCartesianY() {
        return cartesian.getY();
    }

    public double getCartesianZ() {
        return cartesian.getZ();
    }

    public Point3D toPoint3D() {
        return new Point3D(x, y, z);
    }

    public Point3D toCartesianPoint3D() {
        return cartesian;
    }

}
