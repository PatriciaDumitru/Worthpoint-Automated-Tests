
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Master_SalesOrgMaterialsPage extends WBA_MasterDataPage {
    
    By articleField = By.id("filterArticle");
    By salesOrgField = By.cssSelector("#s2id_filterSalesOrgId > a");
    
    public Master_SalesOrgMaterialsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public Master_SalesOrgMaterialsPage setArticle(String item) {
        CommonTask.setInputField(driver, articleField, item);
        return this;
    }
    
    public Master_SalesOrgMaterialsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Master_SalesOrgMaterialsPage pressSearch() {
        getSearchButton().click();
        return new Master_SalesOrgMaterialsPage(driver);
    }
    
    public Master_SalesOrgMaterialsPage pressReset() {
        getResetButton().click();
        return new Master_SalesOrgMaterialsPage(driver);
    }
    
    public String getHiddenTypes() {;
        
        By typeCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(7)");
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(typeCell));
        
        return driver.findElement(typeCell).getText();
        
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Sales Org. Materials Master Page: Article Field not displayed correctly", getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Sales Org. Materials Master Page: Sales Org Field not displayed correctly", getSalesOrgField().isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
    }
    
}
