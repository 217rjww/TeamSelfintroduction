package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.User;
import sql.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  //ログイン画面を表示させる
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		RequestDispatcher dispatcher =
  				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
  		dispatcher.forward(request, response);
  	}

  	// ログイン処理の実装
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// 文字コードの設定
  		response.setContentType("text/html; charset=UTF-8");
  		request.setCharacterEncoding("UTF-8");

  		// ログイン画面で入力された値を取得
  		String userId = request.getParameter("id");
  		String password = request.getParameter("password");
  		
  		// 初期化
  		String idErrMsg = "";
  		String passwordErrMsg = "";
  		
  		// id のバリデーション
  		if (userId == null || userId.isEmpty()) {
  		    idErrMsg = "IDは必須入力です。";
  		} else if (!userId.matches("[a-zA-Z0-9]+")) {
  		    idErrMsg = "IDは半角英数字で入力してください。";
  		} else if (userId.length() > 10) {
  		    idErrMsg = "IDは10文字以内で入力してください。";
  		}
  		
  		// password のバリデーション
  		if (password == null || password.isEmpty()) {
  		    passwordErrMsg = "パスワードは必須入力です。";
  		} else if (!password.matches("[a-zA-Z0-9]+")) {
  		    passwordErrMsg = "パスワードは半角英数字で入力してください。";
  		} else if (password.length() > 10) {
  		    passwordErrMsg = "パスワードは10文字以内で入力してください。";
  		}

  		// バリデーションに問題がある場合は、エラーメッセージを設定して、再度 ログイン画面に遷移
  		if (!idErrMsg.isEmpty() || !passwordErrMsg.isEmpty()) {
  		    request.setAttribute("idErrMsg", idErrMsg);
  		    request.setAttribute("passwordErrMsg", passwordErrMsg);
  		    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
  		    dispatcher.forward(request, response);
  		    return; 
  		}
  		// ログイン処理
  		Login login = new Login();
  		User user = login.check(userId, password);

  		if(user.isLoginFlag()) {
  			// ログイン成功ログ出力
  			System.out.println("ログイン成功");
  			
  			// session作成
  			HttpSession session = request.getSession(true);
  			session.setAttribute("user_id", userId);
  			
  			// 画面遷移
  			RequestDispatcher dispatcher =
  					request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
  			dispatcher.forward(request, response);
  		} else {
  			// ログイン失敗 → ログイン画面へ遷移	
  			System.out.println("ログイン失敗");
  			RequestDispatcher dispatcher =
  					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
  			dispatcher.forward(request, response);
  		}
  	}

}
