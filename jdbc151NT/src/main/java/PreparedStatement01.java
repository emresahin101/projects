
import java.sql.*;
import java.util.AbstractMap;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "falcon1145");
        Statement statement = connection.createStatement();
        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //PreparedStatement oluşturmak için:

        //1. Adım: PreparedStatement query'si oluştur --> Paremetrelendirme yapılacak yerlere ? gir
        String sql = "UPDATE companies SET number_of_employees = ? WHERE company = ?";//? --> Paremetrelendirme

        //2. Adım: PreparedStatement objesi oluştur
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3. Adım: PreparedStatement objesi ile setInt gibi methodlarla Paremetrelendirmelerin yerine koymak istediğin değerleri yerleştir
        preparedStatement.setInt(1, 9999);
        preparedStatement.setString(2, "IBM"); // "IBM" ürününün number_of_employeesini 9999 olarak güncelle.

        //4. Adım: Query'yi çalıştır
        int guncellenenSatirSayisi = preparedStatement.executeUpdate();  //Bunu kullanmasakta işlem yine oluyor.Burda kaç satırda değişiklik
        // yapıldıysa onu alırız eğer herhangi bir satırda değişiklik yapılmadıysa 0 yazar.

        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2) + "--" + resultSet.getObject(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı CASPER olan number_of_employees değerini 10000 olarak güncelleyin.
        preparedStatement.setInt(1, 10000);
        preparedStatement.setString(2, "CASPER"); //Ama Casper adında ürün olmadığı için değişiklik yapılamaz.


        int guncellenenSatirSayisi2 = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }


        connection.close();
        statement.close();
    }
}