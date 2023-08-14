/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalet
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Tasks> todoList;

    public TaskService() {
        todoList = new ArrayList<>();
    }
    
    public void createTask(String title, String description) {
    String sql = "INSERT INTO tasks (title, description, done ) VALUES (?, ?, ?)";

    try (Connection connection = ComunDB.obtenerConexion();
         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        statement.setString(1, title);
        statement.setString(2, description);
        statement.setBoolean(3,false);
        
        statement.executeUpdate();

        // Obtener el ID generado automáticamente
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                Tasks todo = new Tasks(title, description, false);
                todoList.add(todo);
            } else {
                throw new SQLException("Fallo al obtener el ID generado para el producto.");
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    public Tasks readTask(int id) {
        String sql = "SELECT * FROM tasks WHERE Id = ?";

        try (Connection connection = ComunDB.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Tasks product = new Tasks(
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("done")
                    );
                    return product;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void checkDone(int id, String title, String description) {
        Tasks todo = readTask(id);
        if (todo != null) {
            todo.setTaskTitle(title);
            todo.setDescription(description);
            todo.setDone(false);
       

            String sql = "UPDATE Products SET title = ?, description = ?, done = ? WHERE Id = ?";

            try (Connection connection = ComunDB.obtenerConexion();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, title);
                statement.setString(2, description);
                statement.setBoolean(3,false);
                statement.setInt(6, id);

                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

  

    public List<Tasks> getAllTodos() {
        String sql = "SELECT * FROM tasks";
        List<Tasks> todos= new ArrayList<>();

        try (Connection connection = ComunDB.obtenerConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Tasks todo = new Tasks(
                        resultSet.getString("ProductName"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("done")
                );
                todoList.add(todo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return todos;
    }
}



