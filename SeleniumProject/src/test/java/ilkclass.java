import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ilkclass {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/resources/drivers/chromedriver.exe");
        System.out.println(System.getProperty("webdriver.chrome.driver")); //Buda bize yukarıdaki yolu yazdırır..
        //"src/resources/drivers......
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.techproeducation.com");
    }
}
