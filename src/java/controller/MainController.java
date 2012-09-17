package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.RequestDispatcher;
import model.*;

/**
 * Created with help from jlombardo.
 * Needs recreate on own. See project WeddingMenuApp.
 * 
 * @author Cody Auter
 */
public class MainController extends HttpServlet
{

    private OrderService orderService;

    /**
     * Constructor of the object.
     */
    public MainController()
    {
        super();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException
    {
        boolean responseCommitted = false;
        String destination = "/index.jsp";
        String action = request.getParameter("action");
        String orderEvent = request.getParameter("submit");
        List<MenuItem> menuList = orderService.getMenuList();
        List<MenuItem> orderList = orderService.getOrderList();
        String[] orderItems = null;

        if (action.equals("selectCheckboxes"))
        {
            orderItems = request.getParameterValues("menuItems");
            orderList.clear();
            for (String item : orderItems)
            {
                for (MenuItem menuItem : menuList)
                {
                    if (menuItem.getName().equals(item))
                    {
                        orderList.add(menuItem);
                        break;
                    }
                }
            }
            if (orderList.size() > 0)
            {
                orderService.placeOrder();
            }

            // We need to get the value of responseCommitted back from
            // the method because the original is passed by value (a copy)                    
            responseCommitted =
                    outputConfirmation(request, response, menuList, orderList,
                                       responseCommitted);

        }

        if (!responseCommitted)
        {
            request.setAttribute("menuList", menuList);
            request.setAttribute("orderList", orderList);

            // Redirect to destination page
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    private boolean outputConfirmation(HttpServletRequest request,
                                       HttpServletResponse response,
                                       List menuList, List<MenuItem> orderList,
                                       boolean responseCommitted) throws
            IOException
    {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.
                println(
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.
                println(
                "  <HEAD><TITLE>Your meal order has been placed!</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    <h2>Thank you! Your meal order has been placed.</h2> ");
        out.println("<p>You ordered the following:</p>");
        out.println("<ul>");
        for (MenuItem item : orderList)
        {
            out.println("<li>" + item.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("<a href='order?action=new'>Place a new order</a>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        menuList.addAll(orderList);
        orderList.removeAll(orderList);
        responseCommitted = true;
        return responseCommitted;
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occure
     */
    @Override
    public void init() throws ServletException
    {
        orderService = new OrderService();
    }
}
