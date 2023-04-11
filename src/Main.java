import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Main {


    String ActualTitle = "Your Title";
    String   Website = "file:///C:/My%20Web%20Sites/https___smmsenex.com_/smmsenex.com/index.html"; //Enter Website Name
    String WebDrive = "C:\\\\Users\\\\DON JERRY\\\\Documents\\\\softwares\\\\cchromedriver.exe"; //Web driver location chromium is used
    int ScrollPixel = 1000 ; //number of pixel to scroll through your website
    ChromeOptions options = new ChromeOptions();
    String Title;
    public WebDriver driver;

    @BeforeTest
    void first() {

        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", WebDrive);
        driver = new ChromeDriver(options);




    }
    @Test(priority = 0)
    void  WebsiteTitle(   ) throws InterruptedException {

        driver.get(Website); //Enter your Website Name

         Title = driver.getTitle();
        System.out.println( "your Website NAme is " +Title); //Website title is played in console
        Thread.sleep(5000);

        try{
            Assert.assertEquals(Title, ActualTitle);
        }
        catch (AssertionError e)
        {
            System.out.println(" unequal Tittle");
        }


    }

    @Test(priority = 1)
    void Responsive() // TEST WEBSITE RESPONSIVENESS
    {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        System.out.println("<<<<<Website is Scrolling down<<<<<");
        for( int i = 0; i < ScrollPixel ; i ++) {
            //System.out.println(i); //code test
            js.executeScript("window.scrollBy(0, "+i+")"); }


        System.out.println("<<<<<Website is Scrolling up<<<<<");
        for(int i =0 ; i > -(ScrollPixel) ; i --) {
           // System.out.println(i); //code test
            js.executeScript("window.scrollBy(0, "+i+")"); }


    }









    }



