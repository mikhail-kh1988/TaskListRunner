package com.listrunner.storage;

import com.listrunner.task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Storage {

    private String DB_NAME = "sla";
    private String HOST = "localhost";
    private String PORT = "5432";
    private String USER = "sla";
    private String PASS = "sla";
    private Connection connect;


    public Storage()throws SQLException {
        String URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+DB_NAME;
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("ssl", "true");
        connect = DriverManager.getConnection(URL, properties);
    }

    public ArrayList<Task> getListTasks() throws SQLException{
        ArrayList<Task> tasks = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from task");
        while (resultSet.next()){
            Task task = new Task();
            task.setID(resultSet.getInt(1));
            task.setName(resultSet.getString(2));
            task.setCommand(resultSet.getString(3));
            task.setStatus(resultSet.getInt(4));
            task.setCountRepeat(resultSet.getInt(5));
            tasks.add(task);
        }
        resultSet.close();
        statement.close();
        return tasks;
    }

    public void updateStatusList(ArrayList<Task> tasks) throws SQLException{
        String QueryUpdate = "update task set status = ? where id = ?";
        for (int i = 0; tasks.size() > i; i++){
            PreparedStatement preparedStatement = connect.prepareStatement(QueryUpdate);
            preparedStatement.setInt(1, tasks.get(i).getStatus());
            preparedStatement.setInt(2, tasks.get(i).getID());
            int j = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    public void insertTask(Task task) throws SQLException{
        String QueryString = "insert into task(name, command, status, count_repeat) values(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(QueryString);
        preparedStatement.setString(1, task.getName());
        preparedStatement.setString(2, task.getCommand());
        preparedStatement.setInt(3, task.getStatus());
        preparedStatement.setInt(4, task.getCountRepeat());
        int res = preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
