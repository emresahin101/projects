import java.sql.*;

public class JdbcUtils {
    //Bu class'ta tekrarlı yapılacak işlemlerin methodları bulunacak
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    //Database'e bağlanma methodu
    public static Connection connectToDataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "falcon1145");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //Medunna Database'e bağlanma methodu
    public static Connection connectToMedunnaDataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //Statement oluşturan method
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }

    //execute methodu ile query çalıştıran method
    public static boolean execute(String sql) {

        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        //executeQuery(), executeUpdate(), closeConnection() methodları ödev....

    //executeQuery() methodu ile query çalıştıran method
    public static ResultSet executeQuery(String sql) {

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    //Bağlantıyı kapatan method
    public static void closeConnection(){

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
