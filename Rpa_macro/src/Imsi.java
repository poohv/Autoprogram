import java.awt.Robot;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*; 

public class Imsi {

	
	public static void test() {
		try {
			
			Robot bot =  new Robot();	
			  bot.mouseMove(1466,222); bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//
			  //���ʴ����� 
			  bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// ����
			  
			  bot.keyPress( KeyEvent.VK_CONTROL ); bot.keyPress( KeyEvent.VK_A );
			  bot.keyRelease(KeyEvent.VK_CONTROL); bot.keyRelease( KeyEvent.VK_A );
			  
			  
			  bot.keyPress( KeyEvent.VK_CONTROL ); bot.keyPress( KeyEvent.VK_C );
			  bot.keyRelease( KeyEvent.VK_CONTROL ); bot.keyRelease( KeyEvent.VK_C );
			  
			  
			  //���� ������ �������� 
			  Clipboard clipboard =Toolkit.getDefaultToolkit().getSystemClipboard(); 
			  Transferable contents = clipboard.getContents(clipboard);   
			  String current = (String)contents.getTransferData(DataFlavor.stringFlavor);
			  int aa = Integer.parseInt(current);
			 			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static int exceldata(int aa,int params) throws IOException {
		int num = 	params;
		//���� ������ �� ��������
		String path = Imsi.class.getResource("").getPath();
		FileInputStream file = new FileInputStream(path+"rotto.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		//�� row ��
		int row_num =  sheet.getPhysicalNumberOfRows();
		// ��Ʈ ���� 	
		row = sheet.getRow(num);
		cell = row.getCell(4);
		int bb = (int) cell.getNumericCellValue();
					
		return bb;
			
		
	}
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		while (true) {
			Thread.sleep(5000);
			test();
		}
		
	
		
	}
	
	
	

}
