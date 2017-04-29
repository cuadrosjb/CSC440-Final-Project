package jcuadros.page.replacement.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey B Cuadros
 * 
 * Implementation of the least recently used algorithm
 *
 */
public class LRU implements Replacement {

	private int[] page;
	private int frame;

	private int missed;
	private List<Integer> lst;
	private List<Integer> vals;

	public LRU() {

	}

	public LRU(int[] page, int frame) {
		this.page = page;
		this.frame = frame;
		lst = new ArrayList<Integer>();
		vals = new ArrayList<Integer>();
		
	}

	@Override
	public void run() {
		System.out.println("LRU Page Replacement with " + frame + " frames");
		for (int i : page) {
			insert(i);
		}
		System.out.println("Page fault: " + missed + "\r");
	}

	public void insert(int value) {
		if (lst.size() < frame) {
			if (!lst.contains(value)) {
				missed++;
				lst.add(value);
				vals.add(value);
				print();
			}
		} else {

			if (!lst.contains(value)) {
				missed++;
				removeLRU(value);
				print();
			}
			vals.add(value);
		}

	}

	public int getMeTheUniqueLoc() {

		List<Integer> tmpLst = new ArrayList<Integer>();

		for (int i = vals.size() - 1; i >= 0; i--) {
			if (tmpLst.size() == frame) {
				return tmpLst.get(frame);
			} else {
				if (!tmpLst.contains(vals.get(i))) {
					tmpLst.add(vals.get(i));
					if (tmpLst.size() == frame) {
						return vals.get(i);
					}
				}
			}
		}
		return 0;
	}

	public void removeLRU(int value) {
		Integer tmp = getMeTheUniqueLoc();
		int index = lst.indexOf(tmp);
		lst.set(index, value);
	}

	public void print() {
		for (int i = 0; i < frame; i++) {
			try {
				System.out.println("|" + lst.get(i) + "|\r");
			} catch (IndexOutOfBoundsException iob) {
				System.out.println("| |\r");
			}
		}
		System.out.println("---");

	};

}
