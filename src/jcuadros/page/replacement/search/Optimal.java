package jcuadros.page.replacement.search;

import java.util.HashSet;
import java.util.Set;

public class Optimal implements Replacement{
	
	private int[] page;
	private int frame;
	private int missed;
	
	public Optimal(){
		
	}
	
	public Optimal(int[] page, int frame){
		this.page = page;
		this.frame = frame;
		missed = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Integer[] current = new Integer[frame];

		for(int i = 0; i < page.length; i++){
			if(current[i%frame] == null){
				missed++;
				current[i%frame] = page[i];
			}else{
				byte flag = 0;
				for(int val : current){
					if(val == page[i]){
						flag = 1;
					}
				}
				
				if(flag != 1){
					missed++;
					current[findNext(current, page, i)] = page[i];
				}
				
			}
		}
		
		System.out.println("using Optimal Page Replacement with Frame Size: " + frame + " We missed: " + missed);
		
	}
	
	public int findNext(Integer[] curr, int[] arr, int idx){
		int[] temp = new int[frame];
		int value = page[idx];
		idx++;
		int j = 0;
		while(temp.length == frame || idx < arr.length){
			temp[j] = page[idx];
			idx++;
			j++;
		}
		
		
		
		return 0;
		
	}
	
	

}
