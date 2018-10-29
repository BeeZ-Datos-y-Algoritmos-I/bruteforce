package console;

import analyzer.common.IAnalyzer;
import analyzer.SimpleAnalyzer;
import config.Config;
import util.AnalyzerUtil;
import util.FileUtil;

import java.io.IOException;
import java.util.Date;

public class Console {

    public static IAnalyzer analyzer;

    public static void start(String...args) throws IOException {

        System.out.println("[INFORMACIÓN]: Inicializado el sistema de analisis por fuerza bruta, se leerá el archivo bruteforce.ini");
        System.out.println("[ACCIÓN]: Leyendo el archivo bruteforce.ini");

        Config.load(args);
        System.out.println("[ACCIÓN]: Leyendo el archivo de entrada " + Config.FILES.get("input_path") + "...");
        System.out.println("[ACCIÓN]: Se ha pre-cargado y se ejecutará con [threads=" + Config.VALUES.get("number_of_threads") + ", lines= " + (FileUtil.countLines(Config.FILES.get("input_path")) + 1) + "].");
        System.out.println("[ACCIÓN]: Generando archivo " + Config.FILES.get("output_path") + "...");

        Date date = makeDate();
        analyzer = AnalyzerUtil.getAnalyzer();
        analyzer.make(false);

        System.out.println("[INFORMACIÓN]: Se han detectado " + FileUtil.countLines(Config.FILES.get("output_path"))  + " posibles colisiones.");
        System.out.println("[INFORMACIÓN]: Se ha generado en " + (makeDate().getTime() - date.getTime()) + " ms.");

    }

    public static Date makeDate() {
        return new Date();
    }

}