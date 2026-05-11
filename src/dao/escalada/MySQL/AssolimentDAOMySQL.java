package dao.escalada.MySQL;

import dao.escalada.AssolimentDAO;
import model.Assoliment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementació DAO MySQL per Assoliment
 */
public class AssolimentDAOMySQL implements AssolimentDAO {

    /**
     * CREATE
     */
    @Override
    public void create(Assoliment a) throws Exception {

        String sql = """
                INSERT INTO assoliment
                (id_escalador, id_via, data_assoliment)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getIdEscalador());
            ps.setInt(2, a.getIdVia());

            if (a.getDataAssoliment() != null) {
                ps.setDate(3, Date.valueOf(a.getDataAssoliment()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.executeUpdate();
        }
    }

    /**
     * GET BY ID
     */
    @Override
    public Assoliment getById(int id) throws Exception {

        String sql = """
                SELECT *
                FROM assoliment
                WHERE id_assoliment = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapAssoliment(rs);
            }
        }

        return null;
    }

    /**
     * GET ALL
     */
    @Override
    public List<Assoliment> getAll() throws Exception {

        List<Assoliment> llista = new ArrayList<>();

        String sql = "SELECT * FROM assoliment";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                llista.add(mapAssoliment(rs));
            }
        }

        return llista;
    }

    /**
     * UPDATE
     */
    @Override
    public void update(Assoliment a) throws Exception {

        String sql = """
                UPDATE assoliment
                SET id_escalador = ?,
                    id_via = ?,
                    data_assoliment = ?
                WHERE id_assoliment = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getIdEscalador());
            ps.setInt(2, a.getIdVia());

            if (a.getDataAssoliment() != null) {
                ps.setDate(3, Date.valueOf(a.getDataAssoliment()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setInt(4, a.getIdAssoliment());

            ps.executeUpdate();
        }
    }

    /**
     * DELETE
     */
    @Override
    public void delete(int id) throws Exception {

        String sql = """
                DELETE FROM assoliment
                WHERE id_assoliment = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    /**
     * Converteix ResultSet → Assoliment
     */
    private Assoliment mapAssoliment(ResultSet rs) throws SQLException {

        Assoliment a = new Assoliment();

        a.setIdAssoliment(rs.getInt("id_assoliment"));
        a.setIdEscalador(rs.getInt("id_escalador"));
        a.setIdVia(rs.getInt("id_via"));

        Date data = rs.getDate("data_assoliment");

        if (data != null) {
            a.setDataAssoliment(data.toLocalDate());
        }

        return a;
    }

    @Override
    public List<Assoliment> getByEscalador(int idEscalador) throws Exception {
        return List.of();
    }

    @Override
    public List<Assoliment> getByVia(int idVia) throws Exception {
        return List.of();
    }

    @Override
    public boolean exists(int idEscalador, int idVia) throws Exception {
        return false;
    }
}