
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

public class FileFactory {
    
    //The items to head each column in the file. Standard across all file types for simplicity. Items can be left blank if they are not required
    public static String[] headData = {"Customer Name","Ship to Party Name","Customer PO No","Required Date","Your Material Number","Article","Brand","Ticket",
        "Length","Finish","Shade Code","Qty","Contract PO No.","Line Reference","Sub Account","Requestor Name","Contract Order"};
    
    //Data used for testing MOQ (Minimum Order Quantity, a.k.a MDQ) function. MOQ must be active for the customer used (can be changed in Mastesr > Customer)
    public static String[][] MOQData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1711","1","","","",""},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","090","5000","STANDARD","C1202","3","","","",""}};
    
    //Data used for testing SUSST upload orders
    public static String[][] susstBasicData = {{"Life Easy Customer","CCE HUB OFFICES","","","","8754120","astra","120","5000","STANDARD","C1711","3","","","","abc test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","abc test"}};
    
    //Data used for testing SUMST upload orders
    public static String[][] sumstBasicData = {{"Life Easy Customer","CCE HUB OFFICES","","","","8754120","astra","120","5000","STANDARD","C1711","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Your Material Number
    public static String[][] sumstYMNInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","InvalidMaterialNum","","","","","","","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","8754180","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Article
    public static String[][] sumstArtInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","0000000000","","","","","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","8754120","","","","","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Brand
    public static String[][] sumstBrndInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","invalid","120","5000","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Ticket
    public static String[][] sumstTktInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","999","5000","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Length
    public static String[][] sumstLgthInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","9.13892","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Finish
    public static String[][] sumstFnshInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","5000","invalid","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","invalid","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Shade
    public static String[][] sumstShadeInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","5000","STANDARD","WR0NG","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data containing invalid Ship To
    public static String[][] sumstShipInvalidData = {{"Life Easy Customer","invalid place","","","","","astra","120","5000","STANDARD","C1711","3","","","","approver 1 test"},
        {"Life Easy Customer","invalidPlace","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    //Data for invalid contract order tests (negative conditions)
    public static String[][] susstCOInvalidData = {{"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","random","random","","joe sykes", "Y"},
        {"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","random","random","","joe sykes", "Y"}};
    
    //Data for Contract Order tests (positive conditions)
    public static String[][] susstCOValidData = {{"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","40000992","10","","joe sykes", "Y"},
        {"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","40000992","10","","joe sykes", "Y"}};
    
    //Data for Sub Account tests
    public static String[][] sumstSubAcctValidData ={{"Angler Test Indonesia","test","","","","","astra","120","5000","STANDARD","C9700","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","gral","180","3000","STANDARD","C1711","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","astra","030","1000","STANDARD","C1202","3","","","andywisak","abc test"}};
    
    //Data to be used in Backend Upload tests (this data is duplicated by the program to produce large files)
    public static String[][] susstBackendData = {{"Life Easy Customer","Life Easy Customer","","","","","astra","120","5000","STANDARD","C9700","1","","","","Life Easy"},
        {"Life Easy Customer","Life Easy Customer","","","","","gral","180","3000","STANDARD","H0972","1","","","","Life Easy"}};
    
    public static void main(String[] args) throws IOException {
        //Main method can be used to test the FileFactory class in isolation
        createFile("SUSST",102,"BE","false",true);
    }
    
    public static String createFile(String soldTo,int lineCount,String type,String combination,boolean valid) throws IOException {
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
        String filePath = "C:\\Selenium\\" + fileName +".xlsx";
        
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
            String data[][] = getAppropriateData(soldTo,lineCount,type,combination,valid,uniqueId);

            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {
                
                //Set date in data
                data[rowCount-1][3] = getDate();

                //create new row for data
                XSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new XSSFRichTextString(data[rowCount-1][colCount]));
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
        System.out.println("Filepath used: "+filePath);
        DataItems.lastUsedFilepath = filePath;
        
        //Return the filepath string
        return filePath;
    }
    
    public static String[][] getAppropriateData(String soldTo, int lineCount,String type, String combination, boolean valid,String id) throws IOException {
        //This method determines which data is required for the file depending on the criteria (parameters)
        
        //Create a new array with 2 lines (maximum for all tests except backend). There are 16 fields which need to be held
        String data[][] = new String[2][16];
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
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    data = susstBasicData.clone();                   
                } else if (type.equals("BE")) {
                    //getBackendData will handle files with large line counts. "samePO" argument determines whether each line will have the same PO or not, and can be stored in "combination"
                    return getBackendData(soldTo,lineCount,combination,valid,id);
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
                        case "YMN": break;
                        case "YMNShade": break;
                        case "Article": break;
                        case "Combination": break;
                        default: break;
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
                        case "YMN": break;
                        case "YMNShade": break;
                        case "Article": break;
                        case "Combination": break;
                    }
                } else if (type.equals("SA")) {
                    po = "UO_SUMST_SubAcct";
                    data = sumstSubAcctValidData.clone();
                }
                
            } else if (!valid) {
                po = "UO_SUMST" + id;
                switch (combination) {
                    case "YMN": data = sumstYMNInvalidData; break;
                    case "Article": data = sumstArtInvalidData; break;
                    case "Brand": data = sumstBrndInvalidData; break;
                    case "Ticket": data = sumstTktInvalidData; break;
                    case "Length": data = sumstLgthInvalidData; break;
                    case "Finish": data = sumstFnshInvalidData; break;
                    case "Shade": data = sumstShadeInvalidData; break;
                    case "Ship": data = sumstShipInvalidData; break;
                }
                
            }
            
        }
        //Set the PO number for each line. To allow any number of lines to be used in an upload order test (rather than a max of 2), this task must be placed in a loop
        data[0][2] = po;
        data[1][2] = po;
        
        return data;
        
    }
    
    public static String[][] getBackendData(String soldTo, int lineCount, String samePO, boolean valid, String id) throws IOException {
        //This method will get the data requird for a backend file, setting the PO as unique or uniform depending on samePO
        
        //New array to hold data. 16 fields are present hence the array size
        String[][] data = new String[lineCount][16];
        
        if (valid) {
            //Every two lines, duplicate the data from susstBackendData with either a unique or uniform PO
            for (int line = 0; line < lineCount; line=line+2) {
                //Copy data into pair of lines
                data[line] = susstBackendData[0];
                data[line+1] = susstBackendData[1];
            
                if (samePO.equals("true")) {
                    //Use a uniform PO
                    System.out.println("Same PO to be used");
                    data[line][2] = "BackendUpload" + id;
                    data[line+1][2] = "BackendUpload" + id;
                } else {
                    //Generate a new ID number every two lines
                    String po = CommonTask.generatePO("file");
                    //Use A and B to make every PO unique
                    data[line][2] = "BackendUpload" + po + "A";
                    System.out.println("Line " + line + ": " + data[line][2]);
                    data[line+1][2] = "BackendUpload" + po + "B";
                    System.out.println("Line " + line + ": " + data[line+1][2]);
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
    
}
