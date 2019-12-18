package singleton;

/*
 * 饿汉式
 * 
*/
public class Mgr01 {

	// 如果存在释放资源的情况, 则final不能加。 否则建议加上,代表不可修改
	private static final Mgr01 INSTANCE = new Mgr01();

//	public void releaseInstance(){ 
//	    if(INSTANCE != null){ 
//	    	INSTANCE = null; 
//	    } 
//	}
	
	private Mgr01() {}

	public static Mgr01 getInstance() {
		return INSTANCE;
	}

}