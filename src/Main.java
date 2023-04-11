import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Main {

    //************************************************************************************
    //Variable TExt
    String ActualTitle = "Your Title";
    String   Website = "http://YourWebsiteAddress"; //Enter Website Name
    String WebDrive = "Your Webdriver Location"; //Web driver location chromium is used
    int ScrollPixel = 1000 ; //number of pixel to scroll through your website
    //************************************************************************************
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
    void ResponsiveScroll() throws InterruptedException // TEST WEBSITE RESPONSIVENESS
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
        Thread.sleep(3000);



    }

    @Test(priority = 2 )
    void VisitPages()
    {
        WebElement bodyText = driver.findElement(By.cssSelector("body")); //locates all text in website
       String BodilyText = bodyText.getText(); //grabs all the text in the website
        System.out.println(BodilyText);
        String[] BodyTextArray = BodilyText.split("\n");
        System.out.println(BodyTextArray.length); // displays the texts divided into lines.
        for(String Clicker : BodyTextArray )
        {

            try
            {

                System.out.println("visiting " +Clicker + " Relax");

                driver.findElement(By.linkText(Clicker)).click(); //locate each text and click
                Thread.sleep(3000);
                driver.navigate().back();
                Thread.sleep(3000);
            }
            catch (NoSuchElementException e)
            {
                    //this is empty for catching unClickable links
            } catch (InterruptedException e) {
                throw new RuntimeException(e); //this is for the thread waiting..
            }

        }

    }









    }



