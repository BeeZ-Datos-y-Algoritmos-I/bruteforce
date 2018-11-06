package analyzer;

import analyzer.binary.BinaryBee3D;
import analyzer.common.IAnalyzer;
import analyzer.data.Bee;
import console.Console;

import java.util.HashSet;
import java.util.Set;

public class BinaryTreeAnalyzer implements IAnalyzer {

    @Override
    public boolean make(boolean print) {

        final Set<BinaryBee3D> collided = new HashSet<>();

        BinaryBee3D root = null;

        for(Bee common : Console.reader.getBees()) {

            BinaryBee3D bee = new BinaryBee3D(common.getX(), common.getY(), common.getZ(), common.toCartesianPoint3D());

            if(root == null)
                root = bee;
            else {

                root.addNode(bee);
                BinaryBee3D result = root.check(bee);

                if(result != null) {

                    collided.add(bee);
                    collided.add(result);

                }

            }

        }

        System.out.println("[ANALIZADOR]: Se han detectado " + collided.size() + " abejas que pueden colisionar.");
        return true;
    }

    @Override
    public boolean requiresReader() {
        return true;
    }

}
