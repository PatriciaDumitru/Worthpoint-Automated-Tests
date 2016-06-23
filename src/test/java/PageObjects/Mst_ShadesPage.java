
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_ShadesPage extends WBA_BasePage {
    
    //Locators
    By shadeCardField = By.id("s2id_filterShadeCardId");
    By shadeNameField = By.id("filterShadeName");
    By shadeCodeField = By.id("filterShadeCode");

    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#content > div.actions > ul > li:nth-child(2) > a");
    By newShadeButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By redValueField = By.id("filterColorRatioR");
    By greenValueField = By.id("filterColorRatioG");
    By blueValueField = By.id("filterColorRatioB");
    By standardTypeField = By.id("filterStandardType");
    By typeCodeField = By.id("filterTypeCode");

    By dateFromField = By.id("filterUpdatedFrom");
    By dateToField = By.id("filterUpdatedTo");
    
    public Mst_ShadesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_ShadesPage setShadeCode(String item) {
        CommonTask.setInputField(driver,shadeCodeField,item);
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage setShadeCard(String item) {
        CommonTask.setSearchField(driver,shadeCardField,item);
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage setShadeName(String item) {
        CommonTask.setInputField(driver, shadeNameField, item);
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage pressSearch() {
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage pressReset() {
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        reset.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(export).build().perform();
        
        By xlsxLocator = By.linkText("XLSX");
        WebElement xlsx = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsxLocator));
        xlsx.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_ImportShadesPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportShadesPage(driver);
    }
    
    public Mst_AddShadePage pressNewShade() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeButton));
        element.click();
        
        return new Mst_AddShadePage(driver);
    }
    
    public int getRow(String code) {
        By shadeCodeHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(shadeCodeHeader));
        AssertJUnit.assertTrue("Shades Page: Shade Code column has moved, update locators",header.getText().equals("Shade Code"));
        
        By countLocator = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(countLocator);
        
        for (int i = 0; i < count; i++) {
            By shadeCode = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+2)+") > td:nth-child(4)");
            WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(shadeCode));
            
            if (element.getText().equals(code)) {
                return (i+2);
            }
            
        }
        return -1;
        
    }
    
    public String getShadeCode(int row) {
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        
        AssertJUnit.assertTrue("Shades Page: Shade Code column has moved, update locators",header.getText().equals("Shade Code"));
        
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(4)");
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return cell.getText();
        
    }
    
    public Mst_EditShadePage pressEdit(int row) {
        By selector = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        element.click();
        
        return new Mst_EditShadePage(driver);
    }
    
    public Mst_ShadesPage pressDelete(int row) {
        By selector = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        element.click();

        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        return new Mst_ShadesPage(driver);
    }
     
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement shadeCard = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardField));
        WebElement shadeName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeNameField));
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement rgbValueR = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(redValueField));
        WebElement rgbValueG = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(greenValueField));
        WebElement rgbValueB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(blueValueField));
        WebElement stdType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(standardTypeField));
        WebElement typeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(typeCodeField));
        WebElement dateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dateFromField));
        WebElement dateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dateToField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement importbtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement exportbtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newShadeBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Shades Page: Shade Card field not displayed",shadeCard.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Shade Name field not displayed",shadeName.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Shade Code field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Import button not displayed",importbtn.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Export button not displayed",exportbtn.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: New Shade button not displayed",newShadeBtn.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: RGB value Red field not displayed",rgbValueR.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: RGB value Green field not displayed",rgbValueG.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: RGB value Blue field not displayed",rgbValueB.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Standard Type field not displayed",stdType.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Type Code field not displayed",typeCode.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Date From field not displayed",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Shades Page: Date To field not displayed",dateTo.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardField));
    }

    public Mst_AddShadePage setRedValue(String item) {
        CommonTask.setInputField(driver,redValueField,item);
        return new Mst_AddShadePage(driver);
    }

    public Mst_AddShadePage setGreenValue(String item){
        CommonTask.setInputField(driver,greenValueField,item);
        return new Mst_AddShadePage(driver);
    }

    public Mst_AddShadePage setBlueValue(String item) {
        CommonTask.setInputField(driver,blueValueField,item);
        return new Mst_AddShadePage(driver);
    }

    public Mst_AddShadePage setStandardType(String item) {
        CommonTask.setInputField(driver,standardTypeField,item);
        return new Mst_AddShadePage(driver);
    }

    public Mst_AddShadePage setTypeCode(String item) {
        CommonTask.setInputField(driver,typeCodeField,item);
        return new Mst_AddShadePage(driver);
    }

    public void deleteShade(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Shades matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setShadeCard("GCR");
            setShadeName("AutoShade");
            setRedValue("135");
            setGreenValue("135");
            setBlueValue("135");
            setStandardType("Auto");
            setTypeCode("AUTO");
            pressSearch();
            waitForElement();
        }
        System.out.println("Shades cleared");
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

        for(int i=2; i <= nrOfCol - 2;i++){

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
