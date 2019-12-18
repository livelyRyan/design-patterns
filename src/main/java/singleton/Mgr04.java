package singleton;

/*
 * 不仅可以解决线程同步，还可以防止反序列化
 * java创始人
 * 不过一个类变成了枚举, 感觉也挺奇怪的
*/
public enum Mgr04 {

	INSTANCE;

	public void print() {System.out.println("message");}
}