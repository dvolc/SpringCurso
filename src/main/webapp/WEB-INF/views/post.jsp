<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<!doctype html/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h1><spring:message code="post.editar.POST.ACTUAL" /></h1>
 
   
   <c:out value="${nombre}" />
   
   
   <h2>${post.titulo}</h2>
   
   <p>${post.contenido}</p>
   
   <c:forEach var="comentario" items="${post.comentarios}">
   	<h2> <c:out value="${comentario.comentarista}" /> dice:</h2>
   	<p>	<c:out value="${comentario.comentario}" />
   		
   </c:forEach>

</body>
</html>