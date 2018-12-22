package Path;
import java.util.ArrayList;
import java.util.Iterator;

import Geom.MyCoords;
import Geom.Point3D;
public class Path {
	private ArrayList<Point3D> pointArray= new ArrayList<Point3D>();
	private ArrayList<Long> timeStamps = new ArrayList<Long>();
	/**
	 * bring the array of the times(of the specific packman)
	 * @return
	 */
	public  ArrayList<Long> getTimeStamps(){
		return timeStamps;
	}
	/**
	 * add specific time to the specific packman
	 * @param time
	 */
	public  void addTimeStamps(long time){
		timeStamps.add(time);
	}
	/**
	 * add point3d(fruit) to the path of the specific packaman
	 * @param point
	 */
	public void addToPath(Point3D point) {
		pointArray.add(point);
	}
		public double pathLength() {
			if(pointArray.size() <= 1)	return 0;
			MyCoords d = new MyCoords();
			double sum = 0;
			Iterator<Point3D> iter = pointArray.iterator();
			Point3D point0 = iter.next();
			while(iter.hasNext()) {
				Point3D point1 = iter.next();
				sum =sum+ d.distance3d(point0, point1);
				point0 = point1;
			}
	
			return sum;
		}
		//iterator of the fruits point that in the path
	public Iterator<Point3D> iterator() {
		return pointArray.iterator();
	}
	//getter for the arraylist of the fruit points array
	public ArrayList<Point3D> getPathArray(){
		return pointArray;
	}
//	public void sort(PathListComperator pathListComperator) {
//		// TODO Auto-generated method stub	
//	}
}