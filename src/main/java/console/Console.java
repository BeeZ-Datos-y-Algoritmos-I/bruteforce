package console;

import analyzer.common.IAnalyzer;
import config.Config;
import reader.common.IReader;
import util.AnalyzerUtil;
import util.FileUtil;

import java.io.IOException;
import java.util.Date;

public class Console {

    public static IAnalyzer analyzer;
    public static IReader reader;

    public static void start(String...args) throws IOException {

        System.out.println("[INFORMACIÓN]: Inicializado el sistema de analisis por fuerza bruta, se leerá el archivo bruteforce.ini");
        System.out.println("[ACCIÓN]: Leyendo el archivo bruteforce.ini");

        Config.load(args);
        System.out.println("[ACCIÓN]: Leyendo el archivo de entrada " + Config.FILES.get("input_path") + "...");
        System.out.println("[ACCIÓN]: Se ha pre-cargado y se ejecutará con [threads=" + Config.VALUES.get("number_of_threads") + ", lines= " + (FileUtil.countLines(Config.FILES.get("input_path")) + 1) + "].");

        analyzer = AnalyzerUtil.getAnalyzer();
        System.out.println("[ACCIÓN]: Se utilizará el analizador " + analyzer.getClass().getName() + ".");

        if(analyzer.requiresReader()) {

            reader = AnalyzerUtil.getReader();
            System.out.println("[ACCIÓN]: Se utilizará el lector " + reader.getClass().getName() + ".");
            System.out.println("[LECTÓR]: Generando lectura...");

            reader.load();

        }

        Date date = makeDate();

        System.out.println("[ANALIZADOR]: Generando archivo " + Config.FILES.get("output_path") + "...");
        analyzer.make(false);
        long diff = (makeDate().getTime() - date.getTime());

        System.out.println("[INFORMACIÓN]: Se ha generado en " + diff + " ms.");

    }

    public static Date makeDate() {
        return new Date();
    }

}