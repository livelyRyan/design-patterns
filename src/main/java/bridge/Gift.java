package bridge;

// Gift有属性GiftImpl, 因此设计为抽象类
public abstract class Gift {

	public GiftImpl giftImpl = null;
	
	public abstract void draw();
}
