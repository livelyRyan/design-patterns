package bridge;


/**
 * �Ž�ģʽ�� ������������ʵ�֣������ǿ��Զ��Է�չ
 * 
 * Git��GitImpl�����ߵ������·�չ���������
 * Git�ж�GitImpl�����˾ۺϣ��������ܰ�������������
 * 
 */
public class Main {

	public static void main(String[] args) {
		new WhiteGift(new Book()).draw();
		new BlueGift(new Flower()).draw();
	}

}
