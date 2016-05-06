package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.google.common.base.Verify;
import net.sf.cglib.asm.ByteVector;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_SOS_ServeArticle_Page extends WBA_BasePage  {

    public CCE_SOS_ServeArticle_Page(WebDriver passedDriver) {
            super(passedDriver);
        }

    // ------------------------------------- Locators

    By newEntry = By.xpath(".//*[@id='OurStockListForm']/div[3]/ul/li[3]/a");  //has the same selector for both HUB And Warehouse
    By deleteAllButton = By.cssSelector("#OurStockListForm > div.actions > ul > li:nth-child(4) > input[type=\"submit\"]"); //has the same selector for both HUB And Warehouse
    By selectAllCheckBox = By.id("select_all"); //has the same selector for both HUB And Warehouse
    By filterPlantField = By.xpath(".//*[@id='s2id_filterPlantId']/a/span[1]");
    By searchButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");

    By currentSOS = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(10)"); //In Order Status Page
    By viewOrder = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(16) > a > span"); //In Order Status Page
    By article = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(2)"); //In View Order Page

    //Add New Article Locators
    By newArticleButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    By articleName = By.id("SalesOrgMaterialArticle");
    By articleSalesOrg = By.id("SalesOrgMaterialSalesOrgId");
    By articlePlant = By.id("SalesOrgMaterialPlantId");
    By articleBrand = By.id("SalesOrgMaterialBrandId");
    By articleTicket = By.id("SalesOrgMaterialTicketId");
    By articleMumType = By.id("SalesOrgMaterialMumTypeId");
    By articleLenght = By.id("SalesOrgMaterialLengthId");
    By articleCCEandEcommRadioButton = By.id("SalesOrgMaterialBulkSampleId3");
    By saveNewArticleButton = By.cssSelector("#SalesOrgMaterialAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");





    // ------------------------------------- Locators in Methods to select Data for Warehouse Stocks or Hub Stocks when creating new Entry

    public void newEntry(){
        driver.findElement(newEntry).click();
    }

    public void setSalesOrg(String location, String item) {
        By salesOrgField = By.id(location + "StockSalesOrgId");
        CommonTask.setDropDownField(driver, salesOrgField, item);
    }

    public void setPlant(String location, String item) {
        By plantField= By.id(location + "StockPlantId");
        CommonTask.setDropDownField(driver, plantField, item);
    }

    public void setArticle(String location, String item) {
        By articleField = By.id("s2id_" + location + "StockArticleId");
        CommonTask.setSearchField(driver, articleField, item);
    }

    public void setBrand(String location, String item) {
        By brandField = By.id(location + "StockBrandId");
        CommonTask.setDropDownField(driver, brandField, item);
    }

    public void setTicket(String location, String item) {
        By ticketField = By.id(location + "StockTicketId");
        CommonTask.setDropDownField(driver, ticketField, item);
    }

    public void setMUMType(String location, String item) {
        By mumTypeField = By.id(location + "StockMumTypeId");
        CommonTask.setDropDownField(driver, mumTypeField, item);
    }

    public void setShade(String location, String item ) {
        By shadeField = By.id("s2id_" + location + "StockShadeId");
        CommonTask.setSearchField(driver, shadeField, item);
    }

    public void setQuantity(String location, String item) {
        By quantityField = By.id(location + "StockQuantity");
        WebElement element = Wait.clickable(driver,quantityField);
        element.clear();

        CommonTask.setInputField(driver, quantityField, item);
    }

    public void saveNewEntry(String location) {
        By saveButton = By.cssSelector("#" + location + "StockAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
        driver.findElement(saveButton).click();
    }

    // ------------------------------------- Methods

    // Add new Entry to Hub/Warehouse (string location)
    public void setStockArticleAndSave(String location, String salesOrg, String plant, String article, String shade, String qty){
        newEntry();
        setSalesOrg(location, salesOrg);
        setPlant(location, plant);
        setArticle(location, article);
        setShade(location, shade);
        setQuantity(location, qty);
        saveNewEntry(location);
    }

    public WebElement getArticleFromOrder() {
        //find and return element
        return driver.findElement(article);
    }

    public WebElement getCurrentSOS(){
        //find and return element
        return driver.findElement(currentSOS);
    }

    public WebElement getArticleFromOrderLine(int lineNumber){
        By articleLine1 = By.cssSelector("#popup_content > div:nth-child(3) > table > tbody > tr:nth-child("+ lineNumber +") > td:nth-child(2)");
        //find and return element
        return driver.findElement(articleLine1);
    }

    public WebElement getCurrentSOSLine(int lineNumber){
        By lineOne = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(10)");
        By restOfLines = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(" + lineNumber + ") > td:nth-child(5)");
        //find and return element
        if (lineNumber == 2) return driver.findElement(lineOne);
        else return  driver.findElement(restOfLines);

    }

    public void pressViewOrder() {
        driver.findElement(viewOrder).click();
        CommonTask.waitForOverlay(driver);
    }

    public void filterArticlesByPlantAndDeleteAll(String plant){
        filterPlant(plant);
        searchArticles();
        checkSelectAllCheckBox();
        deleteAllArticles();

        Alert alert = Wait.alert(driver);
        alert.accept();
    }

    public void filterPlant(String item){
        CommonTask.setSearchField(driver, filterPlantField, item);
    }

    public void searchArticles(){
        driver.findElement(searchButton).click();
    }

    public void checkSelectAllCheckBox(){
        CommonTask.setCheckBox(driver, selectAllCheckBox);
    }

    public void deleteAllArticles(){
        driver.findElement(deleteAllButton).click();
    }


    // ------------------------------------------ Similar Article Methods

    public void newArticle(){
        driver.findElement(newArticleButton).click();
    }

    public void setNewArticleName(String name){
        CommonTask.setInputField(driver, articleName, name);
    }

    public void setNewArticleSalesOrg(String salesOrg){
        CommonTask.setDropDownField(driver, articleSalesOrg, salesOrg);
    }

    public void setNewArticlePlant(String plant){
        CommonTask.setDropDownField(driver, articlePlant, plant);
    }

    public void setNewArticleBrand(String brand){
        CommonTask.setDropDownField(driver, articleBrand, brand);
    }

    public void setNewArticleTicket(String ticket){
        CommonTask.setDropDownField(driver, articleTicket, ticket);
    }

    public void setNewArticleMuMType(String mum){
        CommonTask.setDropDownField(driver, articleMumType, mum);
    }

    public void setNewArticleLenght(String lenght){
        CommonTask.setDropDownField(driver, articleLenght, lenght);
    }

    public void setArticleToCCEandEcomm(){
        driver.findElement(articleCCEandEcommRadioButton).click();
    }

    public void saveNewArticle(){
        driver.findElement(saveNewArticleButton).click();
    }

    public void createNewArticle(String name, String salesOrg, String plant, String brand, String ticket,String mum, String lenght){
        driver.get(DataItems.newArticleURL);
        newArticle();
        setNewArticleName(name);
        setNewArticleSalesOrg(salesOrg);
        setNewArticlePlant(plant);
        setNewArticleBrand(brand);
        setNewArticleTicket(ticket);
        setNewArticleMuMType(mum);
        setNewArticleLenght(lenght);
        setArticleToCCEandEcomm();
        saveNewArticle();
    }






}
