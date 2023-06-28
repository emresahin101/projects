package techproed.day12_WindowHandles;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import techproed.utilities.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class C01_WindowHandles extends TestBase {
    @Test
    public void test01() {
        //  https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        //  ilk sayfasının Handle degerini alın yazdırın
        String ilkSayfaHandle = driver.getWindowHandle();
        System.out.println("İlk Sayfa Handle Değeri : " + ilkSayfaHandle);

        //  ilk sayfadaki textin “Opening a new window” olduğunu test edin.
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        String expectedText = "Opening a new window";
        Assert.assertEquals(expectedText, actualText);

        //  ilk sayfa Title'ının “The Internet” olduğunu test edin.
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        Assert.assertEquals(expectedTitle, actualTitle);

        //  "Click Here" butonuna tıklayın.
        driver.findElement(By.xpath("(//a)[2]")).click();//--> Kontrolümüz dışında Yeni bir sekme açıldı
        bekle(3);
        /*
            Bir button'a click yaptığımızda kontrolümüz dışında yeni bir sekme yada pencere açılırsa
         yeni açılan pencerenin handle değerini bilmediğim için normal getWindowHandle ile driver'imi yeni pencere
         geçiremem. Bunu getWindowHandles() methoduyla açılan tüm pencereleri bir Set'e assign edip, ilk açtiğimiz
         pencere değilse ikinci açılan yeni penceredir mantığıyla bir loop için çözebiliriz(Ama bu yol çok tercih edilmez)
         */
        Set<String> pencereler = driver.getWindowHandles();//!!!!!!!!!!!!!!!!!!!!!!!!!!!1.YOL
        for (String w : pencereler) {
            System.out.println(w);
            if (!w.equals(ilkSayfaHandle)) { //4B2F4A705D3101A4BB4BD9B9CBD6F463 --> ilk sayfa handle değeri
                driver.switchTo().window(w);//796FE7F5E70323F2A5127DEFEBBD386D (Driverı bizden bağımsız açılan window getirdik)
            }
        }

        //  ikinci sayfa Title'ının “New Window” olduğunu test edin.
        String actualTitleNewWindow = driver.getTitle();
        String expectedTitleNewWindow = "New Window";
        Assert.assertEquals(expectedTitleNewWindow, actualTitleNewWindow);

        String ikinciSayfaHandle = driver.getWindowHandle();

        System.out.println("İkinci Sayfa Handle Değeri : " + ikinciSayfaHandle);
        bekle(2);
        //  ilk sayfaya dönün ve Title'ının “The Internet” olduğunu test edin.

        driver.switchTo().window(ilkSayfaHandle);//2. sayfadan ilk sayfaya geçtik

        String ilkSayfaActualTitle = driver.getTitle();
        String ilkSayfaExpectedTitle = "The Internet";
        Assert.assertEquals(ilkSayfaExpectedTitle, ilkSayfaActualTitle);
        bekle(2);

        //  ikinci sayfaya tekrar geçin.
        driver.switchTo().window(ikinciSayfaHandle);
        bekle(2);

        //  ilk sayfaya tekrar dönün.
        driver.switchTo().window(ilkSayfaHandle);
        bekle(2);
    }

    @Test
    public void test02() { //Yukarıdaki sorunun aynısı bağımsız açılan 2.sayfaya geçiş için 2.yol

        driver.get("https://the-internet.herokuapp.com/windows"); //  https://the-internet.herokuapp.com/windows adresine gidin.

        //  ilk sayfadaki textin “Opening a new window” olduğunu test edin.
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        String expectedText = "Opening a new window";
        Assert.assertEquals(expectedText, actualText);

        //  ilk sayfa Title'ının “The Internet” olduğunu test edin.
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        Assert.assertEquals(expectedTitle, actualTitle);

        //  "Click Here" butonuna tıklayın.
        driver.findElement(By.xpath("(//a)[2]")).click();//--> Kontrolümüz dışında Yeni bir sekme açıldı
        bekle(3);

        //  ikinci sayfa Title'ının “New Window” olduğunu test edin.
        List<String> pencereler = new ArrayList<>(driver.getWindowHandles());//!!!!!!!!!!!Bu 2.YOL daha pratik
         /*
            getWindowHandles() methodunu kullanarak açılan tüm pencerelerin handle değerlerini bir arraylist'e atadık.
        Index 0(sıfır)'dan başladığı için kontrolüm dışında açılan pencere 1. index de olduğundan
         driver.switchTo().window(pencereler.get(1)); ile yeni açılan penceye geçiş yaptık
         */
        driver.switchTo().window(pencereler.get(1)); //indeks 1 yani 2.sayfa geçiş yapıyoruz

        String actualTitleNewWindow = driver.getTitle();
        String expectedTitleNewWindow = "New Window";
        Assert.assertEquals(expectedTitleNewWindow, actualTitleNewWindow);


        bekle(2);
        //  ilk sayfaya dönün ve Title'ının “The Internet” olduğunu test edin.
        driver.switchTo().window(pencereler.get(0));
        String ilkSayfaActualTitle = driver.getTitle();
        String ilkSayfaExpectedTitle = "The Internet";
        Assert.assertEquals(ilkSayfaExpectedTitle, ilkSayfaActualTitle);

        //  ikinci sayfaya tekrar geçin.
        driver.switchTo().window(pencereler.get(1));
        bekle(2);
        //  ilk sayfaya tekrar dönün.
        driver.switchTo().window(pencereler.get(0));
        bekle(2);
    }

    @Test //Yukarıdaki sorunun aynısı bağımsız açılan sayfaya geçişin 3. YOLU
    public void test03() {
        //  https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        //  ilk sayfadaki textin “Opening a new window” olduğunu test edin.
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        String expectedText = "Opening a new window";
        Assert.assertEquals(expectedText, actualText);

        //  ilk sayfa Title'ının “The Internet” olduğunu test edin.
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        Assert.assertEquals(expectedTitle, actualTitle);

        //  "Click Here" butonuna tıklayın.
        driver.findElement(By.xpath("(//a)[2]")).click();//--> Kontrolümüz dışında Yeni bir sekme açıldı
        bekle(3);

        //  ikinci sayfa Title'ının “New Window” olduğunu test edin.

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());//!!!!!!!!!!!!!!!!!
        /*
        Set ve ArrayList kullanmadan switchTo().window() methodu içine argüman olarak driver.getWindowHandles()
        methodunu ile bütün açılan pencereli bir array olarak alıp index belirterek isteğim pencereye geçiş yapabilirim.
         */
        String actualTitleNewWindow = driver.getTitle();
        String expectedTitleNewWindow = "New Window";
        Assert.assertEquals(expectedTitleNewWindow, actualTitleNewWindow);
        /*
            getWindowHandles() methodunu kullanarak açılan tüm pencerelerin handle değerlerini bir arraylist'e atadık.
        Index 0(sıfır)'dan başladığı için kontrolüm dışında açılan pencere 1. index de olduğundan
         driver.switchTo().window(pencereler.get(1)); ile yeni açılan penceye geçiş yaptık
         */

        bekle(2);
        //  ilk sayfaya dönün ve Title'ının “The Internet” olduğunu test edin.
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        String ilkSayfaActualTitle = driver.getTitle();
        String ilkSayfaExpectedTitle = "The Internet";
        Assert.assertEquals(ilkSayfaExpectedTitle, ilkSayfaActualTitle);

        //  ikinci sayfaya tekrar geçin.
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        bekle(2);
        //  ilk sayfaya tekrar dönün.
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        bekle(2);
    }

    @Test ////Yukarıdaki aynı soru bu sefer  TESTBASE den switchToWindow metodunu çağırarak yapıyoruz.
    public void test04() {
        //  https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        //  ilk sayfadaki textin “Opening a new window” olduğunu test edin.
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        String expectedText = "Opening a new window";
        Assert.assertEquals(expectedText, actualText);

        //  ilk sayfa Title'ının “The Internet” olduğunu test edin.
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        Assert.assertEquals(expectedTitle, actualTitle);

        //  "Click Here" butonuna tıklayın.
        driver.findElement(By.xpath("(//a)[2]")).click();//--> Kontrolümüz dışında Yeni bir sekme açıldı
        bekle(3);

        //  ikinci sayfa Title'ının “New Window” olduğunu test edin.

        switchToWindow(1); //TESTBASE e atmış olduğumuz metodtan yaptık aşağıda!!!!!!!!!!!!!

       /* public void switchToWindow(int index){   TESTBASE den çağırmış olduğumuz method
            List<String> pencereler = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(pencereler.get(index));

        Set ve ArrayList kullanmadan switchTo().window() methodu içine argüman olarak driver.getWindowHandles()
        methodunu ile bütün açılan pencereli bir array olarak alıp index belirterek isteğim pencereye geçiş yapabilirim.
         */
        String actualTitleNewWindow = driver.getTitle();
        String expectedTitleNewWindow = "New Window";
        Assert.assertEquals(expectedTitleNewWindow, actualTitleNewWindow);
        /*
            getWindowHandles() methodunu kullanarak açılan tüm pencerelerin handle değerlerini bir arraylist'e atadık.
        Index 0(sıfır)'dan başladığı için kontrolüm dışında açılan pencere 1. index de olduğundan
         driver.switchTo().window(pencereler.get(1)); ile yeni açılan penceye geçiş yaptık
         */

        bekle(2);
        //  ilk sayfaya dönün ve Title'ının “The Internet” olduğunu test edin.
        switchToWindow(0);
        String ilkSayfaActualTitle = driver.getTitle();
        String ilkSayfaExpectedTitle = "The Internet";
        Assert.assertEquals(ilkSayfaExpectedTitle, ilkSayfaActualTitle);

        //  ikinci sayfaya tekrar geçin.
        switchToWindow(1);
        bekle(2);

        //  ilk sayfaya tekrar dönün.
        switchToWindow(0);
        bekle(2);
    }

}
