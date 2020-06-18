package com.listrunner;

import com.listrunner.storage.Storage;
import com.listrunner.task.Task;
import com.listrunner.task.TaskList;

import java.sql.SQLException;

public class ListRunner {

    private static Storage storage;
    private static TaskList taskList;

    public static void main(String[] args) throws SQLException {

        storage = new Storage();
        taskList = new TaskList();
        Task task = new Task();
        task.setName("Тестовая задача");
        task.setCommand("command for cmd");
        task.setStatus(2);
        task.setCountRepeat(4);
        //taskList.addTask(task);
        //storage.insertTask(task);


        while (true){

            taskList.addListTask(storage.getListTasks());
            taskList.RunList();

        }
    }

}
