package MainPack.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.mindfusion.common.DateTime;

import MainPack.controller.TaskController;
import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class MainDisplay {

	private JFrame Display;
	private TaskController taskController;
	private Cal day;
	private JTextField txtTaskTitle;
	TaskManager tManager;
	private JTextField txtAddMain;
	private JTextField txtAddSub;
	private JComboBox cbxMainCateogory;
	private JComboBox cbxTaskType;  
	private JSpinner spnTaskScale; 
	private JSpinner spnDuration; 
	private JCheckBox chkTaskImportancy;
	private JCheckBox chkTaskUrgent;
	private JSpinner spnTaskDeadLine;
	private JComboBox cbxTaskCategory;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainDisplay window = new MainDisplay();
//					window.Display.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	
	public MainDisplay( ) {
		this.taskController = TaskController.getInstance();
		day = Cal.getInstance(); 
		tManager = new TaskManager();
		System.out.println("here");
		tManager.Render();
		initialize();
		Vector<Task> tasks = taskController.getSortedTasks();
		for(Task t : tasks) {
			day.addAppointment(t);
		}
	//	tManager.Render();
		Display.setVisible(true);
	}

	/**
	 * Initialize the contents of the Display.
	 */
	private void initialize() {
		Display = new JFrame();
		Display.setTitle("AutoSched");
		Display.setBounds(100, 100,1341, 798);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Display = new JFrame();
		Display.setTitle("AutoSched");
		Display.setBounds(100, 100,1341, 798);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(Color.YELLOW);
		
		JPanel MiddlePanel = new JPanel();
		MiddlePanel.setBackground(Color.WHITE);
		
		JPanel DayliListPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(Display.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(LeftPanel, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MiddlePanel, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DayliListPanel, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(MiddlePanel, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
						.addComponent(LeftPanel, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addComponent(DayliListPanel, GroupLayout.PREFERRED_SIZE, 741, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		
		
//		tManager.TaskVec.add(new Task("sfsd","AAA"));
//		tManager.TaskVec.add(new Task("sfsd","BBB"));
//		tManager.TaskVec.add(new Task("sfsd","CCC"));
//		tManager.MainVec.add(new MainCategory(1234,"a"));
//		tManager.MainVec.add(new MainCategory(5678,"b"));
//		tManager.MainVec.add(new MainCategory(9012,"c"));
//		tManager.SubVec.add(new SubCategory("sfsd","ami"));
//		tManager.SubVec.add(new SubCategory("sfsd","oovva"));
//		tManager.SubVec.add(new SubCategory("sfsd","xxref"));
		
	
		
		
		
		GroupLayout gl_LeftPanel = new GroupLayout(LeftPanel);
		gl_LeftPanel.setHorizontalGroup(
			gl_LeftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LeftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tManager, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_LeftPanel.setVerticalGroup(
			gl_LeftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LeftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tManager, GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
					.addContainerGap())
		);
		LeftPanel.setLayout(gl_LeftPanel);
		
		JPanel DataPanel = new JPanel();
		
		JPanel UserPanel = new JPanel();
		
		JPanel ControlPanel = new JPanel();
		GroupLayout gl_MiddlePanel = new GroupLayout(MiddlePanel);
		gl_MiddlePanel.setHorizontalGroup(
			gl_MiddlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiddlePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MiddlePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(UserPanel, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
						.addComponent(DataPanel, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
						.addComponent(ControlPanel, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_MiddlePanel.setVerticalGroup(
			gl_MiddlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiddlePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(DataPanel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(UserPanel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ControlPanel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = txtTaskTitle.getText();
				String type = cbxTaskType.getSelectedItem().toString();
				int scale =  (Integer)(spnTaskScale.getValue());
				int duration =  (Integer)(spnDuration.getValue());
				boolean importancy =  chkTaskImportancy.isSelected() ;
				boolean urgetncy =  chkTaskUrgent.isSelected();
				System.out.println("new date time" + spnTaskDeadLine.getValue().toString());
				Date deadlineDate = (Date)spnTaskDeadLine.getValue();
				DateTime deadLine = new DateTime(deadlineDate) ;  
				MainCategory cat = taskController.getRootCategory();
				if(cbxTaskCategory.getSelectedItem() != null) {
					cat = (MainCategory)cbxTaskCategory.getSelectedItem(); 
				}
				System.out.println(cat);

				//TODO create a new Task instance

				Task task = taskController.addTask(cat, title, type, scale, duration, importancy, urgetncy, deadLine);
				Cal.getInstance().addAppointment(task);
				
				//TODO refresh tree
				//TODO refresh calendar				//TODO create a new Task instance
				//Task task = new Task();

				//taskController.addTask(null);
				
				//TODO refresh tree
				//TODO refresh calendar
			}
		});
		
		JLabel lblNewLabel = new JLabel("Task Title");
		
		txtTaskTitle = new JTextField();
		txtTaskTitle.setColumns(10);
		
		cbxTaskType = new JComboBox();
		cbxTaskType.setModel(new DefaultComboBoxModel(new String[] {"Anchor ", "Habit ", "Regular"}));
		
		spnTaskScale = new JSpinner();
		spnTaskScale.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		
		 spnDuration = new JSpinner();
		spnDuration.setModel(new SpinnerNumberModel(0, 0, 240, 15));
		
		 chkTaskImportancy = new JCheckBox("Important");
		
		 chkTaskUrgent = new JCheckBox("Urgent");
		
		 spnTaskDeadLine = new JSpinner();
		 spnTaskDeadLine.setModel(new SpinnerDateModel(new Date(1635109200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel lblTaskType = new JLabel("Task Type");
		
		JLabel lblScale = new JLabel("Scale(1-10)");
		
		JLabel lblDuration = new JLabel("Duration (min)");
		
		JLabel lblDeadline = new JLabel("Deadline");
		
		ArrayList<MainCategory> categories	 = new ArrayList<>();
		categories.add(taskController.getRootCategory());
		categories.addAll(taskController.getSubCategories());
		JComboBox cbxTaskCategory = new JComboBox(categories.toArray());
		
		JLabel lblCategory = new JLabel("Category");
		GroupLayout gl_UserPanel = new GroupLayout(UserPanel);
		gl_UserPanel.setHorizontalGroup(
			gl_UserPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UserPanel.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addComponent(btnAddTask)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_UserPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTaskType, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
						.addComponent(lblScale)
						.addComponent(lblDuration)
						.addComponent(lblDeadline, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_UserPanel.createSequentialGroup()
								.addComponent(chkTaskUrgent, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_UserPanel.createSequentialGroup()
									.addComponent(chkTaskImportancy)
									.addContainerGap())
								.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_UserPanel.createSequentialGroup()
										.addComponent(spnDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_UserPanel.createSequentialGroup()
											.addComponent(spnTaskScale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())
										.addGroup(gl_UserPanel.createSequentialGroup()
											.addComponent(cbxTaskType, 0, 115, Short.MAX_VALUE)
											.addGap(259))))))
						.addGroup(gl_UserPanel.createSequentialGroup()
							.addComponent(txtTaskTitle, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_UserPanel.createSequentialGroup()
							.addGroup(gl_UserPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(cbxTaskCategory, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spnTaskDeadLine, Alignment.LEADING))
							.addContainerGap())))
		);
		gl_UserPanel.setVerticalGroup(
			gl_UserPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_UserPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTaskTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxTaskType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTaskType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spnTaskScale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblScale))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spnDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuration))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkTaskImportancy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkTaskUrgent)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spnTaskDeadLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeadline))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_UserPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_UserPanel.createSequentialGroup()
							.addComponent(cbxTaskCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(btnAddTask))
						.addComponent(lblCategory))
					.addContainerGap())
		);
		UserPanel.setLayout(gl_UserPanel);
		
		JButton AddMainCatButton = new JButton("Add Main Category");
		
		AddMainCatButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				String catName; 
				catName = txtAddMain.getText();
				MainCategory newMain = new MainCategory(catName); 
				taskController.addMainCategory(newMain);
				 
				txtAddMain.setText("");
				tManager.Render();
				
				//tManager.treeList = new JTree();
				 //DefaultTreeModel model = (DefaultTreeModel)  tManager.treeList.getModel();
				// model.setRoot(myComputer);
				 // tManager.treeList.setModel(model);
		       //     model.reload();
//				DefaultTreeModel model = (DefaultTreeModel) tManager.treeList.getModel();
//				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//				
//				model.reload(root);
				
//				tmp.setVisible(true);
//				
//				tmp.AddNewMainCat.addMouseListener(new MouseAdapter() {
//					public void mouseClicked(MouseEvent e) {
//						System.out.println(tmp.gettext());
//						tManager.AddMainCategoryToVector(tmp.gettext(), isSaved);
//					}
//				});
				
				/*
				 * while(!isSaved) { if(tmp.gettext().isBlank()) {
				 * 
				 * } }
				 */
//				if(isSaved) {
//					tmp.dispose();
//				}
				
		}});
		
		JButton AddSubCatButton = new JButton("Add Sub Category");
		AddSubCatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(SubCategory tmp: TaskController.getInstance().getSubCategories()) {
					System.out.println(tmp.getTitle());
					System.out.println("first");
				}
				String catName; 
				String mainName; 
				mainName = cbxMainCateogory.getSelectedItem().toString(); 
				catName = txtAddSub.getText();
				MainCategory mainOfSub = new MainCategory(mainName); 
				SubCategory newSub = new SubCategory(mainOfSub,catName); 
				taskController.addSubCategory(newSub);
				 
				txtAddSub.setText("");
				tManager.Render();
				for(SubCategory tmp: TaskController.getInstance().getSubCategories()) {
					System.out.println(tmp.getTitle());
				}
			}
		});
		
		txtAddMain = new JTextField();
		txtAddMain.setColumns(10);
		
		txtAddSub = new JTextField();
		txtAddSub.setColumns(10);
		
		cbxMainCateogory = new JComboBox(TaskController.getInstance().getMainCategories());
		GroupLayout gl_ControlPanel = new GroupLayout(ControlPanel);
		gl_ControlPanel.setHorizontalGroup(
			gl_ControlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ControlPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_ControlPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtAddMain, Alignment.LEADING)
						.addComponent(AddMainCatButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_ControlPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cbxMainCateogory, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtAddSub, Alignment.LEADING)
						.addComponent(AddSubCatButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(159, Short.MAX_VALUE))
		);
		gl_ControlPanel.setVerticalGroup(
			gl_ControlPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ControlPanel.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(cbxMainCateogory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ControlPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAddMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ControlPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddMainCatButton)
						.addComponent(AddSubCatButton))
					.addContainerGap())
		);
		ControlPanel.setLayout(gl_ControlPanel);
		MiddlePanel.setLayout(gl_MiddlePanel);
		Display.getContentPane().setLayout(groupLayout);
		
		DayliListPanel.add(day.getCalender()); 
		DayliListPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//treeItem = new treeItem().makeUI(tManager.MainVec, tManager.SubVec, tManager.TaskVec); 
		
//		tManager.Render();
//		tManager.VecsStrings(taskController.getMainCategories(), taskController.getSubCategories()); 

	}
}
