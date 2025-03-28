<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Obtener el rol del usuario desde la sesión
    String rol = (String) session.getAttribute("userRole");

    // Verificar si el usuario tiene rol de cliente
    if (rol == null || !rol.equalsIgnoreCase("cliente")) {
        response.sendRedirect("login.jsp"); // Redirigir a login si no es cliente
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Bienvenido, Cliente</h2>
    <p>Aquí puedes ver tus entrenamientos y progreso.</p>
    <a href="login.jsp">Cerrar sesión</a>
</div>
</body>
</html>
