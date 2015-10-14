
package TestCases;

import AutomationFramework.TestSuiteOLD;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.WBA_SelectionPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ecomm_SUSST_Base {
    
    WebDriver driver;
    
    public Ecomm_SUSST_Base(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public Ecomm_MainPage SUSST_SetUp(String testTitle,String scenarioID) {
        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);
    
        //navigate to QA site
        driver.get(TestSuiteOLD.targetURL);
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(TestSuiteOLD.validCoatsUsername,TestSuiteOLD.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();
        eCommPage.waitForLoad();

        System.out.println("eComm page loaded.");
        
        return eCommPage;
    }
    
    public Ecomm_MainPage SUSST_SetUp(String testTitle,String scenarioID,String username, String password) {
        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);
    
        //navigate to QA site
        driver.get(TestSuiteOLD.targetURL);
    
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
        
        return eCommPage;
    }
    
}
