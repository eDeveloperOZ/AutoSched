package MainPack.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.mindfusion.scheduling.Calendar;

import MainPack.controller.TaskController;
import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;

public class TaskManager extends JPanel {

	//public Vector<Task> TaskVec = new Vector<Task>();
//	public Vector<MainCategory> MainVec = new Vector<MainCategory>();
//	public Vector<SubCategory> SubVec = new Vector<SubCategory>();
	private JTextField txtTaskManager;
	public JScrollPane TMList;

	private TaskController taskController;
	private Cal cal;
	/**
	 * Create the panel.
	 */
	public TaskManager() {

		this.taskController = TaskController.getInstance();
		this.cal = Cal.getInstance();
		txtTaskManager = new JTextField();
		txtTaskManager.setEditable(false);
		txtTaskManager.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaskManager.setText("Task Manager");
		txtTaskManager.setColumns(10);
		TMList = new JScrollPane(); 
		

		Render();
		

		VecsStrings(taskController.getMainCategories(), taskController.getSubCategories());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(TMList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(txtTaskManager, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtTaskManager, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TMList, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	/**
	 * // returns a model of all
	 * cetogris names. --NEED TO
	 * ADD IT TO THE APPROPRIATE
	 * BUTTONS
	 * (https://stackoverflow.com/questions/7387299/dynamically-adding-items-to-a-jcombobox)
	 * @param mainVec2
	 * @param subVec2
	 */
	public void VecsStrings(Vector<MainCategory> mainVec2, Vector<SubCategory> subVec2) { 
		// TODO function returns a string vector of mainVec and subVec to the jcomboBox

		Vector back = new Vector();

		for (int i = 0; i < mainVec2.size(); i++) {
			back.add(mainVec2.get(i).getTitle());
		}
		for (int j = 0; j < subVec2.size(); j++) {
			back.add(subVec2.get(j).getTitle());
		}
//		System.out.println("here" + back); //TESTING 
		final SortedComboBoxModel model = new SortedComboBoxModel(back);
		// return model;
	}

	public void Render() { // re-Creating list tree of all elements; deletes all first! --Still deosnt
							// work, needs a fix! might be a problem with returning type - not likly tho
		 //TMList.removeAll();
		// for (int i = 0; i < MainVec.size(); i++)
		// System.out.println(MainVec.get(i).title + "render"); //TESTING
		//Vector<MainCategory> mainVec = new Vector<>();
		//Vector<SubCategory> subVec = new Vector<>();
		//Vector<Task> mainVec1 = new Vector<>();
		
		 //TMList = new JScrollPane(new treeItem().makeUI(TaskController.getInstance().getMainCategories(), TaskController.getInstance().getSubCategories(), TaskController.getInstance().getTasks()));
		 
		 TMList.setViewportView(new treeItem().makeUI(TaskController.getInstance().getMainCategories(), TaskController.getInstance().getSubCategories(), TaskController.getInstance().getRootTasks()));
		 //TMList.revalidate();
		 //TMList.repaint();
		// TMList.add(new treeItem().makeUI(MainVec, SubVec, TaskVec)); //create the
		// tree and return it within a jScrollPanel
		// new JTree().add(MainVec.get(i).title, MainVec.get(i))
	}

	public void UpdateAddButtonList() {
		
		// update jcombo box to all the categories
	}

	public boolean AddMainCategoryToVector(String mainName) {

		if(taskController.mainCategoryExists(mainName)) {
			showMessageDialog(null, "category Name already exist, Please choose a different name");
			return false;
		}
		taskController.addMainCategory(new MainCategory(mainName));
		showMessageDialog(null, "Item added!");
		return true;
		
	}

	public void AddSubCategoryToVector(SubCategory tmp) {
		taskController.addSubCategory(tmp);
	}

	public void AddTaskToTaskVector(Task task) {

		taskController.addTask(task);
		cal.addAppointment(task);
	}

	public void NewTask(Task tmp) {

	}

	public class SortedComboBoxModel extends DefaultComboBoxModel { // private class to dinamaclly build comboBox list

		private static final long serialVersionUID = 1L;

		public SortedComboBoxModel() {
			super();
		}

		public SortedComboBoxModel(Object[] items) {
			Arrays.sort(items);
			int size = items.length;
			for (int i = 0; i < size; i++) {
				super.addElement(items[i]);
			}
			setSelectedItem(items[0]);
		}

		public SortedComboBoxModel(Vector items) {
			Collections.sort(items);
			int size = items.size();
			for (int i = 0; i < size; i++) {
				super.addElement(items.elementAt(i));
			}
			setSelectedItem(items.elementAt(0));
		}

		@Override
		public void addElement(Object element) {
			insertElementAt(element, 0);
		}

		@Override
		public void insertElementAt(Object element, int index) {
			int size = getSize();
			// Determine where to insert element to keep model in sorted order
			for (index = 0; index < size; index++) {
				Comparable c = (Comparable) getElementAt(index);
				if (c.compareTo(element) > 0) {
					break;
				}
			}
			super.insertElementAt(element, index);
		}
	}

	public static class addNewMainCategory extends JFrame {

		private JPanel contentPane;
		private JTextField txtCategoryName;
		private JTextField catName;
		public JButton AddNewMainCat;

		/**
		 * Create the frame.
		 */
		public addNewMainCategory() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);

			txtCategoryName = new JTextField();
			txtCategoryName.setEditable(false);
			txtCategoryName.setText("Category name: ");
			txtCategoryName.setColumns(10);

			catName = new JTextField();
			catName.setColumns(10);

			JButton AddNewMainCat = new JButton("Add New Category");
			/*
			 * AddNewMainCat.addMouseListener(new MouseAdapter() { public void
			 * mouseClicked(MouseEvent e) {
			 * 
			 * } });
			 */
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup().addGap(27)
											.addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE,
													GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18).addComponent(catName, GroupLayout.PREFERRED_SIZE,
													GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup().addGap(81)
											.addComponent(AddNewMainCat)))
							.addContainerGap(207, Short.MAX_VALUE)));
			gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup().addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(catName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE))
							.addGap(18).addComponent(AddNewMainCat).addGap(165)));
			contentPane.setLayout(gl_contentPane);
		}

		public String gettext() {
			return (this.catName.getText());
		}

	}
	
	public class AddTaskHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
