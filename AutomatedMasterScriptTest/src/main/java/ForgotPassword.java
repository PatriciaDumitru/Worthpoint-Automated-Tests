
import java.awt.AWTException;
import java.awt.Rectangle;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ForgotPassword {
    WebDriver driver = new ChromeDriver();
    
    @Test
    public void coatsForgotPassword() throws AWTException, IOException, InterruptedException, UnsupportedFlavorException {
        //Scenario ID: G_FP_1
        //Description: Coats User click on forgot password

        System.out.println("TEST:G_FP_1 - Coats User click on forgot password");
        
        
        //get login page
        driver.get(TestSuite.url);
        //enter username
        driver.findElement(By.xpath("//*[@id=\"UserUsername\"]")).sendKeys(TestSuite.coatsUsername);
        //click forgot password
        driver.findElement(By.xpath("//*[@id=\"forgot-password\"]/div/ul/li/a")).click();
        //take a screenshot
        CommonTask.takeScreenshot("ForgotPassword\\forgotPasswordPage", "Forgot password entry page", driver);
        //enter username again
        driver.findElement(By.xpath("//*[@id=\"UserEmail\"]")).sendKeys(TestSuite.coatsUsername);
        //click recover
        driver.findElement(By.xpath("//*[@id=\"UserForgetpwdForm\"]/div[5]/input")).click();
        //take screenshot
        CommonTask.takeScreenshot("ForgotPassword\\forgotPasswordRecoverPage", "Forgot password recover pressed", driver);
    
        //Scenario ID: G_FP_2
        //Description: Coats User check for password reset e-mail
        
        System.out.println("TEST:G_FP_2 - Coats User check for password reset e-mail");
        
        try {
        //use robot to navigate to outlook and check e-mail
        Robot robot = new Robot();
        //move mouse to start button and click
        robot.mouseMove(10,740);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        //wait for start menu to appear
        robot.delay(1000);
        //Click on outlook (ensure outlook is pinned to top of start menu)
        robot.mouseMove(30,200);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        //wait for outlook to load and e-mail to arrive
        robot.delay(20000);
        //click on e-mail
        robot.mouseMove(300,250);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        //take a screenshot using robot
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage screenCapture = 
                robot.createScreenCapture(new Rectangle(screenSize));
        File scrFile = new File(TestSuite.screenshotFolder+"\\ForgotPassword\\resetEmail.png");
        ImageIO.write(screenCapture,"png",scrFile);
        //highlight link in email
        robot.mouseMove(600,390);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(300);
        robot.mouseMove(600,410);
        Thread.sleep(300);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        //CTRL+C to copy
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        
        } catch (Exception e) {
            System.out.println("Error obtaining reset link from e-mail in Outlook.");
        }
        
        try {
        //go to link on clipboard
        String url = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        driver.get(url);
        } catch (Exception e) {
            System.out.println("Unable to navigate to URL from password reset e-mail.");
        }
        
        //take screenshot of new password form
        CommonTask.takeScreenshot("ForgotPassword\\newPassword", "New password form", driver);
        
        //find new password field and enter new password. Do this for both fields
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id=\"confirmpassword\"]")).sendKeys("password");
        
        //submit form to reset password
        driver.findElement(By.xpath("//*[@id=\"reset-form_bg\"]/table/tbody/tr[4]/td/input")).click();
        
        /* Following code takes screenshot of reset confirmation but is unreliable.
        //set focus to driver browser
        Robot robot = new Robot();
        robot.mouseMove(700, 750);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        
        //take screenshot
        CommonTask.takeScreenshot("ForgotPassword\\resetConfirmation", "Password reset confirmaion screen", driver);
        */
        
        driver.quit();
        
    }

}

