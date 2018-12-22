package Path;
import java.util.ArrayList;
import java.util.Iterator;

import Game.Game;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Packman;
import Geom.MyCoords;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlgo {
	Map map=new Map();
	ArrayList<GameObjects> gameArray;
	Iterator<GameObjects> iter;
	Point3D packmanPoint;
	ArrayList<Packman> packArray=new ArrayList<Packman>();
	ArrayList<Fruit> fruitArray=new ArrayList<Fruit>();
	public ArrayList<Packman> getPackArray() {
		return packArray;
	}
	public Iterator<Packman> getPackIter() {
		return packArray.iterator();
	}
	/**
	 * constructor of the algorithem
	 * initialize the game array(packmans+fruits), and initialize the iterator of this array
	 *calling to Sort function that creating fruits array, packmans array
	 *calling to Calc function that sorting the fruits that each packman needs to eat
	 *calling to listSort that using comperator for the paths of the packmans to sort the points of the fruits
	 * @param game
	 */
	public  ShortestPathAlgo(Game game){
		gameArray=game.getGameArray();
		iter=game.getGameIterator();
		Sort();
		Calc();	
//		listSort();
		//	printPath();
	}
	/**
	 * Sort function that creating fruits array, packmans array
	 */
	private void Sort() {
		GameObjects gameobjects;
		while(iter.hasNext()) {
			gameobjects=iter.next();
			if(gameobjects instanceof Packman) {
				packArray.add((Packman)gameobjects);
			}
			if(gameobjects instanceof Fruit) {
				fruitArray.add((Fruit)gameobjects);
			}
		}
	}
	/**
	 *  sorting the fruits that each packman needs to eat
	 *  each packman get his path of the fruit he need to eat
	 *  each path will be sorted by distance (that using the speed of the packman)
	 */
	private void Calc() {
		Iterator<Packman> packIter=packArray.iterator();
		Iterator<Fruit> fruitIter=fruitArray.iterator();
		Packman zeroPack;
		Point3D zeroPoint;
		for(int i=0;i<packArray.size();i++) {
			zeroPack=packIter.next();
			zeroPoint=new Point3D(zeroPack.getLatitude(),zeroPack.getLontitude(),zeroPack.getAltitude());
			zeroPack.addToPath(zeroPoint);
		}
		fruitIter=fruitArray.iterator();
		while(fruitArray.size()>0) {
			double cheapestDist=Integer.MAX_VALUE;
			Packman cheapestPack=packArray.get(0);
			Fruit cheapestFuit=fruitArray.get(0);
			Packman currentPack;
			Fruit currentFruit;
			fruitIter=fruitArray.iterator();
			while(fruitIter.hasNext()) {
				currentFruit=fruitIter.next();
				 packIter=packArray.iterator();
				while(packIter.hasNext()) {
					currentPack=packIter.next();
					if(packDistance(currentPack,currentFruit,currentPack.getRadius(),currentPack.getSpeedWeight())<cheapestDist) {
						cheapestFuit=currentFruit;
						cheapestPack=currentPack;
						cheapestDist=packDistance(currentPack,currentFruit,currentPack.getRadius(),currentPack.getSpeedWeight());
					}
				}
			}
			Point3D bestPoint=new Point3D(cheapestFuit.getLatitude(),cheapestFuit.getLontitude(),cheapestFuit.getAltitude());
			cheapestPack.addToPath(bestPoint);
			fruitArray.remove(cheapestFuit);
		}		
	}
	public double packDistance(Packman pack,Fruit fruit,double r,double speed) {
		Point3D point=pack.getPath().getPathArray().get(pack.getPath().getPathArray().size()-1);
		Point3D fruitPoint=new Point3D(fruit.getLatitude(),fruit.getLontitude(),fruit.getAltitude());
		MyCoords m=new MyCoords();
		double dist;
		Point3D p2=fruitPoint;
		point=map.Pixels2Polar(point);
		p2=map.Pixels2Polar(p2);
		dist=((m.distance3d(point, p2)-r)/speed);
		return dist;
	}
}