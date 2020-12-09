<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='java.util.ArrayList' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Examen M3 - David Iglesias Perez</title>

	<script type="text/javascript">
	  function cargarMunicipios() {
		  //console.log(arguments[0]);
		  window.location = "<%=request.getContextPath()%>/municipios?provincia=" + arguments[0];
	  }
	</script>
	
	
	
	
	<style>
input[type=text], select {
  width: 100%;
  padding: 8px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=email], select {
  width: 100%;
  padding: 8px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=password], select {
  width: 100%;
  padding: 8px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}



input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

</style>
</head>
<body>
<form name="form" action="<%=request.getContextPath()%>/Controlador" method="post" >
<table align="center">

<tr>
<td>Nombre:</td><td> <input type="text" size="60" name="nombre" placeholder="Nombre del distinatario" required> </td>
</tr>
<tr>
<td>Email destinatario:</td><td>
<input type="email" size="40" name="emaildestino" placeholder="Para ... email@mail.com" id="myEmail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
 <script>
function myFunction() {
  var x = document.getElementById("myEmail").pattern;
  document.getElementById("demo").innerHTML = x;
}
</script>
 </td>
</tr>
<tr>
<td>Provincia:</td><td>
<% 
	String seleccionado = (String)request.getAttribute("seleccionado");
	if(seleccionado == null) {
		seleccionado = "";
	}
	
	//ArrayList<String> listadoProvincias = (ArrayList<String>)request.getAttribute("listadoProvincias");
	ArrayList<String> listadoProvincias = (ArrayList<String>)request.getSession().getAttribute("listadoProvincias");
	
	//ArrayList<String> listadoMunicipios = (ArrayList<String>)request.getAttribute("listadoMunicipios");
%>
    <select name="provincia" id="provincia" >                
    <% 
    if(listadoProvincias != null) {
    %>
        <option value="">Elige una provincia...</option>
    <%
    	  for(String provincia : listadoProvincias) {
    		    if(provincia.equals(seleccionado)) {
    %>
               <option value="<%= provincia %>" selected><%= provincia %></option>
    <%
            } else {
    %>
               <option value="<%= provincia %>"><%= provincia %></option>
    <% 
            } 
    		} 
    }
    %>
    </select>

<!--  
<input type="text" size="60" name="provincia" placeholder="provincia" required>
-->
</td>
</tr>
<tr>
<td>Datos email de origen:</td><td> <input type="email" size="40" placeholder="email de origen" name="emaildeorigen" value="" required>
 <input type="password" size="40" name="clavemail" placeholder="contraseña" value=""  required>
 

 </td>
<td> </td>
</tr>
<tr>
<td>Mensaje:</td><td> <textarea name="mensaje" cols="70" rows="7" placeholder="Escribe aqui tu mensaje" size="100"></textarea></td>
</tr>

<tr>
<td>
<input type="submit" value="Enviar mensaje">
</td>
</tr>
</table>
</form>
</body>
</html>