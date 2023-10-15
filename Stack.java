package dataScience;

public class Stack implements StackInterface{
	
	private class Node{
		char data;
		Node next;
		
		Node(char d){
			data = d;
			next = null;
		}
	}
	
	Node head;
	
	public Stack() {
		head = null;
	}

	@Override
	public char pop() {
		Node current = head;
		Node past = null;
		Node future = null;
		if (current == null) {
			throw new IllegalArgumentException();
		} 
		if (current.next == null) {
			head = null;
			return current.data;
		}
		while (current.next.next != null) {
			current = current.next;
		}
		past = current;
		future = current.next;
		past.next = null;
		return future.data;
	}

	@Override
	public char peek() {
		if (head == null) {
			throw new IllegalArgumentException();
		}
		return head.data;
	}

	@Override
	public void push(char toPush) {
		Node a = new Node(toPush);
		Node current = head;
		if (head == null) {
			head = a;
			return;
		}
		while (current.next != null) {
			current = current.next;
		}
		current.next = a;
	}

	public static void main(String[] args) {
		Stack stacky = new Stack();
		stacky.push('a');
		stacky.push('b');
		stacky.push('c');
		
		System.out.println(stacky.pop());
		System.out.println(stacky.pop());
		System.out.println(stacky.pop());
	}
}