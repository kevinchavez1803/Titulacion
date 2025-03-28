<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Usuario" %>

<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    if (usuarios == null) {
        response.sendRedirect("UsuarioServlet?action=list");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="CSS/styles.css">
</head>
<body>
<header class="header">
    <div class="header-container">
        <h1 class="title">Lista de Usuarios</h1>
        <nav>
            <ul class="nav-links">
                <li><a href="dashboard.jsp">Listar Usuarios</a></li>
                <li><a href="nuevoUsuario.jsp">Nuevo Usuario</a></li>
                <li><a href="ClienteEntrenamiento.jsp">Asignar Usuario</a></li>
            </ul>
        </nav>
        <ul class="logout">
            <li><a href="login.jsp" class="btn-logout">Cerrar Sesión</a></li>
        </ul>
    </div>
</header>

<main>
    <section class="message-section">
        <c:if test="${not empty mensaje}">
            <div class="mensaje">${mensaje}</div>
        </c:if>
    </section>

    <section class="user-table">
        <table id="usuariosTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Usuario</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty usuarios}">
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.id}</td>
                            <td><input type="text" value="${usuario.nombre}" id="nombre_${usuario.id}"></td>
                            <td><input type="text" value="${usuario.apellido}" id="apellido_${usuario.id}"></td>
                            <td><input type="text" value="${usuario.email}" id="email_${usuario.id}"></td>
                            <td><input type="text" value="${usuario.username}" id="usuario_${usuario.id}"></td>
                            <td><input type="text" value="${usuario.telefono}" id="telefono_${usuario.id}"></td>
                            <td><input type="text" value="${usuario.direccion}" id="direccion_${usuario.id}"></td>
                            <td>
                                <button onclick="guardarCambios(${usuario.id})" class="btn-save">Guardar</button>
                                <a href="UsuarioServlet?action=delete&id=${usuario.id}" class="btn-delete" onclick="return confirm('¿Está seguro de eliminar este usuario?');">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="8">No hay usuarios registrados.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </section>
</main>

<script>
    function guardarCambios(id) {
        let nombre = document.getElementById("nombre_" + id).value;
        let apellido = document.getElementById("apellido_" + id).value;
        let email = document.getElementById("email_" + id).value;
        let usuario = document.getElementById("usuario_" + id).value;
        let telefono = document.getElementById("telefono_" + id).value;
        let direccion = document.getElementById("direccion_" + id).value;

        fetch('UsuarioServlet?action=update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `id=${id}&nombre=${nombre}&apellido=${apellido}&email=${email}&usuario=${usuario}&telefono=${telefono}&direccion=${direccion}`
        })
            .then(response => response.text())
            .then(data => {
                alert('Usuario actualizado correctamente');
                location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
</body>
</html>
