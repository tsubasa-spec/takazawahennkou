package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Goods;
import dao.GoodsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String error = "";
		

		try {
		GoodsDAO objDao = new GoodsDAO();
			

			ArrayList<Goods> goodsList = objDao.selectAll();
	
			request.setAttribute("goodsList", goodsList);

		
			}finally {
				if (error.equals("")) {
					//エラーがない場合PurchaseServletにフォワードする
					
					request.getRequestDispatcher("/view/purchase.jsp").forward(request, response);
				} else {
					//エラーがある場合はerror.jspにフォワードする
					request.setAttribute("error", error);
					
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}
	}
		
}
