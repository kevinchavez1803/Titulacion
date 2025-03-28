<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="css/styles.css">
    <script>
        document.addEventListener("DOMContentLoaded", async () => {
            const params = new URLSearchParams(window.location.search);
            const userId = params.get("id");

            if (!userId) {
                alert("ID de usuario no válido");
                window.location.href = "dashboard.jsp";
                return;
            }

            try {
                const response = await fetch(`EditarUsuarioServlet?id=${userId}`);
                if (!response.ok) throw new Error("Error al obtener usuario");

                const usuario = await response.json();
                document.getElementById("id_usuario").value = usuario.id;
                document.getElementById("nombre").value = usuario.nombre;
                document.getElementById("apellido").value = usuario.apellido;
                document.getElementById("email").value = usuario.email;
                document.getElementById("username").value = usuario.username;
                document.getElementById("telefono").value = usuario.telefono;
                document.getElementById("direccion").value = usuario.direccion;
            } catch (error) {
                console.error("Error:", error);
                alert("No se pudo cargar la información del usuario.");
                window.location.href = "dashboard.jsp";
            }
        });

        async function actualizarUsuario(event) {
            event.preventDefault();
            const formData = new FormData(event.target);

            try {
                const response = await fetch("EditarUsuarioServlet", {
                    method: "POST",
                    body: new URLSearchParams(formData)
                });

                if (response.ok) {
                    alert("Usuario actualizado correctamente.");
                    window.location.href = "dashboard.jsp";
                } else {
                    alert("Error al actualizar usuario.");
                }
            } catch (error) {
                console.error("Error al actualizar:", error);
            }
        }
    </script>
</head>
<body>
<nav>
    <ul>
        <li><a href="dashboard.jsp">Listar Usuarios</a></li>
        <li><a href="nuevoUsuario.jsp">Nuevo Usuario</a></li>
    </ul>
</nav>

<h1>Editar Usuario</h1>

<form id="editarUsuarioForm" onsubmit="actualizarUsuario(event)">
    <input type="hidden" id="id_usuario" name="id_usuario">

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required><br><br>

    <label for="apellido">Apellido:</label>
    <input type="text" id="apellido" name="apellido" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="username">Usuario:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="telefono">Teléfono:</label>
    <input type="text" id="telefono" name="telefono"><br><br>

    <label for="direccion">Dirección:</label>
    <input type="text" id="direccion" name="direccion"><br><br>

    <button type="submit">Actualizar</button>
</form>
</body>
</html>
