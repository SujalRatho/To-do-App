package com.todoapp.dao;

import com.todoapp.model.Todo;
import com.todoapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    private static final String SELECT_ALL_TODOS =
            "SELECT id, title, description, completed, created_at, updated_at FROM todos ORDER BY created_at DESC";

    private static final String SELECT_TODO_BY_ID =
            "SELECT id, title, description, completed, created_at, updated_at FROM todos WHERE id = ?";

    private static final String INSERT_TODO =
            "INSERT INTO todos (title, description) VALUES (?, ?)";

    private static final String UPDATE_TODO =
            "UPDATE todos SET title = ?, description = ?, completed = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

    private static final String DELETE_TODO =
            "DELETE FROM todos WHERE id = ?";

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TODOS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                todos.add(extractTodoFromResultSet(resultSet));
            }
        }

        return todos;
    }

    public Todo getTodoById(int id) throws SQLException {
        Todo todo = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TODO_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    todo = extractTodoFromResultSet(resultSet);
                }
            }
        }

        return todo;
    }

    public boolean addTodo(Todo todo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TODO)) {

            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateTodo(Todo todo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TODO)) {

            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
            statement.setBoolean(3, todo.isCompleted());
            statement.setInt(4, todo.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteTodo(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TODO)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Todo extractTodoFromResultSet(ResultSet resultSet) throws SQLException {
        return new Todo(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getBoolean("completed"),
                resultSet.getTimestamp("created_at"),
                resultSet.getTimestamp("updated_at")
        );
    }
}