package Game;
import java.util.ArrayList;
import java.util.Iterator;
import GameObjects.GameObjects;
/**
 * game has arraylist that contains packmans or fruits
 * this array has iterator getter
 * @author orh92
 *
 */
public class Game {
	private ArrayList<GameObjects> gameArray=new ArrayList<GameObjects>();
	public   ArrayList<GameObjects> getGameArray() {
		return gameArray;
	}
	public  Iterator<GameObjects> getGameIterator() {	
		  Iterator<GameObjects> itemIterator=gameArray.iterator();
		return itemIterator;
	}

	public void toCsv(String string) {
		// TODO Auto-generated method stub
		
	}
}