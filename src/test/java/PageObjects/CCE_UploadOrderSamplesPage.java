package PageObjects;

import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;

/**
 * Created by Daniel Ion on 11.03.2016.
 */
public class CCE_UploadOrderSamplesPage extends WBA_BasePage {

    //Locators
    static By browseFileLocator = By.id("BrowserVisible");
    By fileNameFieldLocator = By.id("filename");
    By fileNameOutputLocator = By.id("FileField");
    By uploadButtonLocator = By.cssSelector("#txtbox > div.btn_upload");
    //static By orderConfHeadingLocator = By.cssSelector("#content > div > h1");

    By shadeCodeLocator = By.cssSelector("#s2id_SampleOrderLine0ShadeId > a > span.select2-chosen");
    By shadeCodeLocator2 = By.cssSelector("#s2id_SampleOrderLine1ShadeId > a > span.select2-chosen");
    By shadeCodeLocator3 = By.cssSelector("#s2id_SampleOrderLine2ShadeId > a > span.select2-chosen");
    By shadeCodeLocator4 = By.cssSelector("#s2id_SampleOrderLine3ShadeId > a > span.select2-chosen");
    By shadeCodeLocator5 = By.cssSelector("#s2id_SampleOrderLine4ShadeId > a > span.select2-chosen");


    By orderLineLocator2 = By.cssSelector("#tabs > ul > li:nth-child(2) > a:nth-child(1)");
    By orderLineLocator3 = By.cssSelector("#tabs > ul > li:nth-child(3) > a:nth-child(1)");
    By orderLineLocator4 = By.cssSelector("#tabs > ul > li:nth-child(4) > a:nth-child(1)");
    By orderLineLocator5 = By.cssSelector("#tabs > ul > li:nth-child(5) > a:nth-child(1)");

    By MUMTypeLocator = By.cssSelector("#SampleOrderLine0MumTypeId10");     //cop
    By MUMTypeLocator2 = By.cssSelector("#SampleOrderLine1MumTypeId20");    //cone
    By MUMTypeLocator3 = By.cssSelector("#SampleOrderLine0MumTypeId30");    //vicone

    By requestTypeLocator = By.cssSelector("#SampleOrderLine0RequestTypeId1");     //color matching
    By requestTypeLocator2 = By.cssSelector("#SampleOrderLine2RequestTypeId2");    //sewing

    By directEnrichTypeLocator = By.cssSelector("#SampleOrderLine0IsDirectEnrich1");    //yes
    By directEnrichTypeLocator2 = By.cssSelector("#SampleOrderLine3IsDirectEnrich0");    //no

    By sourceOfSupTypeLocator = By.cssSelector("#SampleOrderLine1SosId30");     //hub
    By sourceOfSupTypeLocator2 = By.cssSelector("#SampleOrderLine0SosId50");    //lab
    By sourceOfSupTypeLocator3 = By.cssSelector("#SampleOrderLine2SosId40");    //warehouse

    public WebElement getSourceOfSupType() {
        return driver.findElement(sourceOfSupTypeLocator);
    }

    public WebElement getSourceOfSupType2() {
        return driver.findElement(sourceOfSupTypeLocator2);
    }

    public WebElement getSourceOfSupType3() {
        return driver.findElement(sourceOfSupTypeLocator3);
    }

    public WebElement getDirectEnrichType() {
        return driver.findElement(directEnrichTypeLocator);
    }

    public WebElement getDirectEnrichType2() {
        return driver.findElement(directEnrichTypeLocator2);
    }

    public WebElement getRequestType() {
        return driver.findElement(requestTypeLocator);
    }

    public WebElement getRequestType2() {
        return driver.findElement(requestTypeLocator2);
    }

    public WebElement getMUMType() {
        return driver.findElement(MUMTypeLocator);
    }

    public WebElement getMUMType2() {
        return driver.findElement(MUMTypeLocator2);
    }

    public WebElement getMUMType3() {
        return driver.findElement(MUMTypeLocator3);
    }

    public WebElement getShadeCode() {
        return driver.findElement(shadeCodeLocator);
    }

    public WebElement getShadeCode2() {
        return driver.findElement(shadeCodeLocator2);
    }

    public WebElement getShadeCode3() {
        return driver.findElement(shadeCodeLocator3);
    }

    public WebElement getShadeCode4() {
        return driver.findElement(shadeCodeLocator4);
    }

    public WebElement getShadeCode5() {
        return driver.findElement(shadeCodeLocator5);
    }

    public WebElement getOrderLine2() {
        return driver.findElement(orderLineLocator2);
    }

    public WebElement getOrderLine3() {
        return driver.findElement(orderLineLocator3);
    }

    public WebElement getOrderLine4() {
        return driver.findElement(orderLineLocator4);
    }

    public WebElement getOrderLine5() {
        return driver.findElement(orderLineLocator5);
    }


    public CCE_UploadOrderSamplesPage(WebDriver passedDriver) {
        super(passedDriver);
    }

    public WebElement getBrowseFile() {
        //find and return element
        return driver.findElement(browseFileLocator);
    }

    public CCE_UploadOrderSamplesPage setFilePath(String filePath) throws AWTException {
        //Wait for element to be available
        WebElement field = Wait.visible(driver, fileNameOutputLocator);
        //Type filepath
        driver.findElement(fileNameFieldLocator).sendKeys(filePath);

        //Click away to allow update
        By textLocator = By.cssSelector("#txtbox > p");
        driver.findElement(textLocator).click();

        return this;
    }

    public CCE_MappingAlert pressUpload() {
        //Wait for button to be clickable
        WebElement upload = Wait.clickable(driver, uploadButtonLocator);
        //Click button
        Actions clickUpload = new Actions(driver);
        clickUpload.click(upload).build().perform();

        return new CCE_MappingAlert(driver);
    }


}
