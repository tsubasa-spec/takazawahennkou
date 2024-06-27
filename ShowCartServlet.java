//カート確認

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

//@WebServletの記述
@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		User user = null;
		int total = 0;
		try {
			String delno = request.getParameter("delno");
			
			HttpSession session = request.getSession();
			// ログインしていなかったらエラー
			user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、カート状況は確認できません。";
				cmd = "logout";
				return;
			}
			
			// セッションからカートを取得
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
			// もし削除対象のデータが送られていればカートから削除
			if (delno != null) {
				cartList.remove(Integer.parseInt(delno));
			}
			
			
			GoodsDAO goodsDao = new GoodsDAO();
			ArrayList<Goods> goodsList = new ArrayList<>();
			if (cartList != null) {
				// bookinfoからorderList(カートデータ)分だけ商品情報を呼び出す。
				for (int i = 0; i < cartList.size(); i++) {
					Goods goods = goodsDao.selectById(String.valueOf(cartList.get(i).getGoodsid()));
					// 取得したデータをbookListに追加
					goodsList.add(goods);
					total += goods.getPrice();
				}
			}
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("total", total);
			
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート状況は確認できません。";
			cmd = "logout";
			
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showcart.jsp").forward(request, response);
				
			} else {//エラーなら
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				
			}
		}

	}
	
}
