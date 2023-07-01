package day05_practice;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class C01_Iframe extends TestBase {


        // https://www.jqueryscript.net/demo/jQuery-Plugin-For-Responsive-Flexible-Iframes-Flexy/ sayfasına gidiniz
        // Videoyu görecek kadar asagiya ininiz
        // Videoyu izlemek icin Play tusuna basiniz
        // videoyu durdurunuz
        // videoyu tam ekran yapınız
        // videoyu calıstırınız
        // videoyu kucultunuz
        // videoyu durdurunuz
        // Videoyu calistirdiginizi test ediniz
        // 'jQuery Flexy Plugin Demos' yazısının gorunur oldugunu test ediniz

    @Test
    public void iframe() throws InterruptedException {

        // https://www.jqueryscript.net/demo/jQuery-Plugin-For-Responsive-Flexible-Iframes-Flexy/ sayfasına gidiniz
        driver.get("https://www.jqueryscript.net/demo/jQuery-Plugin-For-Responsive-Flexible-Iframes-Flexy/");

        // Videoyu görecek kadar asagiya ininiz
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        // Videoyu izlemek icin Play tusuna basiniz

        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='https://www.youtube.com/embed/x3kfyZJhC3U?rel=0&showinfo=0']"));
        driver.switchTo().frame(iframe);

        /*
        play'i dogru locate edip click yapmamıza ragmen videoyu calıstırmadı.
        Bunun üzerine HTML kodlarını inceleyince Play'in aslında bir iframe icerisinde oldugunu gördük
        Bu durumda önce iframe locate edip sonra switchTo() ile iframe'e gecmeliyiz
         */

        WebElement playTusu = driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']"));
        playTusu.click(); // Video nun ortasındaki play tuşunu locate alıyoruz burda

        Thread.sleep(3000);

        // videoyu durdurunuz
        WebElement videoDurdur = driver.findElement(By.xpath("//button[@class='ytp-play-button ytp-button']"));
        videoDurdur.click(); //Burda videonun altta  küçük play/durdurma iconunu locate alıyoruz.Yukarıdaki ile aynı yer değil

        Thread.sleep(3000);

        // videoyu tam ekran yapınız
        WebElement tamEkran = driver.findElement(By.xpath("//button[@class='ytp-fullscreen-button ytp-button']"));
        tamEkran.click();

        Thread.sleep(3000);

        // videoyu calıstırınız
        videoDurdur.click();//Bu durdurduğumuz yerden play de yapabiliyoruz sol alt küçük ikonlardan.Yukarıda
        // durdurmuştuk şimdi aynı yere tıkladığımızda tekrar başlatabiliyoruz.


        Thread.sleep(3000);

        // videoyu kucultunuz
        tamEkran.click();//Yukarıda video alt bölüm iconlarından tam ekran iconundan yine ekran küçültmesi yapıyoruz

        Thread.sleep(3000);

        // videoyu durdurunuz
        videoDurdur.click();

        // Videoyu calistirdiginizi test ediniz(O çalışması önemli değil bir kere çalıştırılıp sonra durdurulsa altta o yazı ve iconlar
        //açılıyor.Şu zaten video çalıştırılmış ve durdurulmuş bir durumda.
        WebElement youTubeYazisi = driver.findElement(By.xpath("//a[@class='ytp-youtube-button ytp-button yt-uix-sessionlink']"));
        Assert.assertTrue(youTubeYazisi.isDisplayed()); //Video ilk çalıştığında altta küçük iconlar ve YouTube yazısı çıkıyor
        //o youtube yazısının gözüküp gözükmediğini test ediyoruz.
        // 'jQuery Flexy Plugin Demos' yazısının gorunur oldugunu test ediniz

        driver.switchTo().parentFrame();//Küçük frameden bir üst frame geçişe sağlarız.

        WebElement yazi = driver.findElement(By.xpath("//h1[text()='jQuery Flexy Plugin Demos']"));
        Assert.assertTrue(yazi.isDisplayed());

    }
}
