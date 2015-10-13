
package AutomationFramework;

import TestTemplates.Ecomm_ManualEntryTemplate;
import TestTemplates.Ecomm_UploadOrderTemplate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class CreateTest {
    
    private static String testReference;
    private static String testType;
    private static String userType;
    private static String filePath;
    private static String customerName;
    private static String shipToPartyName;
    private static String requestor;
    private static String buyer;
    private static String customerPONo;
    private static String[][] lineDetails;
    
    private static boolean uploadOrder = true;
    
    public static void main(String[] args) {
        readXML(TestSuite.xmlFilepath);
    }
    
    public static boolean readXML(String xmlFilepath) {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            
            System.out.println("Reading XML file...");
            
            File xmlFile = new File(xmlFilepath);
            DocumentBuilder db = dbf.newDocumentBuilder();           
            Document doc = db.parse(xmlFile);           
           
            doc.getDocumentElement().normalize();
            
            System.out.println("File read. Fetching each Test element...");
            
            NodeList nList = doc.getElementsByTagName("test");
            
            for (int count = 0; count < nList.getLength(); count++) {
                Node test = nList.item(count);
                   
                System.out.println("===Test "+count+"===");
                
                NamedNodeMap attributes = test.getAttributes();
                testReference = attributes.getNamedItem("testReference").getTextContent();
                testType = attributes.getNamedItem("testType").getTextContent();
                userType = attributes.getNamedItem("userType").getTextContent();
                try {
                    filePath = attributes.getNamedItem("filePath").getTextContent();
                } catch (Exception e) {
                    System.out.println("Filepath not found, upload order not enabled");
                    uploadOrder = false;
                }

                System.out.println("Test Details:");
                System.out.println("\t Test Reference: "+testReference);
                System.out.println("\t Test Type: " + testType);
                System.out.println("\t User Type: "+userType);
                System.out.println("\t File Path: "+filePath);
                
                NodeList testComponents = test.getChildNodes();
                Node customerDetailsNode = testComponents.item(1);

                ArrayList<Node> custDetails = new ArrayList<Node>();
                
                if (!uploadOrder) {
               
                for (int i = 0; i < customerDetailsNode.getChildNodes().getLength(); i++) {
                    Node temp = customerDetailsNode.getChildNodes().item(i);
                    if (temp.getNodeType()== Node.ELEMENT_NODE) {
                        custDetails.add(temp);
                    }
                }
                
                customerName = custDetails.get(0).getTextContent();
                shipToPartyName = custDetails.get(1).getTextContent();
                requestor = custDetails.get(2).getTextContent();
                buyer = custDetails.get(3).getTextContent();;
                customerPONo = custDetails.get(4).getTextContent();
                
                System.out.println("Customer Details:");
                System.out.println("\t Customer name: "+customerName);
                System.out.println("\t Ship To Party Name: "+shipToPartyName);
                System.out.println("\t Requestor: "+requestor);
                System.out.println("\t Buyer: "+buyer);
                System.out.println("\t Customer PO No.: " + customerPONo);
                
                NodeList lines = doc.getElementsByTagName("line");
                
                lineDetails = new String[lines.getLength()][13];
                
                System.out.println("Line Details: ");
                
                for (int lineCount = 0; lineCount < lines.getLength(); lineCount++) {
                    Node line = lines.item(lineCount);
                    Element lineElement = (Element) line;
                    
                    System.out.println("\tLine "+(lineCount+1));
                    
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
                    lineDetails[lineCount][10] = lineElement.getElementsByTagName("styleNo").item(0).getTextContent();
                    lineDetails[lineCount][11] = lineElement.getElementsByTagName("otherInformation").item(0).getTextContent();
                    lineDetails[lineCount][12] = lineElement.getElementsByTagName("contractPONo").item(0).getTextContent();
                    
                    System.out.println("\t\t Line Ref: "+lineDetails[lineCount][0]);
                    System.out.println("\t\t Your Mat Num: "+lineDetails[lineCount][1]);
                    System.out.println("\t\t Article: "+lineDetails[lineCount][2]);
                    System.out.println("\t\t Brand: "+lineDetails[lineCount][3]);
                    System.out.println("\t\t Ticket: "+lineDetails[lineCount][4]);
                    System.out.println("\t\t Length: "+lineDetails[lineCount][5]);
                    System.out.println("\t\t Finish: "+lineDetails[lineCount][6]);
                    System.out.println("\t\t Shade Code: "+lineDetails[lineCount][7]);
                    System.out.println("\t\t Quantity: "+lineDetails[lineCount][8]);
                    System.out.println("\t\t Required Date: "+lineDetails[lineCount][9]); 
                    System.out.println("\t\t Style No.: "+lineDetails[lineCount][10]);
                    System.out.println("\t\t Other Information: "+lineDetails[lineCount][11]);
                    System.out.println("\t\t Contract PO No.: "+lineDetails[lineCount][12]);
                    
                }
                
                    triggerTest(testReference, testType, userType, customerName,shipToPartyName,requestor,buyer,customerPONo,lineDetails);
                }
                //Reset values so that none are carried into the next test
                customerName="";
                shipToPartyName="";
                requestor="";
                buyer="";
                customerPONo="";
                
            }
    
        } catch (ParserConfigurationException | SAXException | IOException | DOMException | InterruptedException e) {
            System.out.println("Exception occurred reading XML");
        }
        
        return true;
    }
    
    public static void triggerTest(String testReference, String testType, String userType,String customerName, String shipToPartyName, String requestor, String buyer, String customerPONo, String[][] lineDetails) throws InterruptedException, IOException {
        
        String[] testDetails = {testReference,testType,userType};
        String[] custDetails = {customerName,shipToPartyName,requestor,buyer,customerPONo};
        
        if (testType.equals("manual entry")) {
            Ecomm_ManualEntryTemplate meTemp = new Ecomm_ManualEntryTemplate(testDetails,custDetails,lineDetails);
        } else if (testType.equals("upload order")) {
            Ecomm_UploadOrderTemplate uoTemp = new Ecomm_UploadOrderTemplate(testDetails,custDetails);
        }
    }
    
}
