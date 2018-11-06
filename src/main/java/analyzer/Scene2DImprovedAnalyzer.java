package analyzer;

import analyzer.common.IAnalyzer;
import analyzer.data.Bee;
import analyzer.data.Bee3D;
import config.Config;
import console.Console;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import util.AnalyzerUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Scene2DImprovedAnalyzer implements IAnalyzer {

    @Override
    public boolean make(boolean print) {

        final Set<Bee3D> collided = new HashSet<>();
        Bee3D last = null;

        for(Bee common : Console.reader.getBees()) {

            Bee3D bee = new Bee3D(common.getX(), common.getY(), common.getZ(), common.toCartesianPoint3D());

            if(last == null) {

                last = bee;
                continue;

            }

            Bee3D result = last.check(bee);

            if(result != null) {

                collided.add(bee);
                collided.add(result);

            }

            last.setChild(bee);
            bee.setFather(last);

            last = bee;

        }

        System.out.println("[ANALIZADOR]: Se han detectado " + collided.size() + " abejas que pueden colisionar.");
        return true;
    }

    @Override
    public boolean requiresReader() {
        return true;
    }

}
