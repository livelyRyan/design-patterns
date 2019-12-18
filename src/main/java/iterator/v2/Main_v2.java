package iterator.v2;

/*
 * ������ģʽ, ר�����������ĵ���
 * 
 * v1�汾, ��ʵ����ArrayList_��LinkedList_��
 * 
 * ��ʱ�и�����: ����arrs��linkeds, Ҫ�����ͨ����, ����������ض�ʵ��д�ض���������
 * 
 * ���ڸ���Collection_ʵ�����в�ͬ�ĵײ�ṹ, ����������������ݲ�ͬʵ��д��ͬ�ı����߼�����, ������������:
 * 1. ��������û��ͨ����
 * 2. ���ܻ��ƻ���ķ�װ��,��Ϊ��Щ���Կ�����ԭ��������Ⱪ¶��, Ϊ��������ʵ�ֱ��������ò����б�¶
 * 
 * ����취: ����iterator_�ӿ�, ���������ڲ�ʵ�־���ĵ����߼�.
 * 		      ��������������, ����ͨ�õĽӿڷ������б���.
 * 
 * ����: �ҳ��仯����ȶ���, �仯����ʵ�����ڲ��Լ�ʵ��, �ȶ����ýӿڽ���Լ������¶���ⲿ, ��ʵ���ⲿ���õ�ͨ����.
 * 
 * �仯��: ÿ������ʵ��������߼���һ��;
 * �ȶ���: ������ʱ��,ֻ�漰��������: 
 * 		 1. �������Ƿ�����һ��Ԫ��;
 * 		 2. ������������һ��Ԫ��;
 * 
 * tp: �˴�ע�����ģʽ, ��������ʵ�ֽ���ʾ��, ��Ҫ�
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
		System.out.println("---���� " + typee + "---");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("---���� " + typee + " ����---");
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

// �����ӿ�, ����������ʵ�ָýӿ�
interface Collection_ {
	int add(Object o);

	int size();

	iterator_ iterator();
}