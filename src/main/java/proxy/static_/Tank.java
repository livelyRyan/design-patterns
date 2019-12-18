package proxy.static_;

import java.util.Random;

/*
 * 静态代理， 显示创建代理类
 * 
 * 写法和Decorator很像
*/
public class Tank implements Movable {
	public void move() {
		System.out.println("Tank moving claclacla...");
		try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		Tank tank = new Tank();
		new TankLogProxy(new TankTimeProxy(tank)).move();
	}
	
}

class TankTimeProxy implements Movable {

	public Movable movable;
	
	public TankTimeProxy(Movable movable) {
		this.movable = movable;
	}
	
	@Override
	public void move() {
		long start = System.currentTimeMillis();
		movable.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
	}
}

class TankLogProxy implements Movable {
    Movable movable;

    public TankLogProxy(Movable m) {
        this.movable = m;
    }

    @Override
    public void move() {
        System.out.println("start moving");
        movable.move();
        System.out.println("move stopped!");
    }
}

interface Movable {
	void move();
}