package day06_practice;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.TestBase;
import java.util.Set;

public class C03_ActionMoveToElement extends TestBase {


        // https://amazon.com adresine gidiniz
        // sag ust bolumde bulunan dil secenek menusunun acilmasi icin mause'u bu menunun ustune getirelim
        // Change country/region butonuna basiniz
        // United States dropdown'undan 'Turkey (Türkiye)' seciniz
        // Go to website butonuna tiklayiniz
        // acilan yeni sayfadanin Title'inin Elektronik icerdigini test ediniz


    @Test
    public void test01() throws InterruptedException {

        // https://amazon.com adresine gidiniz
        driver.get("https://amazon.com");

        String sayfa1Handle = driver.getWindowHandle();
        System.out.println("SAYFA1HANDLE: " + sayfa1Handle);

        // sag ust bolumde bulunan dil secenek menusunun acilmasi icin mause'u bu menunun ustune getirelim
        Actions actions = new Actions(driver);

        WebElement dilMenu = driver.findElement(By.xpath("//div[text()='EN']"));

        actions.moveToElement(dilMenu).perform();

        Thread.sleep(2000);

        // Change country/region butonuna basiniz

        driver.findElement(By.xpath("(//div[@class='icp-mkt-change-lnk'])[1]")).click();

        Thread.sleep(2000);

        // United States dropdown'undan 'Turkey (Türkiye)' seciniz

        WebElement ddm = driver.findElement(By.xpath("//select[@id='icp-dropdown']"));

        Select select = new Select(ddm);

        select.selectByVisibleText("Turkey (Türkiye)");

        Thread.sleep(2000);

        // Go to website butonuna tiklayiniz

        driver.findElement(By.xpath("//span[text()='Changing country/region website']")).click();
        /*
        dropdown opsiyon listesi 'Go to website' butonuna basmamıza engel oldugu icin
        herhangi bir yere clic yapıp dropdown option listesinin toparlanmasını sagladık.
         */

        driver.findElement(By.xpath("//input[@class='a-button-input']")).click();

        Thread.sleep(2000);

        // acilan yeni sayfadanin Title'inin Elektronik icerdigini test ediniz

        Set<String> windowHandleSeti = driver.getWindowHandles();

        String sayfa2Handle ="";

        for (String each:windowHandleSeti) {

            if(!each.equals(sayfa1Handle)){

                sayfa2Handle = each;
            }
        }

        System.out.println("SAYFA2HANDLE: " + sayfa2Handle);

        driver.switchTo().window(sayfa2Handle);

        Thread.sleep(2000);

        String ikinciSayfaTitle = driver.getTitle();
        System.out.println("İKİNCİ SAYFA TİTLE: " + ikinciSayfaTitle);

        Assert.assertTrue(ikinciSayfaTitle.contains("Elektronik"));

    }
}
