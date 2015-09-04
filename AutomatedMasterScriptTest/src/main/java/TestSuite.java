import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Suite.class)
@SuiteClasses ({
    CoatsEcommOrderCreation.class
})

public class TestSuite {
    //QA URL
    static String url = "https:\\qawcs.coatscolourexpress.com";
    //Folder directory to save screenshots in
    static String screenshotFolder = "C:\\Users\\ukspjsykes\\OneDrive for Business\\Selenium Tests\\Screenshots\\";
    //Coats User account details
    static String coatsUsername = "joe.sykes@coats.com";
    static String coatsPassword = "password";
    //SSUST User account details
    static String SUSSTUsername = "joe.sykes@coats.com";
    static String SUSSTPassword = "password";
    //SUMST User account details
    static String SUMSTUsername = "joe.sykes@coats.com";
    static String SUMSTPassword = "password";
    
    //PO number ID file location
    static String idFileURL = "C:\\Users\\ukspjsykes\\OneDrive for Business\\Selenium Tests\\ID.txt";
    
    @BeforeClass
    public static void setUp() {
        //locate chromedriver exe so that new instances can be created in tests
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
    }
}

