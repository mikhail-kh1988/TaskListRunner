package com.listrunner.task;

public class Task {
    private int     ID;
    private String  Name;
    private String  Command;
    private int     Status;
    private int     CountRepeat;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getCountRepeat() {
        return CountRepeat;
    }

    public void setCountRepeat(int countRepeat) {
        CountRepeat = countRepeat;
    }
}
