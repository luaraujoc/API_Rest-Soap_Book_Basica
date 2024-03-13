<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Demo para probar api rest y ws soap</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-blue-300">

<div class="container mx-auto text-center my-24">
    <h1 class="text-5xl text-white"> Bibloteca Uy</h1>
    <br/>
    <a href="book-servlet?action=all" class="text-white font-bold text-lg bg-gray-800 hover:bg-gray-700 py-2 px-4 rounded inline-block mb-4">Ver libros disponibles (GET action = all)</a>
    <br/>
    <a href="addBook.jsp" class="text-white font-bold text-lg bg-gray-800 hover:bg-gray-700 py-2 px-4 rounded inline-block">Agregar un libro (POST)</a>
</div>

</body>
</html>
