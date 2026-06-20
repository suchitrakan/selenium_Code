package Utility;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

public class DataproviderManager {
	
	
	
	@DataProvider(name = "UserData")
	public Object[][] getExcelData(Method method) {
		String testName = method.getName(); 
	    ExcelUtily excel = new ExcelUtily("src/main/resources/User.xlsx" , "User");
	    return excel.getRowData(testName);
	}


}
