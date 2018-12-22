package Map;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;

import Game.Game;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Packman;
import Geom.Point3D;

public class Csvload extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game gameObjects =new Game();
	Iterator<GameObjects> gameIterator =gameObjects.getGameIterator();
/**
 * loading the game by reading the data from csv file
 * @return
 */
	public Game load(){
		FileDialog fd = new FileDialog(Csvload.this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\Users\\גיא\\eclipse-workspace\\PackmanGame1\\game_1543684662657.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();                 
		try {
			FileReader fr = new FileReader(folder + fileName);
			BufferedReader br = new BufferedReader(fr);
			String str = new String();
			str = br.readLine();
			str = br.readLine();
			for (int i = 0; str != null; i =i++) {									
				if (str != null) {
					String SplitedFile[]=str.split(",");
					//System.out.println(str);
					Point3D packfruit =new Point3D(Double.parseDouble(SplitedFile[3]),Double.parseDouble(SplitedFile[2]),Double.parseDouble(SplitedFile[4]));
					Map map=new Map();
					packfruit=	 map.Polar2Pixels(packfruit);
				//	System.out.println(packfruit.x()+" its the x.loadd. "+packfruit.y()+"  its the y..");
					if(SplitedFile[0].equals("P")) {  
						gameObjects.getGameArray().add( new Packman(Double.parseDouble(SplitedFile[1]),packfruit.x(),packfruit.y(),packfruit.z(),Double.parseDouble(SplitedFile[5]),Double.parseDouble(SplitedFile[6]))) ;								
					}

					if(SplitedFile[0].equals("F")) {
						gameObjects.getGameArray().add( new Fruit(Double.parseDouble(SplitedFile[1]),packfruit.x(),packfruit.y(),packfruit.z(),Double.parseDouble(SplitedFile[5])));	
					}

				}

				str = br.readLine();	
			}		
			br.close();
			fr.close();

		} catch (IOException ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}

		return gameObjects;

	}

}

