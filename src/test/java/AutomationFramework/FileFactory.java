
package AutomationFramework;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileFactory {
    
    //The items to head each column in the file. Standard across all file types for simplicity
    static String[] headData = {"Customer Name","Ship to Party Name","Customer PO Number","Required Date","Your Material Number","Article","Brand","Ticket",
        "Length","Finish","Shade Code","Qty","Contract PO No","Line Reference","Sub Account"};
    
    static String[][] susstMOQData = {{"Life Easy Customer","CCE HUB OFFICES","","","","","astra","180","5000","STANDARD","C1711","1","","",""},
        {"Life Easy Customer","CCE HUB OFFICES","","","","","astra","090","5000","STANDARD","C1202","3","","",""}};
    
    static String[][] susstCOInvalidData = {{"Star Garments","Star Garments Ltd.","","","","8754120","astra","120","5000","STANDARD","1","random","random",""},
        {"Star Garments","Star Garments Ltd.","","","","8754180","astra","180","5000","STANDARD","1","random","random",""}};
    
    static String[][] susstCOValidData = {{"Star Garments","Star Garments Ltd.","TEST ZCQ ARUN 01","","","","","","","","","1","40000992","10",""}};
    
    public static String createFile(String soldTo,int lineCount,String type,String combination,boolean valid) throws IOException {
        
        //Create file name based on PO
        String uniqueId = CommonTask.generatePO("");
        String fileName = "UploadTestFile" + uniqueId;
        String filePath = "C:\\Selenium\\" + fileName +".xls";
        
        try {
            
            //New workbook and sheet
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            
            //New head row
            HSSFRow headRow = sheet.createRow(0);
            
            //Create a cell for each column and set value to head items
            for (int i = 0; i < headData.length; i++) {
                headRow.createCell(i).setCellValue(headData[i]);
            }
           
            String data[][] = getAppropriateData(soldTo,lineCount,type,combination,valid,uniqueId);
            
            //For the number of rows declared, create row and fill values accordingly
            for (int rowCount = 1; rowCount <= lineCount; rowCount++) {
                
                //Set date in data
                data[rowCount-1][3] = getDate();

                //Create a new row
                HSSFRow row = sheet.createRow(rowCount);

                //For each field item, create a cell and give it the corresponding data value
                for (int colCount = 0; colCount < data[0].length; colCount++) {
                    row.createCell(colCount).setCellValue(new HSSFRichTextString(data[rowCount-1][colCount]));
                }
                
            }
            
            //Write data to file using FileOutputStream
            FileOutputStream fos = new FileOutputStream(filePath);
            wb.write(fos);
            fos.close();
            
            System.out.println("File written");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return filePath;
    }
    
    public static String[][] getAppropriateData(String soldTo, int lineCount,String type, String combination, boolean valid,String id) throws IOException {
        
        String data[][] = new String[2][15];
        String po = "";
        
        if (soldTo.equals("SUSST")) {
            
            if (valid) {
                
                if (type.equals("MOQ")) {
                    po = "UO_SUSST_MOQ" + id;
                    data = susstMOQData;
                } else if (type.equals("CO")) {
                    po = "UO_SUSST_CO" + id;
                    data = susstCOValidData;
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    switch (combination) {
                        case "YMN": break;
                        case "YMNShade": break;
                        case "Article": break;
                        case "Combination": break;
                    }
                }
                
            } else if (!valid) {
                
                if (type.equals("CO")) {
                    data = susstCOInvalidData;
                    po = "UO_SUSST_CO" + id;
                } else if (type.equals("Basic")) {
                    po = "UO_SUSST" + id;
                    switch (combination) {
                        case "YMN": break;
                        case "YMNShade": break;
                        case "Article": break;
                        case "Combination": break;
                    }
                }
                
            }
            
        } else if (soldTo.equals("SUMST")) {
            
            if (valid) {
                
                if (type.equals("MOQ")) {
                    po = "UO_SUMST_MOQ" + id;
                } else if (type.equals("Basic")) {
                    po = "UO_SUMST" + id;
                    switch (combination) {
                        case "YMN": break;
                        case "YMNShade": break;
                        case "Article": break;
                        case "Combination": break;
                    }
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
