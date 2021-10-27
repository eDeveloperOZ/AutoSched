package MainPack.model;

public class Priority {
	protected boolean Important; 
	protected boolean Urgent; 	
	
	Priority(){
		this.Important = false; 
		this.Urgent = true; 
	}
	
	Priority(boolean I, boolean U){
		this.Important = I; 
		this.Urgent = U;
	}
	
	
	public boolean isImportant() {
		return Important;
	}

	public void setImportant(boolean important) {
		Important = important;
	}

	public boolean isUrgent() {
		return Urgent;
	}

	public void setUrgent(boolean urgent) {
		Urgent = urgent;
	}

	public int CalcRes() { // If Its important and Urgent, its top priority, if urgent but not important second, not important but urgent - ask for help. 
		if(this.Urgent) {
			if(this.Important) {
				return 4; 
			}
			else {
				return 3; 
			}
		}
		else {
			if(this.Important) {
			return 2; 
			}
		} 
		return 1; 
	}
}
