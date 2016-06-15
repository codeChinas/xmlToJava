package com.sermo.lru;

import java.util.Hashtable;

public class LRUCache {
	
	//  缓存大小
	private int cacheSize;
	//缓存容器
	private Hashtable<Object, Entry> nodes; 
	// 当前缓存对象数量
	private int currentSize;
	// 链表头
	private Entry first;
	// 链表尾
	private Entry last;

	public LRUCache(int cacheSize) {
		this.currentSize = 0;
		this.cacheSize = cacheSize;
		// 缓存容器
		nodes = new Hashtable<>(cacheSize);
	}
	
	/**
	 * 获取缓存中对象
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		Entry node = nodes.get(key);
		if (node != null) {
			moveToHead(node);
			return node.value;
		}else {
			return null;
		}
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value) {
		// 先查看  hashtable 是否存在该 entry, 如果存在, 则只更新其 value 
		Entry node = nodes.get(key);
		
		if (node == null) {
			// 缓存容器是否已经超过大小
			if (currentSize >= cacheSize) {
				if (last != null) {
					nodes.remove(last.key);
				}
				removeLast();
			} else {
				currentSize++;
			}
			node = new Entry();
		}
		node.value = value;
		// 将最新使用的节点放到链表头, 表示最新使用的
		moveToHead(node);
		nodes.put(key, node);
	}
	
	/**
	 * 将 entry 删除
	 * @param key
	 */
	public void remove(Object key) {
		Entry node = nodes.get(key);
		// 在链表中删除
		if (node != null) {
			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.next;
			}
			if (last == node) {
				last = node.prev;
			}
			if (first == node) {
				first = node.next;
			}
		}
		nodes.remove(key);
	}
	
	/**
	 * 删除链表尾部节点
	 */
	private void removeLast(){
		if (last != null) {
			if (last.prev != null) {
				last.prev.next = null;
			} else {
				first = null;
			}
			last = last.prev;
		}
	}
	
	/**
	 * 移动到链表头
	 * @param node
	 */
	private void moveToHead(Entry node){
		if (node == first) {
			return;
		}
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		if (last == node) {
			last = node.prev;
		}
		if (first != null) {
			node.next = first;
			first.prev = node;
		}
		first = node;
		node.prev = null;
		if (last == null) {
			last = first;
		}
	}
	
	/**
	 * 清空缓存
	 */
	public void clear(){
		first = null;
		last = null;
		currentSize = 0;
	}
}

