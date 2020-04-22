package ie.tudublin;

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
}