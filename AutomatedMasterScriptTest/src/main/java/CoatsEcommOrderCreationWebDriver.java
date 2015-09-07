
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoatsEcommOrderCreationWebDriver {
    
    //Single User, Single sold to tests
    @Test 
    public void SUSST() {
    
    //Scenario ID: G_OOC_ME_SUSST_1
    //Description: Manual Entry Single User Single Sold To Using Your Material NO (with Shade Code in the master data) (Your Material No only)
    String[] test1Data = {"Life Easy Customer","CCE HUB OFFICES","approver 1 test","*OTHERS*","G_OOC_ME_SUSST_1","andy test 11","3"};
        try {
            createOrder(test1Data,1);
        } catch (InterruptedException ex) {
            System.out.println("Interrupt Exception at SUSST Test 1");
        }
    
}
    
    public void createOrder(String[] testData, int lineCount) throws InterruptedException {
        System.out.println("----------NEXT TEST----------");
        System.out.println("Scenario ID: " + testData[4]);
        
        //Create a new driver object
        WebDriver driver = new ChromeDriver();
        
        //Navigate to the QA site with the driver
        driver.get(TestSuite.url);
        
        //Maximize the webpage for better print screens
        driver.manage().window().maximize();
        
        //Call login function to handle login. SUSST details are used. "True" tells driver to press continue after login 
        CommonTask.login(TestSuite.SUSSTUsername,TestSuite.SUSSTPassword,driver,true);  
        
        //The WBA selection screen appears. Choose eComm.
        driver.findElement(By.xpath("/html/body/div[1]/center/map/area[4]")).click();
        
        //Wait for the navbar to be present, and then click the "Orders" tab
        WebElement watiForNavBar = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topnav\"]")));
        //WebElement waitForOrdersTab = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class=\"head\"]")));
        Actions clickOrders = new Actions(driver);
        clickOrders.moveToElement(driver.findElement(By.xpath("//a[@class=\"head\"]"))).build().perform();
        clickOrders.clickAndHold().build().perform();
        
        //Wait for the drop-down to appear and click "Manual Entry"
        WebElement waitForDropDown = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"topnav\"]/li[1]/div/div/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"topnav\"]/li[1]/div/div/ul/li[1]/a")).click();
        
        //Test that the Manual Entry page is now loaded by checking the h1 element at the top of the page
        Assert.assertEquals("Manual Entry title not found. Page may not have loaded.",
                            driver.findElement(By.xpath("//*[@id=\"list_page_breadcrumb\"]/h1")).getText(), 
                            "Orders | Manual Entry");
        
        //Enter the customer name, waiting for any search boxes to appear before typing
        WebElement custNameField = driver.findElement(By.xpath("//*[@id=\"s2id_customer_id\"]/a/span[1]"));
        custNameField.click();
        WebElement custNameSearchField = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        WebElement waitForNameSearch = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]/div/input")));
        custNameSearchField.sendKeys(testData[0]);
        Thread.sleep(600);
        custNameSearchField.sendKeys(Keys.RETURN);
        
        //wait for Customer Name Field to update
        Boolean waitForCustNameUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(custNameField, testData[0]));
        //wait for rest of page to update
        WebElement waitForPage = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"2\"]/h1"))));
        
        //Enter the Ship To Party
        WebElement shipToField = driver.findElement(By.xpath("//*[@id=\"ship_to_party_id\"]"));
        shipToField.click();
        //Wait for drop down to appear        
        Boolean waitForShipField = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(shipToField, testData[1]));
        shipToField.sendKeys(testData[1]+Keys.ENTER);
        
        //wait for ship to party address to be NOT empty
        Boolean waitForShipAddress = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//*[@id=\"ship_to_party_address\"]")), ","));
        
        //Enter the Requester Name
        WebElement requesterNameField = driver.findElement(By.xpath("//*[@id=\"BulkOrderRequesterId\"]"));
        requesterNameField.click();
        requesterNameField.sendKeys(testData[2]+Keys.RETURN);
        
        //Enter the Buyer Name
        WebElement buyerField = driver.findElement(By.xpath("//*[@id=\"s2id_BuyerId\"]/a/span[1]"));
        buyerField.click();
        WebElement waitForBuyerSearch = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"s2id_BuyerId\"]/a/span[1]")));
        WebElement buyerSearchField = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        buyerSearchField.sendKeys(testData[3]);
        Thread.sleep(500);
        buyerSearchField.sendKeys(Keys.RETURN);
        
        //Enter the PO number with an ID number appended to allow for reference
        WebElement poNumberField = driver.findElement(By.xpath("//*[@id=\"BulkOrderPoNumber\"]"));
        //generate random number (temporary fix to ensure PO number is not used twice)
        int num = (int) Math.floor(Math.random()*101);
        String ID = String.valueOf(num);
        poNumberField.sendKeys(testData[4]+"_"+ID);
        
        //Now the order information must be entered into the correct lines
        for (int i = 0; i < lineCount; i++) {
            //Enter the "Your Material Number" value into the first line
            WebElement ymnField = driver.findElement(By.xpath("//*[@id=\"materialno_"+i+"\"]"));
            ymnField.sendKeys(testData[5]);
        
            //Enter the Quantity
            WebElement qtyField = driver.findElement(By.xpath("//*[@id=\"quantity"+i+"\"]"));
            qtyField.click();
            qtyField.sendKeys(testData[6]);
        
            //The required date must now be calculated and entered
            WebElement dateField = driver.findElement(By.xpath("//*[@id=\"required_date_"+i+"\"]"));
            dateField.click();
            WebElement waitForDatePicker = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"s2id_BuyerId\"]/a/span[1]")));
        
            //get day of month
            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            //If the day is less than the 28th, choose the date in three days' time. Else, click to the next month and choose the 3rd.
            if (dayOfMonth < 28) {
                String date = String.valueOf((int)dayOfMonth+3);
                driver.findElement(By.xpath("//a[contains(text(),\""+date+"\")]")).click();
            } else {
                driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
                driver.findElement(By.xpath("//a[contains(text(),\"3\")]")).click();
            }
        }
       
        //Now that all order infomation is entered into the correct lines, the "Next" button is pressed 
        driver.findElement(By.xpath("//*[@id=\"next\"]")).click();
        
        //confirm alert
        Alert reviewAlert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert received: "+ reviewAlert.getText());
        reviewAlert.accept();
        System.out.println("Alert accepted.");
        
        //Confirm Order review
        driver.findElement(By.xpath("//*[@id=\"submit1\"]")).click();
        
        //Confirm Alert
        Alert submitAlert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert received: " + submitAlert.getText());
        submitAlert.accept();
        System.out.println("Alert accepted.");
    }
    
    
    /* This method allows unique identifiers to be managed
    public String getID() {
        try {
            //Create new reader and writers to access the txt file which holds the ID count
            BufferedReader br = new BufferedReader(new FileReader(TestSuite.idFileURL));
            BufferedWriter bw = new BufferedWriter(new FileWriter(TestSuite.idFileURL));
            //get the current ID count and parse it to int
            String contents = br.readLine();
            int ID = Integer.valueOf(contents);
            //increment the ID count to ensure unique ID's are provided
            ID++;
            //write the incremented ID to the file
            bw.write(String.valueOf(ID));
            //Return the ID value (before it was incremented)
            return contents;
        } catch (IOException e) {
            //If an IO error occurs, the ID will be given as "error"
            System.out.println("Error getting PO number ID");
            return "error";
        }
    }
    */
    
}
