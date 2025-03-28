<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesion</title>
    <link rel="stylesheet" href="CSS/stylesLogin.css">
</head>
<body style="background-image: url('IMG/img.2.jpg');">
<div class="login-container">
    <!-- Botón de regresar -->
    <a href="index.html" class="btn-back">Regresar</a>

    <h2>Iniciar sesion</h2>

    <%-- Mostrar mensaje de error si está presente --%>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form action="/GymApp_war/LoginServlet" method="post">
        <input type="text" name="username" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <input type="submit" value="Iniciar sesion">
    </form>
</div>
</body>
</html>
