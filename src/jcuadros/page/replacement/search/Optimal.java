package jcuadros.page.replacement.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey B Cuadros
 * 
 * Implementation of the Optimal algorithm
 *
 */
public class Optimal implements Replacement {

	private int[] page;
	private int frame;
	private int missed;

	private List<Integer> prev;
	private List<Integer> curr;
	private List<Integer> next;

	public Optimal() {
		prev = new ArrayList<Integer>();
		next = new ArrayList<Integer>();
		curr = new ArrayList<Integer>();
	}

	public Optimal(int[] page, int frame) {
		this.page = page;
		this.frame = frame;
		missed = 0;
		prev = new ArrayList<Integer>();
		curr = new ArrayList<Integer>();

	}

	@Override
	public void run() {
		System.out.println("Optimal Page Replacement with " + frame + " frames");
		for (int i = 0; i < page.length; i++) {
			insert(page[i], i);
		}

		System.out.println("Page fault: " + missed + "\r");
	}

	public void insert(int value, int index) {
		/**
		 * fill the arraylist "curr" till the size of "curr" is equal to the frame size
		 */
		
		if (curr.size() < frame) {
			if(!curr.contains(value)){
				missed++;
				curr.add(value);
				prev.add(value);
				print();
			}
		} else {
			/**
			 *If "curr" contains the next page value, then I don't anything
			 */
			if (!curr.contains(value)) {
				/**
				 *Since "curr" doesn't contain that page I need to fill the arraylist "next" 
				 *with the next available pages.
				 */
				
				fillNext(index + 1);
				
				int m = howManyMatches();
				
				/**
				 *If m is 0 means that there were no matches,
				 *and the apply FIFO algorithm 
				 * 
				 */
				
				if (m == 0) {
					/**
					 *If m is 0 means that there were no matches,
					 *and the apply FIFO algorithm 
					 * 
					 */
					missed++;
					int tmp = curr.indexOf(prev.get(prev.size() - frame));
					if (tmp == -1) {
						tmp = curr.indexOf(prev.get(prev.size() - frame - 1));
					}
					curr.set(tmp, value);
					prev.add(value);
					print();
				} else if (m == curr.size()) {
					/**
					 *If "m" and the size of the "curr" are same, then
					 *take the last added value of the "next" arraylist 
					 *and get position of that value inside the "curr" arraylist
					 *and set that position to the new value
					 * 
					 */
					int tmp = curr.indexOf(next.get(next.size() - 1));
					curr.set(tmp, value);
					prev.add(value);
					print();
				} else if (m < curr.size()) {
					/**
					 *If "m" is less than the size of the "curr", then
					 *look for the first value inside "curr" that is 
					 *not in the arraylist "next" and replace it, with
					 *the new value
					 * 
					 */
					int counter = 0;
					while (counter < frame) {
						int tmp = curr.get(counter);
						if (!next.contains(tmp)) {
							missed++;
							curr.set(counter, value);
							prev.add(value);
							print();
							break;
						}
						counter++;
					}
				} else {
					System.out.println("Error.. This should not happen...");
				}
			}
		}
	}

	/**
	 * @return How many matches are between the 
	 * next pages and the current pages inside the frame
	 */
	public int howManyMatches() {
		int counter = 0;
		for (int i : curr) {
			for (int j : next) {
				if (i == j) {
					counter++;
					break;
				}
			}
		}
		return counter;
	}

	/**
	 * @param idx index to the start the search 
	 * the next following possible values.
	 * 
	 * Fills the "next" arraylist with the next possible values
	 * 
	 */
	public void fillNext(int idx) {
		next = new ArrayList<Integer>();
		while (next.size() < frame && idx < page.length) {
			if (!next.contains(page[idx])) {
				next.add(page[idx]);
			}
			idx++;
		}
	}

	/**
	 * Pints the current state of the frame
	 */
	public void print() {
		int counter = 0;

		while (counter < frame) {
			try {
				if (curr.get(counter) != null) {
					System.out.println("|" + curr.get(counter) + "|\r");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("| |\r");
			}
			counter++;
		}
		System.out.println("---");
	}

}
