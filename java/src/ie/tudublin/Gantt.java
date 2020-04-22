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
		float margin = width / 16;
		float y;
		float rectH = 40;
		float rectS, rectE, rectW;
		float color;
		float leftBorder = width / 6;
		float textX = X * 3.5f;
		float textY = height * 0.08f;
		int textSpace = 21;
	
		//Tasks
		for(int i = 0; i < tasks.size(); i++)
		{
			fill(255);
			y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			text(tasks.get(i).getTasks(), margin, y);

			// drawing rects for each tasks

				//HSB color
				smooth();
				noStroke();
				color = map(i, 0, tasks.size(), 0, 255);
				fill(color, 255, 255);
				
				rectS = map(tasks.get(i).getStartDate(), 1, 30, leftBorder, width - margin);
				rectE = map(tasks.get(i).getEndDate(), 1, 30, leftBorder, width - margin);
				rectW = rectE - rectS;
				rect(rectS, y - rectH / 2, rectW, rectH);
		}
		

		// lines and numbers
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
		float Top;
		float box = 40;

		float textX = X * 3.5f;
		
		int Space = 21;
		float rectH = 50;

		Top = height * 0.15f;
			
		for(int i = 0; i < tasks.size(); i++)
        {
			Task task = tasks.get(i);

			float left = map(task.getStartDate(), 1, 30, textX, textX + (29 * Space));
			float right = map(task.getEndDate(), 1, 30, textX, textX + (29 * Space));
			

            if(mouseX > left && mouseX < left + box && mouseY > Top - (rectH / 2) && mouseY < Top + (rectH/2))
            {
                println("Mouse Pressed");	
			}
			else if(mouseX > right - box && mouseX < right && mouseY > Top - (rectH / 2) && mouseY < Top + (rectH / 2))
			{
				println("Mouse Pressed");
			}
		
			Top += Space;
        }
	}

	public void mouseDragged()
	{
	
		//println("Mouse dragged");	
	
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
