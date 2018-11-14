package analyzer;

import analyzer.common.IAnalyzer;
import config.Config;
import javafx.geometry.Point3D;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FastAnalyzer implements IAnalyzer {

    public static final LinkedList<String> lines = new LinkedList<String>();

    public static final ThreadLocalRandom current = ThreadLocalRandom.current();
    public static final List<Thread> threads = new ArrayList<Thread>();

    @Override
    public boolean make(boolean print) {

        FileWriter FWRITER = null;
        try {
            FWRITER = new FileWriter(Config.FILES.get("output_path"), false);
        } catch (IOException e) { e.printStackTrace(); }

        final PrintWriter WRITER = new PrintWriter(FWRITER);
        final String PATH = Config.FILES.get("input_path");

        FileReader READER;
        BufferedReader BUFFER;

        try {

            READER = new FileReader(PATH);
            BUFFER = new BufferedReader(READER);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return false;

        }

        try {

            int collision = 0;
            int bees = 0;

            String line;
            while ((line = BUFFER.readLine()) != null)
                lines.add(line);

            COMPLETE : for(int i = 0; i < lines.size(); i++) {

                bees++;

                try {

                    String[] compose = lines.get(i).split(",");
                    Point3D from = new Point3D(Double.valueOf(compose[0]),
                            Double.valueOf(compose[1]),
                            Double.valueOf(compose[2]));

                    SUB : for(int j = 0; j < lines.size(); j++) {

                        if(i == j)
                            continue SUB;

                        String[] ocompose = lines.get(j).split(",");
                        Point3D to = new Point3D(Double.valueOf(ocompose[0]),
                                Double.valueOf(ocompose[1]),
                                Double.valueOf(ocompose[2]));

                        double distance = distancia(from, to);

                        if(distance <= 100) {
                            collision++;
                            continue COMPLETE;
                        }

                    }

                } catch(Exception e) { continue; }

            }

            WRITER.println("Path analizado: " + PATH);
            WRITER.println("Abejas totales: " + bees);
            WRITER.println("Abejas con riesgo de colisión: " + collision);
            WRITER.flush();
            WRITER.close();

        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }

        return false;
    }

    public static double distancia(Point3D abeja1, Point3D abeja2){
        return Math.sqrt(Math.pow((abeja1.getX() - abeja2.getX()) * 111111, 2) +
                Math.pow((abeja1.getY() - abeja2.getY()) * 111111, 2) +
                Math.pow(abeja1.getZ() - abeja2.getZ(), 2)
        );
    }

    @Override
    public boolean requiresReader() {
        return false;
    }

}
