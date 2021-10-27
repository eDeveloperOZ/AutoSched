package MainPack.controller;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.mindfusion.common.DateTime;

import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;
import MainPack.model.Task.TaskType;

public class TaskController {

	//private Vector<Task> TaskVec = new Vector<Task>();
	
	private MainCategory rootTasks = new MainCategory("Root");
	private Vector<MainCategory> mainCategoryVec = new Vector<MainCategory>();
	private static final TaskController instance = new TaskController();
	
	private Vector<Task> sortedTasks = new Vector<Task>();
	
	
	private TaskController() {
		addDummyData();
	}
	
	public static TaskController getInstance() {
		return instance;
	}
	
	private void addDummyData() {
		DateTime tmpDate = new DateTime(1,10,10);
		TaskType tp = TaskType.REGULAR;  

		addTask(new Task(rootTasks,"Task1",tmpDate,tp,45,45 ,false, false));
		
		MainCategory m1 = new MainCategory("main cat a");
		SubCategory sub1 = new SubCategory(m1,"sub cat ami");
		m1.add(sub1);
		Task t1 = new Task(m1,"TaskM1",tmpDate,tp,45,45 ,false, false );
		addTask(t1);
		m1.add(t1);
		
		
		MainCategory m2 = new MainCategory("main cat b");
		SubCategory sub2 = new SubCategory(m1,"sub cat oovva");
		m2.add(sub2);
		Task t2 = new Task(sub2,"task BBB",tmpDate,tp,45,45 ,false, false);
		sub2.add(t2);
		addTask(t2);
		
		MainCategory m3 = new MainCategory("main cat c");
		SubCategory sub3 = new SubCategory(m1,"sub cat xxref");
		SubCategory sub31 = new SubCategory(m1,"sub cat abcde");
		m3.add(sub3);
		sub3.add(sub31);
		sub31.add(new Task(sub31,"task CCCC",tmpDate,tp,45,45 ,false, false));
		
		
		addMainCategory(m1);
		addMainCategory(m2);
		addMainCategory(m3);	
	}
	
	// ***** Task handling ****//
	
	public Task addTask(MainCategory cat, String title, String type, int scale, int duration, boolean importancy, boolean urgency, DateTime deadline) {
		Task task = new Task(cat, title, deadline, TaskType.valueOf(type), duration, scale, importancy, urgency);
		addTask(task);
		return task;
	}
	public void addTask(Task task) {
		rootTasks.add(task);
		
		sortedTasks.add(task);
		Collections.sort(sortedTasks, new TaskPriorityComparator());
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
	}
	
	public void addSubCategory(SubCategory sub) {
		this.mainCategoryVec.add(sub);
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
	
}
