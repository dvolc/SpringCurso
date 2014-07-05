<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:url var="urlAutentificar" value="/autentificar"/>

	<h1>LOGIN DEL BLOG</h1>
	
 <!-- SI VIENE LA BANDERA ACTUALIZADO PINTAMOS UN MENSAJE -->
   <c:if test="${errorLogin}">
      <h3>Datos Incorrectos</h3>

      <p> Email introducido: ${SPRING_SECURITY_LAST_EXCEPTION.authentication.name} </p>
	  <p> PASSWORD introducido: ${SPRING_SECURITY_LAST_EXCEPTION.authentication.credentials} </p>
	
   </c:if>
	
	<div>
		Sesion, El contenido de la variable es: <c:out value="${sessionScope.variable}"/>	
	</div>


	
	<!-- Lo correcto seria poner el context root con c: -->
	<form action="${urlAutentificar}" method="post">
   <div>
        <label>Email:</label><input type="text" name="email" />
   </div>
   <div>
         <label>Password:</label><input type="password" name="password" />
   </div>
   <input type="submit" value="Enviar" />
</form>
	
</body>
</html>