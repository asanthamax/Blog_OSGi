<%@page import="com.ayomaonline.osgi.main.api.MenuItem"%>
<%@page import="java.util.List"%>
<%@page import="com.ayomaonline.osgi.main.registrar.MenuItemRegistrar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Menu</title>
</head>
<body>
	<ul>
		<%
			for (String module : MenuItemRegistrar.getMenuItems().keySet()) {
				List<MenuItem> menuItemList = MenuItemRegistrar.getMenuItems().get(module);
				for (MenuItem menuItem : menuItemList) {
					%>
						<li><a href="<%=module%>/<%=menuItem.getPath()%>"><%=menuItem.getKey() %></a></li>
					<%
				}
			}
		%>
	</ul>
</body>
</html>