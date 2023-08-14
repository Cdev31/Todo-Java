/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalet
 */
public class Tasks {
    private int id;
    private String title;
    private String description;
    private Boolean done;
   

   
    public Tasks( String title, String description, Boolean done) {
        this.description = description;
        this.title= title;
        this.done = done;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getTaskTitle() {
        return title;
    }

    public void setTaskTitle(String title) {
        this.title = title;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

   
}
