<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,bean.Goods, util.MyFormat"%>

<%
//リクエストスコープに登録した書籍情報を取得
ArrayList<Goods> goods_list = (ArrayList<Goods>) request.getAttribute("goods_list");
//MyFormatクラスのオブジェクトを生成
MyFormat objMyFormat = new MyFormat();
// 合計価格用の変数
int total = (int) request.getAttribute("total");
%>

<html>
<head>
<title>書籍管理システム</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>
		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション  -->
				<div id="nav">
					<ul>
		
					</ul>

				</div>
				<!-- ページタイトル -->
				<div id="page_title">
					<h2>購入品確認</h2>
				</div>

			</div>
		</div>
		<!-- 購入確定画面のコンテンツ部分 -->
		<div id="main" class="container">
			<p class="message">
				下記の商品を購入しました。 <br> ご利用ありがとうございました。
			</p>
			<table class="buyConfirm-table">
				<tr>
					<th>ID</th>
					<th class="buyConfirm_th">名前</th>
					<th>価格</th>
				</tr>
				<%
				if (goods_list != null) {
					for (int i = 0; i < goods_list.size(); i++) {
				%>
				<tr>
					<td><%=goods_list.get(i).getGoodsId()%></td>
					<td><%=goods_list.get(i).getGoodsName()%></td>
					<td><%=objMyFormat.moneyFormat(goods_list.get(i).getPrice())%></td>
				</tr>
				<%
				}
				}
				%>
			</table>
			<div class="border_line_bottom"></div>

			<p class="total">
				<span class="total_back">合計</span><span class="total_price"><%=objMyFormat.moneyFormat(total)%></span>
			</p>
		</div>
		</main>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>
</html>