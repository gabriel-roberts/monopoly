package edu.ncsu.monopoly;


public class GameBoardCCMovePlayer extends GameBoardCC {
    public GameBoardCCMovePlayer() {
		super();
        Card ccCard1 = new MovePlayerCard("Blue 1" , Card.TYPE_CC);
        Card ccCard2 = new MovePlayerCard("Blue 2", Card.TYPE_CC);
        Card chanceCard1 = new MovePlayerCard("Blue 1", Card.TYPE_CHANCE);
        
		addCard(ccCard1);
		addCard(ccCard2);
    }
}
