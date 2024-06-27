package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Cart;
import bean.Goods;
import bean.User;
import dao.CartDAO;
import dao.GoodsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/BuyConfirm")
public class BuyConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "logout";
		GoodsDAO goodsDao = new GoodsDAO();
		CartDAO cartDao = new CartDAO();
		ArrayList<Goods> goodsList = new ArrayList<>();
		try {
			HttpSession session = request.getSession();

			// ログインしていなかったらエラー
			User user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、購入はできません。";
				return;
			}

			// カートの中に何も入っていなかったら購入不可
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
			if (cartList == null || cartList.size() == 0) {
				error = "カートの中に何もなかったので購入はできません。";
				cmd = "menu";
				return;
			}

			//メール文、合計計算
			String buyList = "";
			int total = 0;

			// bookinfoからorderList(カートデータ)分だけ書籍情報を呼び出す。
			for (Cart cart : cartList) {
				Goods goods = goodsDao.selectById( String.valueOf(cart.getGoodsid()));
				
				// 取得したデータをlistに追加
				goodsList.add(goods);

				// メール本文の注文情報を格納
				buyList += goods.getGoodsId() + "\t"
						+ goods.getGoodsName() + "\t"
						+ goods.getPrice() + "円\n";
				//合計金額を計算
				total += goods.getPrice();
				request.setAttribute("total", total);
				request.setAttribute("goods_list", goodsList);
				// DBのorderinfoに注文情報を登録する
				cartDao.insert(cart);
			}

			// メールの件名を作成
			String subject = "【超エキサイティング！！】ご購入誠にありがとうございます。";
			// メール本文を作成
			String body = user.getUser_id() + "様\n\n"
					+ "ユニフォームのご購入ありがとうざいます。\n"
					+ "以下内容でご注文を受け付けましたので、ご連絡致します。\n\n"
					+ buyList
					+ "合計" + total + "円\n\n"
					+ "またのご利用よろしくお願いします。 ";

			// メール送信
			SendMail mail = new SendMail();
			mail.send(subject, body, user.getEmail());

			
			

			// オーダー情報クリア（カートの中を空にする）
			session.setAttribute("orderList", null);
		} /*catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入はできません。";
			} */finally {
			if (error.equals("")) {//エラーなし
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			} else {//エラーあり
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}