
package AutomationFramework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
    
    public static WebElement clickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static WebElement clickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver,seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static WebElement presence(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public static WebElement visible(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static WebElement visible(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver,seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static boolean invisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public static Alert alert(WebDriver driver) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
    }
    
    public static Alert alert(WebDriver driver, int seconds) {
        return new WebDriverWait(driver,seconds).until(ExpectedConditions.alertIsPresent());
    }
    
    public static WebDriver frame(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    
    public static WebDriver frame(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver,seconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    
    public static boolean textContains(WebDriver driver, String id, String text) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.textContains(id, text));
    }
    
    public static boolean checked(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(locator)));
    }
    
    public static boolean checked(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver,seconds).until(CommonTask.boxIsChecked(driver.findElement(locator)));
    }
    
    public static boolean notChecked(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsNotChecked(driver.findElement(locator)));
    }
    
    public static boolean unchecked(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsUnchecked(driver.findElement(locator)));
    }
    
    public static boolean textPresent(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    public static boolean textPresentInput(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.textToBePresentInput(locator,text));
    }

    public static boolean textPresentInput2(WebDriver driver, String element, String text) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.textToBePresentInput2(element,text));
    }
    public static boolean textPresentInput3(WebDriver driver, String element, String text) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.textToBePresentInput3(element,text));
    }

    
    public static boolean selectionPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(locator));
    }
    
    public static boolean selectionToBe(WebDriver driver, By locator, String selection) {
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBe(locator, selection));
    }
    
    public static boolean optionPresent(WebDriver driver, By locator, String option) {
        Select select = new Select(driver.findElement(locator));
        return new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.optionPresent(option, select));
    }
    
}
