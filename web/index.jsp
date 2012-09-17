<%-- 
    Document   : index
    Created on : Sep 12, 2012, 8:11:17 PM
    Author     : Cody Auter
--%>

<%@page import="model.MenuItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<MenuItem> menuList = (List<MenuItem>) request.getAttribute("menuList");
	List<MenuItem> orderList = (List<MenuItem>) request.getAttribute("orderList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cody & Katie's Wedding Web Order Form</title>
    </head>
    <body>
        <h2>Welcome to</h2>
        <h1>Cody & Katie's Wedding</h1>
        <h2>Online Wedding Meal Planner</h2>
        <br />
        <p>Please select what you would like to eat at the reception:</p>
        <form method="POST" action="selectCheckboxes">
            <%
                for (MenuItem menuItem : menuList)
                {
                    String item = menuItem.getName();

            %>

            <input type="checkbox" name="menuItems" value="<%= item%>" /> <%= item%><br/>

            <%
                }
            %>

            <br/>
            <input type="submit" value="Place Order" name="submit">
        </form>
    </body>
</html>
