package sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import config.DBconfig;

public class Register {
	public void customer_register(String user_id, String email, String password, Date created_at) throws FileNotFoundException {
		DBconfig db_config = new DBconfig();
		String url = db_config.getDBinfo().get("url");
		String user = db_config.getDBinfo().get("user");
		String pass = db_config.getDBinfo().get("password");
		
		String db_properties_file = "/Applications/Eclipse_2023-03.app/Contents/workspace/UserAuth/DBconfig.properties";

		Properties db_info = new Properties();
		FileInputStream db_file_stream = new FileInputStream(db_properties_file);

		try {
			// プロパティファイルを読み込む
			db_info.load(db_file_stream);
		} catch (IOException e) {
			System.out.println("データベース設定ファイルが認識できませんでした");
			e.printStackTrace();
		}
		

		// 実行SQL
		String register_sql = "INSERT INTO users (user_id, email, password, created_at) VALUES (?, ?, ?, ?)";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// データベースへの接続
		// try〜catch〜resources構文を使用
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			// オートコミット機能を無効化
			conn.setAutoCommit(false);

			try(PreparedStatement stmt = conn.prepareStatement(register_sql)){
				// 変数register_sqlの一番目の?にdmin_idをセット
				stmt.setString(1, user_id);
				// 変数register_sqlの一番目の?にnameをセット
				stmt.setString(2, email);
				// 変数register_sqlの一番目の?にaddressをセット
				stmt.setString(3, password);
				
				stmt.setDate(4, created_at);
				 // SQLの実行
				stmt.executeUpdate();

				// コミット
				conn.commit();
				System.out.println("コミット処理を行いました");
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
