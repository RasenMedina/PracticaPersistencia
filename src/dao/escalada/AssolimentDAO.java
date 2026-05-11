package dao.escalada;

import dao.DAO;
import model.Assoliment;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO específic per Assoliment
 */
public interface AssolimentDAO extends DAO<Assoliment> {

    /**
     * Obté tots els assoliments d'un escalador
     */
    List<Assoliment> getByEscalador(int idEscalador) throws Exception;

    /**
     * Obté tots els assoliments d'una via
     */
    List<Assoliment> getByVia(int idVia) throws Exception;

    /**
     * Comprova si un escalador ja ha assolit una via
     */
    boolean exists(int idEscalador, int idVia) throws Exception;
}
