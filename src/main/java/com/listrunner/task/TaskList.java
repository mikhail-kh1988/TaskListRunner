package com.listrunner.task;

import com.listrunner.storage.Storage;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> ListTask;
    private TaskExecutor    taskExecutor;
    private Storage         storage;

    public TaskList() throws SQLException{
        ListTask = new ArrayList<>();
        storage = new Storage();
    }

    public void addTask(Task task){
        ListTask.add(task);
    }

    public void addListTask(ArrayList<Task> tasks){
        if(ListTask.size() > 0 || ListTask == null){
            for(int i = 0; tasks.size() > i; i++)
                this.addTask(tasks.get(i));
        }else{
            ListTask = null;
            ListTask = tasks;
        }
    }

    public void RunList() throws SQLException {
        for (Task t: ListTask) {
            if(t.getStatus() == 2) {
                for (int i = 0; t.getCountRepeat() > i; i++)
                    this.RunTask(t);
                t.setStatus(3);
            }
        }
        storage.updateStatusList(ListTask);
        //ListTask = null; // Удалить или посмотреть что потом будет.
    }

    private void deleteTask(Task task){
        int temp = 0;
        for (Task t: ListTask) {
            if(task.getID() == t.getID())
                temp++;
        }
        ListTask.remove(temp);
    }

    private void RunTask(Task aTask){
        taskExecutor = new TaskExecutor(aTask);
        taskExecutor.start();
    }
}
