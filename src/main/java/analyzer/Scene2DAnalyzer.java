package analyzer;

import analyzer.common.IAnalyzer;
import analyzer.data.Bee;
import console.Console;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;

import java.io.IOException;
import java.util.*;

public class Scene2DAnalyzer implements IAnalyzer {

    public static final List<Thread> threads = new ArrayList<Thread>();

    @Override
    public boolean make(boolean print) {

        final Group root = new Group();

        final LinkedList<Bee> bees = Console.reader.getBees();

        final Map<Circle, Bee> circles = new HashMap<>();
        final Set<Circle> collided = new HashSet<>();

        for (Bee bee : bees) {

            Point3D point = bee.toCartesianPoint3D();

            Circle circle = new Circle();
            circle.setRadius(100);

            circle.setTranslateX(point.getX());
            circle.setTranslateY(point.getY());

            root.getChildren().add(circle);
            circles.put(circle, bee);

        }

        ALPHA : for(Circle circle : circles.keySet())
            OMEGA : for(Circle cmcircle : circles.keySet()) {

                        if(circle == cmcircle)
                            continue OMEGA;

                        if(checkCollision(circle, cmcircle)) {

                            circles.get(circle).setCollide(true);
                            collided.add(circle);

                            continue ALPHA;

                        }

                    }

        System.out.println("[ANALIZADOR]: Se han detectado " + collided.size() + " abejas que pueden colisionar.");
        return true;
    }

    public boolean checkCollision(Circle circle1, Circle circle2) {

        double xDif = circle1.getTranslateX() - circle2.getTranslateX();
        double yDif = circle1.getTranslateY() - circle2.getTranslateY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        boolean collision = distanceSquared < (100 + 100) * (100 + 100);

        return collision;

    }

    @Override
    public boolean requiresReader() {
        return true;
    }

}
