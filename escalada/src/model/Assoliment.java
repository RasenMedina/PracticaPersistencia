package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa un Assoliment dins del sistema d'escalada
 */
public class Assoliment {

    /** ATRIBUTS */

    /** id que identifica l'escalador (NN) */
    private int idEscalador;
    /** id que identifica la via (NN) */
    private int idVia;
    /** data de l'assoliment */
    private LocalDate data;

    /**
     * Constructor complet
     */
    public Assoliment(int idEscalador, int idVia, LocalDate data) {
        inicialitzar(idEscalador, idVia, data);
    }

    /**
     * Constructor còpia
     */
    public Assoliment(Assoliment altre) {
        inicialitzar(altre.idEscalador, altre.idVia, altre.data);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idEscalador, int idVia, LocalDate data) {
        setIdEscalador(idEscalador);
        setIdVia(idVia);
        setData(data);
    }

    /** SETTERS amb validació */

    public void setIdEscalador(int idEscalador) {
        if (idEscalador < 0)
            throw new IllegalArgumentException("ID escalador no vàlid");
        this.idEscalador = idEscalador;
    }

    public void setIdVia(int idVia) {
        if (idVia < 0)
            throw new IllegalArgumentException("ID via no vàlid");
        this.idVia = idVia;
    }

    public void setData(LocalDate data) {
        if (data != null && data.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data d'assoliment no vàlida");
        this.data = data;
    }

    /** GETTERS */

    public int getIdEscalador() {
        return idEscalador;
    }

    public int getIdVia() {
        return idVia;
    }

    public LocalDate getData() {
        return data;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Escalador: " + idEscalador + "\n" +
                "Via: " + idVia + "\n" +
                "Data: " + data + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assoliment)) return false;
        Assoliment that = (Assoliment) o;
        return (idEscalador == that.idEscalador) && (idVia == that.idVia);
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idEscalador, idVia);
    }
}
