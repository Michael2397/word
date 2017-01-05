import java.io.IOException;
import java.util.ArrayList;

/** 
 * @author Michael2397 2692613726@qq.com: 
 * @version 创建时间：2016年7月31日 下午12:58:35 
 * 类说明 
 */
public class Operation {
	public static void main(String[] args) throws IOException {
		UpdateDataBase updateDataBase = new UpdateDataBase();
		GetExcelKeyWord getExcelKeyWord = new GetExcelKeyWord();
		ArrayList<String> list = getExcelKeyWord.getWordFromExcel();
		updateDataBase.update(list);
	}
}
