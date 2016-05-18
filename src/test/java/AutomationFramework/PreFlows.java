package AutomationFramework;

/**
 * Created by Andrei
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class PreFlows {

    WebDriver driver;

    //Locators
    //General
    By chooseTheOtherProfile = By.id("access_type"); //CCE or Ecomm depending on which page you are
    By logoutButton = By.xpath(".//*[@id='header']/div/span[2]/span[5]/a"); //LogoutButton

    //Sales Org Masters Page
    By salesOrgIdField = By.id("filterSalesOrgName");
    By filterSalesOrgButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editSalesOrgButton = By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[8]/a[1]/span");

    //Sales Org Edit Page
    By salesOrgNameEditPageField = By.id("SalesOrgSalesOrgName");
    By partialStockCheckBox = By.id("SalesOrgPartialStockCheck");
    By forwardDaysCheckBox = By.id("SalesOrgForwardOrderDaysCheck");
    By forwardDaysField = By.id("SalesOrgForwardOrderDays");
    By saveSalesOrgButton = By.id("save");
    By approvelCheckBoxSalesOrg = By.id("SalesOrgSalesApprovalWorkflow");
    By getCallOffOrderCheckBoxSalesOrg = By.id("SalesOrgOffOrder");
    By ordersWithoutShadeCheckBoxSalesOrg = By.id("SalesOrgEnabledOrdersWithoutShade");
    By autoEnrichOptionsCheckBox = By.id("SalesOrgAutoEnrichOptions");
    By salesOrgShippingCond = By.id("SalesOrgShippingConditionId");

    //Customer Masters Page
    By customerNameField = By.id("filterCustomerCustomerName");
    By filterCustomerButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editCustomerButton= By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[7]/a[1]/span");

    //Customer Edit Page
    By customerNameEditPageField = By.id("CustomerCustomerName");
    By mdqCheckBox = By.id("CustomerCustMdqEnabled");
    By SAPContractValidityCheckBox = By.id("CustomerOffOrderType");
    By approvelCheckBoxCust = By.id("CustomerApprovalWorkflow");
    By saveCustomerButton = By.xpath(".//*[@id='CustomerEditForm']/div[4]/ul/li[1]/input");
    By deliveryPlant = By.id("CustomerDeliveryPlantId");
    By callOffOrderCheckBoxCust = By.id("CustomerOffOrder");
    By autoEnrichNoneOption = By.id("CustomerAutoEnrichOptions0");
    By ordersWithoutShadeCheckBoxCust = By.id("CustomerCusOrdersWithoutShade");
    By enrichToHubRadiobutton = By.id("CustomerAutoEnrichStockId1");
    By enrichToWarehouseRadiobutton = By.id("CustomerAutoEnrichStockId2");
    By enrichToBothRadiobutton = By.id("CustomerAutoEnrichStockId3");
    By custShippingCond = By.id("CustomerShippingConditionId");

    //Manual Entry Page
    By buyersField = By.xpath(".//*[@id='s2id_BuyerId']/a/abbr");
    public static By normalOrderRadioButton = By.cssSelector("#normal_check");
    public static By contractOrderRadioButton = By.cssSelector("#contract_check");




    // --------------------------------- FLOW Of USING PREFLOWS -----------------------------
    // ADD FLAG LOCATOR FOR SALES ORG / CUST
    // CREATE METHODS TO DISABLE/ENABLE FLAG
    // CUSTOMIZE YOUR METHOD FOR MASTER SETUP

    // EXAMPLE
    // GO SALES ORG AND EDIT
    // ENABLE/DISABLE WHAT FLAGS YOU WISH
    // SAVE SALES ORG
    // GO CUSTOMER AND EDIT
    // ENABLE/DISABLE WHAT FLAGS YOU WISH
    // SAVE CUSTOMER






    // -------------------- Changes between CCE and ECOMM
    public void chooseTheOtherProfile(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(chooseTheOtherProfile)).build().perform();
    }


    //  -------------------- Logout
    public void logoutAction(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(logoutButton)).build().perform();
    }



    // -------------------- Quick Edit Sales Org

    public void goToSalesOrgAndEdit(WebDriver driver, String salesOrg){
        driver.get(DataItems.mastersSalesOrgURL);
        setSalesOrg(driver,salesOrg);
        filterSalesOrg(driver);
        editSalesOrg(driver);
        waitForSalesOrgEditPageToOpen(driver);
    }

    public void setSalesOrg(WebDriver driver, String item) {
        Wait.clickable(driver,salesOrgIdField);
        CommonTask.setInputField(driver, salesOrgIdField, item);
    }

    public void filterSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterSalesOrgButton)).build().perform();
    }

    public void editSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editSalesOrgButton)).build().perform();
    }

    public void waitForSalesOrgEditPageToOpen(WebDriver driver) {
        WebElement wait = Wait.clickable(driver, salesOrgNameEditPageField);
    }



    // -------------------- Quick Edit Customer

    public void goToCustomerAndEdit(WebDriver driver, String customer){
        driver.get(DataItems.mastersCustomerURL);
        setCustomerName(driver, customer);
        filterCustomerName(driver);
        editCustomerName(driver);
        waitForCustomerEditPageToOpen(driver);
    }

    public void setCustomerName(WebDriver driver, String item) {
        CommonTask.setInputField(driver, customerNameField, item);
    }

    public void filterCustomerName(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterCustomerButton)).build().perform();
    }

    public void editCustomerName(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editCustomerButton)).build().perform();
    }

    public void waitForCustomerEditPageToOpen(WebDriver driver) {
        WebElement wait = Wait.clickable(driver,customerNameEditPageField);
    }



    // -------------------- Save Sales Org
    public void saveSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveSalesOrgButton)).build().perform();
    }

    // -------------------- Save Customer
    public void saveCustomer(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveCustomerButton)).build().perform();
    }




    // -------------------- Sales Org Flags Enable / Disable and Others from Edit Page

    public void enableApprovalCheckBoxForSalesOrg(WebDriver driver){
        CommonTask.setCheckBox(driver, approvelCheckBoxSalesOrg);
    }

    public void disableApprovalCheckBoxForSalesOrg(WebDriver driver){
        CommonTask.unSetCheckBox(driver, approvelCheckBoxSalesOrg);
    }


    public void enableCallOffOrderCheckboxForSalesOrg(WebDriver driver){
        CommonTask.setCheckBox(driver,getCallOffOrderCheckBoxSalesOrg);
    }

    public void disableCallOffOrderCheckboxForSalesOrg(WebDriver driver){
        CommonTask.unSetCheckBox(driver,getCallOffOrderCheckBoxSalesOrg);
    }


    public void enableEOwSForSalesOrg(WebDriver driver){
        CommonTask.setCheckBox(driver, ordersWithoutShadeCheckBoxSalesOrg);
    }

    public void disableEOwSForSalesOrg(WebDriver driver){
        CommonTask.unSetCheckBox(driver, ordersWithoutShadeCheckBoxSalesOrg);
    }

    public void setDeliveryPlant(WebDriver driver, String plant) {
        CommonTask.setDropDownField(driver, deliveryPlant, plant);
    } //Set Delivery Plant From Sales Org Edit page

    public void enableAutoEnrichToOptions(WebDriver driver){
        CommonTask.setCheckBox(driver,autoEnrichOptionsCheckBox);
    }

    public void disableAutoEnrichOptions(WebDriver driver){
        CommonTask.unSetCheckBox(driver,autoEnrichOptionsCheckBox);
    }

    public void selectShippingCondForSalesOrg (WebDriver driver, String item){
        CommonTask.setDropDownField(driver, salesOrgShippingCond,item);
    }




   // -------------------- Customer Flags Enable / Disable

    public void enableMOQCheckBoxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, mdqCheckBox);
    }

    public void disableMOQCheckBoxForCust(WebDriver driver){
        CommonTask.unSetCheckBox(driver, mdqCheckBox);
    }


    public void enableApprovalCheckBoxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, approvelCheckBoxCust);
    }

    public void disableApprovalCheckBoxForCust(WebDriver driver){
        CommonTask.unSetCheckBox(driver, approvelCheckBoxCust);
    }


    public void enableCallOffOrderCheckboxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver,callOffOrderCheckBoxCust);
    }

    public void disableCallOffOrderCheckboxForCust(WebDriver driver){
        CommonTask.unSetCheckBox(driver,callOffOrderCheckBoxCust);
    }
    public void enableSAPContractValidityCheckBoxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, SAPContractValidityCheckBox);
    }

    public void selectShippingCondForCust (WebDriver driver, String item){
        CommonTask.setDropDownField(driver, custShippingCond,item);
    }


    public void enableEOwSForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, ordersWithoutShadeCheckBoxCust);
    }

    public void disableEOwSForCust(WebDriver driver){
        CommonTask.unSetCheckBox(driver, ordersWithoutShadeCheckBoxCust);
    }


    public void disableAutoEnrichCheckBoxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, autoEnrichNoneOption);
    }

    public void enableEnrichToBoth(WebDriver driver){
        CommonTask.setCheckBox(driver, enrichToBothRadiobutton);
    }
    public void enableEnrichToWarehouse(WebDriver driver){
        CommonTask.setCheckBox(driver, enrichToWarehouseRadiobutton);
    }
    public void enableEnrichToHub(WebDriver driver){
        CommonTask.setCheckBox(driver, enrichToHubRadiobutton);
    }



    // -------------------- OTHERS

    public void removeBuyers(WebDriver driver){         //Remove Buyers from Ecomm -> Manual Entry Page
        Actions action = new Actions(driver);
        action.click(driver.findElement(buyersField)).build().perform();
    }

    public void selectNormalOrderRadioButton(WebDriver driver){         //Select Normal Orders for a Contract Call Customer
        CommonTask.setCheckBox(driver, normalOrderRadioButton);         //In Ecomm -> Manual Entry Page
    }

    public void selectContractOrderRadioButton(WebDriver driver) {      //Select Contract Orders for a Contract Call Customer
        CommonTask.setCheckBox(driver, contractOrderRadioButton);       //In Ecomm -> Manual Entry Page
    }





    // -------------------- CUSTOM METHODS DEPENDING ON MASTER SETUP

    public void activateEnrichToForSalesOrgAndCustomer(WebDriver driver, String salesOrg, String customer, String custEnrichoOption){
        goToSalesOrgAndEdit(driver, salesOrg);
        enableAutoEnrichToOptions(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        if (custEnrichoOption == "Both")enableEnrichToBoth(driver);
        else if (custEnrichoOption == "Hub")enableEnrichToHub(driver);
        else if (custEnrichoOption == "Warehouse")enableEnrichToWarehouse(driver);
        else System.out.println("Customer Enrich To Otion does not exist...");
        disableAutoEnrichCheckBoxForCust(driver);
        saveCustomer(driver);
    }


    public void activateCallOffOrderForCustomer(WebDriver driver, String customer) {
        goToCustomerAndEdit(driver, customer);
        enableCallOffOrderCheckboxForCust(driver);
        saveCustomer(driver);
    }

    public void activateCallOffOrderForSalesOrg(WebDriver driver, String salesOrg){
        goToSalesOrgAndEdit(driver, salesOrg);
        enableCallOffOrderCheckboxForSalesOrg(driver);
        saveSalesOrg(driver);
    }

    public void deactivateCallOffOrderForSalesOrg(WebDriver driver, String salesOrg){
        goToSalesOrgAndEdit(driver, salesOrg);
        disableCallOffOrderCheckboxForSalesOrg(driver);
        saveSalesOrg(driver);
    }
    public void deactivateCallOffOrderForCustomer(WebDriver driver, String customer) {
        goToCustomerAndEdit(driver, customer);
        disableCallOffOrderCheckboxForCust(driver);
        saveCustomer(driver);
    }


    public void deActivateCallOffOrderForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        disableCallOffOrderCheckboxForCust(driver);
        saveCustomer(driver);
    }

    public void activateCallOffOrderAndDeactivateApprovalForCustomer(WebDriver driver, String customer) {
        goToCustomerAndEdit(driver, customer);
        enableCallOffOrderCheckboxForCust(driver);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void deActivateCallOffOrderAndApprovalForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        disableCallOffOrderCheckboxForCust(driver);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);

    }

    public void enableEnableOrdersWithoutShadeForSalesOrgandCust(WebDriver driver,String salesOrg,String customer){
        //This is used as a prerequisite for Ecomm_UO_EOwS_Test class
        goToSalesOrgAndEdit(driver, salesOrg);
        enableEOwSForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        enableEOwSForCust(driver);
        disableMOQCheckBoxForCust(driver);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void disableEnableOrdersWithoutShadeForSalesOrgandCust(WebDriver driver,String salesOrg,String customer){
        //This is used as a prerequisite for Ecomm_UO_Exceptions_Test class
        goToSalesOrgAndEdit(driver, salesOrg);
        disableEOwSForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        disableEOwSForCust(driver);
        disableMOQCheckBoxForCust(driver);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void setShipCondForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer, String saleOrgItem, String custItem){
        /**
         * Created by Stefan on 9th May 2016
         * This is used for Shipping Conditions tests - method will select a Shipping Condition under SalesOrg and Customer
         */
        goToSalesOrgAndEdit(driver,salesOrg);
        selectShippingCondForSalesOrg(driver, saleOrgItem);
        saveSalesOrg(driver);

        goToCustomerAndEdit(driver, customer);
        selectShippingCondForCust(driver, custItem);
        saveCustomer(driver);
    }

    public void enableApprovelCheckBoxForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer){
        goToSalesOrgAndEdit(driver,salesOrg);
        enableApprovalCheckBoxForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        enableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void disableApprovelCheckBoxForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer){
        goToSalesOrgAndEdit(driver,salesOrg);
        disableApprovalCheckBoxForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void setDeliveryPlantAndEnableApprovelCheckboxForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer, String plant){
        goToSalesOrgAndEdit(driver,salesOrg);
        enableApprovalCheckBoxForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        setDeliveryPlant(driver, plant);
        enableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void enableMOQForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        enableMOQCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void enableSAPContractValidityForCust(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        enableSAPContractValidityCheckBoxForCust(driver);
        saveCustomer(driver);
    }






    public void disableMOQForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        disableMOQCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void deActivateAutoEnrichForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        disableAutoEnrichCheckBoxForCust(driver);
        saveCustomer(driver);
    }

}
