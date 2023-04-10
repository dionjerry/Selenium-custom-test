import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    ChromeOptions options = new ChromeOptions();
    String Title;
    String ActualTitle = "Your Title";
    String   Website = "http://cardtonic.com"; //Enter Website Name

    public WebDriver driver;

    @BeforeTest
    void first() {

        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DON JERRY\\Documents\\softwares\\cchromedriver.exe");
        driver = new ChromeDriver(options);




    }
    @Test
            public void  EnterWebsiteName() throws InterruptedException {

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

    @Test
    void Responsive() // TEST WEBSITE RESPONSIVENESS
    {
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
       js.executeScript("window.scrollBy(0, 10000)");

    }







    }



