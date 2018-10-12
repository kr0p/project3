<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selected Stock Data</title>
</head>
<body>
	<table>
		<tbody>
			<tr>
				<td>Company: </td>
				<td><% out.println(session.getAttribute("company")); %></td>
			</tr>
			<tr>
				<td>Date: </td>
				<td><% out.println(session.getAttribute("date")); %></td>
			</tr>
			<tr>
				<td>Open: </td>
				<td><% out.println(session.getAttribute("open")); %></td>
			</tr>
			<tr>
				<td>High: </td>
				<td><% out.println(session.getAttribute("high")); %></td>
			</tr>
			<tr>
				<td>Low: </td>
				<td><% out.println(session.getAttribute("low")); %></td>
			</tr>
			<tr>
				<td>Close: </td>
				<td><% out.println(session.getAttribute("close")); %></td>
			<tr>
			<tr>
				<td>Percent Change:</td>
				<td><% out.println(session.getAttribute("percent")); %></td>
			</tr>
		</tbody>
	</table>
	<button type = "button" name ="back" onclick = "history.back()">Back</button>
	<button type = "button" onclick = "window.open('', '_self', ''); window.close();">Exit</button>
</body>
</html>