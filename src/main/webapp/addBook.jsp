<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Add Book </title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        .border-red-500 {
            border-color: #EF4444; /* Color rojo Tailwind CSS */
        }
    </style>
</head>
<body class="bg-gray-100 pt-16">
<div class="max-w-xl mx-auto p-6 bg-white rounded-md shadow">
    <h1 class="text-2xl font-bold mb-4">Add New Book</h1>
    <form id="addBookForm" class="space-y-4">
        <div>
            <label for="title" class="block font-medium text-gray-700"> Titulo:</label>
            <input type="text" id="title" name="title" class="mt-1 p-2 border border-gray-300 rounded-md w-full" required>
        </div>
        <div>
            <label for="author" class="block font-medium text-gray-700">Autor:</label>
            <input type="text" id="author" name="author" class="mt-1 p-2 border border-gray-300 rounded-md w-full" required>
        </div>
        <div>
            <label for="publicationYear" class="block font-medium text-gray-700">Año de publicacion:</label>
            <input type="text" id="publicationYear" name="publicationYear" class="mt-1 p-2 border border-gray-300 rounded-md w-full" required>
        </div>
        <div>
            <button type="button" id="submitBtn" class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">Agregar Book</button>
        </div>
    </form>
</div>

<!-- Esto lo tuve que agregar porque la funcion POST esperaba un JSON

Si lo dejaba bien basico, daba error porque recibia los parametros pero no en JSON -->

<script>
    document.getElementById("submitBtn").addEventListener("click", function() {
        var form = document.getElementById("addBookForm");
        var publicationYearInput = document.getElementById("publicationYear");

        <!-- AJAX -->

        // Verificar si el valor del año de publicación es un número entero
        if (!Number.isInteger(parseInt(publicationYearInput.value))) {
            publicationYearInput.classList.add("border-red-500"); // Agregue una clase para resaltar el borde en rojo
            return; // Detener el envío del formulario si el año de publicación no es un número entero
        } else {
        publicationYearInput.classList.remove("border-red-500"); // Eliminar la clase de resaltado rojo
        }

        <!-- A partir de aca es tod para convertir a JSON -->
        var formData = new FormData(form);
        var jsonObject = {};
        formData.forEach(function(value, key){
            jsonObject[key] = value;
        });

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "book-servlet");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 201) {
                    alert("Libro agregado exitosamente.");
                    form.reset();
                    window.location.href = "index.jsp";
                } else {
                    alert("Error al agregar libro.");
                }
            }
        };
        xhr.send(JSON.stringify(jsonObject));
    });
</script>

</body>
</html>
