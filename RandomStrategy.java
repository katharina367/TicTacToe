import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy implements Strategy {

	@Override
	public Position calculateNextMove(TicTacToe field) {
		Random r = new Random();
		
		ArrayList<Position> emptyPos = field.getEmptyPositions();
		if(emptyPos.size()==0) return null;
		int a = r.nextInt(0,emptyPos.size());
		return emptyPos.get(a);
		
	}
}
