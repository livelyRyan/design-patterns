package singleton;

/*
 * ��̬�ڲ��෽ʽ
 * JVM��֤����
 * �����ⲿ��ʱ��������ڲ��࣬��������ʵ��������
 * 
*/
public class Mgr03 {

	private Mgr03() {}
	
	private static class Mgr03Holder {
		private final static Mgr03 INSTANCE = new Mgr03();
	}
	
	public static Mgr03 getInstance() {
		return Mgr03Holder.INSTANCE;
	}
	
}
