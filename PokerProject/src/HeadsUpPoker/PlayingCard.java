package HeadsUpPoker;

/**
 * This class represents a playing card and the different aspects of a card: value and suit, 
 * represented through numbers and an enumerated "Suit" class.
 * 
 * @author Sam Allan
 * @version 5/5/24
 */
public class PlayingCard {
	
	private int value;
	private Suit suit;
	
	/**
	 * This is the constructor method of the PlayingCard class. It assigns a card a value and a suit.
	 * @param value - the number value of the card
	 * @param suit - the suit of the card (Spades, Clubs, Hearts, Diamonds)
	 */
	public PlayingCard(int value, Suit suit) {
		if (value > 1 && value < 15) {
			this.value = value;
		}
		this.suit = suit;
	}
	
	/**
	 * This is a getter method for the value of a card.
	 * @returnnthe value of a card
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * This is a getter method for the suit of a card.
	 * @return the suit of a card
	 */
	public Suit getSuit() {
		return this.suit;
	}
	
	/**
	 * This is an equals method used for comparing two cards by value and suit.
	 * @param other - the other object being compared to
	 * return a boolean for whether or not the two cards are equal
	 */
	public boolean equals(Object other) {
		if (!(other instanceof PlayingCard)) {
			return false;
		} else {
			PlayingCard otherCard = (PlayingCard)other;
			return this.value == otherCard.value && this.suit == otherCard.suit;
		}
	}

}
