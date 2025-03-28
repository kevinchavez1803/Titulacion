<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Verificar el rol del usuario desde la sesión
    String rol = (String) session.getAttribute("userRole");
    if (rol == null || !rol.equalsIgnoreCase("entrenador")) {
        response.sendRedirect("login.jsp"); // Redirigir a login si no es entrenador
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Entrenador</title>
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
            max-width: 800px;
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
        /* Barra de navegación */
        .navbar {
            background-color: #333;
            padding: 10px;
            text-align: center;
        }
        .navbar a {
            color: white;
            padding: 14px 20px;
            text-decoration: none;
            display: inline-block;
        }
        .navbar a:hover {
            background-color: #575757;
        }
        .navbar .active {
            background-color: #007bff;
        }
        /* Tabla de entrenamientos */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        table th {
            background-color: #007bff;
            color: white;
        }
        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tr:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<!-- Barra de navegación -->
<div class="navbar">
    <a href="crearEntrenamiento.jsp">Crear Entrenamiento</a>
    <a href="login.jsp">Cerrar sesión</a>
</div>

<div class="container">
    <h2>Bienvenido, Entrenador</h2>
    <p>Aquí puedes gestionar entrenamientos y clientes.</p>

    <!-- Mostrar mensaje de error si aplica -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- Mostrar mensaje si no hay entrenamientos -->
    <c:if test="${empty entrenamientos}">
        <p style="color: red;">No hay entrenamientos disponibles para mostrar.</p>
    </c:if>

    <!-- Mostrar tabla de entrenamientos si hay registros -->
    <c:if test="${not empty entrenamientos}">
        <h1>Lista de Entrenamientos</h1>
        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre del Entrenamiento</th>
                <th>Duración</th>
                <th>Nivel</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${entrenamientos}" var="entrenamiento">
                <tr>
                    <td>${entrenamiento.id}</td>
                    <td>${entrenamiento.nombre}</td>
                    <td>${entrenamiento.duracion}</td>
                    <td>${entrenamiento.nivel}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="/index.jsp">Regresar</a>
</div>
</body>
</html>