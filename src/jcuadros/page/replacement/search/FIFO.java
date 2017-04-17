package jcuadros.page.replacement.search;

public class FIFO implements Replacement{
	
	private int[] page;
	private int frame;
	
	public FIFO(){
		
	}
	
	public FIFO(int[] page, int frame){
		this.page = page;
		this.frame = frame;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
