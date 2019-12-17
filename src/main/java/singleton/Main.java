package singleton;

public class Main {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
//				System.out.println(Mgr01.getInstance().hashCode());
//				System.out.println(Mgr02.getInstance().hashCode());
//				System.out.println(Mgr03.getInstance().hashCode());
				System.out.println(Mgr04.INSTANCE.hashCode());
			}).start(); 
		}
	}

}
