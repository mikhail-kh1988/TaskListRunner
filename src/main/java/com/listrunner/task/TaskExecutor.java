package com.listrunner.task;

public class TaskExecutor implements Runnable{

    private Thread  thread;
    private Task    ExecuteTask;
    private String  ExecuteCommand;

    public TaskExecutor(Task aTask){
        ExecuteTask     = aTask;
        ExecuteCommand  = aTask.getCommand();
    }

    @Override
    public void run() {
        System.out.println("Run TASK:"+ExecuteTask.getName());
        System.out.println("===============================");
        System.out.println(ExecuteCommand);
        System.out.println("===============================");
        System.out.println("End.");
    }

    public void start(){
        if(thread == null) {
            thread = new Thread(this, ExecuteTask.getName());
            thread.start();
        }
    }
}
