package bridge;

public class BlueGift extends Gift {

	public BlueGift(GiftImpl giftImpl) {
		this.giftImpl = giftImpl;
	}

	@Override
	public void draw() {
		System.out.println("blue " + this.giftImpl.getType());
	}
	
}
