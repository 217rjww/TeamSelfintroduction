<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="object.IntroductionObj"%>
<%
List<IntroductionObj> introduction_information_list = (List<IntroductionObj>) request.getAttribute("introductionInformation");
%>
<link href="./css/compleated.css"
	rel="stylesheet">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>自己紹介登録完了</title>
</head>
<body>
	<header class="header">
		<a href="/TeamSelfintroduction/TopServlet"> <img src="./public/favicon.ico">
		</a>
		<nav>
			<ul>
				<li>JavaTeamDevelopment</li>
			</ul>
		</nav>
	</header>
	<div class="compleated">
		<div class="compleatedInner">
			<h3 class="compleatedTitle">自己紹介の登録が完了しました！</h3>
			<div class="compleatedForm">
				<p>下記内容の自己紹介の登録が完了しました！</p>

				<p>
					管理番号：<%=introduction_information_list.get(0).getIntroductionId()%></p>

				<p>
					名前：<%=introduction_information_list.get(0).getIntroductionName()%></p>
				<p>
					フリガナ：<%=introduction_information_list.get(0).getIntroductionKana()%></p>
				<p>
					性別：<%=introduction_information_list.get(0).getIntroductionGender()%></p>
				<div class="hobby">
					<p>趣味：</p>
					<%
					String[] array = introduction_information_list.get(0).getIntroductionHobby();
					%>

					<%
					for (int i = 0; i < array.length; i++) {
					%>
					<p class="hobbyArray"><%=array[i]%></p>
					<%
					}
					%>

				</div>
				<p>
					一言：<%=introduction_information_list.get(0).getIntroductionDescription()%></p>
				<p class="setting">自己紹介の内容を変更するには登録内容一覧画面から編集をして頂くか、管理者へお問い合わせください。</p>
			</div>
			<div class="top">
				<a href="/TeamSelfintroduction/TopServlet">TOPへ戻る</a>
			</div>
		</div>
	</div>
</body>
</html>