<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Todo</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Edit Todo</h1>
            <a href="todos" class="btn btn-secondary">Back to List</a>
        </header>

        <main>
            <form action="todos" method="post" class="todo-form">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${todo.id}">

                <div class="form-group">
                    <label for="title">Title *</label>
                    <input type="text" id="title" name="title"
                           value="${todo.title}" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="4">${todo.description}</textarea>
                </div>

                <div class="form-group">
                    <label class="checkbox-label">
                        <input type="checkbox" name="completed"
                               ${todo.completed ? 'checked' : ''}>
                        Mark as completed
                    </label>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Update Todo</button>
                    <a href="todos" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </main>
    </div>
</body>
</html>