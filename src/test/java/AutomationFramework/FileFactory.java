
package AutomationFramework;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileFactory {

    //The items to head each column in the file. Standard across all file types for simplicity. Items can be left blank if they are not required
    public static String[] headData = {"Customer Name", "Ship to Party Name", "Customer PO No", "Required Date", "Your Material Number", "Article", "Brand", "Ticket",
            "Length", "Finish", "Shade Code", "Qty", "Contract PO No.", "Line Reference", "Sub Account", "Requestor Name"};

    //The items to head each column in the file. Standard across all file types for simplicity. Items can be left blank if they are not required
    public static String[] headData2 = {"Customer Name", "Ship to Party Name", "Customer PO No", "Required Date", "Your Material Number", "Article", "Brand", "Ticket",
            "Length", "Finish", "Shade Code", "Qty", "Contract PO No.", "Line Reference", "Sub Account", "Requestor Name", "Contract Order", "Contract", "Buyers"};

    public static String[] headData3 = {"Customer Name", "Ship to Party Name", "Requester Name", "BusinessPrincipal Name", "1st Light Source", "2nd Light Source", "3rd Light Source", "Articleee",
            "Brand", "Ticket", "Shade Code", "MUM Type", "Qty", "RequestType", "PurposeType", "Requirements", "Customer Reference", "Fabric Reference Name", "Direct Enrich", "Starting shade", "FCE Comments", "Source of Supply"};

    //Data used for testing MOQ (Minimum Order Quantity, a.k.a MDQ) function. MOQ must be active for the customer used (can be changed in Mastesr > Customer)
    public static String[][] MOQData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1711", "1", "", "", "", ""},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "090", "5000", "STANDARD", "C1202", "3", "", "", "", ""}};

    //Data used for testing SUSST upload orders
    public static String[][] susstBasicData = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}};


    //Data used for testing SUSST upload orders
    public static String[][] susstUOData = {
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "black", "", "40000799", "000030", "", "contract@testing.com", "Y", "TEST ZCQ BE POSITIV 03", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "black", "", "", "000030", "", "contract@testing.com", "Y", "TEST ZCQ BE POSITIV 03", "*Others*"}};

    //Data used for testing SUSST upload orders
    public static String[][] susstUOData2 = {
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "black", "", "40000799", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "black", "", "", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}};

    //Data used for testing SUSST upload orders
    public static String[][] susstUOData3 = {
            {"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "40000799", "", "", "contract@testing.com", "Y", "TEST ZCQ SFTP POSITIV 01", "*Others*"},
           };


    public static String[][] susstUOData4 = {
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "black", "", "40000799", "000030", "", "contract@testing.com", "Y", "TEST ZCQ BE POSITIV 03", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "40000799", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "40000799", "", "", "contract@testing.com", "Y", "TEST ZCQ SFTP POSITIV 01", "*Others*"},
    };

    //Data used for testing SUSST upload orders
    public static String[][] susstUOData5 = {{"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "", "", "", "contract@testing.com", "Y", "test ZCQ qty check 01", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "", "", "", "contract@testing.com", "Y", "test ZCQ qty check 01", "*Others*"}};

    //Data used for testing SUSST upload orders
    public static String[][] susstUOData6 = {{"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "", "", "", "contract@testing.com", "Y", "test ZCQ qty check 01", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "", "", "", "contract@testing.com", "Y", "test ZCQ qty check 01", "*Others*"}};

    //Data used for testing SUSST upload orders
    public static String[][] susstUOData7 = {{"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "BLACKD", "", "", "000010", "", "contract@testing.com", "Y", "TEST ZCQ ARUN 02", "*Others*"},
            {"", "Star Garments", "", "18-01-2016", "", "8754120", "", "", "", "", "BLACKD", "", "abc123test", "000010", "", "contract@testing.com", "Y", "TEST ZCQ ARUN 02", "*Others*"}};


    //Data used for testing SUMST upload orders
    public static String[][] sumstBasicData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    public static String[][] sumstBasicData3 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData4 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData5 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData7 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData8 = {
            //{"Star Garments Ltd.","Star Garments","","","","","","","","","","1","TEST ZCQ BE POSITIV 03","000040","",""},
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "1", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
            // {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","ContractOrderNo","0123","","abc test"},
    };

    public static String[][] sumstBasicData9 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData10 = {
            {"Star Garments Ltd.", "Star Garments", "", "20-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData12 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "ABCDEFX", "8754120", "astra", "120", "5000", "STANDARD", "WHITE", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData13 = {
            //{"Star Garments Ltd.","Star Garments","","","","","","","","","","1","TEST ZCQ BE POSITIV 03","000040","",""},
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "999999999", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
            // {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","ContractOrderNo","0123","","abc test"},
    };

    public static String[][] sumstBasicData14 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData15 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData16 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "1", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData17 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999999999999999", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData18 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999999999999", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData19 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "4", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData20 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "5", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "5", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "5", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };


    public static String[][] sumstBasicData21 = {
            //{"Star Garments Ltd.","Star Garments","","","","","","","","","","1","TEST ZCQ BE POSITIV 03","000040","",""},
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "3", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
            // {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","ContractOrderNo","0123","","abc test"},
    };

    public static String[][] sumstBasicData22 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"},
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData23 = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "7", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "7", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "180", "5000", "STANDARD", "C1711", "7", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1202", "7", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "abc test"}
    };

    public static String[][] sumstBasicData24 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "999999999", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData25 = {
            //{"Star Garments Ltd.","Star Garments","","","","","","","","","","1","TEST ZCQ BE POSITIV 03","000040","",""},
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
            // {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","ContractOrderNo","0123","","abc test"},
    };

    public static String[][] sumstBasicData26 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "", "", "", "", "", "", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData27 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData31 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999999", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData32 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData33 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData34 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData35 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData36 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999199", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData37 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData38 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "3", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData39 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "4", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData40 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"},
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData41 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999989", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData42 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData43 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "", "", "", "", "", "", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData45 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData46 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData47 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"},
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };
    public static String[][] sumstBasicData48 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"},
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "8754120", "", "", "", "", "WHITE", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData49 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999199", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData50 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "99999991", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData51 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData52 = {
            {"Star Garments Ltd.", "Star Garments", "", "19-01-2016", "", "", "", "", "", "", "", "", "test ZCQ qty check 01", "", "", "contract@testing.com", "Y", "40000709", "*Others*"}
    };

    public static String[][] sumstBasicData53 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "999999999999999992", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    public static String[][] sumstBasicData54 = {
            {"Star Garments Ltd.", "Star Garments", "", "18-01-2016", "", "", "", "", "", "", "", "2", "TEST ZCQ BE POSITIV 03", "000040", "", "contract@testing.com", "Y", "40000799", "*Others*"}
    };

    //Data containing invalid Your Material Number
    public static String[][] sumstYMNInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "InvalidMaterialNum", "", "", "", "", "", "", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754180", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Article
    public static String[][] sumstArtInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "0000000000", "", "", "", "", "C1202", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "", "", "", "", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Brand
    public static String[][] sumstBrndInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "invalid", "120", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Ticket
    public static String[][] sumstTktInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "999", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Length
    public static String[][] sumstLgthInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "9.13892", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Finish
    public static String[][] sumstFnshInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "invalid", "C1202", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "invalid", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Shade
    public static String[][] sumstShadeInvalidData = {{"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "120", "5000", "STANDARD", "WR0NG", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data containing invalid Ship To
    public static String[][] sumstShipInvalidData = {{"Life Easy Customer", "invalid place", "", "", "", "", "astra", "120", "5000", "STANDARD", "C1711", "3", "", "", "", "approver 1 test"},
            {"Life Easy Customer", "invalidPlace", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "3", "", "", "", "approver 1 test"}};

    //Data for invalid contract order tests (negative conditions)
    public static String[][] susstCOInvalidData = {{"Star Garments Ltd.", "Star Garments", "TEST ZCQ ARUN 02", "", "", "", "", "", "", "", "", "1", "random", "random", "", "joe sykes"},
            {"Star Garments Ltd.", "Star Garments", "TEST ZCQ ARUN 02", "", "", "", "", "", "", "", "", "1", "random", "random", "", "joe sykes"}};

    //Data for Contract Order tests (positive conditions)
    public static String[][] susstCOValidData = {{"Star Garments Ltd.", "Star Garments", "TEST ZCQ ARUN 02", "", "", "", "", "", "", "", "", "1", "40000992", "10", "", "joe sykes"},
            {"Star Garments Ltd.", "Star Garments", "TEST ZCQ ARUN 02", "", "", "", "", "", "", "", "", "1", "40000992", "10", "", "joe sykes"}};

    //Data for Sub Account tests
    public static String[][] sumstSubAcctValidData = {{"Angler Test Indonesia", "test", "", "", "", "", "astra", "120", "5000", "STANDARD", "C9700", "3", "", "", "andywisak", "abc test"},
            {"Angler Test Indonesia", "test", "", "", "", "", "gral", "180", "3000", "STANDARD", "C1711", "3", "", "", "andywisak", "abc test"},
            {"Angler Test Indonesia", "test", "", "", "", "", "astra", "030", "1000", "STANDARD", "C1202", "3", "", "", "andywisak", "abc test"}};

    //Data to be used in Backend Upload tests (this data is duplicated by the program to produce large files)
    public static String[][] susstBackendData = {{"Life Easy Customer", "Life Easy Customer", "", "", "", "", "astra", "120", "5000", "STANDARD", "C9700", "1", "", "", "", "Life Easy"},
            {"Life Easy Customer", "Life Easy Customer", "", "", "", "", "gral", "180", "3000", "STANDARD", "H0972", "1", "", "", "", "Life Easy"}};

    //Data for Enable Orders without Shade
    public static String[][] susstEOwSData = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754180", "astra", "120", "5000", "STANDARD", "fdafadsfasd", "3", "", "", "", "prafull.patil@igate.com"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754180", "astra", "180", "3000", "STANDARD", "C1711", "3", "", "", "", "prafull.patil@igate.com"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754180", "astra", "030", "1000", "STANDARD", "", "3", "", "", "", "prafull.patil@igate.com"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754180", "astra", "030", "1000", "STANDARD", "", "3", "", "", "", "prafull.patil@igate.com"}};

    public static String[][] sumstOFUData = {
            {"CCE HUB offices", "CCE HUB OFFICES", "approver@ccehub.com", "ADIDAS", "D", "A", "A", "8754180", "ASTRA", "110", "BLACK", "Cop", "1", "Color Matching", "Bulk (Samples for Bulk Fabric)", "test requirememt1", "test customrere1", "test fabric1", "Yes", "black", "test fce comment1", "Lab"},
            {"CCE HUB offices", "CCE HUB OFFICES", "approver@ccehub.com", "ADIDAS", "D65", "ABCD001", "ABCD002", "8754180", "ASTRA", "110", "C9700", "Cone", "2", "Color Matching", "Pre-production", "test requirememt2", "test customrere2", "test fabric2", "Yes", "c9700", "test fce comment2", "Hub"},
            {"CCE HUB offices", "CCE HUB OFFICES", "approver@ccehub.com", "ADIDAS", "D65", "ABCD00", "ABCD003", "8754180", "ASTRA", "", "WHITE", "Vicone", "3", "Sewing", "Prototype", "test requirememt3", "test customrere3", "test fabric3", "Yes", "", "test fce comment3", "Warehouse"},
            {"CCE HUB offices", "CCE HUB OFFICES", "approver@ccehub.com", "ADIDAS", "D650", "ABCD0", "ABCD004", "8754180", "ASTRA", "", "C9750", "Vicone", "4", "Sewing", "Salesman Sample", "test requirememt4", "test customrere4", "", "No", "", "", "Hub"},
            {"CCE HUB offices", "CCE HUB OFFICES", "approver@ccehub.com", "ADIDAS", "D6500", "A1", "ABCD000", "8754180", "ASTRA", "", "C9760", "Vicone", "5", "Sewing", "Shade Development", "test requirememt5", "test customrere5", "test fabric5", "No", "", "", "Lab"}
    };

    public static String[][] sumstBasicDataURT = {
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "8754120", "astra", "120", "5000", "STANDARD", "C1711", "1", "", "", "", "abc test"},
            {"Life Easy Customer", "CCE HUB OFFICES", "", "", "", "", "astra", "180", "5000", "STANDARD", "C1202", "2", "", "", "", "abc test"}};
    //{"Life Easy Customer", "CCE HUB OFFICES", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"},
    //{"Life Easy Customer", "CCE HUB OFFICES", "", "18-01-2016", "", "", "", "", "", "", "", "", "TEST ZCQ BE POSITIV 03", "000030", "", "contract@testing.com", "Y", "40000799", "*Others*"}};


    public static void main(String[] args) throws IOException {
        //Main method can be used to test the FileFactory class in isolation

        createFile2("SUSST",1,"UO7","",true);
    }

    public static String createFile(String soldTo, int lineCount, String type, String combination, boolean valid) throws IOException {
        //Create File is called within the program and will co-ordinate the creation of an excel spreadsheet containing data to test Upload Orders
        //Creating files in this manner saves time as otherwise tester would have to rename a file and change its data every time
        /*Parameters: soldTo = SUSST/SUMST (single or multi sold to)
                      lineCount = number of lines to be written in file. Either 1 or 2 for all cases EXCEPT backend which may have 100+
                      type = type of test, Contract Order/MOQ/Sub Account/Basic/Backend/EOwS
                      combination = a variable to more finely determine which data is needed. e.g. YMN/Article/Brand combination
                      valid = true will produce valid data for positive conditions, false the opposite
        */

        //Create file with name based on PO (to ensure unique file names, as WBA will not allow duplicate file names)
        String uniqueId = CommonTask.generatePO("file");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName + ".xlsx";

        //New workbook and sheet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();

        try {

            //New row for field headings
            XSSFRow headRow = sheet.createRow(0);

            //Create a cell for each column and set value to head items
            for (int i = 0; i < headData.length; i++) {
                headRow.createCell(i).setCellValue(headData[i]);
            }

            //Fill an array with the appropriate data based off of the parameters
            String data[][] = getAppropriateData(soldTo, lineCount, type, combination, valid, uniqueId);

            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {

                //Set date in data
                data[rowCount - 1][3] = getDate();

                //create new row for data
                XSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new XSSFRichTextString(data[rowCount - 1][colCount]));
                }

            }

            //Write the above workbook to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);

            //Safely close file
            fos.close();

            System.out.println("File written");

        } catch (Exception e) {
            System.out.println(e);
        }

        //Output filepath used to allow reference if something goes wrong and to allow testers to find the file and ensure the correct data is used
        System.out.println("Filepath used: " + filePath);
        DataItems.lastUsedFilepath = filePath;

        //Return the filepath string
        return filePath;
    }

    public static String[][] getAppropriateData(String soldTo, int lineCount, String type, String combination, boolean valid, String id) throws IOException {
        //This method determines which data is required for the file depending on the criteria (parameters)

        //Create a new array with 2 lines (maximum for all tests except backend). There are 16 fields which need to be held
        String data[][] = new String[lineCount][16];
        String po = "";

        if (soldTo.equals("SUSST")) {

            if (valid) {

                if (type.equals("MOQ")) {
                    //MOQ Test data to be used, customise PO number for easier reference
                    po = "UO_SUSST_MOQ" + id;
                    //Copy data using clone
                    data = MOQData.clone();
                } else if (type.equals("CO")) {
                    po = susstCOValidData[0][2];
                    data = susstCOValidData.clone();
                } else if (type.equals("UO")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData.clone();
                } else if (type.equals("UO2")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData2.clone();
                } else if (type.equals("UO3")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData3.clone();
                } else if (type.equals("UO4")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData4.clone();
                } else if (type.equals("UO5")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData5.clone();
                } else if (type.equals("UO6")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData6.clone();
                } else if (type.equals("UO7")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData7.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    data = susstBasicData.clone();
                } else if (type.equals("EOwS")) {
                    po = "UO_EOwS" + id;
                    data = susstEOwSData.clone();
                } else if (type.equals("BE")) {
                    //getBackendData will handle files with large line counts. "samePO" argument determines whether each line will have the same PO or not, and can be stored in "combination"
                    return getBackendData(soldTo, lineCount, combination, valid, id);
                }

            } else if (!valid) {
                //For tests requiring invalid data

                if (type.equals("CO")) {
                    data = susstCOInvalidData.clone();
                    po = susstCOInvalidData[0][2];
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    switch (combination) {
                        //Check which item needs to be invalid. Note: use SUMST user for invalid item tests unless SUSST is specifically requried
                        case "YMN":
                            break;
                        case "YMNShade":
                            break;
                        case "Article":
                            break;
                        case "Combination":
                            break;
                        default:
                            break;
                    }
                }

            }

        } else if (soldTo.equals("SUMST")) {

            if (valid) {

                if (type.equals("MOQ")) {
                    po = "UO_SUMST_MOQ" + id;
                    data = MOQData.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUMST" + id;
                    data = sumstBasicData.clone();
                    switch (combination) {
                        case "YMN":
                            break;
                        case "YMNShade":
                            break;
                        case "Article":
                            break;
                        case "Combination":
                            break;
                    }

                } else if (type.equals("Basic3")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData3.clone();
                } else if (type.equals("Basic4")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData4.clone();
                } else if (type.equals("Basic5")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData5.clone();
                } else if (type.equals("Basic7")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData7.clone();
                } else if (type.equals("Basic8")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData8.clone();
                } else if (type.equals("Basic12")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData12.clone();
                } else if (type.equals("Basic13")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData13.clone();
                } else if (type.equals("Basic14")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData14.clone();
                } else if (type.equals("Basic15")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData15.clone();
                } else if (type.equals("Basic16")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData16.clone();
                } else if (type.equals("Basic17")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData17.clone();
                } else if (type.equals("Basic18")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData18.clone();
                } else if (type.equals("Basic19")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData19.clone();
                } else if (type.equals("Basic20")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData20.clone();
                } else if (type.equals("Basic21")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData21.clone();
                } else if (type.equals("Basic22")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData22.clone();
                } else if (type.equals("Basic23")) {
                    po = "Non Contract Upload File multiple scenario";
                    data = sumstBasicData23.clone();
                } else if (type.equals("Basic24")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData24.clone();
                } else if (type.equals("Basic25")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData25.clone();
                } else if (type.equals("Basic26")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData26.clone();
                } else if (type.equals("Basic27")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData27.clone();
                } else if (type.equals("Basic31")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData31.clone();
                } else if (type.equals("Basic32")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData32.clone();
                } else if (type.equals("Basic33")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData33.clone();
                } else if (type.equals("Basic34")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData34.clone();
                }
            } else if (type.equals("Basic37")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData37.clone();
            } else if (type.equals("Basic38")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData38.clone();
            } else if (type.equals("Basic39")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData39.clone();
            } else if (type.equals("Basic40")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData40.clone();
            } else if (type.equals("Basic41")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData41.clone();
            } else if (type.equals("Basic42")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData42.clone();
            } else if (type.equals("Basic43")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData43.clone();
            } else if (type.equals("Basic45")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData45.clone();
            } else if (type.equals("Basic46")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData46.clone();
            } else if (type.equals("Basic47")) {
                data = sumstBasicData47.clone();
            } else if (type.equals("Basic48")) {
                data = sumstBasicData48.clone();
            } else if (type.equals("Basic49")) {
                data = sumstBasicData49.clone();
            } else if (type.equals("Basic50")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData50.clone();
            } else if (type.equals("Basic51")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData51.clone();
            } else if (type.equals("Basic52")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData52.clone();
            } else if (type.equals("Basic53")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData53.clone();
            } else if (type.equals("BasicDataURT")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicDataURT.clone();
            } else if (type.equals("Basic54")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicData54.clone();
            } else if (type.equals("SA")) {
                po = "UO_SUMST_SubAcct";
                data = sumstSubAcctValidData.clone();
            } else if (type.equals("BasicDataURT")) {
                po = "Non Contract Upload File multiple scenario" + id;
                data = sumstBasicDataURT.clone();


            } else if (!valid) {
                po = "UO_SUMST" + id;
                switch (combination) {
                    case "YMN":
                        data = sumstYMNInvalidData;
                        break;
                    case "Article":
                        data = sumstArtInvalidData;
                        break;
                    case "Brand":
                        data = sumstBrndInvalidData;
                        break;
                    case "Ticket":
                        data = sumstTktInvalidData;
                        break;
                    case "Length":
                        data = sumstLgthInvalidData;
                        break;
                    case "Finish":
                        data = sumstFnshInvalidData;
                        break;
                    case "Shade":
                        data = sumstShadeInvalidData;
                        break;
                    case "Ship":
                        data = sumstShipInvalidData;
                        break;
                }

            }

        }
        //Set the PO number for each line. To allow any number of lines to be used in an upload order test (rather than a max of 4), this task must be placed in a loop

        for (
                int i = 0;
                i < lineCount; i++)

        {
            data[i][2] = po;
        }

//        data[0][2] = po;
//        data[1][2] = po;
//        data[2][2] = po;
//        data[3][2] = po;

        return data;

    }

    public static String[][] getBackendData(String soldTo, int lineCount, String samePO, boolean valid, String id) throws IOException {
        //This method will get the data requird for a backend file, setting the PO as unique or uniform depending on samePO

        //New array to hold data. 16 fields are present hence the array size
        String[][] data = new String[lineCount][16];

        if (valid) {
            //Every two lines, duplicate the data from susstBackendData with either a unique or uniform PO
            for (int line = 0; line < lineCount; line = line + 2) {
                //Copy data into pair of lines
                data[line] = susstBackendData[0];
                data[line + 1] = susstBackendData[1];

                if (samePO.equals("true")) {
                    //Use a uniform PO
                    System.out.println("Same PO to be used");
                    data[line][2] = "BackendUpload" + id;
                    data[line + 1][2] = "BackendUpload" + id;
                } else {
                    //Generate a new ID number every two lines
                    String po = CommonTask.generatePO("file");
                    //Use A and B to make every PO unique
                    data[line][2] = "BackendUpload" + po + "A";
                    System.out.println("Line " + line + ": " + data[line][2]);
                    data[line + 1][2] = "BackendUpload" + po + "B";
                    System.out.println("Line " + line + ": " + data[line + 1][2]);
                }
            }
        } else {
            //Fill array with invalid items
        }

        System.out.println(data[0][2]);
        System.out.println(data[1][2]);
        System.out.println(data[4][2]);
        System.out.println(data[5][2]);
        System.out.println(data[20][2]);
        return data;
    }

    public static String getDate() {

        //Get current time, add 3 days, and return formatted string value
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(cal.getTime());

    }


    public static String createFile2(String soldTo, int lineCount, String type, String combination, boolean valid) throws IOException {
        //Create File is called within the program and will co-ordinate the creation of an excel spreadsheet containing data to test Upload Orders
        //Creating files in this manner saves time as otherwise tester would have to rename a file and change its data every time
        /*Parameters: soldTo = SUSST/SUMST (single or multi sold to)
                      lineCount = number of lines to be written in file. Either 1 or 2 for all cases EXCEPT backend which may have 100+
                      type = type of test, Contract Order/MOQ/Sub Account/Basic/Backend
                      combination = a variable to more finely determine which data is needed. e.g. YMN/Article/Brand combination
                      valid = true will produce valid data for positive conditions, false the opposite
        */

        //Create file with name based on PO (to ensure unique file names, as WBA will not allow duplicate file names)
        String uniqueId = CommonTask.generatePO("file");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName + ".xlsx";

        //New workbook and sheet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();

        try {

            //New row for field headings
            XSSFRow headRow = sheet.createRow(0);

            //Create a cell for each column and set value to head items
            for (int i = 0; i < headData2.length; i++) {
                headRow.createCell(i).setCellValue(headData2[i]);
            }

            //Fill an array with the appropriate data based off of the parameters
            String data[][] = getAppropriateData2(soldTo, lineCount, type, combination, valid, uniqueId);

            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {

                //Set date in data
                data[rowCount - 1][3] = getDate();

                //create new row for data
                XSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new XSSFRichTextString(data[rowCount - 1][colCount]));
                }

            }

            //Write the above workbook to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);

            //Safely close file
            fos.close();

            System.out.println("File written");

        } catch (Exception e) {
            System.out.println(e);
        }

        //Output filepath used to allow reference if something goes wrong and to allow testers to find the file and ensure the correct data is used
        System.out.println("Filepath used: " + filePath);
        DataItems.lastUsedFilepath = filePath;

        //Return the filepath string
        return filePath;
    }

    public static String[][] getAppropriateData2(String soldTo, int lineCount, String type, String combination, boolean valid, String id) throws IOException {
        //This method determines which data is required for the file depending on the criteria (parameters)

        //Create a new array with 2 lines (maximum for all tests except backend). There are 18 fields which need to be held
        String data[][] = new String[lineCount][18];
        String po = "";

        if (soldTo.equals("SUSST")) {

            if (valid) {

                if (type.equals("MOQ")) {

                } else if (type.equals("CO")) {

                } else if (type.equals("UO")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData.clone();

                } else if (type.equals("UO2")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData2.clone();
                } else if (type.equals("UO3")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData3.clone();
                } else if (type.equals("UO4")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData4.clone();
                } else if (type.equals("UO5")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData5.clone();
                } else if (type.equals("UO6")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData6.clone();
                } else if (type.equals("UO7")) {
                    po = "UO_SUSS" + id;
                    data = susstUOData7.clone();
                } else if (type.equals("Basic")) {

                } else if (type.equals("BE")) {

                }

            } else if (!valid) {
                //For tests requiring invalid data

                if (type.equals("CO")) {
                    data = susstCOInvalidData.clone();
                    po = susstCOInvalidData[0][2];
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    switch (combination) {
                        //Check which item needs to be invalid. Note: use SUMST user for invalid item tests unless SUSST is specifically requried
                        case "YMN":
                            break;
                        case "YMNShade":
                            break;
                        case "Article":
                            break;
                        case "Combination":
                            break;
                        default:
                            break;
                    }
                }

            }

        } else if (soldTo.equals("SUMST")) {

            if (valid) {

                if (type.equals("MOQ")) {
                    po = "UO_SUMST_MOQ" + id;
                    data = MOQData.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUMST" + id;
                    data = sumstBasicData.clone();
                    switch (combination) {
                        case "YMN":
                            break;
                        case "YMNShade":
                            break;
                        case "Article":
                            break;
                        case "Combination":
                            break;
                    }


                } else if (type.equals("SA")) {
                    po = "UO_SUMST_SubAcct";
                    data = sumstSubAcctValidData.clone();
                } else if (type.equals("Basic8")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData8.clone();
                } else if (type.equals("Basic9")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData9.clone();
                } else if (type.equals("Basic10")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData10.clone();
                } else if (type.equals("Basic12")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData12.clone();
                } else if (type.equals("Basic13")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData13.clone();
                } else if (type.equals("Basic17")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData17.clone();
                } else if (type.equals("Basic18")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData18.clone();
                } else if (type.equals("Basic21")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData21.clone();
                } else if (type.equals("Basic22")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData22.clone();
                } else if (type.equals("Basic24")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData24.clone();
                } else if (type.equals("Basic25")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData25.clone();
                } else if (type.equals("Basic26")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData26.clone();
                } else if (type.equals("Basic27")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData27.clone();
                } else if (type.equals("Basic31")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData31.clone();
                } else if (type.equals("Basic32")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData32.clone();
                } else if (type.equals("Basic33")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData33.clone();
                } else if (type.equals("Basic34")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData34.clone();
                } else if (type.equals("Basic35")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData35.clone();
                }
                else if (type.equals("Basic36")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData36.clone();
                }else if (type.equals("Basic37")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData37.clone();
                } else if (type.equals("Basic38")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData38.clone();
                } else if (type.equals("Basic39")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData39.clone();
                } else if (type.equals("Basic40")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData40.clone();
                } else if (type.equals("Basic41")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData41.clone();
                } else if (type.equals("Basic42")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData42.clone();
                } else if (type.equals("Basic43")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData43.clone();
                } else if (type.equals("Basic45")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData45.clone();
                } else if (type.equals("Basic46")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData46.clone();
                } else if (type.equals("Basic47")) {
                    data = sumstBasicData47.clone();
                } else if (type.equals("Basic48")) {
                    data = sumstBasicData48.clone();
                } else if (type.equals("Basic49")) {
                    data = sumstBasicData49.clone();
                } else if (type.equals("Basic50")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData50.clone();
                } else if (type.equals("Basic51")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData51.clone();
                } else if (type.equals("Basic52")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData52.clone();
                } else if (type.equals("Basic53")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData53.clone();
                } else if (type.equals("BasicDataURT")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicDataURT.clone();
                } else if (type.equals("Basic54")) {
                    po = "Non Contract Upload File multiple scenario" + id;
                    data = sumstBasicData54.clone();
                }

            } else if (!valid) {
                po = "UO_SUMST" + id;
                switch (combination) {
                    case "YMN":
                        data = sumstYMNInvalidData;
                        break;
                    case "Article":
                        data = sumstArtInvalidData;
                        break;
                    case "Brand":
                        data = sumstBrndInvalidData;
                        break;
                    case "Ticket":
                        data = sumstTktInvalidData;
                        break;
                    case "Length":
                        data = sumstLgthInvalidData;
                        break;
                    case "Finish":
                        data = sumstFnshInvalidData;
                        break;
                    case "Shade":
                        data = sumstShadeInvalidData;
                        break;
                    case "Ship":
                        data = sumstShipInvalidData;
                        break;
                }

            }

        }
        //Set the PO number for each line. To allow any number of lines to be used in an upload order test (rather than a max of 2), this task must be placed in a loop
        for (int i = 0; i < lineCount; i++) {
            data[i][2] = po;
        }

//        data[0][2] = po;
//        data[1][2] = po;
//        data[2][2] = po;
//        data[3][2] = po;

        return data;

    }


    public static String createFile3(String soldTo, int lineCount, String type, String combination, boolean valid) throws IOException {
        //Create File is called within the program and will co-ordinate the creation of an excel spreadsheet containing data to test Upload Orders
        //Creating files in this manner saves time as otherwise tester would have to rename a file and change its data every time
        /*Parameters: soldTo = SUSST/SUMST (single or multi sold to)
                      lineCount = number of lines to be written in file. Either 1 or 2 for all cases EXCEPT backend which may have 100+
                      type = type of test, Contract Order/MOQ/Sub Account/Basic/Backend
                      combination = a variable to more finely determine which data is needed. e.g. YMN/Article/Brand combination
                      valid = true will produce valid data for positive conditions, false the opposite
        */

        //Create file with name based on PO (to ensure unique file names, as WBA will not allow duplicate file names)
        String uniqueId = CommonTask.generatePO("file");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName + ".xlsx";

        //New workbook and sheet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();

        try {

            //New row for field headings
            XSSFRow headRow = sheet.createRow(0);

            //Create a cell for each column and set value to head items
            for (int i = 0; i < headData3.length; i++) {
                headRow.createCell(i).setCellValue(headData3[i]);
            }

            //Fill an array with the appropriate data based off of the parameters
            String data[][] = getAppropriateData3(soldTo, lineCount, type, combination, valid, uniqueId);

            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {

                //Set date in data
                //data[rowCount-1][3] = getDate();

                //create new row for data
                XSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new XSSFRichTextString(data[rowCount - 1][colCount]));
                }

            }

            //Write the above workbook to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);

            //Safely close file
            fos.close();

            System.out.println("File written");

        } catch (Exception e) {
            System.out.println(e);
        }

        //Output filepath used to allow reference if something goes wrong and to allow testers to find the file and ensure the correct data is used
        System.out.println("Filepath used: " + filePath);
        DataItems.lastUsedFilepath = filePath;

        //Return the filepath string
        return filePath;
    }

    public static String[][] getAppropriateData3(String soldTo, int lineCount, String type, String combination, boolean valid, String id) throws IOException {
        //This method determines which data is required for the file depending on the criteria (parameters)

        //Create a new array. There are 22 fields which need to be held
        String data[][] = new String[lineCount][22];
        String po = "";

        if (soldTo.equals("SUSST")) {

            if (valid) {

                if (type.equals("MOQ")) {

                } else if (type.equals("CO")) {

                } else if (type.equals("OFU")) {
                    po = "OFU" + id;
                    data = sumstOFUData.clone();
                }

            }

        } else if (soldTo.equals("SUMST")) {

            if (valid) {

                if (type.equals("MOQ")) {
                    po = "UO_SUMST_MOQ" + id;
                    data = MOQData.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUMST" + id;
                    data = sumstBasicData.clone();
                    switch (combination) {
                        case "YMN":
                            break;
                        case "YMNShade":
                            break;
                        case "Article":
                            break;
                        case "Combination":
                            break;
                    }
                } else if (type.equals("SA")) {
                    po = "UO_SUMST_SubAcct";
                    data = sumstSubAcctValidData.clone();
                }

            } else if (!valid) {
                po = "UO_SUMST" + id;
                switch (combination) {
                    case "YMN":
                        data = sumstYMNInvalidData;
                        break;
                    case "Article":
                        data = sumstArtInvalidData;
                        break;
                    case "Brand":
                        data = sumstBrndInvalidData;
                        break;
                    case "Ticket":
                        data = sumstTktInvalidData;
                        break;
                    case "Length":
                        data = sumstLgthInvalidData;
                        break;
                    case "Finish":
                        data = sumstFnshInvalidData;
                        break;
                    case "Shade":
                        data = sumstShadeInvalidData;
                        break;
                    case "Ship":
                        data = sumstShipInvalidData;
                        break;
                }

            }

        }

        return data;

    }

    // Creates FILE WITH YOUR DESIRED EXTENSION AND RETURNS PATH
    public static String createExtensionFile(String typeOfExtension) throws IOException {


        String uniqueId = CommonTask.generatePO("file");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName + typeOfExtension;

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        //Return the filepath string
        return filePath;

    }


}
