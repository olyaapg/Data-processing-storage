import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class NormalizerParent {
    public static void normalize() {
        String sqlGetUnique = """
                SELECT COUNT(*)
                FROM (SELECT DISTINCT person_id, parent_id
                      FROM parent) as pipi;""";
        String sqlDeleteDoubles = """
                DELETE FROM parent p1
                    USING parent p2
                WHERE p1.CTID < p2.CTID
                  AND p1.person_id = p2.person_id
                  AND p1.parent_id = p2.parent_id""";
        String sqlGetAll = "SELECT COUNT(*) FROM parent";
        String sqlAddPrimaryKey = """
                ALTER TABLE parent
                ADD CONSTRAINT person_parent_pkey PRIMARY KEY (person_id, parent_id)""";

        try (var conn = DB.connect();
             Statement st = Objects.requireNonNull(conn).createStatement()) {
            ResultSet rs = st.executeQuery(sqlGetUnique);
            int countUnique = 0;
            while (rs.next()) {
                countUnique = rs.getInt(1);
            }

            st.execute(sqlDeleteDoubles);

            rs = st.executeQuery(sqlGetAll);
            while (rs.next()) {
                if (rs.getInt(1) != countUnique) {
                    System.out.println("Произошла ошибка");
                }
            }

            st.execute(sqlAddPrimaryKey);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
