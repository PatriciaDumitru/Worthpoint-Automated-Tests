
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
    
    public static String[][] susstCOInvalidData = {{"Star Garments Ltd.","Star Garments","","","","8754120","astra","120","5000","STANDARD","1","random","random","","joe sykes"},
        {"Star Garments","Star Garments Ltd.","","","","8754180","astra","180","5000","STANDARD","1","random","random","","joe sykes"}};
    
    public static String[][] susstCOValidData = {{"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","8754120","","","","","WHITE","1","40000992","10","","joe sykes"},
        {"Star Garments Ltd.","Star Garments","TEST ZCQ ARUN 02","","","","","","","","","1","40000992","10","","joe sykes"}};
    
    public static String[][] sumstSubAcctValidData ={{"Angler Test Indonesia","test","","","","","astra","120","5000","STANDARD","C9700","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","gral","180","3000","STANDARD","C1711","3","","","andywisak","abc test"},
        {"Angler Test Indonesia","test","","","","","astra","030","1000","STANDARD","C1202","3","","","andywisak","abc test"}};
    
    public static void main(String[] args) throws IOException {
        createFile("SUSST",1,"CO","",true);
    }
    
    public static String createFile(String soldTo,int lineCount,String type,String combination,boolean valid) throws IOException {
        
        //Create file name based on PO
        String uniqueId = CommonTask.generatePO("");
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
                    po = "UO_SUSST_CO" + id;
                    data = susstCOValidData.clone();
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    data = susstBasicData.clone();
                    System.out.println("Basic data selected");
                    
                }
                
            } else if (!valid) {
                
                if (type.equals("CO")) {
                    data = susstCOInvalidData.clone();
                    po = "UO_SUSST_CO" + id;
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
                    case "YMN": break;
                    case "YMNShade": break;
                    case "Article": break;
                    case "Combination": break;
                }
                
            }
            
        }
        data[0][2] = po;
        data[1][2] = po;
        
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
