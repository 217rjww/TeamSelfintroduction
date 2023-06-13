<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/login.css" rel="stylesheet">
<title>ログイン画面</title>
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
	<div class="login">
		<div class="loginInner">
			<h1 class="loginTitle">ログイン画面</h1>
			<form action="/TeamSelfintroduction/LoginServlet" method="POST">
				<div class="loginForm">
					<label for="id" class="form-label">UserID<span>*</span></label> <input
						type="id" class="form-control" id="id" name="id">
					<p class="errMessage">${idErrMsg}</p>
				</div>
				<div class="loginForm">
					<label for="password" class="form-label">パスワード<span>*</span></label>
					<input type="password" class="form-control" id="password"
						name="password">
					<p class="errMessage">${passwordErrMsg}</p>
				</div>
				<div class="btn">
					<button type="submit" class="btn btn-primary">ログイン</button>
				</div>
			</form>

			<a href="/TeamSelfintroduction/SigninServlet">新規登録</a>
		</div>
	</div>
</body>
</html>