package com.atliu.web;

import com.atliu.pojo.Book;
import com.atliu.pojo.Page;
import com.atliu.service.BookService;
import com.atliu.service.impl.BookServiceImpl;
import com.atliu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 价格区间搜索并处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2、调用BookService.page(pageNo,pageSize)page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果最小的价格的参数，追加到分页条的地址参数中
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果最小的价格的参数，追加到分页条的地址的参数中
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3、保存page对象到request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
