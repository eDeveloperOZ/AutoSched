package MainPack.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import MainPack.model.MainCategory;
import MainPack.model.SubCategory;
import MainPack.model.Task;

//for all note purposes cat == Category
public class treeItem {
	
	public JComponent makeUI(Vector<MainCategory> mains, Vector<SubCategory> subs, Vector<Task> tasks) { //init the ceration of the elements -- 
	  // for (int i = 0; i < mains.size(); i++) System.out.println(mains.get(i).title + "treeItem"); // TESTING 
	
		IconUIResource emptyIcon = new IconUIResource(new Icon() {
	      @Override public void paintIcon(Component c, Graphics g, int x, int y) {}
	      @Override public int getIconWidth() {
	        return 0;
	      }
	      @Override public int getIconHeight() {
	        return 0;
	      }
	    });
	    UIManager.put("Tree.expandedIcon",  emptyIcon);
	    UIManager.put("Tree.collapsedIcon", emptyIcon);
	    //UIManager.put("Tree.closedIcon",    emptyIcon);
	    //UIManager.put("Tree.openIcon",      emptyIcon);
	    UIManager.put("Tree.paintLines",    Boolean.FALSE);
	
	    UIDefaults d = new UIDefaults();
	    d.put("Tree:TreeCell[Enabled+Selected].backgroundPainter", new Painter<JComponent>() {
	      @Override public void paint(Graphics2D g, JComponent c, int w, int h) {
	        g.setPaint(Color.GREEN.darker());
	        g.fillRect(0, 0, w, h);
	      }
	    });
	
	    JTree tree = new JTree(makeModel(mains, subs, tasks)); //create the treeRoot and add all categories(main/sub) and tasks.
	    tree.setCellRenderer(new DefaultTreeCellRenderer());
	    //tree.setRowHeight(0);
	    tree.setRootVisible(false);
	    tree.setShowsRootHandles(false);
	    tree.setBackground(Color.WHITE);
	    tree.putClientProperty("Nimbus.Overrides", d);
	    tree.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
	
	    return new JScrollPane(tree);
	  }
	
	  /**
	   * Create all the list items unisg the vectores that holds the data
	   * @param mains
	   * @param subs
	   * @param tasks
	   * @return
	   */
	  private TreeModel makeModel(Vector<MainCategory> mains, Vector<SubCategory> subs, Vector<Task> tasks) {
		//   for (int i = 0; i < mains.size(); i++) System.out.println(mains.get(i).title +"TreeModel"); // TESTING -- GOT HERE
	  	Dimension d64 = new Dimension(64, 64);
	    Dimension d32 = new Dimension(32, 32);
	    Dimension d24 = new Dimension(24, 24);
	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
	    

		    
	    for(MainCategory mainCat : mains) {
	    	DefaultMutableTreeNode setMain = new DefaultMutableTreeNode(
	    			new TestNode(mainCat.getTitle(), Color.ORANGE, d64, true));
	    	for(SubCategory sub : mainCat.getSubCategories()) {
	    		DefaultMutableTreeNode setSub = new DefaultMutableTreeNode(
    				new TestNode(sub.getTitle(), Color.green, d32, sub.getTasks().size() > 0)
				);
	    		for(Task task : sub.getTasks()) {
	    			DefaultMutableTreeNode setTask = new DefaultMutableTreeNode(
						new TestNode(task.getTitle(), Color.RED, d24, false));
	    			setSub.add(setTask);
	    		}
				System.out.println(setSub.toString() + " setSub");// --TESTING 
				setMain.add(setSub);
	    	}
    		for(Task task : mainCat.getTasks()) {
    			DefaultMutableTreeNode setTask = new DefaultMutableTreeNode(
					new TestNode(task.getTitle(), Color.RED, d24, false));
    			setMain.add(setTask);
    		}
	    	root.add(setMain);
	    }
	    


	    return new DefaultTreeModel(root); 
	}

	
	class TestTreeCellRenderer extends DefaultTreeCellRenderer { //
		  @Override public Component getTreeCellRendererComponent(
		      JTree tree, Object value, boolean selected, boolean expanded,
		      boolean leaf, int row, boolean hasFocus) {
		    JLabel l = (JLabel) super.getTreeCellRendererComponent(
		        tree, value, selected, expanded, leaf, row, hasFocus);
		    if (value instanceof DefaultMutableTreeNode) {
		      DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		      Object uo = node.getUserObject();
		      if (uo instanceof TestNode) {
		        TestNode i = (TestNode) uo;
		        l.setForeground(Color.BLACK);
		        l.setIcon(new TestNode(i.title, i.color, i.dim, leaf, expanded));
		
		        int indent = 0;
		        TreeNode parent = node.getParent();
		        while (parent instanceof DefaultMutableTreeNode) {
		          DefaultMutableTreeNode pn = (DefaultMutableTreeNode) parent;
		          if (pn.getUserObject() instanceof TestNode) {
		            TestNode pi = (TestNode) pn.getUserObject();
		            indent += pi.dim.width / 2;
		          }
		          parent = pn.getParent();
		        }
		        l.setBorder(BorderFactory.createEmptyBorder(2, indent, 2, 5));
		      }
		    }
		    return l;
		  }
		}
	

}

 
