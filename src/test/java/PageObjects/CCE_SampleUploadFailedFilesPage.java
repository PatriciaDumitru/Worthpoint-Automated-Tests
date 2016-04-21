package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 * Created by Daniel Ion on 09.03.2016.
 */
public class CCE_SampleUploadFailedFilesPage extends WBA_BasePage {

    //Locators
    By customerNameField = By.id("s2id_filterSampleErrorLogCustomerId");
    By fileNameField=By.id("filterSampleErrorLogFileName");
    By salesOrganizationField=By.id("s2id_filterSampleErrorLogCustomerId");

    By breadcrumb = By.cssSelector("#content > h2");

    public CCE_SampleUploadFailedFilesPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
    }

    public WebElement getBreadcrumb() {
        WebElement item = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
        return item;
    }
    public WebElement getBreadcrumb2() {
        WebElement item = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbzLocator));
        return item;
    }

    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement customerName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement fileName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fileNameField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrganizationField));

        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Sample Upload Failed Files Page: Customer Name field not displayed",customerName.isDisplayed());
        AssertJUnit.assertTrue("Sample Upload Failed Files Page: File Name field not displayed",fileName.isDisplayed());
        AssertJUnit.assertTrue("Sample Upload Failed Files Page: Sales Org field not displayed",salesOrg.isDisplayed());

    }
}

