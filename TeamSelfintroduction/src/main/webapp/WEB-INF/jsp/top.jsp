<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/top.css" rel="stylesheet">
<title>TOP</title>
</head>
<body>
	<header class="header">
		<a href="/TeamSelfintroduction/TopServlet"><img src="./public/favicon.ico">
		</a>
		<nav>
			<ul>
				<li>JavaTeamDevelopment</li>
			</ul>
		</nav>
	</header>
	<div class="top">
		<section class="topSection">
			<div class="sectionTitle">
				<h2>自己紹介登録</h2>
				<div class="btn">
					<a href="/TeamSelfintroduction/IntroductionServlet"><button type="button">登録に進む</button></a>
				</div>
			</div>
			<p>
				新しく自己紹介を作ります。<br> あなたをより知ってもらうために、<br> 趣味や一言を工夫しましょう。<br>
				最高の自己紹介を完成させて、よりあなたを 知ってもらえるチャンスにしましょう。
			</p>
		</section>
		<section class="topSection">
			<div class="sectionTitle">
				<h2>登録内容一覧</h2>
				<div class="btn">
					<a href="/TeamSelfintroduction/ListServlet"><button type="button">一覧を編集する</button></a>
				</div>
			</div>
			<p>
				登録した自己紹介を確認します。<br> 趣味や一言を定期的に変更してあなたのファンを作りましょう。<br>
				また、相手を惑わせないように 不要になった自己紹介は削除して 最新の自己紹介のみ残すように心がけましょう。
			</p>
		</section>
	</div>
</body>
</html>