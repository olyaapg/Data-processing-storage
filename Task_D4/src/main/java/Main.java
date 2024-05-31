import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String sql = """
                SELECT
                    f.flight_no,
                    tf.fare_conditions,
                    MIN(tf.amount) AS min_amount,
                    MAX(tf.amount) AS max_amount
                FROM
                    ticket_flights tf
                        JOIN
                    flights f
                    ON
                        tf.flight_id = f.flight_id
                GROUP BY
                    f.flight_no, tf.fare_conditions;
                """;

        String sqlInsert = "INSERT INTO my_prices (flight_no, fare_conditions, amount) VALUES (?, ?, ?)";

        try (var conn = DB.connect();
             Statement st = Objects.requireNonNull(conn).createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            PreparedStatement ps = Objects.requireNonNull(conn).prepareStatement(sqlInsert);
            while (rs.next()) {
                String flight_no = rs.getString("flight_no");
                String fare_conditions = rs.getString("fare_conditions");
                int min_amount = rs.getInt("min_amount");
                int max_amount = rs.getInt("max_amount");
                if (fare_conditions.equals("Business") || fare_conditions.equals("Comfort") || min_amount == max_amount) {
                    ps.setString(1, flight_no);
                    ps.setString(2, fare_conditions);
                    ps.setInt(3, min_amount);
                    ps.addBatch();
                } else {
                    ps.setString(1, flight_no);
                    ps.setString(2, fare_conditions);
                    ps.setInt(3, min_amount);
                    ps.addBatch();
                    ps.setString(1, flight_no);
                    ps.setString(2, "EconomyPlus");
                    ps.setInt(3, max_amount);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}