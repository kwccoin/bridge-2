package bridge.bidding.rules;

import bridge.bidding.Auctioneer;
import bridge.bidding.Bid;
import bridge.bidding.ResponseCalculator;
import bridge.core.Hand;

public class Respond1ColorRaiseMajorSuit extends Response {

	private ResponseCalculator calculator;

	public Respond1ColorRaiseMajorSuit(Auctioneer a, Hand h) {
		super(a, h);
	}

	@Override
	protected boolean applies() {
		boolean result = false;
		if (super.applies()) {
			calculator = new ResponseCalculator(hand, partnersOpeningBid);
			if (partnersOpeningBid.hasTrump() && partnersOpeningBid.getTrump().isMajorSuit()
					&& partnersOpeningBid.getValue() == 1 && calculator.getCombinedPoints() >= 6
					&& hand.getSuitLength(partnersOpeningBid.getTrump().asSuit()) >= 3) {
				result = true;
			}
		}

		return result;
	}

	@Override
	protected Bid prepareBid() {
		if (calculator.getCombinedPoints() >= 6 && calculator.getCombinedPoints() <= 10) {
			return new Bid(2, partnersOpeningBid.getTrump());
		} else if (calculator.getCombinedPoints() >= 13 && calculator.getCombinedPoints() <= 16) {
			return new Bid(3, partnersOpeningBid.getTrump());
		} else {
			return null;
		}
	}

}
