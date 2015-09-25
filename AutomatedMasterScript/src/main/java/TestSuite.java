import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Suite.class)
@SuiteClasses ({
    //CoatsUserLogin.class,
    //ForgotPassword.class,
    OrderCreation.class
})

public class TestSuite {
    //QA URL and login details
    static String url = "https:\\qawcs.coatscolourexpress.com";
    static String screenshotFolder = "C:\\Users\\ukspjsykes\\OneDrive for Business\\Selenium Tests\\Screenshots\\";
    static String coatsUsername = "joe.sykes@coats.com";
    static String coatsPassword = "password";
    static String SUSTUsername = "joe.sykes@coats.com";
    static String SUSTPassword = "password";
    static String SUMTUsername = "joe.sykes@coats.com";
    static String SUMTPassword = "password";
    
    @BeforeClass
    public static void setUp() {
        //locate chromedriver exe so that new instances can be created in tests
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
    }
}
