package Game;

import java.util.Date;
import java.util.Iterator;

import GUI.MyFrame;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Packman;
import Geom.Point3D;
import Map.Map;
public class PackmanMove extends Thread {
	Map map=new Map();
	private Packman pack;
	double packSpeed;
	Fruit fruit;
	Point3D fruitPoint;
	GameObjects gameOb;
	Iterator<GameObjects> iter;
	private Point3D currentPoint;
	MyFrame myframe;
	/**
	 * using the path array of the packman
	 * changing the packman x,y,z to the next fruit values and every step deleting the fruit from the main array(not from path)
	 * @param pack
	 * @param myframe
	 */
	// the main array used to paint only
	// when the packman done. he return counterPack++ to help the manager do his job
	public PackmanMove(Packman pack,MyFrame myframe) {
		this.pack=pack;
		new Point3D(pack.getLatitude(), pack.getLontitude(), pack.getAltitude());
		packSpeed=pack.getSpeedWeight();
		
		this.myframe=myframe;
	}
	public void run() {
		Iterator<Point3D> currentPointIter=pack.getPath().getPathArray().iterator();
		while(currentPointIter.hasNext()) {
			currentPoint=currentPointIter.next();
			pack.setLatitude(currentPoint.x());
			pack.setLontitude(currentPoint.y());
			pack.setAltitude(currentPoint.z());
			myframe.repaint();
			pack.getPath().addTimeStamps( new Date().getTime());
			synchronized(myframe.getGame()) {
				iter = myframe.getGame().getGameArray().iterator();
				while(iter.hasNext()) {
				gameOb=iter.next();
				if(gameOb instanceof Fruit) {
					fruit=(Fruit)gameOb;
					fruitPoint=new Point3D(fruit.getLatitude(),fruit.getLontitude(),fruit.getAltitude());
					if((fruitPoint.x()==currentPoint.x())&&
							(fruitPoint.y()==currentPoint.y())&&
							(fruitPoint.z()==currentPoint.z())) {
						iter.remove();
					}
				}
			}
			}
			try {					
				Thread.sleep((long)(500/packSpeed));// : the current pack speed
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		MoveManager.counterPack++;
	}
}
