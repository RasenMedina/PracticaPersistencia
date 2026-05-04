package view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe per gestionar la sortida de dades
 */
public class Vista {

    public static final String MODE_CONSOLA = "CONSOLA";
    public static final String MODE_HTML = "HTML";

    private static String mode = MODE_CONSOLA;

    /** Fitxer de sortida opcional */
    private static String outputFile = null;

    public static void setMode(String m) {
        if (m != null) {
            mode = m.toUpperCase();
        }
    }

    public static void setOutputFile(String path) {
        outputFile = path;
    }

    private static String format(String text) {
        if (text == null) return "";

        if (mode.equals(MODE_HTML)) {
            return "<p>" + text + "</p>";
        }

        return text;
    }

    private static void writeToFile(String text) {
        if (outputFile == null) return;

        try (FileWriter fw = new FileWriter(outputFile, true)) {
            fw.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error escrivint a fitxer");
        }
    }

    public static void mostrar(String text) {
        String formatted = format(text);
        System.out.print(formatted);
        writeToFile(formatted);
    }

    public static void mostrarLn(String text) {
        String formatted = format(text);
        System.out.println(formatted);
        writeToFile(formatted);
    }

    public static void error(String text) {
        mostrarLn("❌ ERROR: " + text);
    }

    public static void info(String text) {
        mostrarLn("ℹ️ " + text);
    }

    public static void ok(String text) {
        mostrarLn("✅ " + text);
    }

    public static void titol(String titol) {
        mostrarLn("\n===== " + titol.toUpperCase() + " =====");
    }

    public static void separador() {
        mostrarLn("----------------------------------");
    }

    public static void mostrarMenu(String[] opcions) {
        for (int i = 0; i < opcions.length - 1; i++) {
            mostrarLn((i + 1) + ". " + opcions[i]);
        }
        mostrarLn("0. " + opcions[opcions.length - 1]);
    }
}