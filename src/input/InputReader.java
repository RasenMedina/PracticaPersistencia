package input;

import model.*;
import view.Vista;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe utilitzada per llegir dades des de teclat
 */
public class InputReader {

    /** Scanner únic per tota l'aplicació */
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Llegeix un String no buit
     */
    public static String llegir(String missatge) {
        while (true) {
            Vista.mostrar(missatge + ": ");
            String text = scan.nextLine();

            if (text != null) {
                text = text.trim();
                if (!text.isEmpty()) return text;
            }

            Vista.error("El text no pot estar buit.");
        }
    }

    /**
     * Llegeix un boolean (S/N)
     */
    public static boolean llegirBoolean(String missatge) {
        while (true) {
            String resposta = llegir(missatge + " (S/N)").toUpperCase();

            if (resposta.equals("S")) return true;
            if (resposta.equals("N")) return false;

            Vista.error("Resposta no vàlida. Escriu S o N.");
        }
    }

    /**
     * Llegeix un enter
     */
    public static int llegirInt(String missatge) {
        while (true) {
            try {
                return Integer.parseInt(llegir(missatge));
            } catch (NumberFormatException e) {
                Vista.error("Has d'introduir un enter vàlid.");
            }
        }
    }

    /**
     * Llegeix un double
     */
    public static double llegirDouble(String missatge) {
        while (true) {
            try {
                return Double.parseDouble(llegir(missatge));
            } catch (NumberFormatException e) {
                Vista.error("Has d'introduir un decimal vàlid.");
            }
        }
    }

    /**
     * Llegeix una opció dins un rang
     */
    public static int llegirOpcio(String missatge, int min, int max) {
        while (true) {
            int opcio = llegirInt(missatge);

            if (opcio >= min && opcio <= max) return opcio;

            Vista.error("Opció fora de rang (" + min + " - " + max + ")");
        }
    }

    /**
     * Llegeix una data yyyy-MM-dd
     */
    public static LocalDate llegirData(String missatge) {
        while (true) {
            try {
                String text = llegir(missatge);

                if (!text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    Vista.error("Format incorrecte. Usa yyyy-MM-dd");
                    continue;
                }

                return LocalDate.parse(text);

            } catch (Exception e) {
                Vista.error("Data no vàlida.");
            }
        }
    }

    /**
     * Llegeix la popularitat tot validant-la
     */
    public static String llegirPopularitat() {

        while (true) {

            String p = llegir("Popularitat (baixa, mitjana, alta)").toLowerCase();

            if (p.matches("baixa|mitjana|alta")) {
                return p;
            }

            Vista.error("Popularitat no vàlida");
        }
    }

    /**
     * Llegeix l'orientació tot validant-la
     */
    public static String llegirOrientacio() {

        while (true) {

            String o = llegir("Orientació (N, NE, NO, E, O, S, SE, SO)").toUpperCase();

            if (o.matches("N|NE|NO|E|O|S|SE|SO")) {
                return o;
            }

            Vista.error("Orientació incorrecta");
        }
    }

    /**
     * Llegeix el tipus de via tot validant-la
     */
    public static String llegirTipusVia() {

        while (true) {

            String tipus = llegir("Tipus (esportiva, classica, gel)").toLowerCase();

            if (tipus.matches("esportiva|classica|gel")) {
                return tipus;
            }

            Vista.error("Tipus incorrecte");
        }
    }

    /**
     * Llegeix el grau de dificultat tot validant-lo mitjançant el tipus de via
     */
    public static String llegirGrauDificultat(String tipusVia) {

        while (true) {

            String grau = llegir("Grau dificultat").toLowerCase();

            if (tipusVia.equals("esportiva")) {

                if (grau.matches("4\\+?|5\\+?|6[abc]?\\+?|7[abc]?\\+?|8[abc]?\\+?|9[abc]?\\+?")) {
                    return grau;
                }
            }

            if (tipusVia.equals("classica") || tipusVia.equals("gel")) {

                if (grau.matches("4\\+?|5\\+?|6[abc]?\\+?|7[abc]?\\+?|8[ab]?\\+?")) {
                    return grau;
                }
            }

            Vista.error("Grau no vàlid pel tipus de via");
        }
    }

    /**
     * Llegeix una Escola per teclat tot validant les seves dades
     */
    public static Escola llegirEscola() {

        while (true) {

            try {

                Escola e = new Escola();

                e.setNom(llegir("Nom escola"));
                e.setLloc(llegir("Població"));
                e.setPopularitat(llegirPopularitat());
                e.setAproximacio(llegir("Aproximació"));

                return e;

            } catch (Exception ex) {
                Vista.error("Error creant escola");
            }
        }
    }

    /**
     * Llegeix un sector per teclat tot validant les seves dades
     */
    public static Sector llegirSector() {

        while (true) {

            try {

                Sector s = new Sector();

                s.setIdEscola(llegirInt("ID escola"));
                s.setNom(llegir("Nom sector"));
                s.setLatitud(llegirDouble("Latitud"));
                s.setLongitud(llegirDouble("Longitud"));
                s.setAproximacio(llegir("Aproximació"));
                s.setPopularitat(llegirPopularitat());
                s.setEsGel(llegirBoolean("És sector de gel?"));

                return s;

            } catch (Exception e) {
                Vista.error("Error creant sector");
            }
        }
    }

    /**
     * Llegeix una via per teclat tot validant les seves dades
     */
    public static Via llegirVia() {

        while (true) {

            try {

                Via v = new Via();

                v.setIdSector(llegirInt("ID sector"));
                v.setIdEscaladorCreador(llegirInt("ID escalador creador"));
                v.setNom(llegir("Nom via"));

                String tipus = llegirTipusVia();
                v.setTipusVia(tipus);

                v.setOrientacio(llegirOrientacio());
                v.setAncoratge(llegir("Ancoratge"));
                v.setTipusRoca(llegir("Tipus roca"));
                v.setEstat(llegir("Estat"));

                return v;

            } catch (Exception e) {
                Vista.error("Error creant via");
            }
        }
    }

    /**
     * Llegeix l'estat d'una via tot validant-lo
     */
    public static String llegirEstatVia() {

        while (true) {

            String estat = llegir("Estat (apte, construccio, tancada)").toLowerCase();

            if (estat.matches("apte|construccio|tancada")) {
                return estat;
            }

            Vista.error("Estat no vàlid");
        }
    }

    /**
     * Llegeix un escalador per teclat tot validant les seves dades
     */
    public static Escalador llegirEscalador() {

        while (true) {

            try {

                Escalador e = new Escalador();

                e.setDni(llegir("DNI"));
                e.setNom(llegir("Nom"));
                e.setCognoms(llegir("Cognoms"));
                e.setAlias(llegir("Àlies"));
                e.setDataNaix(llegirData("Data naixement (yyyy-MM-dd)"));
                e.setEstilPref(llegir("Estil preferit"));

                return e;

            } catch (Exception ex) {

                Vista.error(ex.getMessage());
            }
        }
    }

    /**
     * Llegeix un llarg per teclat tot validant les seves dades
     */
    public static Llarg llegirLlarg() {

        while (true) {

            try {

                Llarg l = new Llarg();

                l.setIdVia(llegirInt("ID via"));
                l.setOrdre(llegirInt("Ordre del llarg"));
                l.setLlargada(llegirInt("Llargada"));
                l.setGrauDificultat(llegir("Grau dificultat"));

                return l;

            } catch (Exception ex) {
                Vista.error(ex.getMessage());
            }
        }
    }

    /**
     * Llegeix un assoliment pert teclat tot validant les seves dades
     */
    public static Assoliment llegirAssoliment() {

        while (true) {

            try {

                Assoliment a = new Assoliment();

                a.setIdEscalador(llegirInt("ID escalador"));
                a.setIdVia(llegirInt("ID via"));
                a.setDataAssoliment(llegirData("Data assoliment (yyyy-MM-dd)"));

                return a;

            } catch (Exception ex) {

                Vista.error(ex.getMessage());
            }
        }
    }

}