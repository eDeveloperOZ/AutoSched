package MainPack.model;

import java.util.List;

public class SubCategory extends MainCategory{
	//protected String OwnerCategoryID; // the id of the category that "holds" the task
	protected MainCategory mainCategory;
	
	//protected int ID;
	
	public SubCategory(MainCategory mainCategory, String t) {
		//this.OwnerCategoryID  =Oid; 
		//this.ID = ID; 
		super(t);
		this.mainCategory = mainCategory;
	}
	public SubCategory(int id, MainCategory mainCategory, String t) {

		super(id, t);
		this.mainCategory = mainCategory;
	}
	public void CreateTask() 
	{
		
	}
	public MainCategory getMainCategory() {
		return mainCategory;
	}
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void addSubCategory(SubCategory subCategory) {
		subCategories.add(subCategory);
	}

}
