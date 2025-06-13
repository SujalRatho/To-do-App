<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>My Todo List</h1>
            <a href="todos?action=new" class="btn btn-primary">Add New Todo</a>
        </header>

        <main>
            <c:if test="${empty todos}">
                <div class="empty-state">
                    <h3>No todos yet!</h3>
                    <p>Click "Add New Todo" to get started.</p>
                </div>
            </c:if>

            <c:if test="${not empty todos}">
                <div class="todo-grid">
                    <c:forEach var="todo" items="${todos}">
                        <div class="todo-card ${todo.completed ? 'completed' : ''}">
                            <div class="todo-header">
                                <h3>${todo.title}</h3>
                                <span class="status ${todo.completed ? 'completed' : 'pending'}">
                                    ${todo.completed ? 'Completed' : 'Pending'}
                                </span>
                            </div>

                            <div class="todo-body">
                                <p>${todo.description}</p>
                            </div>

                            <div class="todo-footer">
                                <small class="date">
                                    Created: <fmt:formatDate value="${todo.createdAt}" pattern="MMM dd, yyyy" />
                                </small>
                                <div class="actions">
                                    <a href="todos?action=edit&id=${todo.id}" class="btn btn-secondary">Edit</a>
                                    <a href="todos?action=delete&id=${todo.id}" class="btn btn-danger"
                                       onclick="return confirm('Are you sure you want to delete this todo?')">Delete</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </main>
    </div>
</body>
</html>