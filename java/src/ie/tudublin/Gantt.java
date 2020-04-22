package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;


public class Gantt extends PApplet
{
	public ArrayList<Task> tasks = new ArrayList<Task>();	
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");

		for(TableRow row:t.rows())
		{
			Task tk = new Task(row);
			tasks.add(tk);
		}
		
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{
			println(t);
		}
		
	}

	public void displayTasks(){
		float X = width * 0.05f;
		float margin = width / 20;
	
		float y;
	
		//Tasks
		for(int i = 0; i < tasks.size(); i++)
		{
			fill(255);
			y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			text(tasks.get(i).getTasks(), margin, y);
		}
		

		// lines and numbers
		float textX = X * 3.5f;
		float textY = height * 0.08f;
		int textSpace = 21;

		fill(255);

		
		for(int count = 1; count < 31; count++)
		{
			if(count % 2 == 0)
			{
				stroke(255);
			}
			else
			{
				stroke(120);
			}
			line(textX, textY, textX, height - textY);
			textAlign(CENTER);
			text(count, textX, textY - textSpace);
			textX += textSpace;
		}
	
	}
	

	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();

		colorMode(HSB);
	}
	
	public void draw()
	{			
		background(0);

		displayTasks();
	}
}
