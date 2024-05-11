import tables.Spouse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SpouseDB {
    public static void add(List<Spouse> spouses) {
        String sql = "INSERT INTO spouse (person_id, spouse_id) VALUES(?,?)";

        try (var conn = DB.connect();
             PreparedStatement ps = Objects.requireNonNull(conn).prepareStatement(sql)) {
            for (Spouse spouse : spouses) {
                ps.setString(1, spouse.getPerson_id());
                ps.setString(2, spouse.getSpouse_id());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}