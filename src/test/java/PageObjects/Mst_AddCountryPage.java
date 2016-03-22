
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class Mst_AddCountryPage extends WBA_BasePage {

    //Locators
    By countryNameField = By.id("s2id_CountryCountryName");
    By countryCodeField = By.id("CountryCountryCode");
    By languageField = By.id("CountryLanguageId");
    By saveButton = By.xpath("/html/body/div[1]/div[3]/form/div[3]/ul/li[1]/input");
    By cancelButton = By.cssSelector("#CountryAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCountryPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCountryPage setCountryName(String item) {
        CommonTask.setSearchField(driver,countryNameField,item);
        WebElement element=driver.findElement(countryCodeField);
        String elementval = element.getAttribute("value");
        System.out.print(elementval);
        Wait.textPresentInput3(driver,elementval, "VA");

        return new Mst_AddCountryPage(driver);
    }

    public Mst_AddCountryPage setCountryCode(String item) {
        CommonTask.setInputField(driver,countryCodeField,item);
        return new Mst_AddCountryPage(driver);
    }
    
    public Mst_CountriesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_CountriesPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();

        return new Mst_CountriesPage(driver);


    }

    public Mst_CountriesPage wait3Seconds() {
        driver.manage().timeouts().implicitlyWait(53, TimeUnit.HOURS);
        return new Mst_CountriesPage(driver);
    }
    public void checkFields() {
        WebElement countryName = Wait.clickable(driver,countryNameField);
        WebElement countryCode = Wait.clickable(driver,countryCodeField);
        WebElement language = Wait.clickable(driver,languageField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Country Page: Country Name field not displayed",countryName.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Language field not displayed",language.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement language = Wait.clickable(driver,languageField);
    }
    
}
