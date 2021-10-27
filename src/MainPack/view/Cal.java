package MainPack.view;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.common.DateTime;

import MainPack.model.Task;

public class Cal {
	private Calendar calender; 
	private static final Cal instance = new Cal();
	
	public static Cal getInstance() {
		return instance;
	}
	private Cal() {
		this.calender = new Calendar();
		calender.beginInit();
		calender.setCurrentView(CalendarView.Timetable);
		calender.endInit();
	}
	
	public Calendar getCalender() {
		return calender;
	}

	public void addAppointment(Task task) {
		Appointment app = new Appointment();
		app.setHeaderText(task.getTitle());
		app.setDescriptionText(task.getTaskType().toString());
		app.setStartTime(task.getStartingTime());
		DateTime endTime = task.getStartingTime().addMinutes(task.getDif().getDuration());
		app.setEndTime(endTime);
		calender.getSchedule().getItems().add(app);
	}
}
