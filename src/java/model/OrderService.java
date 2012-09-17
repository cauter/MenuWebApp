package model;

import java.util.*;

/**
 *
 * @author Cody Auter
 */

public class OrderService
{
    private List<MenuItem> menuList;
    private List<MenuItem> orderList;
    private OrderDAOStrategy orderDao;

    public OrderService()
    {
        initItemsDb();
    }

    private void initItemsDb()
    {
        orderDao = new OrderDAODev();
        menuList = orderDao.getCurrentMenuChoices();
        orderList = new ArrayList<MenuItem>();
    }

    public void placeOrder()
    {
        orderDao.saveOrder(orderList);
    }

    public List<MenuItem> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<MenuItem> menuList)
    {
        this.menuList = menuList;
    }

    public List<MenuItem> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(List<MenuItem> orderList)
    {
        this.orderList = orderList;
    }

    public OrderDAOStrategy getOrderDao()
    {
        return orderDao;
    }

    public void setOrderDao(OrderDAOStrategy orderDao)
    {
        this.orderDao = orderDao;
    }
}