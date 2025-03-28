<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Entrenamiento</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Crear Rutina de Entrenamiento</h1>

    <form action="CrearEntrenamientoServlet" method="post">
        <div class="mb-3">
            <label for="nombre_entrenamiento" class="form-label">Nombre del Entrenamiento</label>
            <input type="text" class="form-control" id="nombre_entrenamiento" name="nombre_entrenamiento" required>
        </div>
        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" required></textarea>
        </div>
        <div class="mb-3">
            <label for="duracion" class="form-label">Duración (minutos)</label>
            <input type="number" class="form-control" id="duracion" name="duracion" required>
        </div>
        <div class="mb-3">
            <label for="nivel" class="form-label">Nivel</label>
            <select class="form-select" id="nivel" name="nivel" required>
                <option value="principiante">Principiante</option>
                <option value="intermedio">Intermedio</option>
                <option value="avanzado">Avanzado</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Crear Entrenamiento</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
