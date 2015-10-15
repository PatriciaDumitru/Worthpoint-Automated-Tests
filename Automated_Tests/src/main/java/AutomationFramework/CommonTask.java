
package AutomationFramework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonTask {
    
    private static By contentLocator = By.id("content");
    private static By frameLocator = By.id("TB_iframeContent");

    
    public static void setSearchField(WebDriver driver,By fieldLocator, String item) {
        By searchLocator = By.cssSelector("#select2-drop > div > input");
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();
        
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(searchLocator));
        driver.findElement(searchLocator).sendKeys(item);
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator, item));
        //Press enter
        action.sendKeys(driver.findElement(searchLocator), Keys.ENTER).build().perform();
        
        //Wait for update
        boolean waitForUpdate = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item)); 
    }
    
    public static void resetSearchField(WebDriver driver, String id) {
        
        By resetButton = By.cssSelector("#"+id+" > a > abbr");
        
        driver.findElement(resetButton).click();
        
        //Wait for update
        boolean waitForUpdate = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(By.id(id), "")); 
    }
    
    public static void setDropDownField(WebDriver driver,By fieldLocator,String item) throws InterruptedException {
               
        //Wait for field to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //Click and type
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();
        action.sendKeys(driver.findElement(fieldLocator),item).build().perform();
        Thread.sleep(200);
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
        //Wait again
        boolean waitForUpdate2 = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
    }
    
    public static void setChoiceField(WebDriver driver, By fieldLocator, String item) {
        By resultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
        //Wait for box to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //click and enter item
        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).sendKeys(item);
        //Wait for result
        boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator,item));
        
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
        
    }
    
    public static void setChoiceFieldAlt(WebDriver driver,By fieldLocator,String item){
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        //Wait for box to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //click and enter item
        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).sendKeys(item);
        //Wait for result
        boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator,item));
        
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
    }
    
    public static void setTextField(WebDriver driver,By fieldLocator, String item) {
    	WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
    	driver.findElement(fieldLocator).click();
    	driver.findElement(fieldLocator).sendKeys(item);
    	boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));

    }
    
    public static void setInputField(WebDriver driver, By fieldLocator, String item) {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
    	driver.findElement(fieldLocator).click();
    	driver.findElement(fieldLocator).sendKeys(item);
    	boolean waitForText = new WebDriverWait(driver,10).until(textToBePresentInput(fieldLocator,item));
    }
    
    public static void setInputFieldAlt(WebDriver driver, String id, String item) {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(id))));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('"+id+"').setAttribute('attr', '"+item+"')");
        boolean waitForText = new WebDriverWait(driver,10).until(textToBePresentInput(By.id(id),item));
    }
    
    public static void setCheckBox(WebDriver driver, By fieldLocator) {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        driver.findElement(fieldLocator).click();
        Boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(fieldLocator)));
    }
    
    public static void setDateField(WebDriver driver, By fieldLocator) {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        
        driver.findElement(fieldLocator).click();
        //Get the current day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        
        //New action for clicking
        Actions clickDatePicked = new Actions(driver);
        
        //if current Day of month is less than 28th, choose date three days advance. Else, click next month and choose 3rd
        if (dayOfMonth < 28) {
            //calculate date three days in future
            String date = String.valueOf((int)dayOfMonth+3);
            By dateLocator = By.xpath("//a[contains(text(),'"+date+"')]");
            clickDatePicked.click(driver.findElement(dateLocator)).build().perform();
        } else {
            //locate next month button and click
            By nextMonthLocator = By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span");
            clickDatePicked.click(driver.findElement(nextMonthLocator)).build().perform();
            
            //locate 3rd of month button and click
            By thirdOfMonthLocator = By.xpath("//a[contains(text(),\"3\")]");
            clickDatePicked.click(driver.findElement(thirdOfMonthLocator)).click().perform();
        }       
    }
    
    public static ExpectedCondition<Boolean> textContains(final String text, final String elementText) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (elementText.contains(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        
        };
    }
    
    public static ExpectedCondition<Boolean> boxIsChecked(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> textToBePresentInput(final By locator, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                WebElement inputField = f.findElement(locator);
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static void waitForPageLoad(WebDriver driver) {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
    }
    
    public static void waitForOverlay(WebDriver driver) {
        WebDriver wait = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public static void waitForOverlay(WebDriver driver, By overylayContent) {
        WebDriver wait = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        WebElement waitAgain = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(overylayContent));
    }
    
    public static void waitForViewClose(WebDriver driver) {
        boolean waitForClose = new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
    
    public static void waitForFieldUpdate(WebDriver driver, By fieldLocator, String item) {
        boolean wait = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
    }
    
    public static void closeView(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        
        waitForViewClose(driver);
    }
    
    public static String generatePO(String type) throws FileNotFoundException, IOException {
        String PONumber = "null";
        try {
            //Access file to read
            FileReader fr = new FileReader(DataItems.idFilepath);
            BufferedReader br = new BufferedReader(fr);
            
            //Get current ID
            String idString = br.readLine();
            int id = Integer.valueOf(idString);
            //Increment ID to be written back to file
            id++;
            
            br.close();
            fr.close();
            
            //Access file to write
            FileWriter fw = new FileWriter(DataItems.idFilepath);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //Write incremented id to file
            bw.write(String.valueOf(id));
            //Append the ID and type the PO number
            String prefix;
            if (type.equals("contract")) {
                prefix = DataItems.conOrdDetails[4];
            } else {
                prefix = DataItems.custDetails[4];
            }
            
            PONumber = prefix+idString;       
            
            bw.close();
            fw.close();
            
        } catch (IOException e) {
            System.out.println("IO exception handling the ID file");
        }
         
         return PONumber;
    }
    
}
