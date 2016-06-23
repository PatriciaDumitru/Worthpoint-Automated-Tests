
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_CoatsUsersPage extends WBA_BasePage {
    
    //Locators
    By countryNameField = By.id("s2id_filterCountry");
    By userTypeField = By.id("s2id_filterUserTypeId");
    By statusField = By.id("s2id_filterStatusId");
    By salesOrgField = By.id("s2id_filterSalesOrg");
    By usernameField = By.id("filterUsername");
    By usernameCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(2)");
    By lastUpdatedFromField = By.id("filterUpdatedFrom");
    By lastUpdatedToField = By.id("filterUpdatedTo");
    By hubNameField = By.id("s2id_filterHub");
    By cancelAllowedField = By.id("s2id_filterIsOrderCancelAllowed");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCoatsUserButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By noRecords = By.xpath("//*[@id=\"content\"]/div[2]/div");

    public Mst_CoatsUsersPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getCountryNameField() {
        return driver.findElement(countryNameField);
    }
    
    public WebElement getUserTypeField() {
        return driver.findElement(userTypeField);
    }
    
    public WebElement getStatusField() {
        return driver.findElement(statusField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getUsernameField() {
        return driver.findElement(usernameField);
    }
    
    public WebElement getUsernameCell() {
        return driver.findElement(usernameCell);
    }
    
    public WebElement getLastUpdatedFromField() {
        return driver.findElement(lastUpdatedFromField);
    }
    
    public WebElement getLastUpdatedToField() {
        return driver.findElement(lastUpdatedToField);
    }
    
    public WebElement getHubNameField() {
        return driver.findElement(hubNameField);
    }
    
    public WebElement getCancelAllowedField() {
        return driver.findElement(cancelAllowedField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getImportButton() {
        return driver.findElement(importButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public WebElement getNewCoatsUserButton() {
        return driver.findElement(newCoatsUserButton);
    }
    
    public Mst_CoatsUsersPage setUsername(String item) {
        driver.findElement(usernameField).clear();
        CommonTask.setInputField(driver,usernameField,item);
        return new Mst_CoatsUsersPage(driver);
    }
    
    public Mst_CoatsUsersPage pressSearch() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        
        return new Mst_CoatsUsersPage(driver);
    }
    
    public Mst_CoatsUsersPage pressReset() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        
        return new Mst_CoatsUsersPage(driver);
    }
    
    public Mst_EditCoatsUserPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1)");
        
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        field.click();
        
        return new Mst_EditCoatsUserPage(driver);
    }
    
    public Mst_CoatsUsersPage pressDelete(String username) {
        for (int i = 2; i < 8; i++) {
            By usernameCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(usernameCell));
            if (cell.getText().equals(username)) {
                By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td.actions > a:nth-child(3)");
                WebElement delete = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteButton));
                delete.click();
                Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
                alert.accept();
                return new Mst_CoatsUsersPage(driver);
            }
        }
        return new Mst_CoatsUsersPage(driver);
    }
    
    public Mst_AddCoatsUserPage pressAddCoatsUser() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCoatsUserButton));
        driver.findElement(newCoatsUserButton).click();
        
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(export).build().perform();
        
        By locator = By.cssSelector("#export-menu > ul > li:nth-child(4) > a");
        
        driver.findElement(locator).click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        button.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement countryName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryNameField));
        WebElement userType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement username = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(usernameField));
        WebElement lastUpdatedFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpdatedFromField));
        WebElement lastUpdatedTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpdatedToField));
        WebElement hubName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubNameField));
        WebElement cancelAllowed = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelAllowedField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newUser = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCoatsUserButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Coats Users Page: Country Name field not displayed correctly",getCountryNameField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: User Type field not displayed correctly",getUserTypeField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Status field not displayed correctly",getStatusField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Sales Organisation field not displayed correctly",getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Username field not displayed correctly",getUsernameField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Last Updated From field not displayed correctly",getLastUpdatedFromField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Last Updated To field not displayed correctly",getLastUpdatedToField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Hub Name field not displayed correctly",getHubNameField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Cancel Allowed field not displayed correctly",getCancelAllowedField().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Import Button not displayed correctly",getImportButton().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: Export Button not displayed correctly",getExportButton().isDisplayed());
        AssertJUnit.assertTrue("Coats Users Page: New Coats User Button not displayed correctly",getNewCoatsUserButton().isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
    }

    public boolean noEntry(){
        String noRecordsText = driver.findElement(noRecords).getText().trim();

        if(noRecordsText.equals("No records found.")){
            return true;
        }

        return false;
    };

    public Mst_CoatsUsersPage pressDelete2(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));

        element.click();

        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        return new Mst_CoatsUsersPage(driver);
    }

    public void deleteCoatsUser(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Coats User name is already used");
            System.out.println("Deleting current Coats User");
            pressDelete2(2);
            waitForElement();
        }
        System.out.println("Coats User cleared");
    }

    public static boolean checkRecordDetails(WebDriver driver,String... detail){

        //Getting the number of table columns
        int nrOfCol = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td")).size();
        System.out.println(nrOfCol+" found");

        //Variable used to return the result of the method
        boolean check = false;

        //The i starts from the column you want to check
        //Extract from the nrOfCol the number of the final columns you don't need to check (usually Status and Action columns)
        //The columns you don't check must be in a successive order

        for(int i=2; i <= nrOfCol - 5;i++){

            //fieldLocator is the location of each table column
            By fieldLocator = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td["+i+"]");

            //Getting the text from columns
            String text = driver.findElement(fieldLocator).getText().trim();
            System.out.println(text);
            System.out.println(detail[i-2]);

            //Checking if the column matches the detail data, detail starts from detail[0]
            if(text.equals( detail[i-2] )){
                System.out.println("Column number "+ i +" checked");
                check = true;
            } else {
                System.out.println("Column number "+ i +" does not match the data");
                check = false;
                break;
            }
        }

        return check;
    }
}
