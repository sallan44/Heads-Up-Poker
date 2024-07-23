package HeadsUpPoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class represents a deck of cards using an ArrayList of PlayingCard Objects.
 * 
 * @author Sam Allan
 * @version 5/5/24
 */
public class DeckOfCards {
	
	private List<PlayingCard> deck;
	
	/**
	 * This constructor initiates the list that represents the deck and uses a private 
	 * method to add all the cards.
	 */
	public DeckOfCards() {
		deck = new ArrayList<PlayingCard>();
		this.addCards();
	}
	
	/**
	 * This is a getter method for a random card out of the deck. It then removes the card
	 * that was taken out of the deck.
	 * @return a single card from a deck using a random generator.
	 */
	public PlayingCard getCard() {
		Random random = new Random();
		int randomInt = random.nextInt(0, deck.size());
		PlayingCard card = deck.get(randomInt);
		deck.remove(randomInt);
		return card;
	}
	
	/**
	 * This method uses a private method to clear and add all the cards again, giving the 
	 * impression that the deck was shuffled.
	 */
	public void shuffle() {
		deck.clear();
		this.addCards();
	}
	
	/**
	 * This private method iterates through all the card values (2 all the way to Ace) and 
	 * adds them to the deck.
	 */
	private void addCards() {
		for (int i = 2; i < 15; i++) {
			PlayingCard spadesCard = new PlayingCard(i, Suit.SPADES);
			PlayingCard clubsCard = new PlayingCard(i, Suit.CLUBS);
			PlayingCard heartsCard = new PlayingCard(i, Suit.HEARTS);
			PlayingCard diamondsCard = new PlayingCard(i, Suit.DIAMONDS);
			deck.add(spadesCard);
			deck.add(clubsCard);
			deck.add(heartsCard);
			deck.add(diamondsCard);
		}
	}

}
