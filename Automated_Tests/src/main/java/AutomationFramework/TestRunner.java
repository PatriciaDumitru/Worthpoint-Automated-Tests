
package AutomationFramework;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {

    //Main class - runs the test suite and manages output of failures
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        System.out.println("Suite Failures:");
        int i = 1;
        for (Failure failure : result.getFailures()) {
            System.out.println(i+") "+failure.toString());
            System.out.println("");
            i++;
        }
        System.out.println("Test success: " + result.wasSuccessful());
    }
    
}
