public class Runner {
        public static void main(String[] args) {

            //Database'e bağlan
            JdbcUtils.connectToDataBase();

            //Statement oluştur
            JdbcUtils.createStatement();

            //Query çalıştır
            String sql = "CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)";
            JdbcUtils.execute(sql);
        }}
