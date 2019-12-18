package iterator.v1;

/*
 * 迭代器模式, 专门针对容器类的迭代
 * 
 * v1版本, 简单实现了ArrayList_和LinkedList_类
 * 
 * 此时有个需求: 遍历arrs和linkeds, 要求具有通用性, 即不能针对特定实现写特定遍历代码
 * 
 * 
*/
public class Main_v1 {

	public static void main(String[] args) {
		Collection_ arrs = new ArrayList_();
		for (int i = 0; i < 10; i++) {
			arrs.add(new String(String.valueOf(i)));
		}
		System.out.println(arrs.size());
		
		Collection_ linkeds = new LinkedList_();
		for (int i = 0; i < 10; i++) {
			linkeds.add(new String(String.valueOf(i)));
		}
		System.out.println(linkeds.size());
		
		// TODO 需要遍历arrs和linkeds
	}

}

class LinkedList_ implements Collection_ {
	private Node head = null;
	private Node last = null;
	private int size = 0;
	
	@Override
	public int add(Object val) {
		Node n = new Node(val);
		if ( head == null ) {
			head = n;
			last = n;
		} else {
			last.setNext(n);
			last = n;
		}
		size++;
		return 1;
	}

	@Override
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unused")
	private class Node {
		private Object value;
		private Node next;
		Node(Object value) {
			this.setValue(value);
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
}

class ArrayList_ implements Collection_ {

	private static final int INIT_SIZE = 8;
	private Object[] array = new Object[INIT_SIZE];
	private int nextIndex = 0;
	
	@Override
	public int add(Object o) {
		if ( array.length-1 == nextIndex ) {
			Object[] array_ = new Object[array.length * 2];
			System.arraycopy(array, 0, array_, 0, nextIndex);
			array = array_;
		}
		array[nextIndex] = o;
		nextIndex++;
		return 1;
	}

	@Override
	public int size() {
		return nextIndex;
	}
	
}

// 容器接口, 所有容器都实现该接口
interface Collection_ {
	int add(Object o);
	int size();
}