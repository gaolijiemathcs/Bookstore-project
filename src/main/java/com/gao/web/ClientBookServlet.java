package com.gao.web;

import com.gao.pojo.Book;
import com.gao.pojo.Page;
import com.gao.service.BookService;
import com.gao.service.impl.BookServiceImpl;
import com.gao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("经过了前台ClientBookServlet程序");

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo, pageSize) Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("经过了前台ClientBookServlet程序");

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2 调用BookService.page(pageNo, pageSize) Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如有最小价格的参数 追加到分页条地址参数中
        if(req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }

        if(req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
