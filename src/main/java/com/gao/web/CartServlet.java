package com.gao.web;

import com.gao.pojo.Book;
import com.gao.pojo.Cart;
import com.gao.pojo.CartItem;
import com.gao.service.BookService;
import com.gao.service.impl.BookServiceImpl;
import com.gao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                System.out.println("加入购物车");
        System.out.println("商品编号："+req.getParameter("id"));
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem); 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));
        //重定向回原来商品所在列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
