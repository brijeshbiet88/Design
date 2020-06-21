package com.interview.ds.system.design.lrucache;

public class ListNode {
	
	int key;
	ListNode next;
	ListNode prev;
	
	public ListNode(int key ) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "ListNode [key=" + key + "]";
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public ListNode getPrev() {
		return prev;
	}

	public void setPrev(ListNode prev) {
		this.prev = prev;
	}

}
