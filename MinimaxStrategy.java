public class MinimaxStrategy implements Strategy {

	private int minimax(TicTacToe field, boolean userMove) {
		Position[] emptyPos = new Position[field.getEmptyPositions().size()];
		emptyPos = field.getEmptyPositions().toArray(emptyPos);
		int[] minmax = new int[emptyPos.length];

		if(field.whoHasWon() == TicTacToeFrame.getComputerToken()) return 1;
		if(field.whoHasWon() == TicTacToeFrame.getUserToken()) return -1;
		if (emptyPos.length == 0) return 0;
		Token a;
		if(userMove) a = TicTacToeFrame.getUserToken();
		else a = TicTacToeFrame.getComputerToken();

		for(int i = 0; i < emptyPos.length; i++) {
			field.putToken(emptyPos[i], a);
			minmax[i] = minimax(field,!userMove);
			field.removeToken(emptyPos[i]);
		}
		if(userMove) return minmax[MinimaxStrategy.getMinimumIndex(minmax)];
		return minmax[MinimaxStrategy.getMaximumIndex(minmax)];
	}
	
	private static int getMaximumIndex(int[] array) {
		int max = array[0];
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max) {
				max = array[i];
				index = i;
			}
		}
		return index;

	}
	
	private static int getMinimumIndex(int[] array) {
		int min = array[0];
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] < min) {
				min = array[i];
				index = i;
			}
		}
		return index;
	}	
	
	@Override
	public Position calculateNextMove(TicTacToe field) {
		if(field.isEmpty()) {
			field.putToken(new Position(1,1), TicTacToeFrame.getComputerToken());
			return new Position(1,1);
		}
		Position[] emptyPos = new Position[field.getEmptyPositions().size()];
		emptyPos = field.getEmptyPositions().toArray(emptyPos);
		if(emptyPos.length == 8 && field.getTokenAt(new Position(1,1)) == Token.NONE) {
			field.putToken(new Position(1,1), TicTacToeFrame.getComputerToken());
			return new Position(1,1);
		}
		int[] max = new int [emptyPos.length];
		for(int i = 0; i < emptyPos.length; i++) {
			field.putToken(emptyPos[i], TicTacToeFrame.getComputerToken());
			max[i] = minimax(field,true);
			field.removeToken(emptyPos[i]);
		}
		return emptyPos[MinimaxStrategy.getMaximumIndex(max)];
	}

}
