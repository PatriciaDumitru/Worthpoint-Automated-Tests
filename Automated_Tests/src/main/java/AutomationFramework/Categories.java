
package AutomationFramework;

/*
    TESTS BY CATEGORY
    
    WBA:
        eComm
            eComm_Orders
                eComm_Orders_ContractOrder
                    eComm_Orders_ContractOrder_ManualEntry
                        - Contract order tests for manual entry method
                    eComm_Orders_ContractOrder_UploadOrder
                        - Contract order tests for upload order method
                eComm_Orders_ManualEntry
                    - Standard tests for manual entry method
                    eComm_Orders_ManualEntry_Validation
                        - validation tests for Confirmation page (creating orders without mandatory fields)
                eComm_Orders_UploadOrder
                    - Standard tests for Upload Order method
        
        CCE
            CCE_Orders
                - Tests to order samples
        
        General
            - Login and forgot password checks
    
    QuickSuite:
        - Login, forgot password, eComm and CCE navigation checks, simple order samples (cce), 
            manual entry (ecomm), upload order (ecomm), contract order (ecomm)
*/

public class Categories {
    
    //encompasses all tests (excluding custom tests)
    public interface WBA{}
    
    //eComm Tests
    public interface eComm extends WBA{}
    public interface eComm_Orders extends eComm{}
    public interface eComm_Orders_ContractOrder extends eComm_Orders{}
    public interface eComm_Orders_ContractOrder_ManualEntry extends eComm_Orders_ContractOrder{}
    public interface eComm_Orders_ContractOrder_UploadOrder extends eComm_Orders_ContractOrder{}
    public interface eComm_Orders_ManualEntry extends eComm_Orders{}
    public interface eComm_Orders_ManualEntry_Validation extends eComm_Orders_ManualEntry{}
    public interface eComm_Orders_UploadOrder extends eComm_Orders{}
    public interface eComm_Orders_UploadOrder_Validation extends eComm_Orders_UploadOrder{}
    
    //CCE tests
    public interface CCE extends WBA{}
    public interface CCE_Orders extends CCE{}
    
    //General tests (login, password reset)
    public interface General extends WBA{}
    
    //Custom tests (created through XML file)
    public interface Custom{}
    
    //Tests which regularly fail, producing false-negatives
    public interface Unstable extends WBA{}
    
    //Used to isolate a single test
    public interface Solo{}
    
    //QuickSuite:  run in under 10 minutes
    public interface QuickSuite{}
    
    //Draft Creation tests ensure drafts are only created when appropriate and retain data correctly
    public interface DraftCreation{}
    
}
