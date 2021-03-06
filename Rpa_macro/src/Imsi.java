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
	
	//키보드 콤보 입력
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
	
	//수량 체크
	public static int realtimechk() {
		int buynum = 1;
		try {
			
				Robot bot =  new Robot();	
			  bot.mouseMove(1466,222); bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//
			  //왼쪽누르기 
			  bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// 떄기
			  
			  bot.keyPress( KeyEvent.VK_CONTROL ); bot.keyPress( KeyEvent.VK_A );
			  bot.keyRelease(KeyEvent.VK_CONTROL); bot.keyRelease( KeyEvent.VK_A );
			  
			  
			  bot.keyPress( KeyEvent.VK_CONTROL ); bot.keyPress( KeyEvent.VK_C );
			  bot.keyRelease( KeyEvent.VK_CONTROL ); bot.keyRelease( KeyEvent.VK_C );
			  
			
			  //현재 갯수값 가져오기 
			  Clipboard clipboard =Toolkit.getDefaultToolkit().getSystemClipboard(); 
			  Transferable contents = clipboard.getContents(clipboard);   
			  String current = (String)contents.getTransferData(DataFlavor.stringFlavor);
			  //1 ->2개 사야됨
			  buynum = Integer.parseInt(current);
			  
			  
			 System.out.println("현재수량 : "+buynum);
						  			 			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return buynum;	
	}
	
	
	//시트 가격, 수량 확인
	public static int exceldata(int buynum) 
			throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, AWTException {
		Robot bot =  new Robot();
		//엑셀 데이터 값 가져오기
		int afterbuy = 0;//2
		String path = Imsi.class.getResource("").getPath();
		FileInputStream file = new FileInputStream(path+"rotto.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		//총 row 수
		int row_num =  sheet.getPhysicalNumberOfRows();
		// 시트 수량 
		
		//매수
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
				
				// 좌표 맞추기
				  bot.mouseMove(1400,290); 
				  bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//
				  //왼쪽누르기 
				  bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// 떄기
			  
				  //가격 키보드 입력
				  pressKeys(price);
			
				
				System.out.println("매수: " + buynum +", "+ afterbuy+"가격,수량 : "+price+", "+count);
				//수량 가격 매수
				
				break;
			}
		}
		
		
		//매도
		for (int i = 2; i < row_num; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(10);
			afterbuy = (int) cell.getNumericCellValue(); 
			
			if(buynum == afterbuy) {
				cell = row.getCell(3);
				double price = (double) cell.getNumericCellValue();
				cell = row.getCell(2);
				int count = (int) cell.getNumericCellValue();
				
				System.out.println("매도: " + buynum +", "+ afterbuy+"가격,수량 : "+price+", "+count);
				
				//수량 가격 매도
				
				
				break;
			}
		}
							
		return afterbuy;
			
	}
	
	
	
	
	
	public static void main(String[] args) 
			throws InterruptedException, IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, AWTException {
		
		while (true) {
			//1분180000
		Thread.sleep(3000);
		 int one = realtimechk();
		exceldata(one);
		 
		}		
	}
	
	

}
