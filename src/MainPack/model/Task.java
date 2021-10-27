package MainPack.model;

import java.util.Collections;

import com.mindfusion.common.DateTime;

public class Task implements Comparable<Task>{
	public enum TaskType{
		REGULAR,
		HABIT,
		ANCHORE
	}
	protected MainCategory category; //the id of the category that "holds" the task
	protected String title; 
	protected DateTime DeadLine; 
	protected boolean isComplete; 
	protected DateTime StartingTime; 
	protected int rank; //VAR to decide where it goes on the queue - 0-100 -0.6 for priority and 0.4 for diffcullty. 
	protected TaskType taskType; 
	public Difficulty dif;
	protected Priority priority;
	protected int id;
	
	protected static int idGenerator = 1;
	

	
	public Task(MainCategory cat, String title, DateTime deadLine, boolean importance, boolean urgency) {
		this(cat, title, deadLine, TaskType.REGULAR, 15, 5, importance, urgency);
		this.isComplete = false; 
		
		this.rank = 25;  
	}
	
	
			
	public Task(MainCategory category, String title, DateTime deadLine, TaskType taskType, int duration, int scale, boolean importance, boolean urgency) {
		this.taskType = taskType;
		this.StartingTime = deadLine; // Delete this! it is not true.

		this.isComplete = false; 
		this.category = category;
		this.title = title;
		this.DeadLine = deadLine;
		this.taskType = taskType;
		int hours = duration / 60;
		int remainingMinutes = duration % 60;
		dif = new Difficulty(remainingMinutes, hours, scale);
		priority = new Priority(importance, urgency);
		this.rank =  CalcRank(priority, dif);
		this.id = idGenerator;
		idGenerator++;
	}



	public void EditDetails(MainCategory ID, String Title,DateTime DeadLine, boolean IC, TaskType taskType, Difficulty dif, Priority pr ) {
			
//			switch(TaskType) {
//			case "Anchor": 
//				this.TaskType = "Anchor"; 
//				break; 
//			case "Habit":
//				this.TaskType = "Habit";
//				break; 
//			case "Regular": 
//				this.TaskType = "Regular";
//				break;
//			}

			
		}
		
	public int CalcRank(Priority pr, Difficulty dif) { 
		int res =0; 
		res = pr.CalcRes()*15; //15-60 out of 100
		res += (dif.getDuration()/dif.getscale())*0.4; //0-40 out of 100
		return(res); 
	}

	public MainCategory getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public DateTime getDeadLine() {
		return DeadLine;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public DateTime getStartingTime() {
		return StartingTime;
	}

	public int getRank() {
		return rank;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public Difficulty getDif() {
		return dif;
	}
	public Priority getPriority() {
		return priority;
	}
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return title + "(" + taskType.toString() + ")";
	}

	@Override
	public int compareTo(Task other) {
		if(getTitle().compareTo(other.getTitle())>0)
		{
			return	1;
		}
		else if(getTitle().compareTo(other.getTitle())<0)
		{
			return	-1;
		}
		else 
			return	0;
	}
}
