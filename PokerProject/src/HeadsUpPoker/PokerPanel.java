package HeadsUpPoker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

/**
 * This class creates all of the images for the poker game including the cards and table, which will eventually be put 
 * on to a frame for the final game.
 * 
 * @author Sam Allan
 * @version 6/24/24
 */
public class PokerPanel extends JPanel implements ActionListener, ChangeListener {
	
	private Image background;
	private Image player1Card1;
	private Image player1Card2;
	private Image player2Card1;
	private Image player2Card2;
	private Image commCard1;
	private Image commCard2;
	private Image commCard3;
	private Image commCard4;
	private Image commCard5;
	
	private JButton check1;
	private JButton check2;
	private JButton bet1;
	private JButton bet2;
	private JButton call1;
	private JButton call2;
	private JButton fold1;
	private JButton fold2;
	private JSlider betSlider;
	
	private PokerStack stack1;
	private PokerStack stack2;
	private PokerStack pot;
	private JLabel amount1;
	private JLabel amount2;
	private JLabel amount3;
	private JLabel betLabel;
	private JLabel handOneName;
	private JLabel handTwoName;
	private JLabel winner;
	
	private int bettingRound;
	private int turn;
	private double chipsBet1;
	private double chipsBet2;
	private boolean bettingIsOver;
	
	private PokerFrame frame;
	private JLabel blindLabel;
	private JFormattedTextField blind;
	private JButton start;
	
	/**
	 * This constructor creates the background and nothing else. It is used for the starting screen.
	 * @param background - the image of the poker table
	 */
	public PokerPanel(PokerFrame frame, String background) {
		this.setLayout(null);
		start = new JButton("Start");
		start.addActionListener(this);
		blindLabel = new JLabel("Type your desired blind");
		makeBlindTextField();
		winner = new JLabel("No one");
		handOneName = new JLabel();
		handTwoName = new JLabel();
		this.frame = frame;
		bettingRound = 0;
		this.background = new ImageIcon(background).getImage();
		this.makeButtons();
		disableButtons();
	}

	/**
	 * This method adds the player cards to the screen and resets betting amounts need for the system to work.
	 * @param player1Card1 - the first card of the first player
	 * @param player1Card2 - the second card of the first player
	 * @param player2Card1 - the first card of the second player
	 * @param player2Card2 - the second card of the second player
	 */
	public void addPlayerCards(String player1Card1, String player1Card2, String player2Card1, String player2Card2) {
		
		makePlayerCards(player1Card1, player1Card2, player2Card1, player2Card2);
		bettingIsOver = false;
		if (frame.getDealer() == 1) {
			stack1.takeBigBlind(pot);
			chipsBet1 = stack1.getBlind();
			stack2.takeSmallBlind(pot);
			chipsBet2 = stack1.getBlind()/2;
			turn = 2;
		} else {
			stack2.takeBigBlind(pot);
			chipsBet2 = stack1.getBlind();
			stack1.takeSmallBlind(pot);
			chipsBet1 = stack1.getBlind()/2;
			turn = 1;
		}
	}
	
	/**
	 * This method adds the first three community cards to the screen and resets betting amounts need for the system to work.
	 * @param commCard1 - the first community card
	 * @param commCard2 - the second community card
	 * @param commCard3 - the third community card
	 */
	public void addFirstCommCards(String commCard1, String commCard2, String commCard3) {
		bettingRound = 1;
		this.commCard1 = new ImageIcon(commCard1).getImage();
		this.commCard2 = new ImageIcon(commCard2).getImage();
		this.commCard3 = new ImageIcon(commCard3).getImage();
		chipsBet1 = 0;
		chipsBet2 = 0;
		bettingIsOver = false;
		if (frame.getDealer() == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}
	
	/**
	 * This method adds the fourth community card to the screen and resets betting amounts need for the system to work.
	 * @param commCard4 - the fourth community card
	 */
	public void addFourthCommCard(String commCard4) {
		bettingRound = 2;
		this.commCard4 = new ImageIcon(commCard4).getImage();
		chipsBet1 = 0;
		chipsBet2 = 0;
		bettingIsOver = false;
		if (frame.getDealer() == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}
	
	/**
	 * This method adds the fifth community card to the screen and resets betting amounts need for the system to work.
	 * @param commCard4 - the fifth community card
	 */
	public void addFifthCommCard(String commCard5) {
		bettingRound = 3;
		this.commCard5 = new ImageIcon(commCard5).getImage();
		chipsBet1 = 0;
		chipsBet2 = 0;
		bettingIsOver = false;
		if (frame.getDealer() == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}
	
	/**
	 * This method takes the cards off the screen so that new hands can be played with different cards.
	 */
	public void resetPanel() {
		bettingRound = 0;
		bettingIsOver = false;
		if (frame.getDealer() == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
		player1Card1 = null;
		player1Card2 = null;
		player2Card1 = null;
		player2Card2 = null;
		commCard1 = null;
		commCard2 = null;
		commCard3 = null;
		commCard4 = null;
		commCard5 = null;
		repaint();
	}
	
	/**
	 * This screen is used to retrieve just the background of the table and display a message regarding blinds or
	 * the winner depending on what point the game is at.
	 */
	public void displayEndScreen() {
		if (stack2.getAmount() == 0) {
			 winner = new JLabel("Player 1 Wins");
		} else if (stack1.getAmount() == 0) {
			winner = new JLabel("Player 2 Wins");
		}
		this.background = new ImageIcon(background).getImage();
		player1Card1 = null;
		player1Card2 = null;
		player2Card1 = null;
		player2Card2 = null;
		commCard1 = null;
		commCard2 = null;
		commCard3 = null;
		commCard4 = null;
		commCard5 = null;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (background != null) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		}
		
		if (start != null && blind != null && blindLabel != null) {
			start.setBounds(625, 300, 200, 20);
			this.add(start);
			blind.setBounds(625, 260, 200, 20);
			this.add(blind);
			blindLabel.setBounds(625, 223, 200, 20);
			blindLabel.setOpaque(true);
			this.add(blindLabel);
		}
		
		if (betLabel != null) {
			betLabel.setBounds(625, 50, 200, 20);
			betLabel.setOpaque(true);
			this.add(betLabel);
		}
		
		if (stack1 != null && stack2 != null && pot != null) {
			makeStackLabels();
			amount1.setBounds(75, 200, 175, 75);
			amount1.setOpaque(true);
			this.add(amount1);
			amount2.setBounds(1220, 200, 175, 75);
			amount2.setOpaque(true);
			this.add(amount2);
			amount3.setBounds(625, 223, 200, 20);
			amount3.setOpaque(true);
			this.add(amount3);
		}
		
		if (frame.getFirstHandName() != null && frame.getSecondHandName() != null) {
			
			handOneName.setText(frame.getFirstHandName());
			handTwoName.setText(frame.getSecondHandName());
			if (bettingRound == 3 && bettingIsOver) {
				handOneName.setVisible(true);
				handTwoName.setVisible(true);
			} else {
				handOneName.setVisible(false);
				handTwoName.setVisible(false);
			}
			handOneName.setBounds(100, 800, 200, 20);
			handOneName.setOpaque(true);
			this.add(handOneName);
			handTwoName.setBounds(1165, 800, 200, 20);
			handTwoName.setOpaque(true);
			this.add(handTwoName);
			
		}
		
		if (player1Card1 != null && player1Card2 != null && player2Card1 != null && player2Card2 != null) {
			
			Image backCard = new ImageIcon("backcard.png").getImage();
			
			if (turn == 1) {
				g.drawImage(player1Card1, 75, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player1Card2, 205, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(backCard, 1140, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(backCard, 1270, 600, getWidth()/12, getHeight()/5, this);
			} else {
				g.drawImage(backCard, 75, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(backCard, 205, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player2Card1, 1140, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player2Card2, 1270, 600, getWidth()/12, getHeight()/5, this);
			}
			
			if (bettingRound == 3 && bettingIsOver()) {
				g.drawImage(player1Card1, 75, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player1Card2, 205, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player2Card1, 1140, 600, getWidth()/12, getHeight()/5, this);
				g.drawImage(player2Card2, 1270, 600, getWidth()/12, getHeight()/5, this);
				
				if (frame.getWinLight() == 1) {
					amount1.setBackground(Color.yellow);
				} else if (frame.getWinLight() == 2) {
					amount2.setBackground(Color.yellow);
				} else if (frame.getWinLight() == 3) {
					amount1.setBackground(Color.yellow);
					amount2.setBackground(Color.yellow);
				}
				
			}
		
		}
		
		if (commCard1 != null && commCard2 != null && commCard3 != null) {
			g.drawImage(commCard1, 400, 320, getWidth()/12, getHeight()/5, this);
			g.drawImage(commCard2, 533, 320, getWidth()/12, getHeight()/5, this);
			g.drawImage(commCard3, 665, 320, getWidth()/12, getHeight()/5, this);
		}
		
		if (commCard4 != null) {
			g.drawImage(commCard4, 800, 320, getWidth()/12, getHeight()/5, this);
		}
		
		if (commCard5 != null) {
			g.drawImage(commCard5, 933, 320, getWidth()/12, getHeight()/5, this);
		}
		
		check1.setBounds(75, 500, 75, 75);
		this.add(check1);
		fold1.setBounds(75, 400, 75, 75);
		this.add(fold1);
		bet1.setBounds(175, 400, 75, 75);
		this.add(bet1);
		call1.setBounds(175, 500, 75, 75);
		this.add(call1);	
		
		check2.setBounds(1320, 500, 75, 75);
		this.add(check2);
		fold2.setBounds(1320, 400, 75, 75);
		this.add(fold2);
		bet2.setBounds(1220, 400, 75, 75);
		this.add(bet2);
		call2.setBounds(1220, 500, 75, 75);
		this.add(call2);	
		
		if (turn == 1) {
			enableButtons();
			disableTwoButtons();
		} else if (turn == 2) {
			enableButtons();
			disableOneButtons();
		}
		
		if (winner != null) {
			winner.setBounds(625, 300, 200, 20);
			winner.setOpaque(true);
			winner.setBackground(Color.YELLOW);
			this.add(winner);
			disableButtons();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object click = e.getSource();
		
		if (click == start) {
			
			try {
				
				double desiredBlind = (double)blind.getValue();
				stack1 = new PokerStack(desiredBlind);
				stack2 = new PokerStack(desiredBlind);
				pot = new PokerStack(0);
				this.remove(start);
				this.remove(blind);
				this.remove(blindLabel);
				this.remove(winner);
				start = null;
				blind = null;
				blindLabel = null;
				winner = null;
				repaint();
				new Thread(frame::startGame).start();
				
			} catch (Exception ex) {
				
			}
			
		} else if (click == bet1 || click == bet2) {
			
			betLabel = new JLabel();
			
			if (turn == 1) {
				betSlider = new JSlider((int)chipsBet2, (int)stack1.getAmount(), (int)chipsBet2);
				betSlider.setMajorTickSpacing((int)stack1.getAmount()/10);
				betSlider.setMinorTickSpacing((int)stack1.getBlind());
			} else {
				betSlider = new JSlider((int)chipsBet1, (int)stack2.getAmount(), (int)chipsBet1);
				betSlider.setMajorTickSpacing((int)stack2.getAmount()/10);
				betSlider.setMinorTickSpacing((int)stack2.getBlind());
			}
			betSlider.setPaintTicks(true);
			betSlider.setPaintLabels(true);
			betSlider.setOpaque(true);
			betSlider.addChangeListener(this);
			betSlider.setToolTipText("Use the slider to size bet. Must be at least amount of big blind or greater than or equal to opponent's bet.");
			
			betSlider.setBounds(75, 100, 1323, 50);
			this.add(betSlider);
			disableButtons();
			repaint();
			
		} else if (click == check1) {
			
			if (chipsBet1 == chipsBet2 && frame.getDealer() == 1) {
				bettingIsOver = true;
				repaint();
			} else if (chipsBet1 == chipsBet2) {
				turn = 2;
				repaint();
			}
			
		} else if (click == check2){
			
			if (chipsBet1 == chipsBet2 && frame.getDealer() == 2) {
				bettingIsOver = true;
				repaint();
			} else if (chipsBet1 == chipsBet2) {
				turn = 1;
				repaint();
			}
			
		} else if (click == call1) {
			
			if (chipsBet2 != 0) {
				
				if (chipsBet2 >= stack1.getAmount() || stack2.getAmount() == 0) { // for having less than the previous bet
					chipsBet1 += stack1.getAmount();
					stack1.allIn(pot);
					if (chipsBet2 - chipsBet1 < 0) {
						pot.bet(chipsBet1 - chipsBet2, stack1);
					} else {
						pot.bet(chipsBet2 - chipsBet1, stack2);
					}
					bettingIsOver = true;
				} else if (chipsBet1 == stack1.getBlind() && chipsBet2 == stack1.getBlind()) { //in case one more check is necessary
					check1.doClick();
				} else if (chipsBet1 == stack1.getBlind()/2) { // in case of first round of betting
					stack1.bet(stack1.getBlind()/2, pot);
					chipsBet1 = stack1.getBlind();
				} else {//most cases
					stack1.bet(chipsBet2-chipsBet1, pot);
					bettingIsOver = true;
				}
				
				turn = 2;
				repaint();
				
			}
			
			
		} else if (click == call2) {
			
			if (chipsBet1 != 0) {
				
				if (chipsBet1 >= stack2.getAmount() || stack1.getAmount() == 0) { // for having less than the previous bet
					chipsBet2 += stack2.getAmount();
					stack2.allIn(pot);
					if (chipsBet1 - chipsBet2 < 0) {
						pot.bet(chipsBet2 - chipsBet1, stack2);
					} else {
						pot.bet(chipsBet1 - chipsBet2, stack1);
					}
					bettingIsOver = true;
				} else if (chipsBet2 == stack1.getBlind() && chipsBet1 == stack1.getBlind()) { //in case one more check is necessary
					check2.doClick();
				} else if (chipsBet2 == stack1.getBlind()/2) { // in case of first round of betting
					stack2.bet(stack1.getBlind()/2, pot);
					chipsBet2 = stack1.getBlind();
				} else {//most cases
					stack2.bet(chipsBet1-chipsBet2, pot);
					bettingIsOver = true;
				}
				
				turn = 1;
				repaint();
				
			}
			
		} else if (click == fold1) {
			
			pot.bet(pot.getAmount(), stack2);
			bettingIsOver = true;
			repaint();
			
		} else if (click == fold2) {
			
			pot.bet(pot.getAmount(), stack1);
			bettingIsOver = true;
			repaint();
			
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		JSlider slider = (JSlider)e.getSource();
		
		if (betLabel != null) {
            int value = slider.getValue();
            betLabel.setText("Bet: " + value);
        }
		
		if (!slider.getValueIsAdjusting()) {
			
			int value = (int)slider.getValue();
			
			if (turn == 1) {
				
				//for raising at least a minimum raise
				if (((value > (chipsBet2*2)) && value >= stack1.getBlind())|| value == stack1.getAmount()) {
					
					
					
					if (value != stack1.getAmount()) {
						//taking into account what has already been put in
						if (chipsBet1 == stack1.getBlind()) {//in case its on big blind
							stack1.bet(value-stack1.getBlind(), pot);
							chipsBet1 = value;
						} else if (chipsBet1 == stack1.getBlind()/2) {//in case its on small blind
							stack1.bet(value-(stack1.getBlind()/2), pot);
							chipsBet1 = value;
						} else {//most cases
							stack1.bet(value - chipsBet1, pot);
							chipsBet1 = value;
						}
					} 
					//in case you go all in
					else {
						stack1.bet(value, pot);
						chipsBet1 += value;
					}
					
					
					
					turn = 2;
					this.remove(betSlider);
					this.remove(betLabel);
					betLabel = null;
					repaint();
					
				} else if (value == chipsBet2){
					
					this.remove(betSlider);
					this.remove(betLabel);
					betLabel = null;
					call1.doClick();
					
				}
				
			} else {
				
				//for raising at least a minimum raise
				if (((value > (chipsBet1*2)) && value >= stack1.getBlind()) || value == stack2.getAmount()) {
					
					
					
					if (value != stack2.getAmount()) {
						//taking into account what has already been put in
						if (chipsBet2 == stack1.getBlind()) {//in case its on big blind
							stack2.bet(value-stack1.getBlind(), pot);
							chipsBet2 = value;
						} else if (chipsBet2 == stack1.getBlind()/2) {//in case its on small blind
							stack2.bet(value-(stack1.getBlind()/2), pot);
							chipsBet2 = value;
						} else {//most cases
							stack2.bet(value - chipsBet2, pot);
							chipsBet2 = value;
						}
					} 
					//in case you go all in
					else {
						stack2.bet(value, pot);
						chipsBet2 += value;
					}
					
					
					
					turn = 1;
					this.remove(betSlider);
					this.remove(betLabel);
					betLabel = null;
					repaint();
					
				} else if (value == chipsBet1) {
					
					this.remove(betSlider);
					this.remove(betLabel);
					betLabel = null;
					call2.doClick();
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * This method is used by the frame to see if another hand should be played or if the game is over.
	 * @return a boolean regarding if stack 1 has 0 chips
	 */
	public boolean stack1IsEmpty() {
		if (stack1.getAmount() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used by the frame to see if another hand should be played or if the game is over.
	 * @return a boolean regarding if stack 2 has 0 chips
	 */
	public boolean stack2IsEmpty() {
		if (stack2.getAmount() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used by the frame to see if another hand should be played or if the game is over.
	 * @return a boolean regarding if the pot has 0 chips
	 */
	public boolean potIsEmpty() {
		if (pot.getAmount() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used in the poker frame to pay stack1 if it corresponding hand wins after getting to the river.
	 */
	public void payFirstStack() {
		pot.bet(pot.getAmount(), stack1);
	}
	
	/**
	 * This method is used in the poker frame to pay stack2 if it corresponding hand wins after getting to the river.
	 */
	public void paySecondStack() {
		pot.bet(pot.getAmount(), stack2);
	}
	
	/**
	 * This method is used in the poker frame to split the pot after getting to the river.
	 */
	public void payBothStacks() {
		pot.splitPot(stack1, stack2);
	}
	
	/**
	 * This method is used in the frame to see what betting round it is.
	 * @return an integer regarding what betting round it is
	 */
	public int getBettingRound() {
		return bettingRound;
	}
	
	/**
	 * This method retrieves what player's turn it is to bet.
	 * @return an integer regarding what player's turn it is to bet
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * This method is used is the poker frame class to see if a betting round is over or not and the frame is ready to  deal
	 * the next cards.
	 * @return a boolean regarding if the betting round is over
	 */
	public boolean bettingIsOver() {
		return bettingIsOver;
	}
	
	/**
	 * This method is used once in the frame for when someone goes all in and both player's cards need to be shown.
	 * @param over - a boolean regarding what bettingIsOver should be set to
	 */
	public void setBettingIsOver(boolean over) {
		bettingIsOver = over;
	}
	
	/**
	 * This method is used as a shortcut for getting a displaying the player's cards on to the screen.
	 * @param player1Card1 - the first card of the first player
	 * @param player1Card2 - the second card of the first player
	 * @param player2Card1 - the first card of the second player
	 * @param player2Card2 - the second card of the second player
	 */
	private void makePlayerCards(String player1Card1, String player1Card2, String player2Card1, String player2Card2) {
		this.player1Card1 = new ImageIcon(player1Card1).getImage();
		this.player1Card2 = new ImageIcon(player1Card2).getImage();
		this.player2Card1 = new ImageIcon(player2Card1).getImage();
		this.player2Card2 = new ImageIcon(player2Card2).getImage();
	}
	
	/**
	 * This method instantiates the playing buttons so that they can be painted onto the panel.
	 */
	private void makeButtons() {
		check1 = new JButton("Check");
		check1.addActionListener(this);
		check2 = new JButton("Check");
		check2.addActionListener(this);
		bet1 = new JButton("Bet");
		bet1.addActionListener(this);
		bet2 = new JButton("Bet");
		bet2.addActionListener(this);
		call1 = new JButton("Call");
		call1.addActionListener(this);
		call2 = new JButton("Call");
		call2.addActionListener(this);
		fold1 = new JButton("Fold");
		fold1.addActionListener(this);
		fold2 = new JButton("Fold");
		fold2.addActionListener(this);
	}
	
	/**
	 * This method instantiates chip amount labels.
	 */
	private void makeStackLabels() {
		if (amount1 != null && amount2 != null && amount3 != null) {
			this.remove(amount1);
			this.remove(amount2);
			this.remove(amount3);
		}
		amount1 = new JLabel("Chips: " + stack1.getAmount());
		amount2 = new JLabel("Chips: " + stack2.getAmount());
		amount3 = new JLabel("Pot: " + pot.getAmount());
	}
	
	/**
	 * This method is a shortcut for creating the text field that the blind will be typed in prior to starting the game.
	 */
	private void makeBlindTextField() {
		NumberFormat format = DecimalFormat.getNumberInstance();
		format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        formatter.setMinimum(0.1);
        blind = new JFormattedTextField(formatter);
        blind.setColumns(10);
	}
	/**
	 * This method is used to disable the first player's betting buttons.
	 */
	private void disableOneButtons() {
		check1.setEnabled(false);
		bet1.setEnabled(false);
		call1.setEnabled(false);
		fold1.setEnabled(false);
	}
	
	/**
	 * This method is used to disable the second player's betting buttons.
	 */
	private void disableTwoButtons() {
		check2.setEnabled(false);
		bet2.setEnabled(false);
		call2.setEnabled(false);
		fold2.setEnabled(false);
	}
	
	/**
	 * This method is used to disable the other betting buttons when picking a bet amount with the slider.
	 */
	private void disableButtons() {
		check1.setEnabled(false);
		check2.setEnabled(false);
		bet1.setEnabled(false);
		bet2.setEnabled(false);
		call1.setEnabled(false);
		call2.setEnabled(false);
		fold1.setEnabled(false);
		fold2.setEnabled(false);
	}
	
	/**
	 * This method is used to enable the other betting buttons when picking a bet amount with the slider.
	 */
	private void enableButtons() {
		check1.setEnabled(true);
		check2.setEnabled(true);
		bet1.setEnabled(true);
		bet2.setEnabled(true);
		call1.setEnabled(true);
		call2.setEnabled(true);
		fold1.setEnabled(true);
		fold2.setEnabled(true);
	}
	
	private static final long serialVersionUID = 1L;
	
}
