<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Goods, util.MyFormat" %>

<%
//リクエストスコープに登録した商品情報を取得
Goods goods = (Goods)request.getAttribute("goods");

//取得した情報を各変数に格納
int goodsId = 0;
String goodsName = "";
int price = 0;

if (goodsId == 0) {
	goodsId = goods.getGoodsId();
	goodsName = goods.getGoodsName();
	price = goods.getPrice();
}

//MyFormatクラスのオブジェクトを生成
MyFormat objMyFormat = new MyFormat();
%>

<html>
	<head>
		<title>カート</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%-- <%@include file="../common/header.jsp" %>--%>

			<main>
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
					
						</ul>
					</nav>
					
				</div>

				<p class="message">下記の商品をカートに追加しました。</p>
				<table class="insertCart-table">
					<tr>
						<th>商品名</th>
						<td><%= goodsId%></td>
					</tr>
					<tr>
						<th class="uppercase">商品ID</th>
						<td><%= goodsName %></td>
					</tr>
					<tr>
						<th>価格</th>
						<td><%= objMyFormat.moneyFormat(price) %></td>
					</tr>
				</table>
				<form class="insertCart-form btn" action="<%= request.getContextPath() %>/showCart" method="get">
					<input type="hidden" name="goods_Id" value="<%= goodsId %>">
					<input type="submit" value="カート確認">
				</form>
			</main>

			<%-- <%@include file="../common/footer.jsp" %>--%>
		</div>
	</body>
</html>