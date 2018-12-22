package Map;

import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Packman;
import Geom.Point3D;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import Game.Game;

public class Csvsave {
	String Type;
	private double id,lat,lon,alt,speed,radius;
	GameObjects object;
	Packman packman;
	Fruit fruit;
	Point3D point=new Point3D(lat, lon, alt);
/**
 * saving the data of the game in csv file
 * @param gameObjects
 * @param path
 */
	public  void save(Game gameObjects, String path){
		try {
			StringBuilder sb = new StringBuilder();
			Iterator<GameObjects> iterator = gameObjects.getGameIterator();
			sb.append("Type,id,Lat,Lon,Alt,Speed,Radius\n");
			while(iterator.hasNext()) {
				object=iterator.next();
				if (object instanceof Packman) {
					packman=(Packman)object;
					this.id=packman.getId();
					this.lat=packman.getLatitude();
					this.lon=packman.getLontitude();
					this.alt=packman.getAltitude();
					this.speed=packman.getSpeedWeight();
					this.radius= packman.getRadius();
					sb.append("P, "+id+", "+lat+", "+lon+", "+alt+", "+speed+", "+radius+"\n");
				}if(object instanceof Fruit) {
					fruit = (Fruit) object;
					this.id=fruit.getId();
					this.lat=fruit.getLatitude();
					this.lon=fruit.getLontitude();
					this.alt=fruit.getAltitude();
					this.speed=fruit.getSpeedWeight();
					sb.append("F,"+id+","+lat+","+lon+","+alt+", "+speed+"\n");
				}
			}
			PrintWriter pr = new PrintWriter(new File(path+".csv"));
			pr.write(sb.toString());
			pr.flush();
			pr.close();
			System.out.println("done!");

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}