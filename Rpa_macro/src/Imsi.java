import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*; 

public class Imsi {
	
	//Ű���� �޺� �Է�
    public static void pressKeys(Double keysCombination) 
    		throws IllegalArgumentException, AWTException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
    	Robot bot =  new Robot();
	    String ch = Double.toString(keysCombination);
	    System.out.println(ch);			    
	    String[] sp = new String[ch.length()];
	    
	    for (int i = 0; i < ch.length(); i++) {
	    	sp[i] = ch.substring(i, 1+i);
	    	
	    	if(sp[i].equals(".")) {
	    		bot.keyPress((int) KeyEvent.class.getField("VK_PERIOD").getInt(null));	
	    	}else {
	    		bot.keyPress((int) KeyEvent.class.getField("VK_" + sp[i]).getInt(null));
	    	}			    		
		}			
    }
	
	//���� üũ
	public static int realtimechk() {
		int buynum = 1;
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
			  //1 ->2�� ��ߵ�
			  buynum = Integer.parseInt(current);
			  
			  
			 System.out.println("������� : "+buynum);
						  			 			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return buynum;	
	}
	
	
	//��Ʈ ����, ���� Ȯ��
	public static int exceldata(int buynum) 
			throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, AWTException {
		Robot bot =  new Robot();
		//���� ������ �� ��������
		int afterbuy = 0;//2
		String path = Imsi.class.getResource("").getPath();
		FileInputStream file = new FileInputStream(path+"rotto.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		//�� row ��
		int row_num =  sheet.getPhysicalNumberOfRows();
		// ��Ʈ ���� 
		
		//�ż�
		for (int i = 2; i < row_num; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(11);
			afterbuy = (int) cell.getNumericCellValue(); 
			
			if(buynum == afterbuy) {
				cell = row.getCell(1);
				System.out.println(cell.getCellType());
				double price = (double) cell.getNumericCellValue();
				cell = row.getCell(2);
				int count = (int) cell.getNumericCellValue();
				
				// ��ǥ ���߱�
				  bot.mouseMove(1400,290); 
				  bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//
				  //���ʴ����� 
				  bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// ����
			  
				  //���� Ű���� �Է�
				  pressKeys(price);
			
				
				System.out.println("�ż�: " + buynum +", "+ afterbuy+"����,���� : "+price+", "+count);
				//���� ���� �ż�
				
				break;
			}
		}
		
		
		//�ŵ�
		for (int i = 2; i < row_num; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(10);
			afterbuy = (int) cell.getNumericCellValue(); 
			
			if(buynum == afterbuy) {
				cell = row.getCell(3);
				double price = (double) cell.getNumericCellValue();
				cell = row.getCell(2);
				int count = (int) cell.getNumericCellValue();
				
				System.out.println("�ŵ�: " + buynum +", "+ afterbuy+"����,���� : "+price+", "+count);
				
				//���� ���� �ŵ�
				
				
				break;
			}
		}
							
		return afterbuy;
			
	}
	
	
	
	
	
	public static void main(String[] args) 
			throws InterruptedException, IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, AWTException {
		
		while (true) {
			//1��180000
		Thread.sleep(3000);
		 int one = realtimechk();
		exceldata(one);
		 
		}		
	}
	
	

}
