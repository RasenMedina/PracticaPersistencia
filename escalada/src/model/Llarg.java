package model;

import java.util.Objects;

/**
 * Classe que representa un Llarg dins del sistema d'escalada
 */
public class Llarg {

    /** ATRIBUTS */

    /** id que identifica el llarg (NN) */
    private int idLlarg;
    /** id de la via al qual pertany el llarg (NN) */
    private int idVia;
    /** número de llarg dins de la via (NN) */
    private int num;
    /** llargada del llarg en metres (NN) */
    private int llargada;
    /** [4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b]  */
    private String grauDificultat;

    /**
     * Constructor complet
     */
    public Llarg(int idLlarg, int idVia, int num, int llargada, String grauDificultat) {
        inicialitzar(idLlarg, idVia, num, llargada, grauDificultat);
    }

    /**
     * Constructor còpia
     */
    public Llarg(Llarg altre) {
        inicialitzar(altre.idLlarg, altre.idVia, altre.num, altre.llargada, altre.grauDificultat);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idLlarg, int idVia, int num, int llargada, String grauDificultat) {
        setIdLlarg(idLlarg);
        setIdVia(idVia);
        setNum(num);
        setLlargada(llargada);
        setGrauDificultat(grauDificultat);
    }

    /** SETTERS amb validació */

    private void setIdLlarg(int idLlarg) {
        if (idLlarg < 0) throw new IllegalArgumentException("ID llarg no vàlid");
        this.idLlarg = idLlarg;
    }

    public void setIdVia(int idVia) {
        if (idVia < 0) throw new IllegalArgumentException("ID via no vàlid");
        this.idVia = idVia;
    }

    public void setNum(int num) {
        if (num < 0) throw new IllegalArgumentException("número de llarg no vàlid");
        this.num = num;
    }

    public void setLlargada(int llargada) {
        if (llargada < 0) throw new IllegalArgumentException("llargada no vàlida");
        this.llargada = llargada;
    }

    public void setGrauDificultat(String grauDificultat) {
        if (grauDificultat == null){
            this.grauDificultat = null;
            return;
        }

        grauDificultat = grauDificultat.trim().toLowerCase();

        if (!grauDificultat.matches("^(4|4\\+|5|5\\+|6[abc]\\+?|7[abc]\\+?|8[ab]\\+?)$"))
            throw new IllegalArgumentException("Grau no vàlid");

        this.grauDificultat = grauDificultat;
    }

    /** GETTERS */

    public int getIdLlarg() {
        return idLlarg;
    }

    public int getIdVia() {
        return idVia;
    }

    public int getNum() {
        return num;
    }

    public int getLlargada() {
        return llargada;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Num: " + num + "\n" +
                "Via: " + idVia + "\n" +
                "Llargada: " + llargada + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Llarg)) return false;
        Llarg that = (Llarg) o;
        return idLlarg == that.idLlarg;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idLlarg);
    }
}
