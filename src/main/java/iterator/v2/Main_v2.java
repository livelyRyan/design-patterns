package iterator.v2;

/*
 * 迭代器模式, 专门针对容器类的迭代
 * 
 * v1版本, 简单实现了ArrayList_和LinkedList_类
 * 
 * 此时有个需求: 遍历arrs和linkeds, 要求具有通用性, 即不能针对特定实现写特定遍历代码
 * 
 * 由于各个Collection_实现类有不同的底层结构, 因此如果在类外面根据不同实现写不同的遍历逻辑代码, 会有以下问题:
 * 1. 遍历代码没有通用性
 * 2. 可能会破坏类的封装性,因为有些属性可能类原本不想对外暴露的, 为了在外面实现遍历而不得不进行暴露
 * 
 * 解决办法: 定义iterator_接口, 在容器类内部实现具体的迭代逻辑.
 * 		      而对于外层调用者, 调用通用的接口方法进行遍历.
 * 
 * 核心: 找出变化点和稳定点, 变化点在实现类内部自己实现, 稳定点用接口进行约定并暴露给外部, 以实现外部调用的通用性.
 * 
 * 变化点: 每个容器实现类遍历逻辑不一样;
 * 稳定点: 遍历的时候,只涉及两个问题: 
 * 		 1. 容器中是否有下一个元素;
 * 		 2. 返回容器的下一个元素;
 * 
 * tp: 此处注重设计模式, 容器代码实现仅做示例, 不要深究
 * 
*/
public class Main_v2 {

	public static void main(String[] args) {
		Collection_ arrs = new ArrayList_();
		for (int i = 0; i < 10; i++) {
			arrs.add(new String(String.valueOf(i)));
		}

		Collection_ linkeds = new LinkedList_();
		for (int i = 0; i < 10; i++) {
			linkeds.add(new String(String.valueOf(i)));
		}

		traversalAndPrint(arrs.iterator(), "ArrayList");
		traversalAndPrint(linkeds.iterator(), "LinkedList");
	}

	public static void traversalAndPrint(iterator_ iterator, String typee) {
		System.out.println("---遍历 " + typee + "---");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("---遍历 " + typee + " 结束---");
	}
}

class LinkedList_ implements Collection_ {
	private Node head = null;
	private Node last = null;
	private int size = 0;

	@Override
	public int add(Object val) {
		Node n = new Node(val);
		if (head == null) {
			head = n;
			last = n;
		} else {
			last.next = n;
			last = n;
		}
		size++;
		return 1;
	}

	@Override
	public int size() {
		return size;
	}

	private class Node {
		private Object value;
		private Node next;

		Node(Object value) {
			this.value = value;
		}

		Node(Object value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	private class LinkedInerate implements iterator_ {
		private Node previousNode = new Node("", head);

		@Override
		public Boolean hasNext() {
			if (previousNode == null || previousNode.next == null) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			previousNode = previousNode.next;
			return previousNode.value;
		}
	}

	@Override
	public iterator_ iterator() {
		return new LinkedInerate();
	}
}

class ArrayList_ implements Collection_ {

	private static final int INIT_SIZE = 8;
	private Object[] array = new Object[INIT_SIZE];
	private int nextIndex = 0;

	@Override
	public int add(Object o) {
		if (array.length - 1 == nextIndex) {
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

	class ArrayInerate implements iterator_ {
		private int currentIndex = -1;

		@Override
		public Boolean hasNext() {
			if (currentIndex == nextIndex - 1) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			currentIndex++;
			return array[currentIndex];
		}
	}

	public iterator_ iterator() {
		return new ArrayInerate();
	}
}

interface iterator_ {
	Boolean hasNext();

	Object next();
}

// 容器接口, 所有容器都实现该接口
interface Collection_ {
	int add(Object o);

	int size();

	iterator_ iterator();
}