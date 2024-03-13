<%@ page import="org.laraujo.demo_api.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Lista de libros </title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-200">

<div class="container mx-auto text-center my-8">
    <h1 class="text-3xl font-bold mb-4"> Lista de libros </h1>
    <table class="table-auto mx-auto">
        <thead>
        <tr>
            <th class="px-4 py-2">Título</th>
            <th class="px-4 py-2">Autor</th>
            <th class="px-4 py-2">Año de publicacion</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book book : books) {
        %>
        <tr>
            <td class="border px-4 py-2"><%= book.getTitle() %></td>
            <td class="border px-4 py-2"><%= book.getAuthor() %></td>
            <td class="border px-4 py-2"><%= book.getPublicationYear() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>

