package decorator;

/*
 * 装饰器类，
 * 用于增强类的功能
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
		System.out.println("开启自动巡航系统");
	}
	
	private void after() {
		System.out.println("到达目的地，自动停止");
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
		System.out.println("踩油门");
	}
	
	private void after() {
		System.out.println("踩刹车");
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