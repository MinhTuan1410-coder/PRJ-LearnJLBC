<%-- 
    Document   : product-list
    Created on : Dec 14, 2025, 5:05:38 PM
    Author     : Trần Minh Tuấn
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductDAO"%>


<%!
    //declearation
    String name="Dat LT";
%>

<%
    //scriptlet,
    ProductDAO productDAO = new ProductDAO();
    List<Product> productList = productDAO.getAllProducts();
%>    




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Product List</h1>
        <h1><%=name%></h1>
        
       <table border>
           <th>ProductID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Size</th>
                <th>Status</th>
            </tr>

            
        <%
            for (Product product : productList) {
        %>
        
            <tr>
                <td><%=product.getProductID()%></td>
                <td><%=product.getName()%></td>
                <td><%=product.getDescription()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getSize()%></td>
                <td><%=product.isStatus()%></td>
            </tr>



        <%
            }
        %>
        </table>
    </body>
</html>
