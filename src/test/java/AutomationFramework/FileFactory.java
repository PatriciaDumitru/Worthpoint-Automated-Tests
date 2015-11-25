
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
    
    //The items to head each column in the file. Standard across all file types for simplicity
    public static String[] headData = {"Customer Name","Ship to Party Name","Customer PO Number","Required Date","Your Material Number","Article","Brand","Ticket",
        "Length","Finish","Shade Code","Qty","Contract PO No","Line Reference","Sub Account","Requestor Name"};
    
    public static String[][] MOQData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1711","1","","","",""},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","090","5000","STANDARD","C1202","3","","","",""}};
    
    public static String[][] susstBasicData = {{"Life Easy Customer","CCE HUB OFFICES","","","","8754120","astra","120","5000","STANDARD","C1711","3","","","","abc test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","abc test"}};
    
    public static String[][] sumstBasicData = {{"Life Easy Customer","CCE HUB OFFICES","","","","8754120","astra","120","5000","STANDARD","C1711","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstYMNInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","InvalidMaterialNum","","","","","","","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","8754180","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstArtInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","0000000000","","","","","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","8754120","","","","","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstBrndInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","invalid","120","5000","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstTktInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","999","5000","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstLgthInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","9.13892","STANDARD","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstFnshInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","5000","invalid","C1202","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","invalid","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstShadeInvalidData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","120","5000","STANDARD","WR0NG","3","","","","approver 1 test"},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] sumstShipInvalidData = {{"Life Easy Customer","invalid place","","","","","astra","120","5000","STANDARD","C1711","3","","","","approver 1 test"},
        {"Life Easy Customer","invalidPlace","","","","","astra","180","5000","STANDARD","C1202","3","","","","approver 1 test"}};
    
    public static String[][] susstCOInvalidData = {{"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","random","random","","joe sykes"},
        {"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","random","random","","joe sykes"}};
    
    public static String[][] susstCOValidData = {{"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","40000992","10","","joe sykes"},
        {"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","40000992","10","","joe sykes"}};
    
    public static String[][] sumstSubAcctValidData ={{"Angler Test Indonesia","test","","","","","astra","120","5000","STANDARD","C9700","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","gral","180","3000","STANDARD","C1711","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","astra","030","1000","STANDARD","C1202","3","","","andywisak","abc test"}};
    
    public static String[][] susstBackendData = {{"Life Easy Customer","Life Easy Customer","","","","","astra","120","5000","STANDARD","C9700","1","","","","Life Easy"},
        {"Life Easy Customer","Life Easy Customer","","","","","gral","180","3000","STANDARD","H0972","1","","","","Life Easy"}};
    
    public static void main(String[] args) throws IOException {
        createFile("SUSST",102,"BE","false",true);
    }
    
    public static String createFile(String soldTo,int lineCount,String type,String combination,boolean valid) throws IOException {
        
        //Create file name based on PO
        String uniqueId = CommonTask.generatePO("file");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName +".xlsx";
        
        //New workbook and sheet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        
        try {
            
            //New head row
            XSSFRow headRow = sheet.createRow(0);
            
            //Create a cell for each column and set value to head items
            for (int i = 0; i < headData.length; i++) {
                headRow.createCell(i).setCellValue(headData[i]);
            }
           
            String data[][] = getAppropriateData(soldTo,lineCount,type,combination,valid,uniqueId);

            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {
                
                //Set date in data
                data[rowCount-1][3] = getDate();

                //create new row
                XSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new XSSFRichTextString(data[rowCount-1][colCount]));
                }
                
            }
            
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);
            fos.close();
            
            System.out.println("File written");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Filepath used: "+filePath);
        DataItems.lastUsedFilepath = filePath;
        return filePath;
    }
    
    public static String[][] getAppropriateData(String soldTo, int lineCount,String type, String combination, boolean valid,String id) throws IOException {
        
        String data[][] = new String[2][16];
        String po = "";
        
        if (soldTo.equals("SUSST")) {
            
            if (valid) {
                
                if (type.equals("MOQ")) {
                    po = "UO_SUSST_MOQ" + id;
                    data = MOQData.clone();
                } else if (type.equals("CO")) {
                    po = susstCOValidData[0][2];
                    data = susstCOValidData.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    data = susstBasicData.clone();                   
                } else if (type.equals("BE")) {
                    //getBackendData will handle files with large line counts. "samePO" determines whether each line will have the same PO or not
                    return getBackendData(soldTo,lineCount,combination,valid,id);
                }
                
            } else if (!valid) {
                
                if (type.equals("CO")) {
                    data = susstCOInvalidData.clone();
                    po = susstCOInvalidData[0][2];
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    switch (combination) {
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
        data[0][2] = po;
        data[1][2] = po;
        
        return data;
        
    }
    
    public static String[][] getBackendData(String soldTo, int lineCount, String samePO, boolean valid, String id) throws IOException {
        //This method will get the data requird for a backend file, catering for Customer PO requirements (same or different with each record)
        
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
