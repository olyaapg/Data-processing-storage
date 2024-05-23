import tables.Parent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ParentDB {
    public static void add(List<Parent> parents) {
        String sql = "INSERT INTO parent (person_id, parent_id) VALUES (?, ?)";

        try (var conn = DB.connect();
             PreparedStatement ps = Objects.requireNonNull(conn).prepareStatement(sql)) {
            for (Parent parent : parents) {
                ps.setString(1, parent.getPerson_id());
                ps.setString(2, parent.getParent_id());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
