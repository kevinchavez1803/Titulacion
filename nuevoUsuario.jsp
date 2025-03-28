
<link rel="stylesheet" href="CSS/StylesNueUsuario.css">
<!-- Contenedor del formulario -->
<div class="container">
    <!-- Botón de redirección al Dashboard -->
    <a href="dashboard.jsp" class="btn-dashboard">Regresar</a>

    <div class="form-box">
        <h2>Crear Nuevo Usuario</h2>
        <form action="UsuarioServlet" method="post">
            <input type="hidden" name="action" value="create">

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" class="form-control" id="apellido" name="apellido" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="username" class="form-label">Usuario:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Contrasena:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Telefono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono">
            </div>

            <div class="mb-3">
                <label for="direccion" class="form-label">Direccion:</label>
                <input type="text" class="form-control" id="direccion" name="direccion">
            </div>

            <!-- Campo de selección para el rol -->
            <div class="mb-3">
                <label for="rol" class="form-label">Rol:</label>
                <select class="form-control" id="rol" name="rol" required>
                    <option value="cliente">Cliente</option>
                    <option value="entrenador">Entrenador</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary w-100">Crear</button>
        </form>
    </div>
</div>
