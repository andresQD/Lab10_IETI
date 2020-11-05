package eci.ieti.data.model;

import java.util.Date;

public class Todo {

    
    private int priority;

    private String fileUrl;

    private String id;
    
    private String description;
    
    private User responsible;
    
    private String dueDate;
    
    private String status;

    public Todo() {
    }

    public Todo(String description,String dueDate, User responsible) {
        this.description = description;
        this.priority = 5;
        this.dueDate = dueDate;
        this.responsible = responsible;
    }
    
    public Todo(String description, User responsible, String dueDate, String status) {
        this.description = description;
        this.responsible = responsible;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", responsible=" + responsible +
                '}';
    }
}
