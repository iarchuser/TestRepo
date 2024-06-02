package shopping.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	XSSFWorkbook wb;
	XSSFSheet ws;
	
	public ExcelData(String fileName)
	{
		File src = new File(fileName);
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error - " + e.getMessage());
		}
		
	}
	
	public int getRowCount(int SheetNumber)
	{
		int rowCount = wb.getSheetAt(SheetNumber).getLastRowNum();
		rowCount = rowCount+1;
		return rowCount;
	}
	
	public String getData(int SheetNumber, int row, int col)
	{
		ws = wb.getSheetAt(SheetNumber);
		String data = ws.getRow(row).getCell(col).getStringCellValue();
		return data;
	}

}
