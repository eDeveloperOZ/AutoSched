package MainPack.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;



/**
 * Write all tasks to a file in CSV format
 * 
 * @author 
 *
 */
public class TaskPersistence {
	private static final String TASK_FILE = "task.csv";
	
	public void saveTask(List<Task> tasks) {

        StringBuilder sb = new StringBuilder();

        for(Task task : tasks){
        	sb.append(task.getId());
        	sb.append(",");
        	sb.append(task.getCategory().getTitle());
        	sb.append(",");
        	sb.append(task.getTitle());
        	sb.append(",");
        	sb.append(task.getDeadLine());
        	sb.append(",");
          	sb.append(task.isComplete());
        	sb.append(",");
          	sb.append(task.getStartingTime());
        	sb.append(",");
          	sb.append(task.getRank());
        	sb.append(",");
          	sb.append(task.getTaskType());
        	sb.append(",");
         	sb.append(task.getDif().getDuration() + "," + task.getDif().getscale());
        	sb.append(",");
         	sb.append(task.getPriority().isUrgent() + ","+ task.getPriority().isImportant());            	
        	
            sb.append("\n");
        }
        saveData(sb, "main_catgories.csv");
	}
	
	public void saveMainCategory(List<MainCategory> categories) {
  
	    StringBuilder sb = new StringBuilder();
	
	    for(MainCategory cat : categories){
	    	sb.append(cat.getId());
	    	sb.append(",");
	    	sb.append(cat.getTitle());
	    	sb.append(",");
	    	
	        sb.append("\n");
	    }
	    saveData(sb, "main_catgories.csv");

	}
	
	public void saveSubCategory(List<SubCategory> categories) {
		  
	    StringBuilder sb = new StringBuilder();
	
	    for(SubCategory cat : categories){
	    	sb.append(cat.getId());
	    	sb.append(",");
	    	sb.append(cat.getTitle());
	    	sb.append(",");
	    	
	        sb.append("\n");
	    }
	    saveData(sb, "sub_catgories.csv");

	}
	
	public void saveData(StringBuilder sb, String fileName) {
       try(PrintWriter out = new PrintWriter("./" + fileName)){
            out.println(sb.toString());
            out.flush();
        }catch(FileNotFoundException ex){
            System.out.println("Unable to access or create "+ TASK_FILE);
        }
	}
}
