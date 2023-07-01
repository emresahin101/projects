package day08_practice;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBase;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class C02_FilesDownload extends TestBase {

        // 'https://the-internet.herokuapp.com/download' adresine gidiniz
        // some-file.txt dosyasini indirelim
        // dosyanin bilgisayarınızda Downloads(indirilenler)'a basariyla indirilip indirilmedigini test ediniz


    @Test
    public void test01() throws InterruptedException {

        // 'https://the-internet.herokuapp.com/download' adresine gidiniz
        driver.get("https://the-internet.herokuapp.com/download");

        Thread.sleep(3000);

        File silinecekDosya = new File("C:\\Users\\BURAK\\Downloads\\some-file.txt");
        silinecekDosya.delete();

        // some-file.txt dosyasini indirelim
        driver.findElement(By.xpath("//a[text()='some-file.txt']")).click();

        Thread.sleep(3000);

        // dosyanin bilgisayarınızda Downloads(indirilenler)'a basariyla indirilip indirilmedigini test ediniz

        //            "C:\Users\BURAK\Downloads\some-file.txt"

        // Assert.assertTrue(Files.exists(Paths.get("C:\\Users\\BURAK\\Downloads\\some-file.txt")));


        // daha dinamik hale getirelim

        //            "C:\Users\BURAK                       \Downloads\some-file.txt"

        String farkliKisim = System.getProperty("user.home");
        String ortakKisim = "\\Downloads\\some-file.txt";

        String dosyaYolu = farkliKisim + ortakKisim;

        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));

    }
}
