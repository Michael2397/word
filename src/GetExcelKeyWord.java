import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import config.StdConfig;
import config.iConfig;

/** 
 * @author Michael2397 2692613726@qq.com: 
 * @version 创建时间：2016年7月31日 上午10:04:12 
 * 返回excel中的值*/
 
public class GetExcelKeyWord {
	
	public List<String> getWordFromConfig() throws Exception{
		
		iConfig config=new StdConfig("config.xml");
		String keyword = config.getXpathText("config/keywords/text()");
		String [] keywords=keyword.split(";");
		List<String> keywordList =  Arrays.asList(keywords);
		return keywordList;
	}
	
	@Test
	public List<String> getWordFromExcel() throws IOException{
		List<String> columnList = new ArrayList<String>();
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
			cell = row.getCell(3);//第3+1列
			if(null!=cell){
				String cellValue =cell.getStringCellValue().trim();
				
				columnList.add(cellValue);
			}
			
		}
		return columnList;
		
	}
	
	
}
