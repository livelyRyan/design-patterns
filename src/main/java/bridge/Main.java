package bridge;


/**
 * 桥接模式： 分离抽象与具体实现，让它们可以独自发展
 * 
 * Git和GitImpl类两者单独向下发展，互不相干
 * Git中对GitImpl进行了聚合，这样就能把两个连接起来
 * 
 */
public class Main {

	public static void main(String[] args) {
		new WhiteGift(new Book()).draw();
		new BlueGift(new Flower()).draw();
	}

}
