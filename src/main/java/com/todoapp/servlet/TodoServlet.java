package com.todoapp.servlet;

import com.todoapp.dao.TodoDAO;
import com.todoapp.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TodoServlet extends HttpServlet {
    private TodoDAO todoDAO;

    @Override
    public void init() throws ServletException {
        todoDAO = new TodoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteTodo(request, response);
                    break;
                default:
                    listTodos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            switch (action == null ? "" : action) {
                case "insert":
                    insertTodo(request, response);
                    break;
                case "update":
                    updateTodo(request, response);
                    break;
                default:
                    listTodos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listTodos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Todo> todos = todoDAO.getAllTodos();
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-todo.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = todoDAO.getTodoById(id);
        request.setAttribute("todo", existingTodo);
        request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Todo newTodo = new Todo(title, description);
        todoDAO.addTodo(newTodo);
        response.sendRedirect("todos");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = request.getParameter("completed") != null;

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(completed);

        todoDAO.updateTodo(todo);
        response.sendRedirect("todos");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDAO.deleteTodo(id);
        response.sendRedirect("todos");
    }
}