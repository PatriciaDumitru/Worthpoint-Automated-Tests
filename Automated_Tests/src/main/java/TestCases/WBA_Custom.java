
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.CreateTest;
import java.awt.AWTException;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.Custom.class)
public class WBA_Custom {

    @Category(Categories.OrderCreation.class)
    @Test
    public void generated() throws InterruptedException, IOException, AWTException {
        CreateTest generate = new CreateTest();      
    }
    
}
