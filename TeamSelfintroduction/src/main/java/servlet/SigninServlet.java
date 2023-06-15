package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.Register;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SigninServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/Signin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの文字コードをUTF-8に設定する
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータを取得する
		String registerId = request.getParameter("id");
		String registerEmail = request.getParameter("email");
		String registerPassword = request.getParameter("password");
		java.sql.Date createdAt = new java.sql.Date(System.currentTimeMillis());

		// バリデーションエラーのメッセージを初期化する
		String idErrMsg = "";
		String emailErrMsg = "";
		String passwordErrMsg = "";

		// id のバリデーション
		if (registerId == null || registerId.isEmpty()) {
			idErrMsg = "IDは必須入力です。";
		} else if (!registerId.matches("[a-zA-Z0-9]+")) {
			idErrMsg = "IDは半角英数字で入力してください。";
		} else if (registerId.length() > 10) {
			idErrMsg = "IDは10文字以内で入力してください。";
		}

		// email のバリデーション
		if (registerEmail == null || registerEmail.isEmpty()) {
			emailErrMsg = "Eメールは必須入力です。";
		} else if (!registerEmail.matches("[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}")) {
			emailErrMsg = "正しいEメールアドレスを入力してください。";
		} else if (registerEmail.length() > 100) {
			emailErrMsg = "Eメールは100文字以内で入力してください。";
		}

		// password のバリデーション
		if (registerPassword == null || registerPassword.isEmpty()) {
			passwordErrMsg = "パスワードは必須入力です。";
		} else if (!registerPassword.matches("[a-zA-Z0-9]+")) {
			passwordErrMsg = "パスワードは半角英数字で入力してください。";
		} else if (registerPassword.length() > 10) {
			passwordErrMsg = "パスワードは10文字以内で入力してください。";
		}

		// バリデーションに問題がある場合は、エラーメッセージを設定して、再度 Signin.jsp にフォワードします。
		if (!idErrMsg.isEmpty() || !emailErrMsg.isEmpty() || !passwordErrMsg.isEmpty()) {
			request.setAttribute("idErrMsg", idErrMsg);
			request.setAttribute("emailErrMsg", emailErrMsg);
			request.setAttribute("passwordErrMsg", passwordErrMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Signin.jsp");
			dispatcher.forward(request, response);
			return; 
		}

		// バリデーションに問題がない場合は、ユーザー情報をデータベースに登録
		Register register = new Register();
		register.customer_register(registerId, registerEmail, registerPassword, createdAt);


		// 登録が完了したら、login.jsp にフォワードします。
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
}

