package dao.escalada;

import dao.DAO;
import model.Escalador;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO específic per Escalador
 */
public interface EscaladorDAO extends DAO<Escalador> {

    /**
     * Cercar per DNI
     */
    Escalador getByDni(String dni) throws Exception;

    /**
     * Get by nivell
     */
    List<Escalador> getByNivell(String nivell) throws Exception;

    /**
     * Get by estil preferit
     */
    List<Escalador> getByEstil(String estil) throws Exception;
}
