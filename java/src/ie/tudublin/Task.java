package ie.tudublin;

import processing.data.TableRow;

public class Task {
    private String Tasks;
    private int StartDate;
    private int EndDate;


    public String getTasks(){
        return Tasks;
    }

    public void setTasks(String Tasks){
        this.Tasks = Tasks;
    }
    
    public int getStartDate(){
        return StartDate;
    }

    public void setStartDate(){
        this.StartDate = StartDate;
    }

    public int getEndDate(){
        return EndDate;
    }

    public void setEndDate(){
        this.EndDate = EndDate;
    }

    public Task (String Tasks, int StartDate, int EndDate){
        this.Tasks = Tasks;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public Task(TableRow tr){
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    @Override
    public String toString() {
        return "Task [EndDate=" + EndDate + ", StartDate=" + StartDate + ", Tasks=" + Tasks + "]";
    }

    
}