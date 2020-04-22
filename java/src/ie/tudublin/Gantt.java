package ie.tudublin;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;


public class Gantt extends PApplet
{
	public ArrayList<Task> tasks = new ArrayList<Task>();	

	float rightMargin = 0;
	float leftMargin = 0;
	
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
    
	}

	public void mouseDragged()
	{
		//println("Mouse dragged");	
		float rectStart = 0;
		float rectEnd = 0;
		float rectY = 0;
		float rectH = height * 0.07f;

		for(int i = 0; i < tasks.size(); i ++)
		{
			Task t = tasks.get(i);
			rectStart = map(t.getStartDate(), 1, 30, rightMargin, leftMargin);
			rectEnd = map(t.getEndDate(), 1, 30, rightMargin, leftMargin);
			rectY = map(i, 0, tasks.size(), height * 0.15f, height - height * 0.15f);
		
		if(mouseX > rectStart - 20 && mouseX <= rectStart + 20 && mouseY >= (rectY - rectH / 2) && mouseY <= (rectY + rectH / 2))
		{
			if(t.getStartDate() > 1 && mouseX < rectStart - 10)
			{
					t.setStartDate(t.getStartDate() - 1);
			}
			if(t.getStartDate() < 30 && mouseX > rectStart + 10)
			{
				if(t.getStartDate() != (t.getEndDate() - 1))
					t.setStartDate(t.getStartDate() + 1);
			}
			if(mouseX > rectEnd - 20 && mouseX <= rectEnd + 20 && mouseY >= (rectY - rectH / 2) && mouseY <= (rectY + rectH / 2))
			{	
				if(t.getEndDate() > 1 && mouseX < rectEnd - 10)
				{
					if(t.getStartDate() != (t.getEndDate() - 1))
						t.setEndDate(t.getEndDate() - 1);
				}
				if(t.getEndDate() < 30 && mouseX > rectEnd + 10)
				{
						t.setEndDate(t.getEndDate() + 1);
				}
			}
		}

	}
}


	
	
	public void setup() 
	{
		loadTasks();
		printTasks();

		colorMode(HSB);

		rightMargin = width * 0.15f;
		leftMargin = width - (width * 0.05f);
	}
	
	public void draw()
	{			
		background(0);
	

		displayTasks();
	}
}
