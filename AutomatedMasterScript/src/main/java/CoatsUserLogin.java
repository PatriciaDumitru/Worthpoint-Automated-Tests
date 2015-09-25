
import java.io.File;
import javax.swing.Action;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CoatsUserLogin {
    
    //Scensario ID: G_CoUSL_1
    //Description: Coats User Login using registered user id and password
    WebDriver driver;
    
    @Test
    public void login() throws InterruptedException {
        
        System.out.println("TEST: G_CoUSL_1 - Coats User Login");
        
        driver = new ChromeDriver();
        //navigate to coats colour express url
        driver.get(TestSuite.url);       
        //maximise screen
        driver.manage().window().maximize();
        //take a screenshot
        CommonTask.takeScreenshot("CoatsUserLogin\\loginPage","Login Page",driver);
        //find user input fields and enter username/password
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"UserUsername\"]"));
        usernameField.click();
        usernameField.sendKeys(TestSuite.coatsUsername);
        
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"UserPassword\"]"));
        passwordField.click();
        passwordField.sendKeys(TestSuite.coatsPassword);
        //submit form
        driver.findElement(By.xpath("//*[@id=\"login-bottom\"]/div[3]/input")).click();
        //take a screenshot
        CommonTask.takeScreenshot("CoatsUserLogin\\continuePage","Continue Page",driver);
        //check central image loads
        CommonTask.checkImageLoaded("Continue Page","//*[@id=\"wrapper\"]/div[4]/a[1]/img",driver);
        //click continue
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/a[2]/img")).click();
 
        
        //Scensario ID: G_WHP
        //Description: Display selection of WBA. CCE or eComm
        System.out.println("TEST: G_WHP - Display selection of WBA");
        
        //check image loaded
        CommonTask.checkImageLoaded("WBA selection page", "/html/body/div[1]/center/img", driver);
        //scroll to bottom 
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,250);");
        //take screenshot
        CommonTask.takeScreenshot("CoatsUserLogin\\wbaSelectionPage", "WBA selection page", driver);
        
        //Scenario ID: G_EHP
        //Description: Display the eComm home page
        System.out.println("TEST: G_EHP - Display eComm home page");
        //find eComm button and click
        driver.findElement(By.xpath("/html/body/div[1]/center/map/area[4]")).click();
        //check images loaded
        CommonTask.checkImageLoaded("eComm Home Page: coats logo", "//*[@id=\"header\"]/a/img", driver);
        CommonTask.checkImageLoaded("eComm Home Page: welcome image", "//*[@id=\"welcomefrm1\"]/center/img", driver);
        CommonTask.checkImageLoaded("eComm Home Page: image above navbar","//*[@id=\"header\"]/div[2]/img",driver);
        //check navbar loaded and links available.
        //The dropdownXpath array holds the xpaths of each of the drop down menus expected. 
        //Empty string literal if no drop down box is expected
        System.out.println("eComm Navbar check: ");
        String[][] expectedeCommSubSections = {
            {"Manual Entry","Upload Orders","From Existing Bulk Order","Shade Not Available","Waiting For Shade Code"},
            {"Outstanding Orders List","Outstanding Orders Draft List","Outstanding Upload Draft","Pending Approval List","Denied Order"},
            {},
            {"Invoices","Delivery Notes","Summary of Purchase","Outstanding Payments","My Reports","Privacy Policy And Term & Condition"},
            {"Real Upload Failed Files","Backend In Process Files","Backend Failed Files","FTP Failed Files"}
        };
        String[] dropdownXpath = {
            "//*[@id=\"topnav\"]/li[1]/div",
            "//*[@id=\"topnav\"]/li[2]/div",
            "",
            "//*[@id=\"topnav\"]/li[4]/div",
            "//*[@id=\"topnav\"]/li[5]/div"
        };
        CommonTask.checkNavbarLoaded(driver,"//a[@class=\"head\"]",dropdownXpath,expectedeCommSubSections);
        //take a screenshot
        CommonTask.takeScreenshot("CoatsUserLogin\\eCommHomePage","eComm Home Page",driver);
        
        //Scenario ID: G_CCE_EHP
        //Description: Display the CCE homepage
        
        //navigate to CCE screen
        driver.findElement(By.xpath("//*[@id=\"access_type\"]/a")).click();
        
        //check images loaded
        CommonTask.checkImageLoaded("CCE Homepage: Central image", "//*[@id=\"welcomefrm\"]/img", driver);
        CommonTask.checkImageLoaded("CCE Homepage: Coats logo", "//*[@id=\"header\"]/a/img", driver);
        
        //take a printscreen
        CommonTask.takeScreenshot("CoatsUserLogin\\CCEhomepage", "CCE home page", driver);
        
        //check navbar loaded. Array below holds the xpaths for the dropdown menus in order from left to right
        System.out.println("CCE Navbar check: ");
        String[][] expectedCCESubSections = {
            {"Task List","Task - Completed List","FCE Request"},
            {"Order Samples","Order Draft","Manual Enrich","Order Status","DN Reprint","Feedback","Feedback Completed","Feedback Awaiting"},
            {"Hub SOS","Received Hub"},
            {"Inbox","Inbox SAP"},
            {},
            {},
            {"FCE Task Status","Order Cycle Time","Total Orders"},
            {"ALL User Types","Coats Users","Masters","LRM Log","SAP Log"}
        };
        String[] dropdownXpathCCE = {
            "//*[@id=\"topnav\"]/li[1]/div",
            "//*[@id=\"topnav\"]/li[2]/div",
            "//*[@id=\"topnav\"]/li[3]/div",
            "//*[@id=\"topnav\"]/li[4]/div",
            "",
            "",
            "//*[@id=\"topnav\"]/li[7]/div",
            "//*[@id=\"topnav\"]/li[8]/div"
        };
        CommonTask.checkNavbarLoaded(driver, "//a[@class=\"head\"]", dropdownXpathCCE,expectedCCESubSections);
     
        driver.quit();
        
    }

}
