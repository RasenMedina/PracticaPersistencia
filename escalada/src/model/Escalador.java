package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa un Escalador dins del sistema d'escalada
 */
public class Escalador {

    /** ATRIBUTS */

    /** id que identifica l'escalador (NN) */
    private int idEscalador;
    /** DNI de l'escalador (NN) */
    private String dni;
    /** nom de l'escalador (NN) */
    private String nom;
    /** primer cognom de l'escalador */
    private String cognom1;
    /** segon cognom de l'escalador */
    private String cognom2;
    /** alias de l'escalador */
    private String alias;
    /** data de naixament de l'escalador */
    private LocalDate dataNaix;
    /** estil preferit (esportiva, clàssica, gel) */
    private String estil;

    /** Constants */
    private static final String REGEX_DOC =
            "^[0-9]{8}[A-Z]$" +                 // DNI
                    "|^[XYZ][0-9]{7}[A-Z]$" +          // NIE
                    "|^[A-Z0-9]{6,9}$";                // Passaport (genèric)


    /**
     * Constructor buit
     */
    public Escalador() {
    }

    /**
     * Constructor complet
     */
    public Escalador(int idEscalador, String dni, String nom, String cognom1,
                     String cognom2, String alias, LocalDate dataNaix, String estil) {
        inicialitzar(idEscalador, dni, nom, cognom1, cognom2, alias, dataNaix, estil);
    }

    /**
     * Constructor còpia
     */
    public Escalador(Escalador altre) {
        inicialitzar(altre.idEscalador, altre.dni, altre.nom, altre.cognom1,
                altre.cognom2, altre.alias, altre.dataNaix, altre.estil);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idEscalador, String dni, String nom, String cognom1,
                              String cognom2, String alias, LocalDate dataNaix, String estil) {
        setIdEscalador(idEscalador);
        setDni(dni);
        setNom(nom);
        setCognom1(cognom1);
        setCognom2(cognom2);
        setAlias(alias);
        setDataNaix(dataNaix);
        setEstil(estil);
    }

    /** SETTERS amb validació */

    private void setIdEscalador(int idEscalador) {
        if (idEscalador < 0) throw new IllegalArgumentException("ID escalador no vàlid");
        this.idEscalador = idEscalador;
    }

    public void setDni(String dni) {
        if (dni == null || !dni.matches(REGEX_DOC))
            throw new IllegalArgumentException("Document identificatiu no vàlid");
        this.dni = dni.toUpperCase(); //normalitzem el dni
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Nom d'escalador no vàlid");
        this.nom = nom.trim(); //normalitzem nom
    }

    public void setCognom1(String cognom1) {
        if (cognom1 != null && cognom1.isBlank())
            throw new IllegalArgumentException("Primer cognom no vàlid");
        this.cognom1 = cognom1;
    }

    public void setCognom2(String cognom2) {
        if (cognom2 != null && cognom2.isBlank())
            throw new IllegalArgumentException("Segon cognom no vàlid");
        this.cognom2 = cognom2;
    }

    public void setAlias(String alias) {
        if (alias != null && alias.isBlank())
            throw new IllegalArgumentException("Alias no vàlid");
        this.alias = alias;
    }

    public void setDataNaix(LocalDate dataNaix) {
        if (dataNaix != null && dataNaix.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de naixement no vàlida");
        this.dataNaix = dataNaix;
    }

    public void setEstil(String estil) {
        if (estil == null) {
            this.estil = null;
            return;
        }

        String e = estil.toLowerCase();

        if (!e.equals("esportiva") && !e.equals("clàssica")
                && !e.equals("classica") && !e.equals("gel")) {
            throw new IllegalArgumentException("Estil no vàlid");
        }
        if (e.equals("classica")) e = "clàssica"; //normalitzem accent
        this.estil = e;
    }

    /** GETTERS */

    public int getIdEscalador() {
        return idEscalador;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public String getAlias() {
        return alias;
    }

    public LocalDate getDataNaix() {
        return dataNaix;
    }

    public String getEstil() {
        return estil;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "DNI: " + dni + "\n" +
                "Alias: " + alias + "\n" +
                "Estil: " + estil + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Escalador)) return false;
        Escalador that = (Escalador) o;
        return idEscalador == that.idEscalador;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idEscalador);
    }
}
