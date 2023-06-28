package techproed.day15_FilesExist;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import techproed.utilities.TestBase;

public class C03_FileUploadTest extends TestBase {
    @Test
    public void test01() {
        //https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        /*
        Eğer dosyaSeç butonuna basmamız istenirse ve otomasyon ile bu butona klik yapmak istediğimizde
        exception alıyorsak, direk upload yapmak istediğimiz dosya yolunu alıp sendKeys() methodu ile
        dosyayı sayfaya upload yapabiliriz.

        Eğer upload yada dosyaseç butonuna tıkladığınızda windows pencesi açılıyorsa, o webelemente
        sendKeys() methodu ile dosya gönderemeyebilirsiniz. Böyle bir durumla karşılaşırsanız
        Robot class'ından obje ile methodlar kullanarak bunu aşabilirsiniz.
         */

        String dosyaYolu = "C:/Users/Lenovo/OneDrive/Masaüstü/sen.txt";
        //dosyaSeç butonuna basalim
        WebElement dosyaSeçButton = driver.findElement(By.id("file-upload"));

        /* dosyaSeçButton.click();--> click yapmadı hata aldık!!!!!!!!!!!!!!!!

        Eğer dosyaSeç butonuna basmamız istenirse ve otomasyon ile bu butona klik yapmak istediğimizde
        exception alıyorsak, direk upload yapmak istediğimiz dosya yolunu alıp sendKeys() methodu ile
        dosyayı dosyaSeçButtonuna upload yapabiliriz.Aşağıda öyle yaptık */

        //Yuklemek istediginiz dosyayi secelim.
        dosyaSeçButton.sendKeys(dosyaYolu); //Direkt dosyaSeçButton' una sendkeys() ile dosyayı gönderirirz!!!!!!!!!!!
        bekle(2);

        //Upload butonuna basalim.
        driver.findElement(By.id("file-submit")).click();
        bekle(2);

        //“File Uploaded!” textinin goruntulendigini test edelim.
        Assert.assertTrue(driver.findElement(By.xpath("//h3")).isDisplayed());
    }
}
