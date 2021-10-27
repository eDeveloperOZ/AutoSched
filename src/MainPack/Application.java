package MainPack;

import java.awt.EventQueue;

import MainPack.controller.TaskController;
import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;
import MainPack.view.MainDisplay;

public class Application {

	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay window = new MainDisplay();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
