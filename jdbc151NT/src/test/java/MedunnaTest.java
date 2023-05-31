
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

   When
     User sends the query to get the created room
     (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

   Then
     Assert that room is created properly
     (Odanın düzgün kaydedildiğini doğrular)

   And
     User closes the connection
  */

    @Test
    public void medunnaTest() throws SQLException {
        //     User connects to the database
        JdbcUtils.connectToMedunnaDataBase();
        JdbcUtils.createStatement();

        //User sends the query to get the created room
        String sql = "SELECT * FROM room WHERE room_number = 326556421";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);

        //Assert that room is created properly
       resultSet.next(); //Yukarıdaki throws sqlException buradaki next den dolayı konuyor.
        assertEquals("123.00", resultSet.getString("price"));
        assertEquals("Database Test İçin Oluşturuldu", resultSet.getString("description"));
        assertEquals("mark_twain", resultSet.getString("created_by"));


        String sql2 = "SELECT * FROM room WHERE room_number = 999888777"; //Benim yaptığım örnek....
        ResultSet resultSet2 = JdbcUtils.executeQuery(sql2);
        resultSet2.next();
        assertEquals("400.00", resultSet2.getString("price"));

        //User closes the connection
        JdbcUtils.closeConnection();

    }
}
