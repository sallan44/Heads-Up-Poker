package HeadsUpPoker;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This class handles the mechanics of the poker game. It displays the screen and player hands and eventually
 * all of the cards as well. It calculates winners though instances of the poker hand class and keeps track of 
 * who is in the lead until the game ends.
 * 
 * @author Sam Allan
 * @version 6/24/24
 */
public class PokerFrame extends JFrame {
	
	private PokerPanel panel;
	private DeckOfCards deck;
	
	private PlayingCard p1c1;
	private PlayingCard p1c2;
	private PlayingCard p2c1;
	private PlayingCard p2c2;
	private PlayingCard commCard1;
	private PlayingCard commCard2;
	private PlayingCard commCard3;
	private PlayingCard commCard4;
	private PlayingCard commCard5;
	private PlayingCard[] commCards;
	
	private PokerHand hand1;
	private PokerHand hand2;
	
	private int dealer;
	private int handCount;
	
	private int winLight;
	private String firstHandName;
	private String secondHandName;
	
	public PokerFrame() {
		
		handCount = 3;
		winLight = 0;
		deck = new DeckOfCards();
		commCards = new PlayingCard[5];
		panel = new PokerPanel(this, "pokerTable.jpeg");
		this.setContentPane(panel);
		this.setTitle("Heads Up Poker");
		this.setPreferredSize(new Dimension(2000, 1000));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
	}
	
	/**
	 * This method is a shortcut for beginning the entire game and initiating the poker frame.
	 */
	public void startGame() {
		while (!panel.stack1IsEmpty() && !panel.stack2IsEmpty()) {
			playHand();
			panel.resetPanel();
		}
		panel.displayEndScreen();
	}
	
	/**
	 * This method resets the dealer based on the hand count, deals cards, handles all of the betting rounds for each hand.
	 */
	private void playHand() {
		if (handCount % 2 == 0) {
			dealer = 2;
		} else {
			dealer = 1;
		}
		readyUpCards();
		betLoop: for (int round = 0; round < 4; round++) {
			waitForBettingToFinish();
			//in case someone folds
			if (panel.potIsEmpty()) {
				break betLoop;
			}
			//in case someone pushes all in
			if (panel.stack1IsEmpty() || panel.stack2IsEmpty()) {
				panel.addFirstCommCards(getStringFromCard(commCard1), getStringFromCard(commCard2), getStringFromCard(commCard3));
				panel.addFourthCommCard(getStringFromCard(commCard4));
				panel.addFifthCommCard(getStringFromCard(commCard5));
				panel.setBettingIsOver(true);
				break betLoop;
			}
			updateCommunityCards();
		}
		
		repaint();
		determineWinner();
		try {
            Thread.sleep(8000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		handCount++;
	}
	
	/**
	 * This method is used to determine who wins a hand after betting is over.
	 */
	private void determineWinner() {
		if (hand1.getHandRank() > hand2.getHandRank()) {
			panel.payFirstStack();
			this.setWinLight(1);
		} else if (hand2.getRank() > hand1.getRank()) {
			panel.paySecondStack();
			this.setWinLight(2);
		} else {
			
			int tieScore = hand1.tieBreak(hand2);
			
			if (tieScore == 1) {
				panel.payFirstStack();
				this.setWinLight(1);
			} else if (tieScore == -1){
				panel.paySecondStack();
				this.setWinLight(2);
			} else {
				panel.payBothStacks();
				this.setWinLight(3);
			}
			
		}
		firstHandName = hand1.getHandName();
		secondHandName = hand2.getHandName();
		panel.repaint();
	}
	
	/**
	 * This method deals a community card or cards to the table depending on what betting round it is.
	 */
	private void updateCommunityCards() {
			if (panel.getBettingRound() == 0) {
				panel.addFirstCommCards(getStringFromCard(commCard1), getStringFromCard(commCard2), getStringFromCard(commCard3));
			} else if (panel.getBettingRound() == 1) {
				panel.addFourthCommCard(getStringFromCard(commCard4));
			} else if (panel.getBettingRound() == 2) {
				panel.addFifthCommCard(getStringFromCard(commCard5));
			}
			panel.repaint();
	}
	
	/**
	 * This method shuffles the deck and presets the player cards and community cards. It also predetermines the hand ranks.
	 */
	private void readyUpCards() {
		deck.shuffle();
		p1c1 = deck.getCard();
		p2c1 = deck.getCard();
		p1c2 = deck.getCard();
		p2c2 = deck.getCard();
		commCard1 = deck.getCard();
		commCards[0] = commCard1;
		commCard2 = deck.getCard();
		commCards[1] = commCard2;
		commCard3 = deck.getCard();
		commCards[2] = commCard3;
		commCard4 = deck.getCard();
		commCards[3] = commCard4;
		commCard5 = deck.getCard();
		commCards[4] = commCard5;
		hand1 = new PokerHand(p1c1, p1c2, commCards);
		hand2 = new PokerHand(p2c1, p2c2, commCards);
		panel.addPlayerCards(getStringFromCard(p1c1), getStringFromCard(p1c2), getStringFromCard(p2c1), getStringFromCard(p2c2));
		panel.repaint();
	}
	
	/**
	 * This method is used for every hand to see if betting round are over. It then waits 100 milliseconds before the
	 * next card is dealt.
	 */
	private void waitForBettingToFinish() {
		while (!panel.bettingIsOver()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * This method uses an integer to signify what player is currently in the dealing spot/big blind.
	 * @return an integer regarding who is the dealer
	 */
	public int getDealer() {
		return dealer;
	}
	
	/**
	 * This method is used by the poker panel class to see what stack to light up yellow depending on who wins the hand.
	 * @param value - the number being set to
	 */
	private void setWinLight(int value) {
		this.winLight = value;
	}
	
	/**
	 * This method is used by the poker panel class to see what stack to light up yellow depending on who wins the hand.
	 * @return an integer regarding what player won the hand
	 */
	public int getWinLight() {
		return winLight;
	}
	
	/**
	 * This method is used by the poker panel in order to display the name of the first hand at the end of the round.
	 * @return the name of the hand at the end of the round
	 */
	public String getFirstHandName() {
		return firstHandName;
	}
	
	/**
	 * This method is used by the poker panel in order to display the name of the second hand at the end of the round.
	 * @return the name of the hand at the end of the round
	 */
	public String getSecondHandName() {
		return secondHandName;
	}
	
	/**
	 * This method constructs the file name of a card's image based on its value and suit.
	 * @param card - the card who's image is required
	 * @return the String file name of the card
	 */
	private String getStringFromCard(PlayingCard card) {
		switch (card.getSuit()) {
			case Suit.SPADES:
				return getValueFromDealtCard(card) + "Spades.png";
			case Suit.CLUBS:
				return getValueFromDealtCard(card) + "Clubs.png";
			case Suit.HEARTS:
				return getValueFromDealtCard(card) + "Hearts.png";
			default:
				return getValueFromDealtCard(card) + "Diamonds.png";
		}
	}
	
	/**
	 * This method constructs the beginning of the file name of a card's image based on its value and suit.
	 * @param card - the card who's image is required
	 * @return the String file name of the card
	 */
	private String getValueFromDealtCard(PlayingCard card) {
		switch (card.getValue()) {
			case 1, 14:
				return "ace";
			case 2:
				return "two";
			case 3:
				return "three";
			case 4:
				return "four";
			case 5:
				return "five";
			case 6:
				return "six";
			case 7: 
				return "seven";
			case 8:
				return "eight";
			case 9:
				return "nine";
			case 10:
				return "ten";
			case 11:
				return "jack";
			case 12:
				return "queen";
			default:
				return "king";
		}
	}

	private static final long serialVersionUID = 1L;
	
}
