import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

/** 
 * @author Michael2397 2692613726@qq.com: 
 * @version 创建时间：2016年7月31日 上午10:04:12 
 * 返回excel中的值
 */
public class GetExcelKeyWord {
	
	@Test
	public ArrayList<String> getWordFromExcel() throws IOException{
		ArrayList<String> columnList = new ArrayList<String>();
		File file = new File("content.xls");
		FileInputStream fileInputStream =new  FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		
		int rowStart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		
		Row row = null;
		Cell cell = null;
		for(int i = rowStart+1;i<=rowEnd;i++){
			row = hssfSheet.getRow(i);//取得第i行
			cell = row.getCell(2);
			if(null!=cell){
				String cellValue = cell.getStringCellValue().trim();
				columnList.add(cellValue);
			}
			
		}
		return columnList;
		
	}
}
