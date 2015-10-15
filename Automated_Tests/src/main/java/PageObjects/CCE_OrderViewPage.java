
package PageObjects;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import com.google.common.base.Verify;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_OrderViewPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    By custNameLocator = By.cssSelector("#popup_content > div:nth-child(1) > table > tbody > tr:nth-child(5) > td:nth-child(4)");
    By requestorNameLocator = By.cssSelector("#popup_content > div:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(4)");
    By orderNumLocator = By.cssSelector("#popup_content > div:nth-child(1) > table > tbody > tr:nth-child(5) > td:nth-child(2)");
    By printButton = By.cssSelector("#content > div > table > tbody > tr:nth-child(12) > td > p > a");
    By custNameHeader = By.cssSelector("#content > div > table > tbody > tr:nth-child(1) > th:nth-child(1)");
    By popupContent = By.id("popup_content");
    By contentFrame = By.id("content");
    
    public CCE_OrderViewPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public CCE_OrderViewPage switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        //driver.switchTo().frame(driver.findElement(frameLocator));
        return this;
    }
    
    public String getCustName() {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(custNameLocator));
        return driver.findElement(custNameLocator).getText();
    }
    
    public String getRequestorName() {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(requestorNameLocator));
        return driver.findElement(requestorNameLocator).getText();
    }
    
    public String getOrderNumber() {
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(orderNumLocator));
        return driver.findElement(orderNumLocator).getText();
    }
    
    public String getShadeCode(int lineNumber) {
        By shadeCodeLocator = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child("+lineNumber+") > td:nth-child(6)");
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(shadeCodeLocator));
        return driver.findElement(shadeCodeLocator).getText();
    }
    
    public CCE_OrderStatusPage closeView() {

        Actions pressEscape = new Actions(driver);
        pressEscape.sendKeys(Keys.ESCAPE).build().perform();
        //Handle alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressPrint() {
        switchTo();
        
        if (DataItems.printingEnabled) {
            driver.findElement(printButton).click();
            System.out.println("DN sent to printer.");
        } else {
            System.out.println("Printing disabled - print not pressed.");
        }

        
        return new CCE_ConfirmProductionPage(driver);
    }

    public void verifyCopied() {

        //Locators for cells containing each line's fields
        By mum1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(5)");
        By mum2 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(3) > td:nth-child(5)");
        
        By qty1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(7)");
        By qty2 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(3) > td:nth-child(7)");
        
        By req1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(8)");
        By req2 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(3) > td:nth-child(8)");
        
        By requir1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(9)");
        By requir2 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(3) > td:nth-child(9)");
        
        By fabRef1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(11)");
        By fabRef2 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(3) > td:nth-child(11)");
        
        Verify.verify(driver.findElement(mum1).getText().equals(driver.findElement(mum2).getText()),"Order Samples: MUM Type not copied correctly");
        Verify.verify(driver.findElement(qty1).getText().equals(driver.findElement(qty2).getText()),"Order Samples: Quantity not copied correctly");
        Verify.verify(driver.findElement(req1).getText().equals(driver.findElement(req2).getText()),"Order Samples: Request Type not copied correctly");
        Verify.verify(driver.findElement(requir1).getText().equals(driver.findElement(requir2).getText()),"Order Samples: Requirements not copied correctly");
        Verify.verify(driver.findElement(fabRef1).getText().equals(driver.findElement(fabRef2).getText()),"Order Samples: Fabric Reference not copied correctly");
        
        
    }
    
    public void waitForLoad() {
        switchTo();
        WebElement waitForColumnHeaders = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(custNameHeader));
        WebElement waitForPrintButton = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(printButton));
    }
    
    public void waitForContent() {
        //switchTo();
        WebElement waitForPopup = new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(popupContent));
    }
    
    public void waitForContentPresence() {
        WebElement waitForPopup = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(popupContent));
    }
    
    public void waitForContentAlt2() {
        WebElement waitForContent = new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(contentFrame));
    }
    
    public void waitForInvisibility() {
        boolean waitForInvisiblility = new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
}
