package com.interview.ds.system.design.lrucache;

import java.util.HashMap;

public class LRUCache {

	private int cacheSizeLimit;
	private int cacheSize ;
	private ListNode front ;
	private ListNode rear ;
	HashMap<Integer, ListNode> cache ;
	
	public LRUCache(int cacheSizeLimit) {
		this.cacheSizeLimit = cacheSizeLimit;
		front = null;
		rear = null;
		cacheSize = 0;
		cache = new HashMap<Integer, ListNode>();
	}
	
	public static void main(String[] args) {
		
		LRUCache cache = new LRUCache(3);
		
		int [] pages = {1, 2, 3, 1, 4, 2, 6, 1, 1, 6, 1 , 3 , 5 , 8};
		System.out.print("Pages : ");
		for(int element : pages)
			System.out.print(element+"  ");
		for(int i = 0 ; i < pages.length ; i++) {
			System.out.println();
			cache.getPage(pages[i]);
			System.out.println("Page : "+pages[i]);
			System.out.print("Cache content : ");
			cache.showCacheContent();
		}

	}
	
	private void showCacheContent() {
		ListNode temp  = front;
		while(temp != null ) {
			System.out.print("["+temp.key+"] ");
			temp = temp.next;
		}
		
	}

	private void getPage(int key) {
		if(cache.containsKey(key)) {
			ListNode node = cache.get(key);
			if(node != front) {
				node.prev.next = node.next;
				if (node.next != null)
					node.next.prev = node.prev;
				else if(node == rear){
					rear = rear.prev;
				}
				node.next = front;
				front.prev = node;
				front = node;
			}
			
		}
		else {
			if(isCacheEmpty()) {
				ListNode node = new ListNode(key);
				cacheSize++;
				cache.put(key, node);
				front = node;
				rear =  node;
				
			}
			
			else {
				if(cacheSize < cacheSizeLimit) {
					ListNode node = new ListNode(key);
					cache.put(key, node);
					cacheSize++;
					node.setNext(front);
					front.setPrev(node);
					front = node;
				
				}
				
				else if (cacheSize == cacheSizeLimit) {
					ListNode node = new ListNode(key);
					cache.put(key, node);
					node.setNext(front);
					front.setPrev(node);
					front = node;
					cache.remove(rear.key);
					ListNode rearPrev = rear.prev;
					rearPrev.setNext(null);
					rear = rearPrev;
				}
			}		
		}	
	}

	private boolean isCacheEmpty() {
		if(front == null && rear == null) 
			return true;
			return false;
	}
}
/*
 Pages : 1  2  3  1  4  2  6  1  1  6  1  3  5  8  
Page : 1
Cache content : [1] 
Page : 2
Cache content : [2] [1] 
Page : 3
Cache content : [3] [2] [1] 
Page : 1
Cache content : [1] [3] [2] 
Page : 4
Cache content : [4] [1] [3] 
Page : 2
Cache content : [2] [4] [1] 
Page : 6
Cache content : [6] [2] [4] 
Page : 1
Cache content : [1] [6] [2] 
Page : 1
Cache content : [1] [6] [2] 
Page : 6
Cache content : [6] [1] [2] 
Page : 1
Cache content : [1] [6] [2] 
Page : 3
Cache content : [3] [1] [6] 
Page : 5
Cache content : [5] [3] [1] 
Page : 8
Cache content : [8] [5] [3] 
 * */
