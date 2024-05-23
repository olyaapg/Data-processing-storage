import tables.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PersonDB {
    public static void add(List<Person> people) {
        var sql = "INSERT INTO person (person_id, gender, person_name) VALUES (?, ?, ?)";

        try (var conn = DB.connect();
             PreparedStatement ps = Objects.requireNonNull(conn).prepareStatement(sql)) {
            for (var person : people) {
                ps.setString(1, person.getPerson_id());
                ps.setString(2, person.getGender());
                ps.setString(3, person.getPerson_name());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
