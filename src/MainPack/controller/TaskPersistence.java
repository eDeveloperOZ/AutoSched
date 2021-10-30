package MainPack.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.mindfusion.common.DateTime;

import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;
import MainPack.model.Task.TaskType;



/**
 * Write all tasks to a file in CSV format
 * 
 * @author 
 *
 */
public class TaskPersistence {
	private static final String TASK_FILE = "task.csv";
	private static final String MAIN_CATEGORY_FILE = "main_catgories.csv";
	private static final String SUB_CATEGORY_FILE = "sub_catgories.csv";
	
	Map<Integer, MainCategory> categoriesMap = new HashMap<>();
	private TaskController taskController = TaskController.getInstance();
	
	public void saveTask(List<Task> tasks) {

        StringBuilder sb = new StringBuilder();

        for(Task task : tasks){
        	try {
        	sb.append(task.getId());
        	sb.append(",");
        	sb.append(task.getCategory().getId());
        	sb.append(",");
        	sb.append(task.getTitle());
        	sb.append(",");
        	sb.append(task.getDeadLine().getTicks());
        	sb.append(",");
          	sb.append(task.isComplete());
        	sb.append(",");
          	sb.append(task.getStartingTime().getTicks());
        	sb.append(",");
          	sb.append(task.getRank());
        	sb.append(",");
          	sb.append(task.getTaskType());
        	sb.append(",");
         	sb.append(task.getDif().getDuration() + "," + task.getDif().getscale());
        	sb.append(",");
         	sb.append(task.getPriority().isUrgent() + ","+ task.getPriority().isImportant());            	
        	
            sb.append("\n");
        	}catch(NullPointerException e) {
        		System.out.println("empty data");
        	}
        }
        saveData(sb, TASK_FILE);
	}
	
	public List<Task> loadTask() {

	   Vector<Task> tasks = new Vector<>();

        try(BufferedReader in  = new BufferedReader(new FileReader(TASK_FILE))){
            String record;
            while( (record = in.readLine()) != null){
            	if(record.length() == 0)
            		continue;
            	String[] fields = record.split(",");
            	int id = Integer.parseInt(fields[0]);
            	int categoryId = Integer.parseInt(fields[1]);
            	String title = fields[2];
            	DateTime deadline = new DateTime(Long.parseLong(fields[3])); 
            	boolean isComplete = Boolean.parseBoolean(fields[4]); 
            	DateTime StartingTime = new DateTime(Long.parseLong(fields[5]));
            	int rank = Integer.parseInt(fields[6]);
            	TaskType taskType = TaskType.valueOf(fields[7]); 
            	int duration = Integer.parseInt(fields[8]);;
            	int scale = Integer.parseInt(fields[9]);;
            	boolean urgency = Boolean.parseBoolean(fields[10]);
            	boolean importance = Boolean.parseBoolean(fields[11]);
            	
            	MainCategory mainCat = categoriesMap.get(categoryId);
            	Task task = new Task(mainCat, title, deadline, taskType, duration, scale, importance, urgency);
            	mainCat.add(task);
            	tasks.add(task);
                
            }
        }catch(FileNotFoundException ex){
            System.out.println("Unable to access or create " + TASK_FILE);
        }catch(IOException ex){
            System.out.println("Unable to read "+ TASK_FILE);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(TASK_FILE + " is corrupted. Please fix and try again");
        }catch(NumberFormatException ex){
            System.out.println(TASK_FILE + " invalid data in line ");
        }

        return tasks;
	}
	
	public void saveMainCategory(List<MainCategory> categories) {
  
	    StringBuilder sb = new StringBuilder();
	
	    for(MainCategory cat : categories){
	    	sb.append(cat.getId());
	    	sb.append(",");
	    	sb.append(cat.getTitle());
	        sb.append("\n");
	    	
	    }
	    saveData(sb, MAIN_CATEGORY_FILE);

	}
	
	public List<MainCategory> loadMainCategories() {

	   Vector<MainCategory> mainCategories = new Vector<>();
	   	int lineNum = 0;
        try(BufferedReader in  = new BufferedReader(new FileReader(MAIN_CATEGORY_FILE))){
            String record;
            while( (record = in.readLine()) != null){
            	if(record.length() == 0) continue;
            	String[] fields = record.split(",");
           
            	int id = Integer.parseInt(fields[0]);
            	String title = fields[1];
            	MainCategory mainCategory = new MainCategory(id, title);
            	categoriesMap.put(id, mainCategory);
            	mainCategories.add(mainCategory);
            	lineNum++;
            }
        }catch(FileNotFoundException ex){
            System.out.println("Unable to access or create " + MAIN_CATEGORY_FILE);
        }catch(IOException ex){
            System.out.println("Unable to read "+ MAIN_CATEGORY_FILE);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(MAIN_CATEGORY_FILE + " is corrupted. Please fix and try again");
        }catch(NumberFormatException ex){
            System.out.println(MAIN_CATEGORY_FILE + " invalid data in line " + lineNum);
        }

        if(mainCategories.size() == 0) {
        	mainCategories.add(new MainCategory("Root"));
        }
        return mainCategories;
	}
	
	public void saveSubCategory(List<SubCategory> categories) {
		  
	    StringBuilder sb = new StringBuilder();
	
	    for(SubCategory cat : categories){
	    	sb.append(cat.getId());
	    	sb.append(",");
	    	sb.append(cat.getTitle());
	    	sb.append(",");
	    	sb.append(cat.getMainCategory().getId());
	    	sb.append(",");	    	
	        sb.append("\n");
	    	for(SubCategory childCat : cat.getSubCategories()) {
	    		sb.append(childCat.getId() +  ",");
	    	}
	    	sb.append("\n");        
	    }
	    saveData(sb, SUB_CATEGORY_FILE);

	}
	
	public List<SubCategory> loadSubCategories() {

	   Vector<SubCategory> subCategories = new Vector<>();

        try(BufferedReader in  = new BufferedReader(new FileReader(SUB_CATEGORY_FILE))){
            String record;
            while( (record = in.readLine()) != null){
            	if(record.length() == 0)continue;
            	String[] fields = record.split(",");
            	
            	int id = Integer.parseInt(fields[0]);
            	String title = fields[1];
            	int mainCatid = Integer.parseInt(fields[2]);
            	
            	MainCategory mainCat = categoriesMap.get(mainCatid);
            	SubCategory subCategory = new SubCategory(id, mainCat, title);
            	mainCat.add(subCategory);
            	categoriesMap.put(id, subCategory);
            	subCategories.add(subCategory);
            	
            	
            }
        }catch(FileNotFoundException ex){
            System.out.println("Unable to access or create " + SUB_CATEGORY_FILE);
        }catch(IOException ex){
            System.out.println("Unable to read "+ SUB_CATEGORY_FILE);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(SUB_CATEGORY_FILE + " is corrupted. Please fix and try again");
        }catch(NumberFormatException ex){
            System.out.println(SUB_CATEGORY_FILE + " invalid data from line ");
        }

        return subCategories;
	}	
	
	public void saveData(StringBuilder sb, String fileName) {
       try(PrintWriter out = new PrintWriter("./" + fileName)){
            out.print(sb.toString());
            out.flush();
        }catch(FileNotFoundException ex){
            System.out.println("Unable to access or create "+ fileName);
        }
	}
	
	

}
