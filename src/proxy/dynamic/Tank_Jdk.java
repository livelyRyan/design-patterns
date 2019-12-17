package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk��̬����
 *  1. ������: Tank_DynamicProxy����ʵ��һ���ӿ�, ����jdk��̬����֪������������ʲô���� 
 *  2. jdk��̬�����Զ�����һ������, �����ʵ��Movable�ӿ�, �̳���Proxy��
 *
 */
public class Tank_Jdk implements Movable {

	public void move() {
		System.out.println("tank moving ...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*
		 * ��������,����ǰʾ���е�Tank_DynamicProxy,���¼��Ϊa
		 * 
		 * newProxyInstance(loader, classes, invocationHandler)
		 * 
		 * loader: ָ�����ĸ�������������ض�̬���ɵ���. һ�����a���������. Ҳ������a�ĸ��������(�������Ҳ��һ����״�ṹ),
		   *           ���ǲ���ָ����a���������ƽ�е��������, ��Ϊƽ�е�����������������ݾ͸����� classes: ָ��aʵ�ֵĽӿ��б�,
		   *          �����ɵĴ�����Ҳ��ʵ��ָ���Ľӿ��б� 
		 * invocationHandler: �����ɵ����̳�Proxy��,
		 * Proxy������һ����InvocationHandler�����ĳ�ʼ������.�ò���ֵ����������ʼ����.
		 * 
		 * 
		   *    ����ֵ: Object����, ��ʵ����jdk��̬������������ɵ��´�����, ʵ����Movable�ӿ�.�����ǵ���o.move()ʱ��,�ͻ����invocationHandler.invoke()����
		 * 
		 */
		
		// ���ر������ɵĴ�����
//		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		Movable o = (Movable) Proxy.newProxyInstance(Tank_Jdk.class.getClassLoader(),
				new Class[] { Movable.class }, new TankInvocationHandler(new Tank_Jdk()));
		o.move();
	}
}

class TankInvocationHandler implements InvocationHandler {

	private Movable movable;

	public TankInvocationHandler(Movable movable) {
		this.movable = movable;
	}

	public void before() {
		System.out.println("------------");
	}
	
	public void after() {
		System.out.println("=============");
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		long start = System.currentTimeMillis();
		// ����movableʵ��������method����
		Object result = method.invoke(movable, args);
		System.out.println(System.currentTimeMillis() - start);
		after();
		return result;
	}
}

interface Movable {
	void move();
}