package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *  1. 局限性: Tank_DynamicProxy必需实现一个接口, 否则jdk动态代理不知道代理类里有什么方法 
 *  2. jdk动态代理自动生成一个新类, 新类会实现Movable接口, 继承自Proxy类
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
		 * 被代理类,即当前示例中的Tank_DynamicProxy,以下简称为a
		 * 
		 * newProxyInstance(loader, classes, invocationHandler)
		 * 
		 * loader: 指定用哪个类加载器来加载动态生成的类. 一般会用a的类加载器. 也可以用a的父类加载器(类加载器也是一个树状结构),
		 *           但是不能指定与a的类加载器平行的类加载器, 因为平行的两个类加载器间数据就隔离了 classes: 指定a实现的接口列表,
		 *          新生成的代理类也会实现指定的接口列表 
		 * invocationHandler: 新生成的类会继承Proxy类,
		 * Proxy类中有一个带InvocationHandler参数的初始化方法.该参数值就是用来初始化的.
		 * 
		 * 
		 *    返回值: Object类型, 其实就是jdk动态代理帮我们生成的新代理类, 实现了Movable接口.当我们调用o.move()时候,就会调用invocationHandler.invoke()方法
		 * 
		 */
		
		// 本地保存生成的代理类
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
		// 调用movable实现类对象的method方法
		Object result = method.invoke(movable, args);
		System.out.println(System.currentTimeMillis() - start);
		after();
		return result;
	}
}

interface Movable {
	void move();
}