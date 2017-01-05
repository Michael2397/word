import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

/** 
 * @author Michael2397 2692613726@qq.com: 
 * @version 创建时间：2016年7月31日 上午10:08:47 
 * 类说明 
 */
public class UpdateDataBase {
	
	public void update(ArrayList<String> list){
		String url =  "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1701";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				String sql = "update product_baseinfo set keyword = '"+list.get(i)+"' where (keyword is null or keyword='') and productName like '%"+list.get(i)+"%'";
				System.out.println(sql);
				int count = stmt.executeUpdate(sql);
				System.out.println(count);
			}

			
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
}
