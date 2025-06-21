package intuit.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheInternalLib {
	int capacity;
	Map<Integer,Integer> cacheMap=new HashMap();
	LinkedList<Integer> lruList=new LinkedList();
	LRUCacheInternalLib(int capacity){
		this.capacity=capacity;
	}
	public static void main(String[] args) {
		LRUCacheInternalLib cache = new LRUCacheInternalLib(2);

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}
	public int get(int key){
		if(!cacheMap.containsKey(key)){
			return -1;
		}
		lruList.remove(Integer.valueOf(key));
		lruList.addFirst(key);
		return cacheMap.get(key);
	}

	public void put(int key,int value){
		if(cacheMap.containsKey(key)){
			lruList.remove(Integer.valueOf(key));
			lruList.addFirst(key);
			cacheMap.put(key,value);
		}else{
			lruList.addFirst(key);
			cacheMap.put(key,value);
			if(cacheMap.size()>capacity){
				int leastUsed=lruList.removeLast();
				cacheMap.remove(Integer.valueOf(leastUsed));
			}
		}
	}
}