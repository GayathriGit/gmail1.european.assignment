package com.gmail.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Kirubakaran.K(kk) PropertyFinder-Demo Cucumber Automation
 * 
 */
public class ExcelReadWrite_API {
	/*
	 * kk
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> excelReadWrite(String testcase) throws Exception {
		HashMap<String, String> requestMap = new HashMap<>();
		try {
			File fileObj = new File("TestData/TestData.xlsx");
			FileInputStream inputStream = new FileInputStream(fileObj);
			XSSFWorkbook workBookObject = new XSSFWorkbook(inputStream);
			XSSFSheet sheetObj = workBookObject.getSheet("Sheet1");
			int rowcount = sheetObj.getLastRowNum();

			
			for (int i = 1; i <= rowcount; i++)

			{
				Row rowObj = sheetObj.getRow(i);
				Cell testCaseName = rowObj.getCell(0, MissingCellPolicy.RETURN_BLANK_AS_NULL);
				if (testCaseName != null && testCaseName.getStringCellValue().equalsIgnoreCase(testcase)) {
					for (int j = 0; j < rowObj.getLastCellNum(); j++) {
						Row headerRowObj = sheetObj.getRow(0);
						Cell headerValueFromFristRow = headerRowObj.getCell(j, MissingCellPolicy.RETURN_BLANK_AS_NULL);
						Cell testDataFromColum = rowObj.getCell(j, MissingCellPolicy.RETURN_BLANK_AS_NULL);

						if (testDataFromColum != null && headerValueFromFristRow != null) {

							requestMap.put(headerValueFromFristRow.getStringCellValue(), testDataFromColum.getStringCellValue());
						}

					}
				}

			
			}

			System.out.println(requestMap);
			FileOutputStream outputStream = new FileOutputStream(fileObj);
			workBookObject.write(outputStream);
			workBookObject.close();
		} catch (NullPointerException e) {
			throw new Exception(e);
		}
		return requestMap;
	}
public static void main (String []args) throws Exception	{
	ExcelReadWrite_API excelObj=new ExcelReadWrite_API();
	HashMap<String, String> mapref=new HashMap<String, String>();
	mapref=excelObj.excelReadWrite("basicFlow");
}
	
}
