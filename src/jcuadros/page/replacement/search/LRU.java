package jcuadros.page.replacement.search;

import java.util.ArrayList;
import java.util.List;

public class LRU implements Replacement{
	
	private int[] page;
	private int frame;
	
	private int missed;
	private List<Integer> lst;
	private List<Integer> vals;
	public LRU(){
		
	}
	
	public LRU(int[] page,int frame){
		this.page = page;
		this.frame = frame;
		lst =  new ArrayList<Integer>();
		vals = new ArrayList<Integer>();
	}
	
	@Override
	public void run() {
		
		for (int i : page) {
			insert(i);
			
		}
		
	}
	public void insert(int value) {
		
		if (lst.size() < frame) {
			if (!lst.contains(value)) {
				missed++;
				lst.add(value);
			}
		} else {
			
			if (!lst.contains(value)) {
				System.out.println("Inserting " + value + " into frame");
				missed++;
				removeLRU();
			}
		}
		print();
		System.out.println("size of queue: " + lst.size());
		System.out.println("Page fault: " + missed);
		if(!vals.contains(value)){
			vals.add(value);
		}

	}
	
	public void removeLRU(){
		for (int i = vals.size()-1; i >=0; i--) {
			if(lst.contains(vals.get(i))){
				
			}
		}
		
		
		
	}
	
	public void print(){
		
		for (int i = 0; i < frame; i++) {
			try{
				System.out.println("|" + lst.get(i) + "|\r");
			}catch(IndexOutOfBoundsException iob){
				System.out.println("| |\r");
			}
		}
		
		
	};
	
	
}
