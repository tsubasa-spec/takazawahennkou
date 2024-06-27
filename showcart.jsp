<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.*,bean.Goods, util.MyFormat" %>

<%
//リクエストスコープに登録した書籍情報を取得
ArrayList<Goods> goodsList = (ArrayList<Goods>)request.getAttribute("goodsList");

//MyFormatクラスのオブジェクトを生成
MyFormat objMyFormat = new MyFormat();

//合計価格用の変数
int total = (int)request.getAttribute("total");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート内容確認</title>
	</head>
	<body  style="text-align: center;">
	
	<!-- ヘッター -->
	
	<h2>カートに入れた商品</h2>
	
	<table class="auto">
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>削除</th>
			</tr>
			
					<%
						if (goodsList != null) {
							for (int i = 0; i < goodsList.size(); i++) {
						%>
					
	
			<!--取得した情報をループ処理を利用し画面に表示-->
		
			<tr>
				<td><a href="<%= request.getContextPath() %>/detail?goodsName=<%= goodsList.get(i).getGoodsName() %>&cmd=detail">
							<%= goodsList.get(i).getGoodsName() %></a></td>
				<td><%= objMyFormat.moneyFormat(goodsList.get(i).getPrice())  %></td>
				<td><a href="<%= request.getContextPath() %>/showCart?delno=<%= i %>">削除</a></td>
			</tr>
					<%
						}
					}
					%>
		</table>
	
			<table class="center">
				<td class="total">合計</td>
				<td><%= objMyFormat.moneyFormat(total) %></td>
			</table>
		
		<!-- 購入ボタン -->
		<form class = "get" action="<%=request.getContextPath()%>/BuyConfirm">
			
			<form action="<%= request.getContextPath() %>/buyConfirm" method="get">
			<input type="submit"  value="購入"></input></a> 
		</form>
	
		<!-- フッター -->
		
	</body>
	
	</body>
</html>