/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomationFramework;

/**
 *
 * @author ukspjsykes
 */
public class Categories {
    
    //encompasses all tests
    public interface WBA{}
    //eComm tests only
    public interface eComm extends WBA{}
    //CCE tests only
    public interface CCE extends WBA{}
    //General tests only (login, password reset)
    public interface General extends WBA{}
    //Custom tests (created through XML file)
    public interface Custom{}
    //Tests which regularly fail, producing false-negatives
    public interface Unstable{}
    //All tests across CCE and eComm which create an order of any type
    public interface OrderCreation{}
    //All tests for the Contract Order feature
    public interface ContractOrder extends eComm{}
    
}
