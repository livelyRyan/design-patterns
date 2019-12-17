package decorator;

/*
 * װ�����࣬
 * ������ǿ��Ĺ���
 * 
*/
public class Tank implements GameObject {

	public void move() {
		System.out.println("start moving");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AutoTankDecorator(new Tank()).move();
		System.out.println("-------------------------");
		new PersonDrivedTankDecorator(new Tank()).move();
	}
	
}

abstract class TankDecorator implements GameObject {
	
	GameObject obj;
	
	public TankDecorator(GameObject obj) {
		this.obj = obj;
	}
	
	public void move() {
		this.obj.move();
	}
}

class AutoTankDecorator extends TankDecorator {

	public AutoTankDecorator(GameObject obj) {
		super(obj);
	}
	
	private void before() {
		System.out.println("�����Զ�Ѳ��ϵͳ");
	}
	
	private void after() {
		System.out.println("����Ŀ�ĵأ��Զ�ֹͣ");
	}
	public void move() {
		before();
		obj.move();
		after();
	}
}

class PersonDrivedTankDecorator extends TankDecorator {

	public PersonDrivedTankDecorator(GameObject obj) {
		super(obj);
	}
	
	private void before() {
		System.out.println("������");
	}
	
	private void after() {
		System.out.println("��ɲ��");
	}

	public void move() {
		before();
		obj.move();
		after();
	}
}

interface GameObject {
	void move();
}