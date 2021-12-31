package com.atliu.web;

import com.atliu.pojo.Book;
import com.atliu.pojo.Cart;
import com.atliu.pojo.CartItem;
import com.atliu.service.BookService;
import com.atliu.service.impl.BookServiceImpl;
import com.atliu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();


    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        //先去从session域中去查询是否又购物车（cart）
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //如果等于null表示没有
        if (cart == null){
            //创建购物车
            cart = new Cart();
            //保存到session域中
            req.getSession().setAttribute("cart",cart);
        }
        //调用Cart.addItem(CartItem);添加商品项
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头Referer的值" + req.getHeader("Referer"));
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        //先去从session域中去查询是否又购物车（cart）
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //如果等于null表示没有
        if (cart == null){
            //创建购物车
            cart = new Cart();
            //保存到session域中
            req.getSession().setAttribute("cart",cart);
        }
        //调用Cart.addItem(CartItem);添加商品项
        cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("lastName",cartItem.getName());
        //6、返回购物车的总商品数量和最后一个添加的商品名称
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }

    /**
     * 删除购物车商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品的编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //删除了购物车商品项
            cart.deleteItem(id);
            ////重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //清空购物车
            cart.clear();
            ////重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车中商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            // 修改商品数量
            cart.updateCount(id,count);
            ////重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


}
