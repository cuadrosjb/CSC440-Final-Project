package jcuadros.page.replacement.search;

public class LRU implements Replacement{
	
	private int[] page;
	private int frame;
	
	public LRU(){
		
	}
	
	public LRU(int[] page,int frame){
		this.page = page;
		this.frame = frame;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
