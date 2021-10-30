package MainPack.controller;
//Ofir Ozeri 305003352
//Amit Al-Sheich 311366132
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.mindfusion.common.DateTime;

import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;
import MainPack.model.Task.TaskType;
import MainPack.view.TaskManager;

public class TaskController {

	//private Vector<Task> TaskVec = new Vector<Task>();
	
	private MainCategory rootTasks;
	private Vector<MainCategory> mainCategoryVec = new Vector<MainCategory>();
	
	private Vector<MainCategory> allCategories = new Vector<MainCategory>();
	private static final TaskController instance = new TaskController();
	
	private Vector<Task> sortedTasks = new Vector<Task>();
	private TaskPersistence taskPersistence;
	
	private TaskController() {
		
		this.taskPersistence = new TaskPersistence();
		//taskPersistence.loadMainCategories();
		this.mainCategoryVec = (Vector<MainCategory>) taskPersistence.loadMainCategories();
		for(MainCategory cat : mainCategoryVec) {
			if(cat.getTitle().equals("Root")) {
				rootTasks = cat;
			}
		}
		
		mainCategoryVec.remove(rootTasks);
		Vector<SubCategory> subCats = (Vector<SubCategory>) this.taskPersistence.loadSubCategories();
		sortedTasks = (Vector<Task>) taskPersistence.loadTask(); 
		allCategories.addAll(getMainCategories());
		allCategories.addAll(getSubCategories()); 
		allCategories.add(rootTasks);
		for(SubCategory s :subCats) {
			//s.getMainCategory().add(s);
		}
		//TaskController.getInstance().addSubCategory(subCategory.getTitle(), mainCat.getTitle());
		//addDummyData();
	}
	
	public static TaskController getInstance() {
		return instance;
	}
	public void Save() {
		mainCategoryVec.add(rootTasks);
		taskPersistence.saveMainCategory(mainCategoryVec);
		taskPersistence.saveSubCategory(this.getSubCategories());
		taskPersistence.saveTask(sortedTasks);
	}
	private void addDummyData() {
		
		DateTime tmpDate = new DateTime(1,10,10);
		TaskType tp = TaskType.REGULAR;
		
		Task Task1 = new Task(rootTasks,"Task1",tmpDate,tp,45,45 ,false, false);

		addTask(Task1, rootTasks);
		
		MainCategory m1 = new MainCategory("main cat a");
		SubCategory sub1 = new SubCategory(m1,"sub cat ami");
		m1.add(sub1);
		Task t1 = addTask(m1, "TaskM1", "REGULAR", 45,45, false, false,  tmpDate);
		//addTask(t1, m1);
		
		MainCategory m2 = new MainCategory("main cat b");
		SubCategory sub2 = new SubCategory(m1,"sub cat oovva");
		m2.add(sub2);
		Task t2 = addTask(m2, "task BBB", "REGULAR", 45,45, false, false,  tmpDate);
		//Task t2 = new Task(sub2,"task BBB",tmpDate,tp,45,45 ,false, false);
		sub2.add(t2);
		//addTask(t2,m2);
		
		
		MainCategory m3 = new MainCategory("main cat c");
		SubCategory sub3 = new SubCategory(m1,"sub cat xxref");
		SubCategory sub31 = new SubCategory(m1,"sub cat abcde");
		sub3.add(sub31);
		Task t3 = addTask(sub31, "task CCCC", "REGULAR", 45,45, true, false,  tmpDate);
		sub31.add(t3);
		
		addMainCategory(m1);
		addMainCategory(m2);
		addMainCategory(m3);	
	}
	
	// ***** Task handling ****//
	
	public Task addTask(MainCategory cat, String title, String type, int scale, int duration, boolean importancy, boolean urgency, DateTime deadline) {
		Task task = new Task(cat, title, deadline, TaskType.valueOf(type), duration, scale, importancy, urgency);
		addTask(task, cat);
		return task;
	}
	public void addTask(Task task, MainCategory daddy) {
		daddy.add(task);
		sortedTasks.add(task);
		sortTasks();
		
		System.out.println("----Printing tasks----");
		for(Task t: sortedTasks) {
			System.out.println("title is: "+ t.toString());
			System.out.println("Rank is:"+ t.getRank());
			System.out.println("Start time is: "+ t.getStartingTime());
		}
	}
	public void sortTasks() {
		Collections.sort(sortedTasks, new TaskPriorityComparator());
		this.updateTaskStartTime();
	}
	
	void updateTaskStartTime() {
		DateTime time = new DateTime(1,1,1); 
		
		DateTime endTime = new DateTime(1,1,1); 
		int indicator =0; 
//		Date now = new Date();
		for(Task t: sortedTasks) {
			if(indicator ==0 ) {
			t.setStartingTime(time.now());
			indicator++; 
			}
			else {
			t.setStartingTime(endTime);
			}
			endTime = t.getStartingTime().addMinutes(t.getDif().getDuration()+15);
		}
	}
	
	public Vector<Task> getSortedTasks(){
		return this.sortedTasks;
	}
	
	public Vector<Task> getRootTasks(){
		return (Vector<Task>) rootTasks.getTasks();
	}
	
	public MainCategory getRootCategory() {
		return this.rootTasks;
	}
	
	// ***** Main category handling ****//
	
	public void addMainCategory(MainCategory mainCategory) {
		this.mainCategoryVec.add(mainCategory);
		this.allCategories.add(mainCategory);
	}
	
	public void addSubCategory(SubCategory sub) {
		this.mainCategoryVec.add(sub);
		this.allCategories.add(sub);
	}
	
	public void addSubCategory(String subtitle, String mainCategoryTitle) {
		MainCategory main = null;
		for(MainCategory mainCat :this.mainCategoryVec) {
			if(mainCat.getTitle().equals(mainCategoryTitle)) {
				main = mainCat;
			}
		}
		SubCategory sub = new SubCategory(main, subtitle);
		main.add(sub);
		////this.mainCategoryVec.add(sub);
	}
	
	public Vector<MainCategory> getMainCategories(){
		return mainCategoryVec;
	}
	
	public Vector<MainCategory> getAllCategories(){
		return allCategories;
	}
	
	public Vector<SubCategory> getSubCategories() {
		Vector<SubCategory> categories = new Vector<SubCategory>() ;
		for(MainCategory main : mainCategoryVec) {
			for(SubCategory sub : main.getSubCategories()) {
				categories.add(sub);
			}
		}
		return categories;
	}
	
	public boolean mainCategoryExists(String name) {
		for(MainCategory cat : mainCategoryVec) {
			if(cat.getTitle().equals(name)){
				return true;
			}
		}
		return false;
	}

	public void completedTask(String taskToRemove) {
		
		System.out.println(taskToRemove+",");
		
		for(Task t : sortedTasks) {
			System.out.println(t.getTitle());
			
			if(taskToRemove.contains(t.getTitle())) {
				System.out.println(t.getTitle()+"yas!");
				t.setIsComplete(true);
				//sortedTasks.r(t);
			}
		}
		sortTasks(); 
		// TODO Auto-generated method stub
		
	}
	
}
