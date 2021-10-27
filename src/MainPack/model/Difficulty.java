package MainPack.model;

public class Difficulty {
	private int Duration;  //integer in minutes 
	private int scale; // how hard is the task from 1-10 - default is 5; 

	Difficulty(){
		this.Duration = 45; 
		this.scale = 5; 
	}
	
	Difficulty(int min, int H, int scl){
		this.Duration = ParseDurationTime(min, H); 
		this.scale = scl; 
	}
	
	public int ParseDurationTime(int min, int H) {
		return(min + (H*60)); 
	}
	public int getDuration() {
		return(this.Duration); 
	}
	public int getscale() {
		return(this.scale); 
	}
	public void setDuration(int tmp) {
		this.Duration = tmp;
	}
	public void setScale(int tmp) {
		if(tmp <10 && tmp >0)
			this.scale = tmp; 
	}
	

}
