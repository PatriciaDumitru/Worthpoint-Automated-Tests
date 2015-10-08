
package AutomationFramework;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class CreateTest {
    
    public static Cell getCell(Sheet sheet,String ref) {
        CellReference reference = new CellReference(ref);
        Row row = sheet.getRow(reference.getRow());
        return row.getCell(reference.getCol());
    }
    
    public static void getData() throws FileNotFoundException, IOException, InvalidFormatException {
        
        String[] accountDetails = getAccountDetails();
        
        String[][] eCommTestDetails = getECommTestDetails();
        
        System.out.println("done.");
        
    }
    
    public static String[] getAccountDetails() throws IOException, InvalidFormatException {
        //Open file
        File f = new File(TestSuite.createTestFilepath);
        
        System.out.println("Opening workbook...");
        Workbook wb = WorkbookFactory.create(f);
        
        System.out.println("Workbook open. Fetching account sheet...");
        Sheet accSheet = wb.getSheet("account");
        
        System.out.println("Sheet received. Fetching Account details...");
        
        List<String> accountCells = new ArrayList<String>();
        accountCells.add("C3");
        accountCells.add("C4");
        accountCells.add("C5");
        accountCells.add("C6");
        
        String[] accountDetails = new String[4];
        
        for (int i = 0; i < accountCells.size(); i++) {
            Cell cell = getCell(accSheet,accountCells.get(i));
            accountDetails[i] = cell.getStringCellValue();
        }
        
        System.out.println("Account details fetched.");
        
        wb.close();
        
        return accountDetails;
    }
    
    public static String[][] getECommTestDetails() throws IOException, InvalidFormatException {
        //Open file
        File f = new File(TestSuite.createTestFilepath);
        
        System.out.println("Opening workbook...");
        Workbook wb = WorkbookFactory.create(f);
        
        System.out.println("Workbook open. Fetching eComm sheet...");
        Sheet eCommSheet = wb.getSheet("eComm");
        
        System.out.println("Sheet received. Fetching eComm Test details...");
        
        Cell testCountCell = getCell(eCommSheet,"B1");
        int testCount = (int) testCountCell.getNumericCellValue();
        
        System.out.println("Number of tests: "+testCount);
        
        String[][] testData = new String[testCount][20]; 
        
        List<String>customerDetails = new ArrayList<String>();
        
        for (int counter = 0; counter < testCount; counter++) {
            System.out.println("Fetching test "+(counter+1)+ " customer details...");
            
            Cell testType = getCell(eCommSheet,"B"+(4+counter*16));
            testType.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(testType.getStringCellValue());
            
            Cell custName = getCell(eCommSheet,"C"+(5+counter*16));
            custName.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(custName.getStringCellValue());
            
            Cell shipToPartyName = getCell(eCommSheet,"C"+(6+counter*16));
            shipToPartyName.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(shipToPartyName.getStringCellValue());
            
            Cell requestorName = getCell(eCommSheet,"C"+(7+counter*16));
            requestorName.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(requestorName.getStringCellValue());
            
            Cell buyers = getCell(eCommSheet,"C"+(8+counter*16));
            buyers.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(buyers.getStringCellValue());
            
            Cell custPONo = getCell(eCommSheet,"C"+(9+counter*16));
            custPONo.setCellType(Cell.CELL_TYPE_STRING);
            customerDetails.add(custPONo.getStringCellValue());
                    
            Cell lineCount = getCell(eCommSheet,"C"+(10+counter*16));
            lineCount.setCellType(Cell.CELL_TYPE_STRING);
            int noOfLines = Integer.valueOf(lineCount.getStringCellValue());
            customerDetails.add(lineCount.getStringCellValue());
            
            System.out.println("Customer details fetched. Fetching line details...");
            
            String[][] lineDetails = new String[noOfLines][13];
            
            int desiredColInt = 70;
            
            for (int lineCounter = 0; lineCounter < noOfLines; lineCounter++) {
                
                char desiredCol = (char) desiredColInt;
                
                Cell lineRef = getCell(eCommSheet,desiredCol + "" + (5+(lineCounter*15)));
                lineRef.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][0] = lineRef.getStringCellValue();
                
                Cell yourMatNum = getCell(eCommSheet,desiredCol + "" + (6+(lineCounter*15)));
                yourMatNum.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][1] = yourMatNum.getStringCellValue();
                
                Cell article = getCell(eCommSheet,desiredCol + "" + (7+(lineCounter*15)));
                article.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][2] = article.getStringCellValue();
                
                Cell brand = getCell(eCommSheet,desiredCol + "" + (8+(lineCounter*15)));
                brand.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][3] = brand.getStringCellValue();
                
                Cell ticket = getCell(eCommSheet,desiredCol + "" + (9+(lineCounter*15)));
                ticket.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][4] = ticket.getStringCellValue();
                
                Cell length = getCell(eCommSheet,desiredCol + "" + (10+(lineCounter*15)));
                length.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][5] = length.getStringCellValue();
                
                Cell finish = getCell(eCommSheet,desiredCol + "" + (11+(lineCounter*15)));
                finish.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][6] = finish.getStringCellValue();
                
                Cell shadeCode = getCell(eCommSheet,desiredCol + "" + (12+(lineCounter*15)));
                shadeCode.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][7] = shadeCode.getStringCellValue();
                
                Cell quantity = getCell(eCommSheet,desiredCol + "" + (13+(lineCounter*15)));
                quantity.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][8] = quantity.getStringCellValue();
                
                Cell requiredDate = getCell(eCommSheet,desiredCol + "" + (14+(lineCounter*15)));
                requiredDate.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][9] = requiredDate.getStringCellValue();
                
                Cell styleNo = getCell(eCommSheet,desiredCol + "" + (15+(lineCounter*15)));
                styleNo.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][10] = styleNo.getStringCellValue();
                
                Cell otherInfo = getCell(eCommSheet,desiredCol + "" + (16+(lineCounter*15)));
                otherInfo.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][11] = ticket.getStringCellValue();
                
                Cell custPrice = getCell(eCommSheet,desiredCol + "" + (17+(lineCounter*15)));
                ticket.setCellType(Cell.CELL_TYPE_STRING);
                lineDetails[lineCounter][12] = ticket.getStringCellValue();
                
                desiredColInt++;
            }
            
            System.out.println("Line details fetched. Sorting to single array...");
            
            for (int i = 0; i < testCount; i++) {
                for (int j = 0; j < 7; j++) {
                    testData[i][j] = customerDetails.get(j);
                }
                for (int k = 0; k < noOfLines; k++) {
                    for (int l = 7; l < 19; l++) {
                    testData[i][l] = lineDetails[k][l];
                    }
                }               
            }
            System.out.println("Data sorted. Returning data...");
        }
        
        return testData;
        
    }
    
    /*public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
       
        getData();
    
    }*/
    
}
