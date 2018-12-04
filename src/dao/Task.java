package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Paritosh
 */
public class Task {
    private final int taskID;
    private final String boardName;
    private final String taskName;
    private final String employeeFirstName;
    private final int statusID;
    private final int changed;
    private final int priorityID;
    private final int weightage;
    private final String deadline;

    public Task(int taskID, String boardName, String taskName, String employeeFirstName, int statusID, int changed, int priorityID, int weightage, String deadline) {
        this.taskID = taskID;
        this.boardName = boardName;
        this.taskName = taskName;
        this.employeeFirstName = employeeFirstName;
        this.statusID = statusID;
        this.changed = changed;
        this.priorityID = priorityID;
        this.weightage = weightage;
        this.deadline = deadline;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public int getStatusID() {
        return statusID;
    }

    public int getChanged() {
        return changed;
    }

    public int getPriorityID() {
        return priorityID;
    }

    public int getWeightage() {
        return weightage;
    }

    public String getDeadline() {
        return deadline;
    }
    
    
    
}
