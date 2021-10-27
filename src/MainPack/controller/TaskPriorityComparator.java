package MainPack.controller;

import java.util.Comparator;

import MainPack.model.Task;

public class TaskPriorityComparator implements Comparator<Task>{

	/**
	 * when o1 < o2 => result -1
	 * when o1 == o2 => result 0
	 * when o1 > o2 => result 1
	 */
	@Override
	public int compare(Task o1, Task o2) {
		if(o1.getRank() < o2.getRank()) {
			return -1;
		}else if (o1.getRank() > o2.getRank()) {
			return 1;
		}
		return 0;
	}

}
