package bridge.core.deck;

import bridge.core.Card;

public class Eight {
	public static Card of(Suit denomination) {
		return new Card("8", denomination);
	}
}
