
package TestTemplates;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Ecomm_ManualEntryTemplate extends DriverFactory {
    
private String custName;
private String shipToPartyName;
private String requestorName;
private String buyers;
private String custPONo;

//True/false for values provided
boolean lineRefProvided;
boolean ymnProvided;
boolean articleProvided;
boolean brandProvided;
boolean ticketProvided;
boolean lengthProvided;
boolean finishProvided;
boolean shadeCodeProvided;
boolean qtyProvided;
boolean requiredDateProvided;
boolean styleNoProvided;
boolean otherInfoProvided;
boolean contractPOProvided;

private int lineCount;
    
    public Ecomm_ManualEntryTemplate(String[] testDetails,String[] custDetails,String[][] lineDetails) throws InterruptedException, IOException, Exception {
        //Work out which values have been provided
        /*for (int count = 0; count < lineDetails.length; count++) {
            switch (lineDetails[count][0]) {
                case "": lineRefProvided = false; break;
                default: lineRefProvided = true; break;
            }
            switch (lineDetails[count][1]) {
                case "": ymnProvided = false; break;
                default: ymnProvided = true; break;
            }
            switch (lineDetails[count][2]) {
                case "": articleProvided = false; break;
                default: articleProvided = true; break;
            }
            switch (lineDetails[count][3]) {
                case "": brandProvided = false; break;
                default: brandProvided = true; break;
            }
            switch (lineDetails[count][4]) {
                case "": ticketProvided = false; break;
                default: ticketProvided = true; break;
            }
            switch (lineDetails[count][5]) {
                case "": lengthProvided = false; break;
                default: lengthProvided = true; break;
            }
            switch (lineDetails[count][6]) {
                case "": finishProvided = false; break;
                default: finishProvided = true; break;
            }
            switch (lineDetails[count][7]) {
                case "": shadeCodeProvided = false; break;
                default: shadeCodeProvided = true; break;
            }
            switch (lineDetails[count][8]) {
                case "": qtyProvided = false; break;
                default: qtyProvided = true; break;
            }
            switch (lineDetails[count][9]) {
                case "": requiredDateProvided = false; break;
                default: requiredDateProvided = true; break;
            }
            switch (lineDetails[count][10]) {
                case "": styleNoProvided = false; break;
                default: styleNoProvided = true; break;
            }
            switch (lineDetails[count][11]) {
                case "": otherInfoProvided = false; break;
                default: otherInfoProvided = true; break;
            }
            switch (lineDetails[count][12]) {
                case "": contractPOProvided = false; break;
                default: contractPOProvided = true; break;
            }
            
            boolean[] itemsProvided = {lineRefProvided,ymnProvided,articleProvided,brandProvided,ticketProvided,
            lengthProvided,finishProvided,shadeCodeProvided,qtyProvided,requiredDateProvided,styleNoProvided,
            otherInfoProvided,contractPOProvided};
            
            String combination = identifyCombination(itemsProvided);
            System.out.println("Combination: " + combination);
        }*/
        
        runTest(testDetails,custDetails,lineDetails);
        
    }
    
    public String identifyCombination(boolean[] provided) {
        if (provided[1] && !provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && !provided[7] && provided[8] && !provided[0] && !provided[12]) {
            System.out.println("Your Material Number with all master data test");
            return "Your Material Number with master data shade";           
        } else if (provided[1] && !provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && provided[7] && provided[8] && !provided[0] && !provided[12]) {
            System.out.println("Your Material Number without master shade code test");
            return "Your Material Number without master data shade";
        } else if (!provided[1] && provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && provided[7] && provided[8] && !provided[0] && !provided[12]) {
            System.out.println("Article and shade code test");
            return "Article and shade code";
        } else if (!provided[1] && !provided[2] && provided[3] && provided[4] && provided[5] && provided[6] && provided[7] && provided[8] && !provided[0] && !provided[12]) {
            System.out.println("Brand/Ticket/Length/Finish/Shade Code combination test");
            return "Brand/Ticket etc";
        } else if (provided[0] && provided[2] && provided[7] && provided[12]){
            System.out.println("Contract Order: Article/Shade/PO/Line ref test");
            return "contract order";
        } else {
            return "not recognised";
        }
    }
    
    public void runTest(String[] testDetails, String[] custDetails, String[][] lineDetails) throws InterruptedException, IOException, Exception {
        
        //Check user type in test details to find username and password
        String username="";
        String password="";
        switch(testDetails[1]) {
            case "SUSST Coats": username = DataItems.validCoatsUsername; password = DataItems.validCoatsPassword; break;
            case "SUSST Customer": username = DataItems.validCustUsername; password = DataItems.validCustPassword; break;
        }

        System.out.println("===Starting test: "+testDetails[0]+"===");
        
        //New driver instance
        System.setProperty("webdriver.chrome.driver",DataItems.chromeDriverFilepath);
        WebDriver driver = getDriver();
        
        driver.get(DataItems.targetURL);
        
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(username,password);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();
        eCommPage.waitForLoad();

        System.out.println("eComm page loaded. Navigating to Manual Entry...");

        Ecomm_ManualEntryPage mePage = eCommPage.clickManualEntry();
        mePage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\1Manual Entry Page.png"));
        
        System.out.println("Manual Entry Page reached. Setting customer details...");
        
        if (!custDetails[0].equals("")) {
            mePage.setCustomerName(custDetails[0]);
        }
        if (!custDetails[1].equals("")) {
            mePage.setShipToParty(custDetails[1]);
        }
        if (!custDetails[2].equals("")) {
           mePage.setRequestor(custDetails[2]); 
        }
        if (!custDetails[3].equals("")) {
           mePage.setBuyers(custDetails[3]);
        }
        if (!custDetails[4].equals("")) {
           mePage.setPONumber(custDetails[4]);
           DataItems.lastUsedPO = custDetails[4];
        }
         
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\2Customer details set.png"));

        System.out.println("Customer details set. Entering line details...");
        
        for (int i = 0; i < lineDetails.length; i++) {
            if (!lineDetails[i][1].equals("")) {
                mePage.setYourMaterialNumber(lineDetails[i][1], i);
            }
            
            if (!lineDetails[i][2].equals("")) {
                mePage.setArticle(lineDetails[i][2], i);
            }
            
            if (!lineDetails[i][3].equals("")) {
                mePage.setBrand(lineDetails[i][3], i);
            }
            
            if (!lineDetails[i][4].equals("")) {
                mePage.setTicket(lineDetails[i][4], i);
            }
            
            if (!lineDetails[i][5].equals("")) {
                mePage.setLength(lineDetails[i][5], i);
            }
            
            if(!lineDetails[i][6].equals("")) {
                mePage.setFinish(lineDetails[i][6],i);
            }
            
            if(!lineDetails[i][7].equals("")) {
                mePage.setShadeCode(lineDetails[i][7],i);
            }
            
            if(!lineDetails[i][8].equals("")) {
                mePage.setQty(Integer.valueOf(lineDetails[i][8]),i);
            }
            
            mePage.setDate(i);
            
            if (!lineDetails[i][10].equals("")) {
                mePage.setStyleNo(lineDetails[i][9],i);
            }
            
            if (!lineDetails[i][11].equals("")) {
                mePage.setOtherInfo(lineDetails[i][10],i);
            }
            
            if (!lineDetails[i][12].equals("")) {
                mePage.setContractPO(lineDetails[i][12],i);
            }
            
            if (!lineDetails[i][0].equals("")) {
                mePage.setLineRef(lineDetails[i][0],i);
            }
        }
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\3Line details set.png"));
        
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
        orderConf.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\4Order confirmation page.png"));
        
        System.out.println("Confirmation page reached.");
        
        if (testDetails[2].equals("Positive")) {
            boolean viewRequired = false;
            Ecomm_OutstandingOrdersPage outOrdersPage = null;

            if (lineDetails[0][12].equals("")) {
                outOrdersPage = orderConf.pressSubmit();
                outOrdersPage.waitForLoad(); 
                CommonTask.waitForPageLoad(driver);
                System.out.println("Order Submitted");
                //Take a screenshot
                File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\5Order submitted.png"));
                viewRequired = true;
            } else {
                if (DataItems.contractOrderCallOff) {
                    outOrdersPage = orderConf.pressSubmit();
                    outOrdersPage.waitForLoad(); 
                    System.out.println("Contract Order detected: Order submitted");
                    //Take a screenshot
                    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\5Order submitted.png"));
                    viewRequired = true;
                } else {
                    System.out.println("Contract Order detected: Submit disabled to avoid call-off");
                }           
            }

            if (viewRequired) {
                int row = outOrdersPage.getRow(DataItems.lastUsedPO);
                Ecomm_OrderViewPage viewPage = outOrdersPage.pressView(row);
                viewPage.waitForContent();

                //Take a screenshot
                File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\6Order view displayed.png"));

                viewPage.closeView();
                viewPage.waitForInvisibility();
                driver.switchTo().defaultContent();

                //Take a screenshot
                File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\7View closed.png"));
            }
        } else if (testDetails[2].equals("Negative")) {
            
            System.out.println("Error expected. Waiting for page load...");
            
            Ecomm_ManualEntryPage mePage2 = new Ecomm_ManualEntryPage(driver);
            mePage.pressNextExpectingFailure();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\6Error expected.png"));
            
            try {
                WebElement flashMessage = mePage2.waitForError();
                System.out.println("Error received: " + flashMessage.getText());
            } catch (Exception e) {
                System.out.println("***ERROR: NO ERROR DISPLAYED UPON VALIDATION***");
            }

        }
        
        
    }
    
}
