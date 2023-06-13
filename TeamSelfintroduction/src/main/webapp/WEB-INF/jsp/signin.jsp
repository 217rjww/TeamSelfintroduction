<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/signin.css" rel="stylesheet">
<title>新規登録</title>
</head>
<body>
	<header class="header">
		<a href="/TeamSelfintroduction/LoginServlet"> <img src="./public/favicon.ico">
		</a>
		<nav>
			<ul>
				<li>JavaTeamDevelopment</li>
			</ul>
		</nav>
	</header>
	<div class="signin">
		<div class="signinInner">
			<h1 class="signinTitle">新規登録</h1>
			<form action="/TeamSelfintroduction/SigninServlet" method="POST">
				<div class="signinForm">

					<label for="id" class="form-label">UserID<span>*</span></label> <input
						type="id" class="form-control" id="id" name="id">
					<p class="errMessage">${idErrMsg}</p>

				</div>
				<div class="signinForm">

					<label for="email" class="form-label">Email<span>*</span></label> <input
						type="email" class="form-control" id="email" name="email">
					<p class="errMessage">${emailErrMsg}</p>
				</div>
				<div class="signinForm">

					<label for="password" class="form-label">パスワード<span>*</span></label>
					<input type="password" class="form-control" id="password"
						name="password">
					<p class="errMessage">${passwordErrMsg}</p>
				</div>
				<div class="btn">
					<button type="submit" class="btn btn-primary">登録</button>
				</div>
			</form>

			<a href="/TeamSelfintroduction/LoginServlet">ログイン画面へ戻る</a>
		</div>
	</div>
</body>
</html>