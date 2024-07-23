package HeadsUpPoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class uses multiple methods to determine if a player holds certain hands
 * and a ranking is given to a hand based on its status.
 * 
 * @author Sam Allan
 * @version 6/24/24
 */
public class PokerHand {

	private PlayingCard cardOne;
	private PlayingCard cardTwo;
	private PlayingCard[] commCards;

	private Map<Integer, Integer> cardMemory;
	private int handRank;
	private int highestRelevantCardValue;

	/**
	 * This constructor simply creates a hand object to store the players cards and
	 * rank in.
	 * @param cardOne - the first dealt card of a player's hand
	 * @param cardTwo - the second dealt card of a player's hand
	 * @param commCards - an array holding all the community cards on the table 
	 */
	public PokerHand(PlayingCard cardOne, PlayingCard cardTwo, PlayingCard[] commCards) {
		this.cardOne = cardOne;
		this.cardTwo = cardTwo;
		this.commCards = commCards;
		cardMemory = new HashMap<Integer, Integer>();
		highestRelevantCardValue = 0;
		handRank = 0;
	}

	/**
	 * This method runs through all of the hand tests to see what hand it hands.
	 * 
	 * @return the number associated with the rank of the hand
	 */
	public int getHandRank() {
		if (hasStraightFlush())
			return handRank;
		if (hasFourOfAKind())
			return handRank;
		if (hasFullHouse())
			return handRank;
		if (hasFlush())
			return handRank;
		if (hasStraight())
			return handRank;
		if (hasSet())
			return handRank;
		if (hasTwoPair())
			return handRank;
		if (hasPair())
			return handRank;
		return handRank;
	}

	/**
	 * This method is used to compare high cards of similar hands.
	 * 
	 * @param otherHand - the other hand with the same current rank
	 * @return 1 if this hand is better, -1 if the other is better, 0 if they are of
	 *         equal strength
	 */
	public int tieBreak(PokerHand otherHand) {
		switch (this.getRank()) {
		case 1, 3, 7:

			int thisHigh = this.getHighestRelevantCardValue();
			int otherHigh = otherHand.getHighestRelevantCardValue();

			if (thisHigh > otherHigh) {
				return 1;
			} else if (otherHigh > thisHigh) {
				return -1;
			} else {
				return this.compareFinalHighCards(otherHand);
			}

		case 2:

			int thisHigherPair = this.getHighestRelevantCardValue();
			int otherHigherPair = otherHand.getHighestRelevantCardValue();
			int thisLowerPair = this.getTwoPairLowerPair();
			int otherLowerPair = otherHand.getTwoPairLowerPair();
			int thisHighCard = this.getTwoPairHighCard();
			int otherHighCard = otherHand.getTwoPairHighCard();

			if (thisHigherPair > otherHigherPair) {
				return 1;
			} else if (otherHigherPair > thisHigherPair) {
				return -1;
			} else {

				if (thisLowerPair > otherLowerPair) {
					return 1;
				} else if (otherLowerPair > thisLowerPair) {
					return -1;
				} else {

					if (thisHighCard > otherHighCard) {
						return 1;
					} else if (otherHighCard > thisHighCard) {
						return -1;
					} else {
						return 0;
					}

				}

			}

		case 4, 8:

			int thisStraightHigh = this.getHighestRelevantCardValue();
			int otherStraightHigh = otherHand.getHighestRelevantCardValue();

			if (thisStraightHigh > otherStraightHigh) {
				return 1;
			} else if (otherStraightHigh > thisStraightHigh) {
				return -1;
			} else {
				return 0;
			}

		case 5:

			int thisFlushHigh = this.getFlushHighCard();
			int otherFlushHigh = otherHand.getFlushHighCard();

			if (thisFlushHigh > otherFlushHigh) {
				return 1;
			} else if (otherFlushHigh > thisFlushHigh) {
				return -1;
			} else {
				return 0;
			}

		case 6:

			int thisFHHigh = this.getHighestRelevantCardValue();
			int otherFHHigh = otherHand.getHighestRelevantCardValue();
			int thisPair = this.getFullhousePairValue();
			int otherPair = otherHand.getFullhousePairValue();

			if (thisFHHigh > otherFHHigh) {
				return 1;
			} else if (otherFHHigh > thisFHHigh) {
				return -1;
			} else {

				if (thisPair > otherPair) {
					return 1;
				} else if (otherPair > thisPair) {
					return -1;
				} else {
					return 0;
				}

			}

		default:

			return this.compareFinalHighCards(otherHand);

		}
	}
	
	/**
	 * This method is used by the game to display the name of the hand at the end of every hand.
	 * @return the name of the hand
	 */
	public String getHandName() {
		switch (this.getRank()) {
			case 1:
				return "Pair";
			case 2:
				return "Two Pair";
			case 3:
				return "Three Of A Kind";
			case 4:
				return "Straight";
			case 5:
				return "Flush";
			case 6:
				return "Full House";
			case 7:
				return "Four Of A Kind";
			case 8:
				return "Straight Flush";
			default:
				return "High Card";
		}
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * so see if any of them are pairs.
	 * 
	 * @return a boolean regarding if any of the cards are pairs
	 */
	protected boolean hasPair() {
		resetCardMemory();

		for (Integer key : cardMemory.keySet()) {
			if (cardMemory.get(key) == 2 && key != 1) {
				highestRelevantCardValue = key;
				handRank = 1;
				return true;
			}
		}

		return false;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if there are two pairs.
	 * 
	 * @return a boolean regarding whether or not there are two pairs
	 */
	protected boolean hasTwoPair() {
		resetCardMemory();

		int pairCount = 0;

		for (Integer key : cardMemory.keySet()) {
			if (cardMemory.get(key) == 2 && key != 1) {
				pairCount++;
				if (key > highestRelevantCardValue) {
					highestRelevantCardValue = key;
				}
			}
		}
		if (pairCount >= 2) {
			handRank = 2;
			return true;
		}

		return false;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if there is a set.
	 * 
	 * @return a boolean regarding if there is are three of a kind
	 */
	protected boolean hasSet() {
		resetCardMemory();

		for (Integer key : cardMemory.keySet()) {
			if (cardMemory.get(key) == 3 && key != 1) {
				highestRelevantCardValue = key;
				handRank = 3;
				return true;
			}
		}

		return false;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if there is a straight on the board.
	 * 
	 * @return a boolean regarding if there is a straight
	 */
	protected boolean hasStraight() {
		resetCardMemory();
		boolean hasStraight = false;

		for (Integer key : cardMemory.keySet()) {
			if (cardMemory.containsKey(key + 1) && cardMemory.containsKey(key + 2) && cardMemory.containsKey(key + 3)
					&& cardMemory.containsKey(key + 4)) {
				hasStraight = true;
				highestRelevantCardValue = key + 4;
				handRank = 4;
			}
		}

		return hasStraight;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if five have the same suit.
	 * 
	 * @return a boolean regarding if five cards share the same suit
	 */
	protected boolean hasFlush() {
		Map<Suit, Integer> suitMemory = new HashMap<Suit, Integer>();
		suitMemory.merge(cardOne.getSuit(), 1, Integer::sum);
		suitMemory.merge(cardTwo.getSuit(), 1, Integer::sum);
		for (PlayingCard card : commCards) {
			suitMemory.merge(card.getSuit(), 1, Integer::sum);
		}

		for (Suit key : suitMemory.keySet()) {
			if (suitMemory.get(key) == 5) {
				handRank = 5;
				return true;
			}
		}

		return false;
	}

	/**
	 * This method loops though all of the community cards and current player cards
	 * to see if there is a full house.
	 * 
	 * @return a boolean regarding if there is a full house
	 */
	protected boolean hasFullHouse() {
		resetCardMemory();
		boolean hasThree = false;
		boolean hasTwo = false;

		for (int key : cardMemory.keySet()) {
			if (key != 1) {
				if (cardMemory.get(key) == 3) {
					if (hasThree) {
						if (key > highestRelevantCardValue) {
							highestRelevantCardValue = key;
						}
						handRank = 6;
						return true;
					}
					if (key > highestRelevantCardValue) {
						highestRelevantCardValue = key;
					}
					hasThree = true;
				} else if (cardMemory.get(key) == 2) {
					hasTwo = true;
				}

				if (hasThree && hasTwo) {
					handRank = 6;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if there are four of the same card.
	 * 
	 * @return a boolean regarding if there is four of a kind
	 */
	protected boolean hasFourOfAKind() {
		resetCardMemory();

		for (Integer key : cardMemory.keySet()) {
			if (cardMemory.get(key) == 4 && key != 1) {
				highestRelevantCardValue = key;
				handRank = 7;
				return true;
			}
		}

		return false;
	}

	/**
	 * This method loops through all of the community cards and current player cards
	 * to see if there is a straight on the board.
	 * 
	 * @return a boolean regarding if there is a straight
	 */
	protected boolean hasStraightFlush() {

		Map<Suit, List<Integer>> cards = resetStraightFlushMemory();

		for (Suit suit : cards.keySet()) {
			for (int i = 0; i < cards.get(suit).size(); i++) {
				if (cards.get(suit).contains(cards.get(suit).get(i) + 1)
						&& cards.get(suit).contains(cards.get(suit).get(i) + 2)
						&& cards.get(suit).contains(cards.get(suit).get(i) + 3)
						&& cards.get(suit).contains(cards.get(suit).get(i) + 4)) {
					highestRelevantCardValue = cards.get(suit).get(i) + 4;
					handRank = 8;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * This method compares the high cards of a hand when they share the same rank,
	 * using the player cards.
	 * 
	 * @param other - the other hand being compared to
	 * @return 1 if this hand has higher player cards, -1 if the other does, and 0
	 *         if they are equal
	 */
	private int compareFinalHighCards(PokerHand other) {
		int thisHigh = this.getHigherOfPlayerCards();
		int otherHigh = other.getHigherOfPlayerCards();
		int thisLow = this.getLowerOfPlayerCards();
		int otherLow = other.getLowerOfPlayerCards();

		if (this.playerCardHigherThanCommCard() && !other.playerCardHigherThanCommCard()) {
			return 1;
		} else if (other.playerCardHigherThanCommCard() && !this.playerCardHigherThanCommCard()) {
			return -1;
		} else if (this.playerCardHigherThanCommCard() && other.playerCardHigherThanCommCard()) {

			if (thisHigh > otherHigh) {
				return 1;
			} else if (otherHigh > thisHigh) {
				return -1;
			} else {

				if (thisLow > otherLow) {
					return 1;
				} else if (otherLow > thisLow) {
					return -1;
				} else {
					return 0;
				}

			}

		} else {
			return 0;
		}
	}

	/**
	 * This method is used to find the value of the lower pair of two pair hands.
	 * Used when tie-breaking two different two pairs.
	 * 
	 * @return the value of the lower pair in the hand
	 */
	private int getTwoPairLowerPair() {
		int lowerPair = 0;
		for (int key : cardMemory.keySet()) {
			if (cardMemory.get(key) == 2 && key != this.getHighestRelevantCardValue() && key > lowerPair) {
				lowerPair = key;
			}
		}
		return lowerPair;
	}
	
	/**
	 * This method compares the high cards of hands with two pair.
	 * 
	 * @return the value of the high card of a two pair hand.
	 */
	private int getTwoPairHighCard() {
		int highCard = 0;
		for (int key : cardMemory.keySet()) {
			if (key != this.getTwoPairLowerPair() && key != this.getHighestRelevantCardValue() && key > highCard) {
				highCard = key;
			}
		}
		return highCard;
	}

	/**
	 * This method is used to find the high card within a flush.
	 * 
	 * @return the high card of a flush.
	 */
	private int getFlushHighCard() {
		Map<Suit, List<Integer>> cards = resetStraightFlushMemory();
		int highCard = 0;
		Suit mostSuit = cards.keySet().iterator().next();
		for (Suit key : cards.keySet()) {
			if (cards.get(key).size() >= 5) {
				mostSuit = key;
				break;
			}
		}

		for (int i = cards.get(mostSuit).size() - 1; i > 0; i--) {
			if (cards.get(mostSuit).get(i) > highCard) {
				highCard = cards.get(mostSuit).get(i);
			}
		}
		return highCard;
	}

	/**
	 * This method returns the value of the pair in the full house. Used for
	 * comparing separate full house with the same set value.
	 * 
	 * @return the value of the pair in the full house
	 */
	private int getFullhousePairValue() {
		int highest = 0;
		for (int key : cardMemory.keySet()) {
			if (cardMemory.get(key) >= 2 && key != this.getHighestRelevantCardValue() && key > highest) {
				highest = key;
			}
		}
		return highest;
	}
	
	/**
	 * This method sees if any of the player cards in a hand are greater than the
	 * community cards. Used for calculating high cards.
	 * 
	 * @return a boolean regarding if any of the player card values are greater than
	 *         the community cards.
	 */
	private boolean playerCardHigherThanCommCard() {
		for (PlayingCard card : commCards) {
			if (card.getValue() != this.getHighestRelevantCardValue()
					&& (cardOne.getValue() > card.getValue() || cardTwo.getValue() > card.getValue())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method retrieves the higher value of the two player cards in a hand.
	 * 
	 * @return the higher value of the two player cards in a hand
	 */
	private int getHigherOfPlayerCards() {
		if (cardOne.getValue() > cardTwo.getValue()) {
			return cardOne.getValue();
		}
		return cardTwo.getValue();
	}

	/**
	 * This method retrieves the lower value of the two player cards in a hand.
	 * 
	 * @return the lower value of the two player cards in a hand
	 */
	private int getLowerOfPlayerCards() {
		if (cardOne.getValue() < cardTwo.getValue()) {
			return cardOne.getValue();
		}
		return cardTwo.getValue();
	}

	/**
	 * This retrieves the rank of a poker hand. Used after a hand rank has been
	 * assigned using getHandRank().
	 * 
	 * @return the rank of a poker hand
	 */
	public int getRank() {
		return handRank;
	}
	
	/**
	 * This retrieves the high card, or pair, or set, in a current hand, relative to
	 * the hand it is.
	 * 
	 * @return the high card of the particular hand
	 */
	private int getHighestRelevantCardValue() {
		return highestRelevantCardValue;
	}

	/**
	 * This method is used as a helper as of now to reset the memory of used cards
	 * prior to the final game.
	 */
	private void resetCardMemory() {

		cardMemory.clear();

		if (cardOne.getValue() != 14) {
			cardMemory.merge(cardOne.getValue(), 1, Integer::sum);
		} else {
			cardMemory.merge(cardOne.getValue(), 1, Integer::sum);
			cardMemory.merge(1, 1, Integer::sum);
		}

		if (cardTwo.getValue() != 14) {
			cardMemory.merge(cardTwo.getValue(), 1, Integer::sum);
		} else {
			cardMemory.merge(cardTwo.getValue(), 1, Integer::sum);
			cardMemory.merge(1, 1, Integer::sum);
		}

		for (PlayingCard card : commCards) {
			if (card.getValue() != 14) {
				cardMemory.merge(card.getValue(), 1, Integer::sum);
			} else {
				cardMemory.merge(card.getValue(), 1, Integer::sum);
				cardMemory.merge(1, 1, Integer::sum);
			}
		}
	}

	/**
	 * This is used for resetting the memory of cards when searching for a straight
	 * flush or,, in one other case, looking for the high card of a flush hand.
	 */
	private Map<Suit, List<Integer>> resetStraightFlushMemory() {
		Map<Suit, List<Integer>> cards = new HashMap<Suit, List<Integer>>();
		List<Integer> spadeList = new ArrayList<Integer>();
		List<Integer> clubList = new ArrayList<Integer>();
		List<Integer> diamondList = new ArrayList<Integer>();
		List<Integer> heartList = new ArrayList<Integer>();

		if (cardOne.getSuit() == Suit.SPADES) {
			spadeList.add(cardOne.getValue());
			if (cardOne.getValue() == 14) {
				spadeList.add(1);
			}
		} else if (cardOne.getSuit() == Suit.CLUBS) {
			clubList.add(cardOne.getValue());
			if (cardOne.getValue() == 14) {
				clubList.add(1);
			}
		} else if (cardOne.getSuit() == Suit.DIAMONDS) {
			diamondList.add(cardOne.getValue());
			if (cardOne.getValue() == 14) {
				diamondList.add(1);
			}
		} else {
			heartList.add(cardOne.getValue());
			if (cardOne.getValue() == 14) {
				heartList.add(1);
			}
		}

		if (cardTwo.getSuit() == Suit.SPADES) {
			spadeList.add(cardTwo.getValue());
			if (cardTwo.getValue() == 14) {
				spadeList.add(1);
			}
		} else if (cardTwo.getSuit() == Suit.CLUBS) {
			clubList.add(cardTwo.getValue());
			if (cardTwo.getValue() == 14) {
				clubList.add(1);
			}
		} else if (cardTwo.getSuit() == Suit.DIAMONDS) {
			diamondList.add(cardTwo.getValue());
			if (cardTwo.getValue() == 14) {
				diamondList.add(1);
			}
		} else {
			heartList.add(cardTwo.getValue());
			if (cardTwo.getValue() == 14) {
				heartList.add(1);
			}
		}

		for (PlayingCard card : commCards) {
			if (card.getSuit() == Suit.SPADES) {
				spadeList.add(card.getValue());
				if (card.getValue() == 14) {
					spadeList.add(1);
				}
			} else if (card.getSuit() == Suit.CLUBS) {
				clubList.add(card.getValue());
				if (card.getValue() == 14) {
					clubList.add(1);
				}
			} else if (card.getSuit() == Suit.DIAMONDS) {
				diamondList.add(card.getValue());
				if (card.getValue() == 14) {
					diamondList.add(1);
				}
			} else {
				heartList.add(card.getValue());
				if (card.getValue() == 14) {
					heartList.add(1);
				}
			}
		}

		Collections.sort(spadeList, Collections.reverseOrder());
		Collections.sort(clubList, Collections.reverseOrder());
		Collections.sort(heartList, Collections.reverseOrder());
		Collections.sort(diamondList, Collections.reverseOrder());

		cards.put(Suit.SPADES, spadeList);
		cards.put(Suit.CLUBS, clubList);
		cards.put(Suit.DIAMONDS, diamondList);
		cards.put(Suit.HEARTS, heartList);

		return cards;
	}

}
