package murach.cart;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.data.ProductIO;

public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		ServletContext sc = request.getServletContext();
		
		// get current action
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			action = "cart";
		}
		
		// perform action and set URL to appropriate page
		String url = "/ch09.jsp";
		if (action.equals("shop")) {
			url = "/ch09.jsp";
		}
		else if (action.equals("cart")) {
			String productCode = request.getParameter("productCode");
			String quantityString = request.getParameter("quantity");
			
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
			}
			
			// if the user enters a negative or invalid quantity
			// the quantity is automatically reset 1
			int quantity;
			try {
				quantity = Integer.parseInt(quantityString);
				if (quantity < 0) {
					quantity = 1;
				}
			} catch(NumberFormatException nfe) {
				quantity = 1;
			}
			
			String path = sc.getRealPath("/WEB-INF/chapter09/products.txt");
			Product product = ProductIO.getProduct(productCode, path);
			
			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			lineItem.setQuantity(quantity);
			if (quantity > 0) {
				cart.addItems(lineItem);
			}
			else if (quantity == 0) {
				cart.removeItem(lineItem);
			}
			
			session.setAttribute("cart", cart);
			url = "/WEB-INF/chapter09/cart.jsp";
		}
		else if (action.equals("checkout")) {
			url = "/WEB-INF/chapter09/checkout.jsp";
		}
		
		sc.getRequestDispatcher(url).forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
