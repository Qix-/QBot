package qix;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import qix.qbot.task.Priority;

public class Playground {
	public static void main(String[] args) {
		Queue<Priority> priorities = new PriorityQueue<>(10, Priority.getComparator());
		priorities.addAll(Arrays.asList(
				Priority.HIGH,
				Priority.LOWEST,
				Priority.CRITICAL,
				Priority.HIGH,
				Priority.LOW,
				Priority.REQUEST,
				Priority.REQUEST,
				Priority.REQUEST,
				Priority.LOWEST
				));
		
		System.out.println(priorities);
		System.out.println(priorities.poll());
		System.out.println(priorities.poll());
		System.out.println(priorities.poll());
		System.out.println(priorities);
		System.out.println(priorities.peek());
		System.out.println(priorities);
	}
}
