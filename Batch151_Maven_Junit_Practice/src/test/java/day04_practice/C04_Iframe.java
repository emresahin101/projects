package day04_practice;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;



public class C04_Iframe extends TestBase {

        // https://html.com/tags/iframe sayfasına gidiniz
        // Videoyu görecek kadar asagiya ininiz
        // Videoyu izlemek icin Play tusuna basiniz
        // Videoyu calistirdiginizi test ediniz
        // 'Powerful,but easy to misuse' yazısının gorunur oldugunu test ediniz


    @Test
    public void name() {

        // https://html.com/tags/iframe sayfasına gidiniz
        driver.get("https://html.com/tags/iframe");


        // Videoyu görecek kadar asagiya ininiz

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).perform();
        // Videoyu izlemek icin Play tusuna basiniz

        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='https://www.youtube.com/embed/owsfdh4gxyc']"));

        driver.switchTo().frame(iframe);

        /*
        Play'i dogru locate edip click yapmamıza ragmen videoyu calıstırmadı
        bunun üzerine HTML kodlarını inceleyince
        play'in aslında iframe icerisinde oldugunu gördük
        bu durumda once iframe'i locate edip
        switchTo() ile iframe gecmeliyiz
         */

        WebElement play = driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']"));

        play.click(); //Youtube videosunun ortasında kırmızı play tuşuna tıklıyoruz.Ama Başta oranın locate i alınmıyor.
        //bizde o sayfadaki herhangi bir yerin üzerineden incele yapıp açılan panelin en sol üstünde ok iconu var tıklayıp
        //daha sonra locate ini almak istediğimiz play resmini üzerine gelirsek oranın locati aşağı panelde gözükür

        // Videoyu calistirdiginizi test ediniz

        WebElement youTubeYazisi = driver.findElement(By.xpath("//a[@class='ytp-youtube-button ytp-button yt-uix-sessionlink']"));
        // Video çalıştığında videonun altında youtube,cc(altyazı) yazıları satırı var orası açılıyor.Video açıldığında
        //o satır açılıyor.
        Assert.assertTrue(youTubeYazisi.isDisplayed());

        // 'Powerful,but easy to misuse' yazısının gorunur oldugunu test ediniz

        driver.switchTo().parentFrame();


        WebElement powerYazisi = driver.findElement(By.xpath("//span[@id='Powerful_but_easy_to_misuse']"));


        Assert.assertTrue(powerYazisi.isDisplayed());

    }


}
