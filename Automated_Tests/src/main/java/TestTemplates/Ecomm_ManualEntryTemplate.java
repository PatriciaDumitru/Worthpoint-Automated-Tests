
package TestTemplates;

import AutomationFramework.TestSuite;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ecomm_ManualEntryTemplate {
    
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

private int lineCount;
    
    public Ecomm_ManualEntryTemplate(String[] testDetails,String[] custDetails,String[][] lineDetails) {
        //Work out which values have been provided
        for (int count = 0; count < lineDetails.length; count++) {
            System.out.println("-----Line "+ (count+1)+ " items:-----");
            switch (lineDetails[count][0]) {
                case "": lineRefProvided = false; System.out.println("Line Reference not provided"); break;
                default: lineRefProvided = true; System.out.println("Line Reference found"); break;
            }
            switch (lineDetails[count][1]) {
                case "": ymnProvided = false; System.out.println("Your Material Number not provided"); break;
                default: ymnProvided = true; System.out.println("Your Material Number found");
            }
            switch (lineDetails[count][2]) {
                case "": articleProvided = false; System.out.println("Article not provided"); break;
                default: articleProvided = true; System.out.println("Article found"); break;
            }
            switch (lineDetails[count][3]) {
                case "": brandProvided = false; System.out.println("Brand not provided"); break;
                default: brandProvided = true; System.out.println("Brand found"); break;
            }
            switch (lineDetails[count][4]) {
                case "": ticketProvided = false; System.out.println("Ticket not provided"); break;
                default: ticketProvided = true; System.out.println("Ticket found");
            }
            switch (lineDetails[count][5]) {
                case "": lengthProvided = false; System.out.println("Length not provided"); break;
                default: lengthProvided = true; System.out.println("Length found");
            }
            switch (lineDetails[count][6]) {
                case "": finishProvided = false; System.out.println("Finish not provided"); break;
                default: finishProvided = true; System.out.println("Finish found");
            }
            switch (lineDetails[count][7]) {
                case "": shadeCodeProvided = false; System.out.println("Shade Code not provided"); break;
                default: shadeCodeProvided = true; System.out.println("Shade Code found");
            }
            switch (lineDetails[count][8]) {
                case "": qtyProvided = false; System.out.println("Quantity not provided"); break;
                default: qtyProvided = true; System.out.println("Quantity found");
            }
            switch (lineDetails[count][9]) {
                case "": ymnProvided = false; System.out.println("Required date not provided"); break;
                default: ymnProvided = true; System.out.println("Required date found");
            }         
        }
        
        boolean[] itemsProvided = {lineRefProvided,ymnProvided,articleProvided,brandProvided,ticketProvided,
            lengthProvided,finishProvided,shadeCodeProvided,qtyProvided,requiredDateProvided};
        
        identifyCombination(itemsProvided,testDetails[1]);
        
        
    }
    
    public void identifyCombination(boolean[] provided,String userType) {
        if (provided[1] && !provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && !provided[7] && provided[8] && provided[9]) {
            System.out.println("Your Material Number with all master data test");
            Ecomm_ManualEntryPage mePage = setUp(userType);
            
        } else if (provided[1] && !provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && provided[7] && provided[8] && provided[9]) {
            System.out.println("Your Material Number without master shade code test");
            Ecomm_ManualEntryPage mePage = setUp(userType);
        } else if (!provided[1] && provided[2] && !provided[3] && !provided[4] && !provided[5] && !provided[6] && provided[7] && provided[8] && provided[9]) {
            System.out.println("Article and shade code test");
            Ecomm_ManualEntryPage mePage = setUp(userType);
        } else if (!provided[1] && !provided[2] && provided[3] && provided[4] && provided[5] && provided[6] && provided[7] && provided[8] && provided[9]) {
            System.out.println("Brand/Ticket/Length/Finish/Shade Code combination test");
            Ecomm_ManualEntryPage mePage = setUp(userType);
        } else {
            System.out.println("Combination not recognised");
            Ecomm_ManualEntryPage mePage = setUp(userType);
        }
    }
    
    public Ecomm_ManualEntryPage setUp(String userType) {
        
        String username="";
        String password="";
        switch(userType) {
            case "susst coats": username = TestSuite.validCoatsUsername; password = TestSuite.validCoatsPassword; break;
            case "susst customer": username = TestSuite.validCustUsername; password = TestSuite.validCustPassword; break;
        }

        //New driver instance
        System.setProperty("webdriver.chrome.driver",TestSuite.chromeDriverFilepath);
        WebDriver driver = new ChromeDriver();
        
        driver.get(TestSuite.targetURL);
        
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

        System.out.println("eComm page loaded.");

        Ecomm_ManualEntryPage mePage = eCommPage.clickManualEntry();
        return new Ecomm_ManualEntryPage(driver);
    }

}
