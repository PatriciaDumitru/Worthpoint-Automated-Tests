package com.coats.selenium.tests;

import AutomationFramework.CustomFactory;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;

public class WBA_Custom_IT {
    
    @Test
    (groups = {"General"})
    public void generated() throws InterruptedException, IOException, AWTException, Exception {
        CustomFactory generate = new CustomFactory();      
    }
    
}
