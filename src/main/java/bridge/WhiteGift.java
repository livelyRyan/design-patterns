package bridge;

public class WhiteGift extends Gift {

	public WhiteGift(GiftImpl giftImpl) {
		this.giftImpl = giftImpl;
	}

	@Override
	public void draw() {
		System.out.println("white " + this.giftImpl.getType());		
	}
	
}
