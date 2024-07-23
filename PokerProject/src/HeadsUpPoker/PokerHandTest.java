package HeadsUpPoker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PokerHandTest {

	@Test
	void testHasPair() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		
		//testing if pair is held by both player cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = twoOfSpades;
		commCards1[1] = threeOfHearts;
		commCards1[2] = fourOfDiamonds;
		commCards1[3] = fiveOfClubs;
		commCards1[4] = sixOfSpades;
		
		PokerHand hand1 = new PokerHand(aceOfSpades, aceOfClubs, commCards1);
		assertTrue(hand1.hasPair());
		
		//testing if pair is held between player cards and community cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = twoOfSpades;
		commCards2[1] = threeOfHearts;
		commCards2[2] = fourOfDiamonds;
		commCards2[3] = fiveOfClubs;
		commCards2[4] = aceOfClubs;
		
		PokerHand hand2 = new PokerHand(aceOfSpades, sixOfSpades, commCards2);
		assertTrue(hand2.hasPair());
		
		//testing if pair is in community cards
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = twoOfSpades;
		commCards3[1] = threeOfHearts;
		commCards3[2] = aceOfSpades;
		commCards3[3] = aceOfClubs;
		commCards3[4] = sevenOfHearts;
		
		PokerHand hand3 = new PokerHand(fiveOfClubs, fourOfDiamonds, commCards3);
		assertTrue(hand3.hasPair());
		
		//testing for no pair
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = twoOfSpades;
		commCards4[1] = threeOfHearts;
		commCards4[2] = fourOfDiamonds;
		commCards4[3] = fiveOfClubs;
		commCards4[4] = sixOfSpades;
		
		PokerHand hand4 = new PokerHand(aceOfSpades, sevenOfHearts, commCards4);
		assertFalse(hand4.hasPair());
		
	}
	
	@Test
	void testHasTwoPair() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		
		//testing if pairs are held between both player cards and community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = twoOfSpades;
		commCards1[1] = aceOfSpades;
		commCards1[2] = fourOfDiamonds;
		commCards1[3] = fiveOfClubs;
		commCards1[4] = sixOfSpades;
		
		PokerHand hand1 = new PokerHand(aceOfClubs, twoOfDiamonds, commCards1);
		assertTrue(hand1.hasTwoPair());
		
		//testing if there is a pocket pair and a community pair
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = twoOfSpades;
		commCards2[1] = twoOfDiamonds;
		commCards2[2] = fourOfDiamonds;
		commCards2[3] = fiveOfClubs;
		commCards2[4] = sixOfSpades;
				
		PokerHand hand2 = new PokerHand(aceOfClubs, aceOfSpades, commCards2);
		assertTrue(hand2.hasTwoPair());
		
		//testing if there are two community pairs
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = twoOfSpades;
		commCards3[1] = twoOfDiamonds;
		commCards3[2] = aceOfSpades;
		commCards3[3] = aceOfClubs;
		commCards3[4] = sixOfSpades;
						
		PokerHand hand3 = new PokerHand(fourOfDiamonds, fiveOfClubs, commCards3);
		assertTrue(hand3.hasTwoPair());
		
		//testing if there is only one pair
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = twoOfSpades;
		commCards4[1] = twoOfDiamonds;
		commCards4[2] = threeOfHearts;
		commCards4[3] = aceOfClubs;
		commCards4[4] = sixOfSpades;
								
		PokerHand hand4 = new PokerHand(fourOfDiamonds, fiveOfClubs, commCards4);
		assertFalse(hand4.hasTwoPair());
		
	}
	
	@Test
	void testHasSet() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard twoOfHearts = new PlayingCard(2, Suit.HEARTS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		
		//testing two thirds of set is in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = twoOfSpades;
		commCards1[1] = twoOfDiamonds;
		commCards1[2] = threeOfHearts;
		commCards1[3] = aceOfClubs;
		commCards1[4] = sixOfSpades;
								
		PokerHand hand1 = new PokerHand(twoOfHearts, fiveOfClubs, commCards1);
		assertTrue(hand1.hasSet());
		
		//testing if two thirds of set are player cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = fourOfDiamonds;
		commCards2[1] = twoOfDiamonds;
		commCards2[2] = threeOfHearts;
		commCards2[3] = aceOfHearts;
		commCards2[4] = sixOfSpades;
								
		PokerHand hand2 = new PokerHand(aceOfSpades, aceOfClubs, commCards2);
		assertTrue(hand2.hasSet());
		
		//testing if set is in community cards
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = aceOfSpades;
		commCards3[1] = aceOfClubs;
		commCards3[2] = threeOfHearts;
		commCards3[3] = aceOfHearts;
		commCards3[4] = sixOfSpades;
								
		PokerHand hand3 = new PokerHand(twoOfDiamonds, fiveOfClubs, commCards3);
		assertTrue(hand3.hasSet());
		
		//testing for no set
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = aceOfSpades;
		commCards4[1] = twoOfDiamonds;
		commCards4[2] = threeOfHearts;
		commCards4[3] = fourOfDiamonds;
		commCards4[4] = sixOfSpades;
								
		PokerHand hand4 = new PokerHand(fiveOfClubs, sevenOfSpades, commCards4);
		assertFalse(hand4.hasSet());
		
		//testing for no set but one pair
		PlayingCard[] commCards5 = new PlayingCard[5];
		commCards5[0] = aceOfSpades;
		commCards5[1] = aceOfClubs;
		commCards5[2] = threeOfHearts;
		commCards5[3] = sevenOfSpades;
		commCards5[4] = sixOfSpades;
								
		PokerHand hand5 = new PokerHand(twoOfDiamonds, fiveOfClubs, commCards5);
		assertFalse(hand5.hasSet());
		
		//testing for no set but two pair
		PlayingCard[] commCards6 = new PlayingCard[5];
		commCards6[0] = aceOfSpades;
		commCards6[1] = aceOfClubs;
		commCards6[2] = threeOfHearts;
		commCards6[3] = sevenOfSpades;
		commCards6[4] = twoOfHearts;
								
		PokerHand hand6 = new PokerHand(twoOfDiamonds, fiveOfClubs, commCards6);
		assertFalse(hand6.hasSet());
		
	}
	
	@Test
	void testHasStraight() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard tenOfClubs = new PlayingCard(10, Suit.CLUBS);
		PlayingCard jackOfHearts = new PlayingCard(11, Suit.HEARTS);
		PlayingCard queenOfHearts = new PlayingCard(12, Suit.HEARTS);
		PlayingCard kingOfDiamonds = new PlayingCard(13, Suit.DIAMONDS);
		
		
		//testing if straight is in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = twoOfSpades;
		commCards1[2] = threeOfHearts;
		commCards1[3] = fourOfDiamonds;
		commCards1[4] = fiveOfClubs;
		
		PokerHand hand1 = new PokerHand(aceOfClubs, aceOfHearts, commCards1);
		assertTrue(hand1.hasStraight());
		
		//testing if straight is in community cards and player cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = twoOfSpades;
		commCards2[2] = threeOfHearts;
		commCards2[3] = aceOfClubs;
		commCards2[4] = fiveOfClubs;
				
		PokerHand hand2 = new PokerHand(fourOfDiamonds, sixOfSpades, commCards2);
		assertTrue(hand2.hasStraight());
		
		//testing if straight is in community cards and player cards
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = aceOfSpades;
		commCards3[1] = tenOfClubs;
		commCards3[2] = jackOfHearts;
		commCards3[3] = queenOfHearts;
		commCards3[4] = kingOfDiamonds;
						
		PokerHand hand3 = new PokerHand(fourOfDiamonds, sixOfSpades, commCards3);
		assertTrue(hand3.hasStraight());
		
		//testing for no straight
		PlayingCard[] commCards6 = new PlayingCard[5];
		commCards6[0] = aceOfSpades;
		commCards6[1] = aceOfClubs;
		commCards6[2] = threeOfHearts;
		commCards6[3] = kingOfDiamonds;
		commCards6[4] = queenOfHearts;
										
		PokerHand hand6 = new PokerHand(fourOfDiamonds, fiveOfClubs, commCards6);
		assertFalse(hand6.hasStraight());
		
		//test for straight draw
		PlayingCard[] commCards7 = new PlayingCard[5];
		commCards7[0] = aceOfSpades;
		commCards7[1] = aceOfClubs;
		commCards7[2] = threeOfHearts;
		commCards7[3] = sixOfSpades;
		commCards7[4] = queenOfHearts;
										
		PokerHand hand7 = new PokerHand(fourOfDiamonds, fiveOfClubs, commCards7);
		assertFalse(hand7.hasStraight());
		
	}
	
	@Test
	void testHasFlush() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		
		//testing if all five are in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = sevenOfSpades;
		commCards1[2] = sixOfSpades;
		commCards1[3] = twoOfSpades;
		commCards1[4] = threeOfSpades;
		
		PokerHand hand1 = new PokerHand(aceOfClubs, twoOfDiamonds, commCards1);
		assertTrue(hand1.hasFlush());
		
		//testing if two are in player cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfClubs;
		commCards2[1] = twoOfDiamonds;
		commCards2[2] = sixOfSpades;
		commCards2[3] = twoOfSpades;
		commCards2[4] = threeOfSpades;
		
		PokerHand hand2 = new PokerHand(aceOfSpades, sevenOfSpades, commCards2);
		assertTrue(hand2.hasFlush());
		
		//testing if one is in player cards
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = sevenOfSpades;
		commCards3[1] = twoOfDiamonds;
		commCards3[2] = sixOfSpades;
		commCards3[3] = twoOfSpades;
		commCards3[4] = threeOfSpades;
				
		PokerHand hand3 = new PokerHand(aceOfSpades, aceOfClubs, commCards3);
		assertTrue(hand3.hasFlush());
		
		//testing for no flush
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = sevenOfSpades;
		commCards4[1] = twoOfDiamonds;
		commCards4[2] = sixOfSpades;
		commCards4[3] = twoOfSpades;
		commCards4[4] = aceOfHearts;
				
		PokerHand hand4 = new PokerHand(aceOfSpades, aceOfClubs, commCards4);
		assertFalse(hand4.hasFlush());
		
	}
	
	@Test
	void testHasFullHouse() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard twoOfHearts = new PlayingCard(2, Suit.HEARTS);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		
		//testing if boat is in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = twoOfSpades;
		commCards1[3] = twoOfDiamonds;
		commCards1[4] = twoOfHearts;
		
		PokerHand hand1 = new PokerHand(threeOfHearts, fourOfDiamonds, commCards1);
		assertTrue(hand1.hasFullHouse());
		
		//testing if pair is in player cards and set in in community cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = fiveOfClubs;
		commCards2[1] = sixOfSpades;
		commCards2[2] = twoOfSpades;
		commCards2[3] = twoOfDiamonds;
		commCards2[4] = twoOfHearts;
				
		PokerHand hand2 = new PokerHand(aceOfSpades, aceOfClubs, commCards2);
		assertTrue(hand2.hasFullHouse());
		
		//testing if set is in player cards and pair in in community cards
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = fiveOfClubs;
		commCards3[1] = sixOfSpades;
		commCards3[2] = aceOfSpades;
		commCards3[3] = aceOfClubs;
		commCards3[4] = twoOfHearts;
						
		PokerHand hand3 = new PokerHand(twoOfSpades, twoOfDiamonds, commCards3);
		assertTrue(hand3.hasFullHouse());
		
		//testing if one card is in player cards
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = twoOfDiamonds;
		commCards4[1] = sixOfSpades;
		commCards4[2] = aceOfSpades;
		commCards4[3] = aceOfClubs;
		commCards4[4] = twoOfHearts;
								
		PokerHand hand4 = new PokerHand(twoOfSpades, fiveOfClubs, commCards4);
		assertTrue(hand4.hasFullHouse());
		
		//testing for no boat
		PlayingCard[] commCards5 = new PlayingCard[5];
		commCards5[0] = twoOfDiamonds;
		commCards5[1] = sixOfSpades;
		commCards5[2] = aceOfSpades;
		commCards5[3] = aceOfClubs;
		commCards5[4] = twoOfHearts;
										
		PokerHand hand5 = new PokerHand(sevenOfHearts, fiveOfClubs, commCards5);
		assertFalse(hand5.hasFullHouse());
		
		//testing for no boat with a set though
		PlayingCard[] commCards6 = new PlayingCard[5];
		commCards6[0] = fiveOfClubs;
		commCards6[1] = sixOfSpades;
		commCards6[2] = twoOfSpades;
		commCards6[3] = twoOfDiamonds;
		commCards6[4] = twoOfHearts;
						
		PokerHand hand6 = new PokerHand(sevenOfHearts, aceOfClubs, commCards6);
		assertFalse(hand6.hasFullHouse());
		
		PlayingCard[] commCards7 = new PlayingCard[5];
		commCards7[0] = aceOfClubs;
		commCards7[1] = aceOfHearts;
		commCards7[2] = sixOfSpades;
		commCards7[3] = fiveOfClubs;
		commCards7[4] = twoOfSpades;
				
		PokerHand hand7 = new PokerHand(sevenOfHearts, aceOfSpades, commCards7);
		assertFalse(hand7.hasFullHouse());
		
	}
	
	@Test
	void testHasFourOfAKind() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard twoOfHearts = new PlayingCard(2, Suit.HEARTS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard aceOfDiamonds = new PlayingCard(14, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		
		//testing if all four are in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = aceOfHearts;
		commCards1[3] = aceOfDiamonds;
		commCards1[4] = twoOfHearts;
												
		PokerHand hand1 = new PokerHand(twoOfDiamonds, fiveOfClubs, commCards1);
		assertTrue(hand1.hasFourOfAKind());
		
		//testing if half are in player cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = twoOfDiamonds;
		commCards2[1] = fiveOfClubs;
		commCards2[2] = aceOfHearts;
		commCards2[3] = aceOfDiamonds;
		commCards2[4] = twoOfHearts;
														
		PokerHand hand2 = new PokerHand(aceOfSpades, aceOfClubs, commCards2);
		assertTrue(hand2.hasFourOfAKind());
		
		//testing for no four of a kind
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = twoOfDiamonds;
		commCards3[1] = fiveOfClubs;
		commCards3[2] = twoOfSpades;
		commCards3[3] = aceOfDiamonds;
		commCards3[4] = twoOfHearts;
																
		PokerHand hand3 = new PokerHand(aceOfSpades, aceOfClubs, commCards3);
		assertFalse(hand3.hasFourOfAKind());
//		assertTrue(hand3.hasFullHouse());
		
	}
	
	@Test
	void testHasStraightFlush() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard tenOfSpades = new PlayingCard(10, Suit.SPADES);
		PlayingCard jackOfSpades = new PlayingCard(11, Suit.SPADES);
		PlayingCard queenOfSpades = new PlayingCard(12, Suit.SPADES);
		PlayingCard kingOfSpades = new PlayingCard(13, Suit.SPADES);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		
		//testing for all five in community cards
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = kingOfSpades;
		commCards1[2] = queenOfSpades;
		commCards1[3] = jackOfSpades;
		commCards1[4] = tenOfSpades;
		
		PokerHand hand1 = new PokerHand(fiveOfClubs, sevenOfHearts, commCards1);
		assertTrue(hand1.hasStraightFlush());
		
		//testing for half in player cards
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = fiveOfClubs;
		commCards2[1] = sevenOfHearts;
		commCards2[2] = threeOfSpades;
		commCards2[3] = fourOfSpades;
		commCards2[4] = fiveOfSpades;
		
		PokerHand hand2 = new PokerHand(aceOfSpades, twoOfSpades, commCards2);
		assertTrue(hand2.hasStraightFlush());
		
		//testing for straight flush draw
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = threeOfSpades;
		commCards4[1] = fourOfSpades;
		commCards4[2] = fiveOfSpades;
		commCards4[3] = sevenOfHearts;
		commCards4[4] = jackOfSpades;
		
		PokerHand hand4 = new PokerHand(aceOfSpades, tenOfSpades, commCards4);
		assertFalse(hand4.hasStraightFlush());
		
		//testing for no straight flush
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = fiveOfClubs;
		commCards3[1] = sevenOfHearts;
		commCards3[2] = threeOfSpades;
		commCards3[3] = fourOfSpades;
		commCards3[4] = fiveOfSpades;
				
		PokerHand hand3 = new PokerHand(aceOfSpades, kingOfSpades, commCards3);
		assertFalse(hand3.hasStraightFlush());
		
	}
	
	@Test
	void getHandRankTest() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard aceOfDiamonds = new PlayingCard(14, Suit.DIAMONDS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		
		//testing pair rank
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = sixOfSpades;
		commCards1[1] = threeOfHearts;
		commCards1[2] = fourOfDiamonds;
		commCards1[3] = fiveOfClubs;
		commCards1[4] = eightOfHearts;
		
		PokerHand hand1 = new PokerHand(aceOfSpades, aceOfClubs, commCards1);
		assertEquals(1, hand1.getHandRank());
		
		//testing two pair rank
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = sixOfSpades;
		commCards2[1] = twoOfSpades;
		commCards2[2] = twoOfSpades;
		commCards2[3] = fiveOfClubs;
		commCards2[4] = sevenOfHearts;
				
		PokerHand hand2 = new PokerHand(aceOfSpades, aceOfClubs, commCards2);
		assertEquals(2, hand2.getHandRank());
		
		//testing set rank
		PlayingCard[] commCards3 = new PlayingCard[5];
		commCards3[0] = sixOfSpades;
		commCards3[1] = aceOfHearts;
		commCards3[2] = twoOfSpades;
		commCards3[3] = fiveOfClubs;
		commCards3[4] = sevenOfHearts;
				
		PokerHand hand3 = new PokerHand(aceOfSpades, aceOfClubs, commCards3);
		assertEquals(3, hand3.getHandRank());
		
		//testing straight rank
		PlayingCard[] commCards4 = new PlayingCard[5];
		commCards4[0] = threeOfHearts;
		commCards4[1] = aceOfHearts;
		commCards4[2] = twoOfSpades;
		commCards4[3] = fiveOfClubs;
		commCards4[4] = sevenOfHearts;
						
		PokerHand hand4 = new PokerHand(fourOfDiamonds, aceOfClubs, commCards4);
		assertEquals(4, hand4.getHandRank());
		
		//testing flush rank
		PlayingCard[] commCards5 = new PlayingCard[5];
		commCards5[0] = sixOfSpades;
		commCards5[1] = aceOfHearts;
		commCards5[2] = twoOfSpades;
		commCards5[3] = fiveOfClubs;
		commCards5[4] = sixOfSpades;
						
		PokerHand hand5 = new PokerHand(aceOfSpades, twoOfSpades, commCards5);
		assertEquals(5, hand5.getHandRank());
		
		//testing full house rank
		PlayingCard[] commCards6 = new PlayingCard[5];
		commCards6[0] = twoOfClubs;
		commCards6[1] = aceOfHearts;
		commCards6[2] = twoOfSpades;
		commCards6[3] = fiveOfClubs;
		commCards6[4] = sevenOfHearts;
						
		PokerHand hand6 = new PokerHand(aceOfSpades, aceOfClubs, commCards6);
		assertEquals(6, hand6.getHandRank());
		
		//testing four of a kind rank
		PlayingCard[] commCards7 = new PlayingCard[5];
		commCards7[0] = sixOfSpades;
		commCards7[1] = aceOfHearts;
		commCards7[2] = twoOfSpades;
		commCards7[3] = fiveOfClubs;
		commCards7[4] = aceOfDiamonds;
						
		PokerHand hand7 = new PokerHand(aceOfSpades, aceOfClubs, commCards7);
		assertEquals(7, hand7.getHandRank());
		
		//testing four of a kind rank
		PlayingCard[] commCards8 = new PlayingCard[5];
		commCards8[0] = sixOfSpades;
		commCards8[1] = threeOfSpades;
		commCards8[2] = twoOfSpades;
		commCards8[3] = fiveOfSpades;
		commCards8[4] = aceOfDiamonds;
								
		PokerHand hand8 = new PokerHand(fourOfSpades, aceOfClubs, commCards8);
		assertEquals(8, hand8.getHandRank());
		
	}
	
	@Test
	void testTieBreakHighCard() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard sixOfClubs = new PlayingCard(6, Suit.CLUBS);
		PlayingCard threeOfClubs = new PlayingCard(3, Suit.CLUBS);
		PlayingCard fourOfHearts = new PlayingCard(4, Suit.HEARTS);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard jackOfClubs = new PlayingCard(11, Suit.CLUBS);
		PlayingCard eightOfDiamonds = new PlayingCard(8, Suit.DIAMONDS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		PlayingCard tenOfDiamonds = new PlayingCard(10, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = sixOfClubs;
		commCards1[1] = threeOfClubs;
		commCards1[2] = fourOfHearts;
		commCards1[3] = fiveOfSpades;
		commCards1[4] = eightOfDiamonds;
		PokerHand hcHand1 = new PokerHand(aceOfSpades, nineOfDiamonds, commCards1);
		PokerHand hcHand2 = new PokerHand(jackOfClubs, tenOfDiamonds, commCards1);
		
		assertEquals(hcHand1.getHandRank(), hcHand2.getHandRank());
		assertEquals(1, hcHand1.tieBreak(hcHand2));
		
	}
	
	@Test
	void testTieBreakPair() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard twoOfHearts = new PlayingCard(2, Suit.HEARTS);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fourOfHearts = new PlayingCard(4, Suit.HEARTS);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard sixOfHearts = new PlayingCard(6, Suit.HEARTS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		//testing pair breaks
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = fiveOfSpades;
		commCards1[3] = sevenOfSpades;
		commCards1[4] = sixOfHearts;
		PokerHand pairHand1 = new PokerHand(twoOfSpades, eightOfHearts, commCards1);
		PokerHand pairHand2 = new PokerHand(twoOfHearts, fourOfSpades, commCards1);
		PokerHand pairHand3 = new PokerHand(twoOfHearts, threeOfSpades, commCards1);
		
		assertEquals(pairHand1.getHandRank(), pairHand2.getHandRank());
		assertEquals(pairHand2.getRank(), pairHand3.getHandRank());
		assertEquals(1, pairHand1.tieBreak(pairHand2));
		assertEquals(0, pairHand2.tieBreak(pairHand3));
		assertEquals(-1, pairHand2.tieBreak(pairHand1));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = fourOfHearts;
		commCards2[1] = nineOfDiamonds;
		commCards2[2] = fiveOfSpades;
		commCards2[3] = sevenOfSpades;
		commCards2[4] = sixOfHearts;
		PokerHand pairHand4 = new PokerHand(twoOfSpades, twoOfClubs, commCards2);
		PokerHand pairHand5 = new PokerHand(aceOfSpades, aceOfClubs, commCards2);
		
		assertEquals(pairHand4.getHandRank(), pairHand5.getHandRank());
		assertEquals(-1, pairHand4.tieBreak(pairHand5));
		
	}
	
	@Test
	void testTieBreakSet() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard threeOfClubs = new PlayingCard(3, Suit.CLUBS);
		PlayingCard threeOfHearts = new PlayingCard(3, Suit.HEARTS);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sixOfHearts = new PlayingCard(6, Suit.HEARTS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfClubs = new PlayingCard(7, Suit.CLUBS);
		PlayingCard eightOfSpades = new PlayingCard(8, Suit.SPADES);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard eightOfDiamonds = new PlayingCard(8, Suit.DIAMONDS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = aceOfHearts;
		commCards1[3] = sixOfSpades;
		commCards1[4] = fiveOfSpades;
		PokerHand setHand1 = new PokerHand(sevenOfSpades, nineOfDiamonds, commCards1);
		PokerHand setHand2 = new PokerHand(eightOfSpades, sevenOfClubs, commCards1);
		PokerHand setHand3 = new PokerHand(twoOfSpades, threeOfSpades, commCards1);
		PokerHand setHand4 = new PokerHand(twoOfSpades, fourOfSpades, commCards1);
		
		assertEquals(setHand1.getHandRank(), setHand2.getHandRank());
		assertEquals(setHand3.getHandRank(), setHand4.getHandRank());
		assertEquals(-1, setHand2.tieBreak(setHand1));
		assertEquals(1, setHand1.tieBreak(setHand2));
		assertEquals(0, setHand3.tieBreak(setHand4));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = twoOfSpades;
		commCards2[2] = threeOfClubs;
		commCards2[3] = sixOfHearts;
		commCards2[4] = sevenOfSpades;
		PokerHand setHand5 = new PokerHand(twoOfClubs, eightOfDiamonds, commCards2);
		PokerHand setHand6 = new PokerHand(threeOfHearts, eightOfHearts, commCards2);
		
		assertEquals(setHand5.getHandRank(), setHand6.getHandRank());
		assertEquals(1, setHand6.tieBreak(setHand5));
		
	}
	
	@Test
	void testTieBreakQuads() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfDiamonds = new PlayingCard(14, Suit.DIAMONDS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fourOfClubs = new PlayingCard(4, Suit.CLUBS);
		PlayingCard fourOfHearts = new PlayingCard(4, Suit.HEARTS);
		PlayingCard fiveOfDiamonds = new PlayingCard(5, Suit.DIAMONDS);
		PlayingCard sixOfHearts = new PlayingCard(6, Suit.HEARTS);
		PlayingCard eightOfClubs = new PlayingCard(8, Suit.CLUBS);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard eightOfDiamonds = new PlayingCard(8, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = aceOfHearts;
		commCards1[3] = aceOfDiamonds;
		commCards1[4] = twoOfSpades;
		PokerHand quadHand1 = new PokerHand(eightOfClubs, twoOfClubs, commCards1);
		PokerHand quadHand2 = new PokerHand(eightOfHearts, fiveOfDiamonds, commCards1);
		
		assertEquals(quadHand1.getHandRank(), quadHand2.getHandRank());
		assertEquals(1, quadHand2.tieBreak(quadHand1));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = fourOfClubs;
		commCards2[2] = sixOfHearts;
		commCards2[3] = eightOfDiamonds;
		commCards2[4] = twoOfSpades;
		PokerHand quadHand3 = new PokerHand(aceOfHearts, aceOfHearts, commCards2);
		PokerHand quadHand4 = new PokerHand(fourOfSpades, fourOfHearts, commCards2);
		
		assertEquals(quadHand3.getHandRank(), quadHand4.getHandRank());
		assertEquals(1, quadHand3.tieBreak(quadHand4));
		
	}
	
	@Test
	void testTieBreakStraight() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfDiamonds = new PlayingCard(14, Suit.DIAMONDS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard threeOfClubs = new PlayingCard(3, Suit.CLUBS);
		PlayingCard fourOfHearts = new PlayingCard(4, Suit.HEARTS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard fiveOfDiamonds = new PlayingCard(5, Suit.DIAMONDS);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sixOfClubs = new PlayingCard(6, Suit.CLUBS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfDiamonds = new PlayingCard(7, Suit.DIAMONDS);
		PlayingCard eightOfClubs = new PlayingCard(8, Suit.CLUBS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = twoOfClubs;
		commCards1[2] = threeOfSpades;
		commCards1[3] = fourOfHearts;
		commCards1[4] = fiveOfDiamonds;
		PokerHand straightHand1 = new PokerHand(eightOfClubs, nineOfDiamonds, commCards1);
		PokerHand straightHand2 = new PokerHand(aceOfClubs, aceOfDiamonds, commCards1);
		PokerHand straightHand5 = new PokerHand(sixOfClubs, sevenOfDiamonds, commCards1);
		
		assertEquals(straightHand1.getHandRank(), straightHand2.getHandRank());
		assertEquals(straightHand1.getRank(), straightHand5.getHandRank());
		assertEquals(0, straightHand1.tieBreak(straightHand2));
		assertEquals(1, straightHand5.tieBreak(straightHand2));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = sixOfSpades;
		commCards2[1] = fiveOfClubs;
		commCards2[2] = sevenOfSpades;
		commCards2[3] = aceOfHearts;
		commCards2[4] = aceOfDiamonds;
		PokerHand straightHand3 = new PokerHand(eightOfClubs, nineOfDiamonds, commCards2);
		PokerHand straightHand4 = new PokerHand(threeOfClubs, fourOfDiamonds, commCards2);
		
		assertEquals(straightHand3.getHandRank(), straightHand4.getHandRank());
		assertEquals(1, straightHand3.tieBreak(straightHand4));
		
	}
	
	@Test
	void testTieBreaStraightFlush() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard threeOfDiamonds = new PlayingCard(3, Suit.DIAMONDS);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard sixOfSpades = new PlayingCard(6, Suit.SPADES);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfClubs = new PlayingCard(7, Suit.CLUBS);
		PlayingCard eightOfClubs = new PlayingCard(8, Suit.CLUBS);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = twoOfSpades;
		commCards1[2] = threeOfSpades;
		commCards1[3] = fourOfSpades;
		commCards1[4] = fiveOfSpades;
		PokerHand sfHand1 = new PokerHand(eightOfClubs, nineOfDiamonds, commCards1);
		PokerHand sfHand2 = new PokerHand(sevenOfClubs, threeOfDiamonds, commCards1);
		
		assertEquals(sfHand1.getHandRank(), sfHand2.getHandRank());
		assertEquals(0, sfHand1.tieBreak(sfHand2));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfClubs;
		commCards2[1] = eightOfHearts;
		commCards2[2] = threeOfSpades;
		commCards2[3] = fourOfSpades;
		commCards2[4] = fiveOfSpades;
		PokerHand sfHand3 = new PokerHand(sixOfSpades, sevenOfSpades, commCards1);
		PokerHand sfHand4 = new PokerHand(aceOfSpades, twoOfSpades, commCards1);
		
		assertEquals(sfHand3.getHandRank(), sfHand4.getHandRank());
		assertEquals(1, sfHand3.tieBreak(sfHand4));
		
	}
	
	@Test
	void testTieBreakFlush() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard fourOfSpades = new PlayingCard(4, Suit.SPADES);
		PlayingCard fiveOfSpades = new PlayingCard(5, Suit.SPADES);
		PlayingCard fiveOfClubs = new PlayingCard(5, Suit.CLUBS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		PlayingCard eightOfSpades = new PlayingCard(8, Suit.SPADES);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		PlayingCard jackOfHearts = new PlayingCard(11, Suit.HEARTS);
		PlayingCard jackOfSpades = new PlayingCard(11, Suit.SPADES);
		PlayingCard nineOfSpades = new PlayingCard(9, Suit.SPADES);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = fourOfSpades;
		commCards1[2] = fiveOfSpades;
		commCards1[3] = sevenOfSpades;
		commCards1[4] = eightOfSpades;
		PokerHand flushHand1 = new PokerHand(twoOfClubs, nineOfDiamonds, commCards1);
		PokerHand flushHand2 = new PokerHand(twoOfDiamonds, jackOfHearts, commCards1);
		
		assertEquals(flushHand1.getHandRank(), flushHand2.getHandRank());
		assertEquals(0, flushHand1.tieBreak(flushHand2));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = fourOfSpades;
		commCards2[2] = fiveOfClubs;
		commCards2[3] = sevenOfHearts;
		commCards2[4] = eightOfSpades;
		PokerHand flushHand3 = new PokerHand(twoOfSpades, nineOfSpades, commCards2);
		PokerHand flushHand4 = new PokerHand(twoOfSpades, jackOfSpades, commCards2);
		
		assertEquals(flushHand3.getHandRank(), flushHand4.getHandRank());
		assertEquals(1, flushHand4.tieBreak(flushHand3));
		
	}
	
	@Test
	void testTieBreakFullHouse() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfHearts = new PlayingCard(14, Suit.HEARTS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard twoOfDiamonds = new PlayingCard(2, Suit.DIAMONDS);
		PlayingCard sixOfDiamonds = new PlayingCard(6, Suit.DIAMONDS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfClubs = new PlayingCard(7, Suit.CLUBS);
		PlayingCard sevenOfHearts = new PlayingCard(7, Suit.HEARTS);
		PlayingCard eightOfClubs = new PlayingCard(8, Suit.CLUBS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = aceOfHearts;
		commCards1[3] = twoOfSpades;
		commCards1[4] = twoOfClubs;
		PokerHand FHHand1 = new PokerHand(nineOfDiamonds, eightOfClubs, commCards1);
		PokerHand FHHand2 = new PokerHand(sixOfDiamonds, sevenOfClubs, commCards1);
		
		assertEquals(FHHand1.getHandRank(), FHHand2.getHandRank());
		assertEquals(0, FHHand1.tieBreak(FHHand2));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = sevenOfSpades;
		commCards2[2] = aceOfHearts;
		commCards2[3] = twoOfSpades;
		commCards2[4] = twoOfClubs;
		PokerHand FHHand3 = new PokerHand(twoOfDiamonds, eightOfClubs, commCards2);
		PokerHand FHHand4 = new PokerHand(sevenOfHearts, sevenOfClubs, commCards2);
		PokerHand FHHand5 = new PokerHand(aceOfHearts, sevenOfClubs, commCards2);
		PokerHand FHHand6 = new PokerHand(aceOfHearts, nineOfDiamonds, commCards2);
		
		assertEquals(FHHand3.getHandRank(), FHHand4.getHandRank());
		assertEquals(FHHand5.getHandRank(), FHHand6.getHandRank());
		assertEquals(1, FHHand4.tieBreak(FHHand3));
		assertEquals(1, FHHand5.tieBreak(FHHand6));
		
	}
	
	@Test
	void testTieBreakTwoPair() {
		
		PlayingCard aceOfSpades = new PlayingCard(14, Suit.SPADES);
		PlayingCard aceOfClubs = new PlayingCard(14, Suit.CLUBS);
		PlayingCard aceOfDiamonds = new PlayingCard(14, Suit.DIAMONDS);
		PlayingCard twoOfSpades = new PlayingCard(2, Suit.SPADES);
		PlayingCard twoOfClubs = new PlayingCard(2, Suit.CLUBS);
		PlayingCard threeOfSpades = new PlayingCard(3, Suit.SPADES);
		PlayingCard threeOfClubs = new PlayingCard(3, Suit.CLUBS);
		PlayingCard fourOfDiamonds = new PlayingCard(4, Suit.DIAMONDS);
		PlayingCard sixOfHearts = new PlayingCard(6, Suit.HEARTS);
		PlayingCard sixOfDiamonds = new PlayingCard(6, Suit.DIAMONDS);
		PlayingCard sevenOfSpades = new PlayingCard(7, Suit.SPADES);
		PlayingCard sevenOfDiamonds = new PlayingCard(7, Suit.DIAMONDS);
		PlayingCard eightOfClubs = new PlayingCard(8, Suit.CLUBS);
		PlayingCard eightOfHearts = new PlayingCard(8, Suit.HEARTS);
		PlayingCard nineOfDiamonds = new PlayingCard(9, Suit.DIAMONDS);
		
		PlayingCard[] commCards1 = new PlayingCard[5];
		commCards1[0] = aceOfSpades;
		commCards1[1] = aceOfClubs;
		commCards1[2] = twoOfSpades;
		commCards1[3] = twoOfClubs;
		commCards1[4] = eightOfHearts;
		PokerHand TPHand1 = new PokerHand(sixOfHearts, sevenOfDiamonds, commCards1);
		PokerHand TPHand2 = new PokerHand(sixOfDiamonds, fourOfDiamonds, commCards1);
		PokerHand TPHand3 = new PokerHand(threeOfSpades, nineOfDiamonds, commCards1);
		PokerHand TPHand4 = new PokerHand(eightOfClubs, sevenOfSpades, commCards1);
		
		assertEquals(TPHand1.getHandRank(), TPHand2.getHandRank());
		assertEquals(TPHand3.getHandRank(), TPHand4.getHandRank());
		assertEquals(0, TPHand1.tieBreak(TPHand2));
		assertEquals(1, TPHand3.tieBreak(TPHand2));
		assertEquals(-1, TPHand1.tieBreak(TPHand4));
		
		PlayingCard[] commCards2 = new PlayingCard[5];
		commCards2[0] = aceOfSpades;
		commCards2[1] = threeOfClubs;
		commCards2[2] = sevenOfSpades;
		commCards2[3] = twoOfClubs;
		commCards2[4] = eightOfHearts;
		PokerHand TPHand5 = new PokerHand(threeOfSpades, sevenOfDiamonds, commCards2);
		PokerHand TPHand6 = new PokerHand(threeOfSpades, aceOfDiamonds, commCards2);
		PokerHand TPHand7 = new PokerHand(twoOfSpades, sevenOfDiamonds, commCards2);
		
		assertEquals(TPHand5.getHandRank(), TPHand6.getHandRank());
		assertEquals(TPHand5.getRank(), TPHand7.getHandRank());
		assertEquals(-1, TPHand5.tieBreak(TPHand6));
		assertEquals(1, TPHand5.tieBreak(TPHand7));
		
	}

}
