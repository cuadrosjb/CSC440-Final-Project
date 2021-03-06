package jcuadros.page.replacement.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jeffrey B Cuadros
 * 
 * Implementation of the first in first out algorithm
 *
 */
public class FIFO implements Replacement {

	private int[] page;
	private int frame;

	private int missed;

	private Queue<Integer> q;

	public FIFO() {
		frame = 0;
		missed = 0;
	}

	public FIFO(int[] page, int frame) {
		this.page = page;
		this.frame = frame;
		q = new LinkedList<Integer>();
		
	}

	@Override
	public void run() {
		System.out.println("FIFO Page Replacement with " + frame + " frames");
		for (int i : page) {
			insert(i);
		}
		System.out.println("Page fault: " + missed + "\r");
	}

	public void insert(int value) {

		if (q.size() < frame) {
			if (!q.contains(value)) {
				missed++;
				q.add(value);
			}
		} else {

			if (!q.contains(value)) {
				missed++;
				q.poll();
				q.add(value);
			}
		}
		print();
	}

	public void print() {
		int counter = 0;

		for (Object obj : q) {
			if (obj == null) {
				System.out.println("| |\r");
			} else {
				System.out.println("|" + obj + "|\r");
			}
			counter++;
		}

		while (counter != frame) {
			System.out.println("| |\r");
			counter++;
		}

		System.out.println("---");

	}

}
