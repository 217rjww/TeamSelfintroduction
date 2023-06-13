package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.IntroductionObj;
import sql.Introduction;

/**
 * Servlet implementation class CompleatedServlet
 */
@WebServlet("/CompleatedServlet")
public class CompleatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompleatedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println(session.getAttribute("user_id"));
		if (session == null || session.getAttribute("user_id") == null) {
			// ログインしていない場合の処理
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/compleated.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// introduction_information_listの初期化
		List<IntroductionObj> introductionInformation = new ArrayList<IntroductionObj>();

		String introductionName = request.getParameter("introduction_name");
		String introductionKana = request.getParameter("introduction_kana");
		String introductionGender = request.getParameter("introduction_gender");
		String[] introductionHobby = request.getParameterValues("introduction_hobby");
		String introductionDescription = request.getParameter("description");
		java.sql.Date createdAt = new java.sql.Date(System.currentTimeMillis());
		java.sql.Date updatedAt = new java.sql.Date(System.currentTimeMillis());

		// セッションを取得
		HttpSession session = request.getSession();
		String introductionId = (String) session.getAttribute("user_id");

		Introduction introduction = new Introduction();

		// データベースから取得した顧客情報を格納
		introductionInformation = new ArrayList<IntroductionObj>();


		introductionInformation = introduction.introduction_register(introductionId, introductionName, introductionKana, introductionGender, introductionHobby, introductionDescription, createdAt, updatedAt);

		request.setAttribute("introductionInformation", introductionInformation);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/compleated.jsp");
		dispatcher.forward(request, response);
	}
}

