package analyzer.binary;

import analyzer.data.Bee3D;
import javafx.geometry.Point3D;

public class BinaryBee3D extends Bee3D {

    private double value = 0.0;

    private BinaryBee3D brchild;
    private BinaryBee3D blchild;

    public BinaryBee3D(double x, double y, double z, Point3D cartesian) {
        super(x, y, z, cartesian);
        this.value = x + y + z;
    }

    public void addNode(BinaryBee3D bee) {

        if(bee.getValue() >= getValue()) {

            if(brchild == null) {
                brchild = bee;
                return;
            }

            brchild.addNode(bee);
            return;

        }

        if(blchild == null) {
            blchild = bee;
            return;
        }

        blchild.addNode(bee);
        return;

    }

    public BinaryBee3D check(BinaryBee3D bee) {

        if(bee == this)
            return null;

        if(bee.getValue() >= getValue()) {

            if(brchild == null)
                return checkCollision(bee, this) ? this : null;

            return checkCollision(bee, this) ? this : brchild.check(bee);

        }

        if(blchild == null)
            return checkCollision(bee, this) ? this : null;

        return checkCollision(bee, this) ? this : blchild.check(bee);

    }

    public double getValue() {
        return value;
    }

}
