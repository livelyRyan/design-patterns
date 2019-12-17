package singleton;

/*
 * ����ʽ
 * 
*/
public class Mgr01 {

	// ��������ͷ���Դ�����, ��final���ܼӡ� ���������
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
