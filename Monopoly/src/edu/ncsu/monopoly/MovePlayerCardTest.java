package edu.ncsu.monopoly;

import junit.framework.TestCase;

public class MovePlayerCardTest extends TestCase {
    GameMaster gameMaster;
    Card movePlayerCard;
    
    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCMovePlayer());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		movePlayerCard = new MovePlayerCard("Blue 1", Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }
    
    public void testMovePlayerCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(movePlayerCard, card);
		card.applyAction();
		IOwnable cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        boolean drawCardButtonEnabled = gameMaster.getGUI().isDrawCardButtonEnabled();
		assertTrue(drawCardButtonEnabled);
        boolean endTurnButtonEnabled = gameMaster.getGUI().isEndTurnButtonEnabled();
		assertFalse(endTurnButtonEnabled);
        gameMaster.btnDrawCardClicked();
        assertFalse(drawCardButtonEnabled);
		IOwnable cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
		assertTrue(endTurnButtonEnabled);
		assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
