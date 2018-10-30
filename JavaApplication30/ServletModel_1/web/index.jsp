<%-- 
    Document   : index
    Created on : 2018/10/22, 下午 03:44:46
    Author     : Vivi
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GrooGo SSearch</title>
</head>
<body>
<Form action="Secend" method="POST">
GrooGo Search <input type="text" name="visitor" size = "10"><P/>
<%java.util.Date d =new java.util.Date(); %>
<input type="submit" value="確定"><P/>
<%=d %>
</Form>
</body>
</html>