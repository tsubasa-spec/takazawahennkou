<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="java.util.ArrayList,bean.Goods"%>
<head>
<meta charset="UTF-8">
<title>購入画面</title>
</head>
<body>
	<h1>購入画面</h1>


	<form action="<%=request.getContextPath()%>/insertCart" method="get">
		商品： <select name="goodsId" id = "goodsName">
			<%
			ArrayList<Goods> goodsList = (ArrayList<Goods>) request.getAttribute("goodsList");
			if (goodsList != null) {
				for (Goods goods : goodsList) {
					 out.println("<option value=\"" + goods.getGoodsId() + "\">" + goods.getGoodsName()  +"</option>"); 
				}
			}
			%>
			
			
		</select>
		 個数： <select name="quantity">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> 
		<input type ="hidden" name = "quantity"   >
		
		<input type="submit" value="カートに入れる">
		
	</form>
	
</body>