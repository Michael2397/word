import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import config.StdConfig;
import config.iConfig;

/** 
 * @author Michael2397 2692613726@qq.com: 
 * @version 创建时间：2016年7月31日 上午10:08:47 
 * 类说明 
 */
public class UpdateDataBase {
	
	public void update(List<String> list) throws Exception{
		iConfig config=new StdConfig("config.xml");
		String url = config.getXpathText("config/connetion/url/text()") ;
		String user = config.getXpathText("config/connetion/userName/text()");
		String password = config.getXpathText("config/connetion/password/text()");
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			int totalCount=0;
			for(int i=0;i<list.size();i++){
				String keyword=addWildcard(list.get(i));
				String sql = "update product_baseinfo set singleKeyword = '"+list.get(i)+"'"
			+" where (singleKeyword is null or singleKeyword='') and productName like '"+keyword+"' "
			+"and   productDescription like '"+keyword+"'";
				//System.out.println(sql);
				int count = stmt.executeUpdate(sql);
				System.out.println("利用关键字【"+list.get(i)+"】更新:"+count);
				totalCount+=count;
			}
			System.out.println("一共更新关键字："+totalCount);

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	/**
	 * 给字符串的所有字之间加通配符
	 * @param oldStr
	 * @return
	 */
	@Test
	public String addWildcard(String oldStr){
		String newStr="";
		for(int i=oldStr.length();i>0;i--){
			newStr=oldStr.substring(i-1, i)+"%"+newStr;
		}
		
		return "%"+newStr;
	}
}
