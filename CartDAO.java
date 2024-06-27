package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Cart;

public class CartDAO {

	//データベース接続情報
	private static String RDB_DRIVE ="org.mariadb.jdbc.Driver";
	private static String URL ="jdbc:mariadb://localhost/uniformdb";
	private static String USER ="root";
	private static String PASS ="root123";

	private static Connection getConnection(){
		try{
			Connection con = null;

			//JDBCドライバーをロード
			Class.forName(RDB_DRIVE);
			//DriverManager.getConnection()メソッドを利用してデータベースに接続する処理
			con = DriverManager.getConnection(URL, USER, PASS);

			//生成されたConnectionオブジェクトをリターン
			return con;

		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}   
	
	//引数のカートデータを元にDBのcartinfoテーブルに新規登録処理を行うメソッド
	public void insert(Cart cart) {
		
		Connection con = null;
		Statement smt = null;
		

		try{
			//引数の情報を利用し、登録用のSQL文を文字列として定義
			String sql = "INSERT INTO" 
					+ "cart_info VALUES(NULL,'"
					+cart.getCartid() +"','"
					+cart.getUserid() +"',"
					+cart.getGoodsid() +","
					+cart.getQuantity()+ ",'"
					+cart.getOrderdate() +"','"
					+cart.getPaymentdate() +"','"
					+cart.getGoodsName() +"'"+ ",CURDATE())";
			
			//GoodsDAOクラスに定義した、getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();

			//ConnectionオブジェクトのcreateStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//Statementオブジェクトの、executeUpdate（）メソッドを利用して、SQL文を発行し書籍データを登録
			smt.executeUpdate(sql);
			
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {
			}
		}			
	}
}



