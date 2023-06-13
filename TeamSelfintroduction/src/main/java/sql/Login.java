package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import object.User;

public class Login {
	public User check(String user_id, String password) throws FileNotFoundException {

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_config = new DBconfig();
		String url = db_config.getDBinfo().get("url");
		String user = db_config.getDBinfo().get("user");
		String pass = db_config.getDBinfo().get("password");

		// 実行SQL
		String login_sql = "select * from users "
				+ "where user_id = ? and password = ?;";
		// 管理者のオブジェクトを作成
		User admin = new User();

		// データベースへの接続
		// try〜catch〜resources構文を使用
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url,user,pass)) {
			PreparedStatement stmt = conn.prepareStatement(login_sql);

			// 変数login_sqlの一番目の?に引数のuser_idをセット
			stmt.setString(1, user_id);
			// 変数login_sqlの二番目の?に引数のpasswordをセット
			stmt.setString(2, password);

			// SQLを実行し、結果を取得
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				admin.setUserId(rs.getString("user_id"));
				admin.setEmail(rs.getString("email"));
				admin.setPassword(rs.getString("password"));
				admin.setLoginFlag(true); // ログインフラグを設定
				System.out.println("ログイン成功");
			} else {
				System.out.println("ログイン失敗");
			}
		} catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		// データベースから取得した値を返す
		return admin;
	}
	// ログイン成功後に管理者が管理する顧客情報の取得
	public List<User> getCustomerInfo(String admin_id) throws FileNotFoundException {

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		// admin_id(管理者ID)で該当する顧客情報を取得する
		String customer_sql = "select * from users";

		// 顧客情報のデータを格納するListを作成
		List<User> cus_list = new ArrayList<User>();

		// データベースへの接続
		// try〜catch〜resources構文を使用
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			PreparedStatement stmt = conn.prepareStatement(customer_sql);

//			stmt.setString(1, user_id);
//			stmt.setString(2, email);
//			stmt.setString(3, password);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				// 顧客情報用のオブジェクトを作成
				User cus_info = new User();
				// オブジェクトにデータを一時格納
				cus_info.setUserId(rs.getString("user_id"));
				cus_info.setEmail(rs.getString("email"));
				cus_info.setPassword(rs.getString("password"));
				cus_info.setCreatedAt(rs.getString("created_at"));
				// オブジェクトに格納された
				// 顧客情報用のデータをリストに加える
				cus_list.add(cus_info);
			}
		} catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		return cus_list;
	}
}
