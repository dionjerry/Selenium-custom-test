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
    String   Website = "https://google.com/"; //Enter Website Name
    String WebDrive = "Your Webdriver location"; //Web driver location chromium is used
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
        String parentHandle = driver.getWindowHandle(); // get the current window handle
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
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }

                //code to do something on new window if any
                driver.switchTo().window(parentHandle);  // switch back to the original window new window if any
                driver.navigate().to(Website);
                Thread.sleep(3000);
            }
            catch (NoSuchElementException   e )
            {
                    //this is empty for catching unClickable links
            }
            catch ( ElementClickInterceptedException e)
            {
                System.out.println("navigating back to home page");
                driver.navigate().to(Website);
            }

            catch (InterruptedException e) {
                throw new RuntimeException(e); //this is for the thread waiting.
            }
            catch (NoSuchWindowException ex )
            {
                driver.navigate().to(Website);
            } //4

        }

    }









    }



