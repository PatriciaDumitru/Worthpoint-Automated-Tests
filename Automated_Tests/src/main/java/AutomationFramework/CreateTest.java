
package AutomationFramework;

import TestTemplates.Ecomm_ManualEntryTemplate;
import TestTemplates.Ecomm_UploadOrderTemplate;
import java.awt.AWTException;
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
    
    private static String xmlFilepath;
    private static List<Node> testNodes;
    
    public CreateTest() throws InterruptedException, IOException, AWTException {
        xmlFilepath = DataItems.xmlFilepath;
        
        System.out.println("Scanning XML File for tests...");
        findTests();
        System.out.println("File scanned. Identifying tests...");
        identifyTests();

    }
    
    
    public static void findTests() {
        //New objects to read XML file
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        //Read file
        try {
            
            //Open and parse file
            File xmlFile = new File(xmlFilepath);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            
            //Retrieve "Tests" node and normalise
            Element testsNode = doc.getDocumentElement();           
            testsNode.normalize();
            
            System.out.println("Document Element:" + testsNode.getNodeName());
            
            //Retrieve all element nodes in tests
            testNodes = getElementNodes(testsNode);
            
            System.out.println("Number of tests: "+testNodes.size());           
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
    
    public static void identifyTests() throws InterruptedException, IOException, AWTException {

        //For each test, find type and generate test from template
        for (int i = 0; i < testNodes.size(); i++) {
            //Get current test and evaluate
            Node currentTest = testNodes.get(i);
            if(currentTest.getNodeName().equals("METest")) {
                System.out.println("Generating Test " + (i+1) + ": eComm Manual Entry Test");
                createManualEntryTest(currentTest);
            } else if (currentTest.getNodeName().equals("UOTest")) {
                System.out.println("Generating Test " + (i+1) + ": eComm Upload Order Test");
                createUploadOrderTest(currentTest);
            }           
        }
    }
    
    public static void createManualEntryTest(Node METest) throws InterruptedException, IOException {
        //Get all child nodes from METest with type element
        List<Node> elements = getElementNodes(METest);
        
        //Extract test detail nodes from elements
        Node testDetailsNode = elements.get(0);
        List<Node> testDetailsList = getElementNodes(testDetailsNode);      
        
        String[] testDetails = new String[4];
        testDetails = fillValues(testDetails,testDetailsList);
        
        int lineCount = Integer.valueOf(testDetails[3]);
        
        //Extract customer detail nodes from elements
        Node custDetailsNode = elements.get(1);
        List<Node> custDetailsList = getElementNodes(custDetailsNode);
        
        String[] custDetails = new String[5];
        custDetails = fillValues(custDetails,custDetailsList);
        
        String[][] lineDetails = new String[lineCount][13];
        //Find number of line elements and extract details for each line
        for (int i = 2; (i-2) < lineCount; i++) {
            Node lineNode = elements.get(i);
            List<Node> lineDetailElements = getElementNodes(lineNode);
            lineDetails[i-2] = fillValues(lineDetails[i-2],lineDetailElements);
        }
        
        Ecomm_ManualEntryTemplate meTemp = new Ecomm_ManualEntryTemplate(testDetails,custDetails,lineDetails);
        
    }
    
    public static void createUploadOrderTest(Node UOTest) throws IOException, AWTException, InterruptedException {
        //Get all child nodes from UOTest with type element
        List<Node> elements = getElementNodes(UOTest);
        
        //Extract test detail nodes from elements
        Node testDetailsNode = elements.get(0);
        List<Node> testDetailsList = getElementNodes(testDetailsNode);      
        
        String[] testDetails = new String[6];
        testDetails = fillValues(testDetails,testDetailsList);
        
        //Extract customer detail nodes from elements
        Node custDetailsNode = elements.get(1);
        List<Node> custDetailsList = getElementNodes(custDetailsNode);
        
        String[] custDetails = new String[1];
        custDetails = fillValues(custDetails,custDetailsList);
        
        String[] mapDetails = new String[24];
        
        if (elements.size() == 3) {
            Node mappingNode = elements.get(2);
            List<Node> mappingItems = getElementNodes(mappingNode);
            
            mapDetails = fillValues(mapDetails,mappingItems);
        }
 
        Ecomm_UploadOrderTemplate uoTemp = new Ecomm_UploadOrderTemplate(testDetails,custDetails,mapDetails);
        
    }
    
    public static List<Node> getElementNodes(Node node) {
        
        List<Node> elementNodes = new ArrayList<Node>();
        
        NodeList tempList = node.getChildNodes();
        
        for (int i = 0; i < tempList.getLength(); i++) {
            
            Node currentNode = tempList.item(i);
            
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                elementNodes.add(currentNode);
            }
            
        }
        
        return elementNodes;
        
    }
    
    public static String[] fillValues(String[] details, List<Node> detailsList) {
        
        for (int i = 0; i < details.length; i++) {
            details[i] = detailsList.get(i).getTextContent();
        }
        
        return details;
    }
    
}
