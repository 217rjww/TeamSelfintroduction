package object;

public class User {
	// フィールド変数
		private String user_id;
		private String email;
		private String password;
		private String created_at;
		private boolean loginFlag;
		
		// ゲッター　セッター
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public String getUserId() {
			return user_id;
		}
		public void setUserId(String string) {
			this.user_id = string;
		}

		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public String isCreatedAt() {
			return created_at;
		}
		public void setCreatedAt(String string) {
			this.created_at = string;
		}
		
		public boolean isLoginFlag() {
	        return loginFlag;
	    }
	    public void setLoginFlag(boolean loginFlag) {
	        this.loginFlag = loginFlag;
	    }
	    
	    @Override
	    public String toString() {
	        return "Admin [email=" + email + ", password=" + password + ", loginFlag=" + loginFlag + "]";
	    }
}
