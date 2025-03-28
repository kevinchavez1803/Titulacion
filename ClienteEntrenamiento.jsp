<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Cliente y Entrenador</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body>
<div class="container mt-5">
    <!-- Encabezado -->
    <h1 class="text-center mb-4">Gestión de Cliente y Entrenador</h1>

    <!-- Formulario para asignar cliente a entrenador -->
    <div class="card p-4 mb-4">
        <h3 class="card-title mb-3">Asignar Cliente a Entrenador</h3>
        <form action="AsignacionServlet" method="post">
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="id_cliente" class="form-label">Cliente</label>
                    <select id="id_cliente" name="id_cliente" class="form-select" required>
                        <option value="">Seleccione un Cliente</option>
                        <!-- Aquí puedes insertar opciones dinámicamente desde tu backend -->
                        <option value="1">Cliente 1</option>
                        <option value="2">Cliente 2</option>
                        <option value="3">Cliente 3</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="id_entrenador" class="form-label">Entrenador</label>
                    <select id="id_entrenador" name="id_entrenador" class="form-select" required>
                        <option value="">Seleccione un Entrenador</option>
                        <!-- Aquí puedes insertar opciones dinámicamente desde tu backend -->
                        <option value="1">Entrenador 1</option>
                        <option value="2">Entrenador 2</option>
                        <option value="3">Entrenador 3</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Asignar</button>
        </form>
    </div>

    <!-- Listado de asignaciones existentes -->
    <div class="card p-4">
        <h3 class="card-title mb-3">Asignaciones Actuales</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Cliente</th>
                <th>Entrenador</th>
                <th>Fecha de Asignación</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <!-- Aquí puedes cargar dinámicamente los datos desde el backend -->
            <c:forEach var="asignacion" items="${asignaciones}">
                <tr>
                    <td>${asignacion.id}</td>
                    <td>${asignacion.cliente.nombre}</td>
                    <td>${asignacion.entrenador.nombre}</td>
                    <td>${asignacion.fechaAsignacion}</td>
                    <td>${asignacion.estado}</td>
                    <td>
                        <form action="EliminarAsignacionServlet" method="post" style="display:inline;">
                            <input type="hidden" name="id_asignacion" value="${asignacion.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Mensaje de éxito o error -->
    <c:if test="${mensaje != null}">
        <div class="alert alert-info mt-4" role="alert">
                ${mensaje}
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>