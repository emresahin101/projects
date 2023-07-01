package day08_practice;


import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.WatchEvent;

public class C03_Screenshot extends TestBase {



        // https://www.teknosa.com/ adresine gidin
        // arama cubuguna oppo yazip enter'a basınız
        // sonuc yazısını yazdiriniz
        // ilk urunun fotografını cekin

    @Test
    public void test01() throws IOException {

        // https://www.teknosa.com/ adresine gidin
        driver.get("https://www.teknosa.com/");

        try { //Bazen çıkan cookies lerin locatini aldık eğer çıkmazsada catch bloğundaki yazıyı yazdırırız.
            driver.findElement(By.xpath("//div[@id='ins-editable-button-1580496494']")).click();
        } catch (Exception e) {
            System.out.println("cookies cıkmadı");
        }

        // arama cubuguna oppo yazip enter'a basınız
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Oppo" + Keys.ENTER);

        // sonuc yazısını yazdiriniz
        WebElement sonucYazisi = driver.findElement(By.xpath("//div[@class='plp-panel-block1']"));
        System.out.println(sonucYazisi.getText());

        // ilk urunun fotografını cekin

        WebElement ilkUrun = driver.findElement(By.xpath("(//a[@class=' prd-link '])[1]"));

        File kayit = new File("target/ekranGoruntusu/urun.Jpeg"); //Kayıt yapılacak yerin adresi ve kayıt yapılacak dosyanın adı

        File UrunGoruntusu = ilkUrun.getScreenshotAs(OutputType.FILE); //ilkUrun ün ekran görüntüsünü aldık

        FileUtils.copyFile(UrunGoruntusu,kayit);//Aldığımız ekran görüntüsünü yerini ayarladığımız kayıt yerine aktardık

    }
}
