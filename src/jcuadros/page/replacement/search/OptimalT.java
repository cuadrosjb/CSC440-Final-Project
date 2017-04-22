package jcuadros.page.replacement.search;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class OptimalT implements Replacement{
	
	private int[] page;
	private int frame;
	private int missed;
	
	private List<Integer> prev;
	private List<Integer> curr;
	private List<Integer> next;
	
	
	public OptimalT(){
		prev = new ArrayList<Integer>();
		next = new ArrayList<Integer>();
		curr = new ArrayList<Integer>();
	}
	
	public OptimalT(int[] page, int frame){
		this.page = page;
		this.frame = frame;
		missed = 0;
		prev = new ArrayList<Integer>();
		curr = new ArrayList<Integer>();
	}

	@Override
	public void run() {
	
		for (int i = 0; i < page.length; i++) {
			insert(page[i], i);
		}
	}
	
	public void insert(int value, int index){
		if(curr.size() < frame){
			missed++;
			curr.add(value);
			prev.add(value);
			print();
		}else{
			
			if(!curr.contains(value)){
				fillNext(index++);
				
				int m = howManyMatches();
				
				if(m == 0){
					missed++;
					int tmp = curr.indexOf(prev.get(prev.size()-3));
					curr.set(tmp, value);
					prev.add(value);
					print();
				}else if(m < curr.size()){
					
				}else{
					
				}
			}
			
		}
	}
	
	public int howManyMatches(){
		int counter = 0;
		for(int i : curr){
			for(int j : next){
				if(i == j){
					counter++;
					break;
				}
			}
		}
		System.out.println("howManyMatches: " + counter);
		return counter;
	}
	
	public void fillNext(int idx){
		next = new ArrayList<Integer>();
		while(next.size() < frame && idx < page.length){
			if(!next.contains(page[idx])){
				next.add(page[idx]);
			}
			idx++;
		}
	}
	public void print(){
		int counter = 0;
		
		while(counter < frame){
			try{
				if(curr.get(counter)!=null){
					System.out.println("|" + curr.get(counter) +"|\r");
				}
			}catch (IndexOutOfBoundsException e) {
				System.out.println("| |\r");
			}
			counter++;
		}
		System.out.println("---");
	}
	

}
