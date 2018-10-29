package util;

import analyzer.common.IAnalyzer;
import config.Config;

public class AnalyzerUtil {

    public static IAnalyzer getAnalyzer() {

        try {

            Class<? extends IAnalyzer> clazz = Class.forName("analyzer." + Config.ANALYZER.get("clazz")).asSubclass(IAnalyzer.class);
            IAnalyzer analyzer = clazz.newInstance();

            return analyzer;

        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] El analizador configurado de nombre " + Config.ANALYZER.get("clazz") + " no ha sido encontrado!");
            System.out.println("[ERROR] StackTrace ::");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("[ERROR] Ha ocurrido un error al instanciar el analizador.");
            System.out.println("[ERROR] StackTrace ::");
            e.printStackTrace();
        }

        throw new RuntimeException("[ERROR] Ha ocurrido un problema desconocido durante la instanciación del analizador.");

    }

}
