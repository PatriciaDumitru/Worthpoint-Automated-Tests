
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderCreation {
    
    //Scenario ID: G_OOC_ME_SUSST_1
    //Description: Manual Entry Single User Single Sold To Using Your Material NO (with Shade Code in the master data) (Your Material No Only)
    
    //array holds: Customer name, Ship To Party, Requester, Buyer, PO number, Your material number, quantity
    String[] test1Data = {"Life Easy Customer","CCE HUB OFFICES","approver 1 test","*OTHERS*","G_OOC_ME_SUSST_1","andy test 11","3"};
    String[] test2Data = {"Life Easy Customer","CCE HUB OFFICES","approver 1 test","*OTHERS*","G_OOC_ME_SUSST_2","andy test 11","3"};

    @Test
    public void ymnSingleLineShadeCode() throws InterruptedException {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Test: G_OOC_ME_SUSST_1 - Order using Your material number with shade code in master data (SST)");

        // new webdriver instance
        WebDriver driver = new ChromeDriver();
        //navigate to qa site
        driver.get(TestSuite.url);
        //maximize webpage
        driver.manage().window().maximize();
        //login
        CommonTask.login(TestSuite.SUSTUsername,TestSuite.SUSTPassword,driver,true);  
        //choose eComm
        driver.findElement(By.xpath("/html/body/div[1]/center/map/area[4]")).click();
        //click Orders, select Manual Entry from submenu
        Thread.sleep(600);
        driver.findElement(By.xpath("//a[@class=\"head\"]")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id=\"topnav\"]/li[1]/div/div/ul/li[1]/a")).click();
        
        //Assert manual entry page is loaded
        Thread.sleep(300);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals("Failed to reach Manual Entry page.","https://qawcs.coatscolourexpress.com/qa_p4i/ecom/order/manual",currentURL);
        
        //Enter Customer Name
        WebElement custNameField = driver.findElement(By.xpath("//*[@id=\"s2id_customer_id\"]/a/span[1]"));
        custNameField.click();
        WebElement custNameSearchField = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        custNameSearchField.sendKeys(test1Data[0]);
        Thread.sleep(600);
        custNameSearchField.sendKeys(Keys.RETURN);
        Thread.sleep(1000);
        //Enter ship to party
        WebElement shipToField = driver.findElement(By.xpath("//*[@id=\"ship_to_party_id\"]"));
        shipToField.click();
        shipToField.sendKeys(test1Data[1] + Keys.RETURN);
        //Enter requester name
        WebElement requesterNameField = driver.findElement(By.xpath("//*[@id=\"BulkOrderRequesterId\"]"));
        requesterNameField.click();
        requesterNameField.sendKeys(test1Data[2]+Keys.RETURN);
        //Enter the buyer name
        WebElement buyerField = driver.findElement(By.xpath("//*[@id=\"s2id_BuyerId\"]/a/span[1]"));
        buyerField.click();
        WebElement buyerSearchField = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        buyerSearchField.sendKeys(test1Data[3]);
        Thread.sleep(600);
        buyerSearchField.sendKeys(Keys.RETURN);
        //Enter the PO number
        WebElement poNumberField = driver.findElement(By.xpath("//*[@id=\"BulkOrderPoNumber\"]"));
        //create a random number between 0 and 1000 to reduce risk of same PO being used twice
        int randNum = (int)Math.floor(Math.random()*1001);
        poNumberField.sendKeys(test1Data[4]+"_"+randNum);
        
        //Enter Your Material Number
        WebElement ymnField = driver.findElement(By.xpath("//*[@id=\"materialno_0\"]"));
        ymnField.sendKeys(test1Data[5]);
        //Enter quantity
        WebElement qtyField = driver.findElement(By.xpath("//*[@id=\"quantity0\"]"));
        qtyField.click();
        qtyField.sendKeys(test1Data[6]);
        //Enter required date
        //find date field
        WebElement dateField = driver.findElement(By.xpath("//*[@id=\"required_date_0\"]"));
        dateField.click();
        //get day of month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        Thread.sleep(300);
        //if less than 28th, choose three days advance. Else, click next month and choose 3nd
        if (dayOfMonth < 28) {
            //calculate date three days in future
            String date = String.valueOf((int)dayOfMonth+3);
            driver.findElement(By.xpath("//a[contains(text(),\""+date+"\")]")).click();
        } else {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            driver.findElement(By.xpath("//a[contains(text(),\"3\")]")).click();
        }
        //press next
        driver.findElement(By.xpath("//*[@id=\"next\"]")).click();
        //confirm alert
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        alert.accept();   
        
        try {
            WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"flashMessage\"]"));
            String submissionElement = messageElement.getText();
            System.out.println("Order Error message: " + submissionElement);
        } catch (Exception e) {
            System.out.println("Order continued...");
        }
        
        //take a screenshot
        CommonTask.takeScreenshot("OrderCreation\\OrderReview_SUST_YMN", "Order review screen for your material number test", driver);
        
        //confirm order
        driver.findElement(By.xpath("//*[@id=\"submit1\"]")).click();
        Thread.sleep(800);
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.accept();
        
        Thread.sleep(600);
        
        //take a screenshot
        CommonTask.takeScreenshot("OrderCreation\\OrderConf_SUST_YMN", "Order confirmation screen for your material number test", driver);

        //view order in record and confirm details added correctly.
        driver.findElement(By.xpath("//*[@id=\"FilterOutstandingOrderForm\"]/div[2]/div[3]/div/div[2]/table/tbody[1]/tr[1]/td[1]/a")).click();
        
        
        
    }
    
    //Scenario ID: G_OOC_ME_SUSST_2
    //Description: Manual Entry Single User Single Sold To Using Your Material NO (with Shade Code in the master data)(Login with multi-sold to)
    
    
    
    //Scenario ID: G_OOC_ME_SUSST_3
    //Description: Manual Entry Single User Single Sold To Using Your Material NO (without Shade Code in the master data)
    //(Your Material No +Shade Code)
    
    
}
