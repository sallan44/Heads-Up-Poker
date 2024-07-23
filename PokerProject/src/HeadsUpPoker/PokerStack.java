package HeadsUpPoker;

/**
 * This class represents the interactions between player chip stacks and the communal pot.
 * 
 * @author Sam Allan
 * @version 5/16/24
 */
public class PokerStack {

	private double amount;
	private double bigBlind;
	private double smallBlind;
	
	/**
	 * This constructor composes a stack using and inputed big blind, which will then assign proportional stack sizes.
	 * @param bigBlind
	 */
	public PokerStack(double bigBlind) {
		this.bigBlind = bigBlind;
		this.smallBlind = bigBlind / 2;
		this.amount = bigBlind * 150;
	}
	
	/**
	 * This method is used for shoving all of a stacks chips in.
	 * @param pot - the stack/pot the chips are going to
	 */
	public void allIn(PokerStack pot) {
		double value = this.amount;
		this.amount = 0;
		pot.amount += value;
	}
	
	/**
	 * This method takes the amount of the big blind and delivers it to the pot.
	 */
	public void takeBigBlind(PokerStack pot) {
		this.amount -= bigBlind;
		pot.amount += bigBlind;
	}
	
	/**
	 * This method takes the amount of the small blind and delivers it to the pot.
	 */
	public void takeSmallBlind(PokerStack pot) {
		this.amount -= smallBlind;
		pot.amount += smallBlind;
	}
	
	/**
	 * This method is used for putting bets into the pot and delivering won chips to a player's stack.
	 * @param bet - the amount of money being bet or delivered
	 * @param pot - the stack being delivered to
	 */
	public void bet(double bet, PokerStack pot) {
		this.amount = this.amount - bet;
		pot.amount += bet;
	}
	
	/**
	 * This method splits the amount of money in a stack between two other stacks. It also sets the amount back to zero in case
	 * there's any remaining money from uneven numbers.
	 * @param player1 - the first other stack
	 * @param player2 - the second other stack
	 */
	public void splitPot(PokerStack player1, PokerStack player2) {
		this.bet(this.amount/2, player1);
		this.bet(this.amount/2, player2);
		this.amount = 0;
	}
	
	/**
	 * This method retrieves the amount within a stack.
	 * @return the amount within a stack
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * This method retrieves the amount of the big blind.
	 * @return the big blind amount
	 */
	public double getBlind() {
		return bigBlind;
	}
}
