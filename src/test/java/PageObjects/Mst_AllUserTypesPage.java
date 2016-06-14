
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class Mst_AllUserTypesPage extends WBA_BasePage {
    
    //Locators
    By userTypeField = By.id("filterUserType");
    By requesterTypeField = By.id("s2id_filterUserCategoryId");
    By statusField = By.id("s2id_filterStatusId");
    By lastUpdatedFromField = By.id("filterUpdatedFrom");
    By lastUpdatedToField = By.id("filterUpdatedTo");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By newUserButton = By.cssSelector("#content > div.actions > ul > li > a");
    By noRecords = By.className("norec");
    By flashMessage = By.id("flashMessage");
    
    public Mst_AllUserTypesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public String getMessage() {
        try {
            WebElement wait = Wait.visible(driver,flashMessage);
            return wait.getText();
        } catch (TimeoutException e) {
            return "not found";
        }
        
    }
    
    public Mst_AllUserTypesPage setUserType(String item) {
        CommonTask.setInputField(driver, userTypeField, item);
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_AllUserTypesPage setRequesterType(String item) {
        CommonTask.setSearchField(driver, requesterTypeField, item);
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_AllUserTypesPage setStatus(String item) {
        CommonTask.setSearchField(driver, statusField, item);
        return new Mst_AllUserTypesPage(driver);
    }
   
    public Mst_AllUserTypesPage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_AllUserTypesPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_EditUserTypePage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1)");
        
        WebElement editBtn = Wait.clickable(driver,editButton);
        editBtn.click();
        
        return new Mst_EditUserTypePage(driver);
    }
    
    public boolean pressDelete(String item) {
        By userTypeCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(2)");
        By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td.actions > a:nth-child(3) > span");
        
        WebElement userType = Wait.visible(driver,userTypeCell);
        
        if (userType.getText().equals(item)) {
            driver.findElement(deleteButton).click();
            Alert alert = Wait.alert(driver);
            alert.accept();
            return true;
        }
        return false;
    }
    
    public Mst_AddUserTypePage pressNewUserType() {
        WebElement wait = Wait.clickable(driver,newUserButton);
        driver.findElement(newUserButton).click();
        return new Mst_AddUserTypePage(driver);
    }
    
    public boolean typePresent(String type) {
        
        for (int i = 2; i < 10; i++) {
            By typeCellLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            
            String typeFromTable = driver.findElement(typeCellLocator).getText();
            
            if (typeFromTable.equals(type)) {
                return false;
            }
        }
        
        return true;
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,userTypeField);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement userType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        WebElement requesterType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterTypeField));
        WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement updatedFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpdatedFromField));
        WebElement updatedTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpdatedToField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("All User Types Page: User Type field not displayed correctly",userType.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Requester Type field not displayed correctly",requesterType.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Status field not displayed correctly",status.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Last Updated From field not displayed correctly",updatedFrom.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Last Updated To field not displayed correctly",updatedTo.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Search button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("All User Types Page: Reset button not displayed correctly",reset.isDisplayed());
    }
    
    public boolean recordsAppear() {
        try {
            WebElement wait = Wait.visible(driver,noRecords);
            return false;
        } catch (TimeoutException t) {
            return true;
        }
    }

    public void deleteUser(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Users matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(DataItems.autoUserType);
            setUserType(DataItems.autoUserType);
            pressSearch();
            waitForElement();
        }
        System.out.println("Users cleared");
    }
    
}
