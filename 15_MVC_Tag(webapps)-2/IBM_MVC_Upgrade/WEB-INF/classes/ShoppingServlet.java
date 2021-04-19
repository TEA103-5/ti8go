import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import shopping.BOOK;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		
		@SuppressWarnings("unchecked")
		List<BOOK> buylist = (Vector<BOOK>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的書籍
				BOOK abook = getBook(req);

				if (buylist == null) {
					buylist = new Vector<BOOK>();
					buylist.add(abook);
				} else {
					if (buylist.contains(abook)) {
						BOOK innerBOOK = buylist.get(buylist.indexOf(abook));
						innerBOOK.setQuantity(innerBOOK.getQuantity() + abook.getQuantity());
					} else {
						buylist.add(abook);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			double total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				BOOK order = buylist.get(i);
				Double price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private BOOK getBook(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String publisher = req.getParameter("publisher");
		String price = req.getParameter("price");

		BOOK book = new BOOK();

		book.setName(name);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(new Double(price));
		book.setQuantity((new Integer(quantity)).intValue());
		return book;
	}
}