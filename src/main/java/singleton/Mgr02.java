package singleton;

/*
 * 懒汉式
 * 
*/
public class Mgr02 {

	// volatile 必需加
	private static volatile Mgr02 INSTANCE = null;
	
	private Mgr02() {}
	
	public static Mgr02 getInstance() {
		if ( INSTANCE == null ) {
			synchronized (Mgr02.class) {
				if ( INSTANCE == null ) {
					INSTANCE = new Mgr02();
				}
			}
		}
		return INSTANCE;
	}
	
}
