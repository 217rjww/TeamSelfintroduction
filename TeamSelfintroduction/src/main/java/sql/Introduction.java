package sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import config.DBconfig;
import object.IntroductionObj;

public class Introduction {
	@SuppressWarnings("resource")
	public List<IntroductionObj> introduction_register(
			String user_id,
			String introduction_name,
			String introduction_kana,
			String introduction_gender,
			String[] introduction_hobby,
			String introduction_description,
			Date created_at,
			Date updated_at
			) throws FileNotFoundException {
		DBconfig db_config = new DBconfig();
		String url = db_config.getDBinfo().get("url");
		String user = db_config.getDBinfo().get("user");
		String pass = db_config.getDBinfo().get("password");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<IntroductionObj> cus_list = new ArrayList<IntroductionObj>();


		String db_properties_file = "/Users/kobayashimasakuni/git/TeamSelfintroduction/TeamSelfintroduction/DBconfig.properties";

		Properties db_info = new Properties();
		FileInputStream db_file_stream = new FileInputStream(db_properties_file);
		try {
			db_info.load(db_file_stream);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		try {
			// データベースへの接続
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);

			// ユーザーIDを条件にしてレコードを取得
			String selectSql = "SELECT * FROM introduction WHERE user_id=?";
			stmt = conn.prepareStatement(selectSql);
			stmt.setString(1, user_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				// レコードが存在する場合はUPDATE文を実行
				String updateSql = "UPDATE introduction SET name=?, kana=?, gender=?, hobby=?, word=?, updated_at=? WHERE user_id=?";
				stmt = conn.prepareStatement(updateSql);
				stmt.setString(1, introduction_name);
				stmt.setString(2, introduction_kana);
				stmt.setString(3, introduction_gender);
				stmt.setString(4, String.join(",", introduction_hobby));
				stmt.setString(5, introduction_description);
				stmt.setDate(6, updated_at);
				stmt.setString(7, user_id);

				stmt.executeUpdate();
			} else {
				// レコードが存在しない場合はINSERT文を実行
				String insertSql = "INSERT INTO introduction (user_id, name, kana, gender, hobby, word, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				stmt = conn.prepareStatement(insertSql);
				stmt.setString(1, user_id);
				stmt.setString(2, introduction_name);
				stmt.setString(3, introduction_kana);
				stmt.setString(4, introduction_gender);
				stmt.setString(5, String.join(",", introduction_hobby));
				stmt.setString(6, introduction_description);
				stmt.setDate(7, created_at);
				stmt.setDate(8, updated_at);
				stmt.executeUpdate();
			}

			// コミット
			conn.commit();
			System.out.println("コミット処理を行いました");

			// 顧客情報用のオブジェクトを作成
			IntroductionObj cus_info = new IntroductionObj();

			// オブジェクトにデータを一時格納
			cus_info.setIntroductionId(rs.getInt("introduction_id"));
			cus_info.setIntroductionUserId(rs.getString("user_id"));
			cus_info.setIntroductionName(rs.getString("name"));
			cus_info.setIntroductionKana(rs.getString("kana"));
			cus_info.setIntroductionGender(rs.getString("gender"));
			cus_info.setIntroductionHobby(rs.getString("hobby").split(","));
			cus_info.setIntroductionDescription(rs.getString("word"));

			// オブジェクトに格納された
			// 顧客情報用のデータをリストに加える
			cus_list.add(cus_info);


		} catch (SQLException e) {
			if (conn != null) {
				try {
					// ロールバック
					conn.rollback();
					System.out.println("ロールバック処理を行いました");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cus_list;
	}
}
