package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Cart;
import bean.Goods;
import bean.User;
import dao.GoodsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insertCart")
public class InsertCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "logout";
		User user = null;
		try {
			HttpSession session = request.getSession();
			// ログインしていなかったらエラー
			user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、カートに追加できません。";
				return;
			}
			
			/*String goodsName = request.getParameter("goodsName");*/
			String cartid = "1";
			String userid= "1";
			String goodsId = request.getParameter("goodsId");
			String quantity = String.valueOf(request.getParameter("quantity"));
			String orderdate ="6月12日";
			String paymentdate ="未";
			String goodsName = "商品";
			GoodsDAO goodsDao = new GoodsDAO();
			Goods goods = goodsDao.selectById(goodsId);
			
			// 注文情報を格納
			Cart cart = new Cart();
			cart.setCartid(cartid);
			cart.setUserid(userid);
			cart.setGoodsid(Integer.parseInt(goodsId));
			cart.setQuantity(Integer.parseInt(quantity));
			cart.setOrderdate(orderdate);
			cart.setPaymentdate(paymentdate);
			cart.setGoodsName(goodsName);
			
			/*cart.setUserid(user.getUser_id());*/
			
			// セッションからカートを取り出す、初回の場合はインスタンスを作成する
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
			if (cartList == null) {
				cartList = new ArrayList<Cart>();
			}
			// カートについて
			cartList.add(cart);
			
			request.setAttribute("goods", goods);
			// カートをセッション保存
			session.setAttribute("cartList", cartList);
		} /*catch (IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加はできません。";
			} */finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/insertCart.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}