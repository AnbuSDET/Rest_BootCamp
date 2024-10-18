package BaseClass;

import java.io.IOException;

import Pojos_User.AddressPojo;
import Pojos_User.User_Pojos;
import Utlities.ExcelUtil;

public class User_Payload {
	
	ExcelUtil excelRead= new ExcelUtil("C:\\Numby_Ninja\\NN_Project_DsAlgo\\TestData\\UserModule.xlsx");
	
	
	public User_Pojos RequestBody(String sheet,int Rownum) throws IOException
	{				
       AddressPojo address = new AddressPojo();
		
		address.setPlotNumber(excelRead.getCellData(sheet,Rownum,5));
		address.setStreet(excelRead.getCellData(sheet,Rownum,6));
		address.setState(excelRead.getCellData(sheet,Rownum,7));
		address.setCountry(excelRead.getCellData(sheet,Rownum,8));
		address.setZipCode(excelRead.getCellData(sheet,Rownum,9));

		User_Pojos newUser = new User_Pojos();
		newUser.setUser_first_name(excelRead.getCellData(sheet,Rownum,1));
		newUser.setUser_last_name(excelRead.getCellData(sheet,Rownum,2));
		newUser.setUser_contact_number(excelRead.getCellData(sheet,Rownum,3));
		newUser.setUser_email_id(excelRead.getCellData(sheet,Rownum,4));
		newUser.setUserAddress(address);
		System.out.println("newUser ::"+newUser.toString());		
	
		
	return newUser;
	}


}