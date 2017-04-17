package jcuadros.page.replacement;

import java.util.ArrayList;
import java.util.List;

import jcuadros.page.replacement.search.FIFO;
import jcuadros.page.replacement.search.LRU;
import jcuadros.page.replacement.search.Optimal;
import jcuadros.page.replacement.search.Replacement;

public class Environment {
	
	public static void main(String[] args){
		
		int[] page = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
		
		List<Replacement> list = new ArrayList<Replacement>();
		list.add(new Optimal(page, 3));
		list.add(new Optimal(page, 4));
		list.add(new FIFO(page, 3));
		list.add(new FIFO(page, 4));
		list.add(new LRU(page, 3));
		list.add(new LRU(page, 4));
		
		for(Replacement r : list){
			r.run();
		}
		
	}
	

}
