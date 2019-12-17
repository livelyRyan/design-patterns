package proxy.dynamic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib����
 *
 */
public class Tank_Cglib {

	public void move() {
		System.out.println("tank moving ...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		// ���ö�̬������ĸ���
		enhancer.setSuperclass(Tank_Cglib.class);
		// ���ûص���
		enhancer.setCallback(new TankMethodHandler());
		// ����һ����̬���������ʵ��
		Tank_Cglib tank = (Tank_Cglib)enhancer.create();
		tank.move();
	}
}

class TankMethodHandler implements MethodInterceptor {

	public void before() {
		System.out.println("------------");
	}
	
	public void after() {
		System.out.println("=============");
	}

	/**
	 * o: ��̬���ɵ���
	 * method: ���ص���, �� move();
	 * args: �����Ĳ���
	 * methodProxy: ���е��÷���ע����Ϣ����. �����صķ���������ʱ,������Ϣ��ע�ᵽ������
	 *
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println(o.getClass().getSuperclass().getName());
		before();
		Object result = methodProxy.invokeSuper(o, args);
		after();
		return result;
	}
}
