
package AutomationFramework;

import TestTemplates.Ecomm_ManualEntryTemplate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NamedNodeMap;

public class CreateTest {
    
    private static String testReference;
    private static String testType;
    private static String userType;
    private static String customerName;
    private static String shipToPartyName;
    private static String requestor;
    private static String buyer;
    private static String customerPONo;
    private static String[][] lineDetails;
    
    public static void main(String[] args) {
        readXML(TestSuite.xmlFilepath);
    }
    
    public static boolean readXML(String xmlFilepath) {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            
            System.out.println("Setting up...");
            
            File xmlFile = new File(xmlFilepath);
            DocumentBuilder db = dbf.newDocumentBuilder();           
            Document doc = db.parse(xmlFile);           
            
            System.out.println("File parsed. Normalising...");
            
            doc.getDocumentElement().normalize();
            
            System.out.println("Normalised.");
            
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            
            System.out.println("Getting list of test elements...");
            
            NodeList nList = doc.getElementsByTagName("test");
            
            System.out.println("List fetched. Iterating through tests...");
            
            for (int count = 0; count < nList.getLength(); count++) {
                Node test = nList.item(count);
                System.out.println("Current element: "+test.getNodeName());
                       
                NamedNodeMap attributes = test.getAttributes();
                testReference = attributes.getNamedItem("testReference").getTextContent();
                testType = attributes.getNamedItem("testType").getTextContent();
                userType = attributes.getNamedItem("userType").getTextContent();
                
                NodeList testComponents = test.getChildNodes();
                Node customerDetailsNode = testComponents.item(1);

                ArrayList<Node> custDetails = new ArrayList<Node>();
                
                
                
                for (int i = 0; i < customerDetailsNode.getChildNodes().getLength(); i++) {
                    Node temp = customerDetailsNode.getChildNodes().item(i);
                    if (temp.getNodeType()== Node.ELEMENT_NODE) {
                        custDetails.add(temp);
                    }
                }
                System.out.println("Cust detail nodes added. Setting values...");
                
                customerName = custDetails.get(0).getTextContent();
                shipToPartyName = custDetails.get(1).getTextContent();
                requestor = custDetails.get(2).getTextContent();
                buyer = custDetails.get(3).getTextContent();;
                customerPONo = custDetails.get(4).getTextContent();
                
                System.out.println("Customer Details received:");
                System.out.println(customerName);
                System.out.println(shipToPartyName);
                System.out.println(requestor);
                System.out.println(buyer);
                System.out.println(customerPONo);
                
                NodeList lines = doc.getElementsByTagName("line");
                
                lineDetails = new String[lines.getLength()][10];
                
                for (int lineCount = 0; lineCount < lines.getLength(); lineCount++) {
                    Node line = lines.item(lineCount);
                    Element lineElement = (Element) line;
                    
                    lineDetails[lineCount][0] = lineElement.getElementsByTagName("lineRef").item(0).getTextContent();
                    lineDetails[lineCount][1] = lineElement.getElementsByTagName("yourMaterialNumber").item(0).getTextContent();
                    lineDetails[lineCount][2] = lineElement.getElementsByTagName("article").item(0).getTextContent();
                    lineDetails[lineCount][3] = lineElement.getElementsByTagName("brand").item(0).getTextContent();
                    lineDetails[lineCount][4] = lineElement.getElementsByTagName("ticket").item(0).getTextContent();
                    lineDetails[lineCount][5] = lineElement.getElementsByTagName("length").item(0).getTextContent();
                    lineDetails[lineCount][6] = lineElement.getElementsByTagName("finish").item(0).getTextContent();
                    lineDetails[lineCount][7] = lineElement.getElementsByTagName("shadeCode").item(0).getTextContent();
                    lineDetails[lineCount][8] = lineElement.getElementsByTagName("quantity").item(0).getTextContent();
                    lineDetails[lineCount][9] = lineElement.getElementsByTagName("requiredDate").item(0).getTextContent();
                
                    System.out.println("Line Details received: ");

                    for (int i =0; i < lineDetails[lineCount].length;i++) {
                        System.out.println(lineDetails[lineCount][i]);
                    }
                    
                }
                
                triggerTest(testReference, testType, userType, customerName,shipToPartyName,requestor,buyer,customerPONo,lineDetails);
                
                customerName="";
                shipToPartyName="";
                requestor="";
                buyer="";
                customerPONo="";
                
            }
    
        } catch (Exception e) {
            System.out.println("Exception occurred reading XML");
            e.printStackTrace();
        }
        
        return true;
    }
    
    public static void triggerTest(String testReference, String testType, String userType,String customerName, String shipToPartyName, String requestor, String buyer, String customerPONo, String[][] lineDetails) {
        
        String[] testDetails = {testReference,testType,userType};
        String[] custDetails = {customerName,shipToPartyName,requestor,buyer,customerPONo};
        
        if (testType.equals("manual entry")) {
            Ecomm_ManualEntryTemplate meTemp = new Ecomm_ManualEntryTemplate(testDetails,custDetails,lineDetails);
        } else if (testType.equals("upload order")) {
            
        }
    }
    
}
