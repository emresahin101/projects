
import java.nio.channels.SelectableChannel;
import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "falcon1145");
        Statement statement = connection.createStatement();


        //1. Örnek:  region_id'si 1 olan "country_name" değerlerini çağırın.;
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true ==> DQL ile data çağırıyoruz

        //Datayı çağırıp okumak için executeQuery methodunu kullanmalıyız. execute() methodu sadece true yada false döner!!!!!!
        //Bu çağırdğımız metodu ResultSet interface ine atarız!!!!!!!!!!!!!!!!!!!!!!!
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)); //Burda sütun indexleri 0 dan değil 1 den başlar
            /*yani 1. sütun içinde bu sıralamada yazdırır. yanina başka bir sütun eklensin istersek column 2 deriz 3 ...
            4 diye gider ancak select kısmında seçtiğimiz sütün kadar sütun çağırabiliriz en fazla aşağıda.
            Eğer yukarıda Select * from countries where region_id= 1";
            System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
            şeklinde çağırabilirdik integer tanımlanmış sütunları da getString ile çağırabiliyoruz */

        }

    /* Switzerland
Germany
Denmark
France
Italy
Netherlands
United Kingdom */
        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        System.out.println("\n===== 2. Örnek =====\n");
        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "-- " + resultSet2.getObject(2));
            //columnindex:1 yukarıda tanımlanan country_id, columnindex:2 ise country_name sütunlarını temsil eder.
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("\n===== 3. Örnek =====\n");
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3); //!!! ResultSet kullanmadan önce bir string değişkenine select ile
                                                             //Verileri kaydediyoruz.

        while (resultSet3.next()) {
            System.out.println(resultSet3.getObject(1) + "--" + resultSet3.getObject(2) + "--" + resultSet3.getObject(3));
        }


        connection.close();
        statement.close();
    }}

