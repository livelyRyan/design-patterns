package proxy.dynamic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib代理
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
		// 设置动态生成类的父类
		enhancer.setSuperclass(Tank_Cglib.class);
		// 设置回调类
		enhancer.setCallback(new TankMethodHandler());
		// 创建一个动态生成类对象实例
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
	 * o: 动态生成的类
	 * method: 拦截的类, 即 move();
	 * args: 方法的参数
	 * methodProxy: 带有调用方法注册信息的类. 当拦截的方法被调用时,方法信息会注册到该类中
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