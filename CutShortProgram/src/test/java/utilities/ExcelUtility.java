package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	
	private FileInputStream fi;
	private FileOutputStream fo;
	private File f;
	private Workbook wb;
	private Sheet sh;
	
	public ExcelUtility(String filepath) throws Exception
	{
		 f=new File(filepath);
		 fi=new FileInputStream(f);
		 wb=WorkbookFactory.create(fi);
		 fo=new FileOutputStream(f);
	}
	
	public void openSheet(String sheetname)
	{
       sh=wb.getSheet(sheetname);
	}
	
	 public int getRowCount()
	 {
       int nour=sh.getPhysicalNumberOfRows();
	return nour;
       
	 }
	 
	 public int getColCount()
	 {
        int nouc=sh.getRow(0).getLastCellNum();
        return nouc;
        
	 }
	 
	 public void createResultColumn(int rownum,int colnum)
	 {
		 sh.getRow(rownum).createCell(colnum).setCellValue("Result Column");
	 }
	 
	 public String getCellValue(int rownum,int colnum)
	 {
		 DataFormatter df=new DataFormatter();
String value=df.formatCellValue(sh.getRow(rownum).getCell(colnum));
return value;
	 }
	 
	 public void setCellValue(int rownum,int colnum,String result)
	 {
		 sh.getRow(rownum).getCell(colnum).setCellValue(result);
		 sh.autoSizeColumn(colnum);
	 }
	 
	 public void saveAndCloseExcel() throws Exception
	 {
		 wb.write(fo);
		 wb.close();
		 fi.close();
		 fo.close();
	 }

}
