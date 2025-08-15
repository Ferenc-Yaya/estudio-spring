<!DOCTYPE html>
<html>
<head>
    <title>Pokemon MVC</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>🌟 ¡FUNCIONA! Spring MVC + HTML</h1>

<p><strong>¡Éxito!</strong> Spring MVC está mostrando este HTML correctamente.</p>

<h2>Capturar Pokémon</h2>
<form action="/pokemon/capturar" method="POST">
    <p>
        <label>Nombre:</label><br>
        <input type="text" name="nombre" placeholder="Pikachu" required>
    </p>
    <p>
        <label>Tipo:</label><br>
        <input type="text" name="tipo" placeholder="Eléctrico" required>
    </p>
    <p>
        <button type="submit">⚡ Capturar</button>
    </p>
</form>

<hr>
<p><strong>Ubicación:</strong> src/main/webapp/WEB-INF/views/pokemon-inicio.html</p>

<div style="background: #e3f2fd; padding: 10px; margin: 10px 0;">
    <strong>🎉 ¡Tu primera aplicación Spring MVC funciona!</strong><br>
    Ahora puedes capturar pokémons desde el navegador.
</div>

</body>
</html>