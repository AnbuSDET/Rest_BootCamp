package Utlities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;

//import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Pojos_User.AddressPojo;
import Pojos_User.User_Pojos;

public class ExcelUtil {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public Row row;
	public Cell cell;
	public CellStyle style;
	String path;

	public ExcelUtil(String path) {
		this.path = path;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		String data = "";
		data = getCellValue(sheetName, rownum, colnum);
		return data;
	}

	@SuppressWarnings("resource")
	public User_Pojos getTestData(String Sheet, String TestCase) throws IOException {
		XSSFWorkbook wb;
		@SuppressWarnings("unused")
		String cellValue = "";
		File file = new File("C:\\Numby_Ninja\\NN_Project_DsAlgo\\TestData\\UserModule.xlsx"); // creating a new file
																								// instance
		FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
		// creating Workbook instance that refers to .xlsx file
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(Sheet);

		User_Pojos newUser = new User_Pojos();
		AddressPojo address = new AddressPojo();
		for (Row row : sheet) {

			Cell firstCell = row.getCell(0);
			if (firstCell != null && firstCell.getCellType() == CellType.STRING
					&& firstCell.getStringCellValue().equals(TestCase)) {

				address.setPlotNumber(row.getCell(5).getStringCellValue());
				address.setStreet(row.getCell(6).getStringCellValue());
				address.setState(row.getCell(7).getStringCellValue());
				address.setCountry(row.getCell(8).getStringCellValue());

				address.setZipCode(row.getCell(9).getStringCellValue());

				newUser.setUser_first_name(row.getCell(1).getStringCellValue());
				newUser.setUser_last_name(row.getCell(2).getStringCellValue());
				newUser.setUser_contact_number(row.getCell(3).getStringCellValue());
				newUser.setUser_email_id(row.getCell(4).getStringCellValue());
				newUser.setUserAddress(address);

			}
		}
		return newUser;
	}

	public String getCellValue(String sheetName, int rownum, int colnum) {
		XSSFWorkbook wb;
		String cellValue = "";
		try {
			File file = new File("C:\\Numby_Ninja\\NN_Project_DsAlgo\\TestData\\UserModule.xlsx"); // creating a new
																									// file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(sheetName); // creating a Sheet object to retrieve object

			Row row = sheet.getRow(rownum);
			Cell cell = row.getCell(colnum);

			if (cell.getCellType() == CellType.NUMERIC) {

				cellValue = String.valueOf((long) cell.getNumericCellValue());

			} else {
				cellValue = cell.getStringCellValue();

			}

			wb.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}

}
