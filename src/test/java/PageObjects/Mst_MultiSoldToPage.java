
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_MultiSoldToPage extends WBA_BasePage {
 
    By salesOrgField = By.id("s2id_filterMultisoldtoUserSalesOrgId");
    By custNameField = By.id("s2id_filterMultisoldtoUserCustomerId");
    By userNameField = By.id("filterUsername");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By newUserButton = By.cssSelector("#content > div.actions > ul > li > a");

    By countryField = By.id("s2id_filterCountry");
    
    public Mst_MultiSoldToPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_MultiSoldToPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_MultiSoldToPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_MultiSoldToPage setUserName(String item) {
        CommonTask.setInputField(driver,userNameField,item);
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_MultiSoldToPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_AddMultiUserPage pressNewUser() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newUserButton));
        element.click();
        
        return new Mst_AddMultiUserPage(driver);
    }
    
    public int getRow(String username) {
        
        By usernameHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(usernameHeader));
        AssertJUnit.assertTrue("Multi Sold To Users Page: Username field has moved. Update locators",header.getText().equals("User Name"));
        
        By countLocator = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left"); 
        int recordCount = getRecordCount(countLocator);
        int tableCount = (recordCount>10) ? 10 : recordCount;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By usernameField = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = Wait.visible(driver,usernameField);
            if (cell.getText().equals(username)) {
                return i;
            }
        }    
        return -1;      
    }
    
    public Mst_EditMultiUserPage pressEdit(int row) {
        By edit = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1)");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(edit));
        element.click();
        
        return new Mst_EditMultiUserPage(driver);
    }
    
    public Mst_MultiSoldToPage pressDelete(int row) {
        By edit = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(edit));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_MultiSoldToPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement userName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement newUser = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newUserButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Multi Sold To Users Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Multi Sold To Users Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Multi Sold To Users Page: User Name Field not displayed",userName.isDisplayed());
        AssertJUnit.assertTrue("Multi Sold To Users Page: Search Button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Multi Sold To Users Page: Reset Button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Multi Sold To Users Page: New Multi Sold To User Button not displayed",newUser.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userNameField));
    }

    public Mst_AddMultiUserPage setCountry(String item) throws InterruptedException {
        CommonTask.setDropDownFieldAlt(driver, countryField, item);
        return new Mst_AddMultiUserPage(driver);
    }

    public void deletedMultiSoUs(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Multi Sold to User already used");
            System.out.println("Deleting current braMulti Sold to Usernd");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("Multi Sold to User cleared");
    }

}
