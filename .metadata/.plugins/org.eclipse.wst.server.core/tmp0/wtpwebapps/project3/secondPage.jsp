<%@ page import = "project2.*" %>
<%@ page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
Data d = new Data();
ArrayList<String> companyList = new ArrayList<String>(); 
ArrayList<String> dateList = new ArrayList<String>();

for(int i = 0; i < d.getStockCount(); i++)
	companyList.add(d.getStockName(i));

for(int i = 0; i < d.getWeekCount(); i++)
	dateList.add(d.getWeekDate(i));

Iterator<String> comIter = companyList.iterator();
Iterator<String> dateIter = dateList.iterator();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Data Categories</title>
</head>
<body>
	<form action = "http://localhost:8080/project3/DataServlet" method = "post">
	<select name = "company" >
		<% while(comIter.hasNext()) {%>
		<option><% out.println(comIter.next()); %></option>
		<%} %>
	</select>
	<select name = "date" >
		<% while(dateIter.hasNext()) {%>
		<option><% out.println(dateIter.next()); %></option>
		<%} %>
	</select><br><br>
	<input type = "submit" value = "Submit">
	</form>
</body>
</html>