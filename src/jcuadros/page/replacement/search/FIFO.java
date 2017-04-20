package jcuadros.page.replacement.search;

import java.util.LinkedList;
import java.util.Queue;

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
		for (int i : page) {
			insert(i);
		}
	}

	public void insert(int value) {
		
		if (q.size() < frame) {
			if (!q.contains(value)) {
				missed++;
				q.add(value);
			}
		} else {
			
			if (!q.contains(value)) {
				System.out.println("Inserting " + value + " into frame");
				missed++;
				q.poll();
				q.add(value);
			}
		}
		print();
		System.out.println("size of queue: " + q.size());
		System.out.println("Page fault: " + missed);

	}

	public void print() {
		for (Object obj : q) {
			if (obj == null) {
				System.out.println("| |\r");
			} else {
				System.out.println("|" + obj + "|\r");
			}
		}
	}

}
