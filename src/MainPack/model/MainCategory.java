package MainPack.model;

import java.util.List;
import java.util.Vector;

public class MainCategory {
	//protected int ID;
	protected List<SubCategory> subCategories  = new Vector<>();
	protected List<Task> tasks = new Vector<>();
	protected String title;
	protected int id;
	
	protected static int idGenerator = 1;
	
	public MainCategory(String t) {
		this.title = t; 
		this.id = idGenerator;
		idGenerator++;
	}

	
	public void add(SubCategory subCategory) {
		subCategories.add(subCategory);
	}
	
	public void add(Task task) {
		tasks.add(task);
	}
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public List<SubCategory> getSubCategories(){
		return subCategories;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
	public int compareTo(MainCategory other) { // AMIT: what does that do!? why returning integers?
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

	/*public int compareTo(SubCategory other) {
		if(getTitle().compareTo(other.getSubCategories()))
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
	*/

}
