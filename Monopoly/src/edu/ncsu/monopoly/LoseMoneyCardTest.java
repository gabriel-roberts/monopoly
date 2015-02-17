package edu.ncsu.monopoly;

import junit.framework.TestCase;

public class LoseMoneyCardTest extends TestCase {
    GameMaster gameMaster;
    Card loseMoneyCard;

    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCLoseMoney());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(loseMoneyCard);
    }
    
    public void testLoseMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
		Card card = gameMaster.drawCCCard();
		assertEquals(loseMoneyCard, card);
		card.applyAction();
		assertEquals(origMoney - 20, gameMaster.getCurrentPlayer().getMoney());
    }
    
    public void testLoseMoneyCardUI() {
        gameMaster.movePlayer(0, 1);
        boolean drawCardButtonEnabled = gameMaster.getGUI().isDrawCardButtonEnabled();
		assertTrue(drawCardButtonEnabled);
        boolean endTurnButtonEnabled = gameMaster.getGUI().isEndTurnButtonEnabled();
		assertFalse(endTurnButtonEnabled);
        gameMaster.btnDrawCardClicked();
        assertFalse(drawCardButtonEnabled);
		assertTrue(endTurnButtonEnabled);
    }
}
